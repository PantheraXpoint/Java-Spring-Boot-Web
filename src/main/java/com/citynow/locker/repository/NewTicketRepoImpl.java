package com.citynow.locker.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CollectionJoin;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import com.citynow.locker.entity.AssignmentEntity;
import com.citynow.locker.entity.CustomerEntity;
import com.citynow.locker.entity.LockerEntity;
import com.citynow.locker.entity.TicketEntity;
import com.citynow.locker.entity.dto.TicketDetailDTO;
import com.citynow.locker.entity.newDto.NewTicketHistoryDTO;
import com.citynow.locker.entity.newDto.newTicketDetailDTO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class NewTicketRepoImpl implements NewTicketRepository {
  @PersistenceContext
  private EntityManager em;

  private TicketRepository ticketRepository;

  @Override
  public List<NewTicketHistoryDTO> getAllTickets() {
    CriteriaBuilder builder = em.getCriteriaBuilder();
    CriteriaQuery<NewTicketHistoryDTO> query = builder.createQuery(NewTicketHistoryDTO.class);
    Root<TicketEntity> ticket = query.from(TicketEntity.class);
    Join<TicketEntity, CustomerEntity> ticketHistory =
        ticket.join("customerEntity", JoinType.INNER);
    // query.where(builder.equal(ticketHistory.get("customerId"), ticket.get("")))
    query.multiselect(ticket.get("ticketId"), ticketHistory.get("customerId"));
    return em.createQuery(query).getResultList();
  }

  @Override
  public List<NewTicketHistoryDTO> searchTicketHistory(String paramMode, LocalDate startDateTime,
      LocalDate endDateTime) {
    CriteriaBuilder builder = em.getCriteriaBuilder();
    CriteriaQuery<NewTicketHistoryDTO> query = builder.createQuery(NewTicketHistoryDTO.class);
    Root<TicketEntity> ticketHistory = query.from(TicketEntity.class);
    // Root<AssignmentEntity> ticketAssignment = query.from(AssignmentEntity.class);
    List<Predicate> predicates = new ArrayList<>();

    Join<TicketEntity, CustomerEntity> customerTicketJoin =
        ticketHistory.join("customerEntity", JoinType.INNER);

    var lockerAssignJoin = ticketHistory.join("lockers");
    // Join<TicketEntity, AssignmentEntity> ticketAssignmentJoin =
    // ticketAssignment.join("assignmentEntity", JoinType.INNER);

    // Join<LockerEntity, AssignmentEntity> lockerAssignmentJoin =
    // ticketAssignment.join("lockerCode", JoinType.INNER);

    if (paramMode != null) {
      switch (paramMode) {
        case "ASSIGN_TICKET":
          Predicate activate = builder.isNull(ticketHistory.get("endDateTime"));
          predicates.add(activate);
          if (startDateTime != null) {
            Predicate fromStartDate =
                builder.greaterThanOrEqualTo(ticketHistory.get("startDateTime"), startDateTime);
            predicates.add(fromStartDate);
          }
          break;
        case "RETURNED_TICKET":
          Predicate deactivate = builder.isNotNull(ticketHistory.get("endDateTime"));
          predicates.add(deactivate);
          if (startDateTime != null) {
            Predicate fromStartDate =
                builder.greaterThanOrEqualTo(ticketHistory.get("startDateTime"), startDateTime);
            predicates.add(fromStartDate);
          }
          if (endDateTime != null) {
            Predicate toStartDate =
                builder.greaterThanOrEqualTo(ticketHistory.get("endDateTime"), endDateTime);
            predicates.add(toStartDate);
          }
          break;
        default:
          if (startDateTime != null) {
            Predicate fromStartDate =
                builder.greaterThanOrEqualTo(ticketHistory.get("startDateTime"), startDateTime);
            predicates.add(fromStartDate);
          }
          if (endDateTime != null) {
            Predicate toStartDate =
                builder.lessThanOrEqualTo(ticketHistory.get("endDateTime"), endDateTime);
            predicates.add(toStartDate);
          }
          break;
      }
    }
    Predicate condition = builder.and(predicates.toArray(new Predicate[predicates.size()]));
    query.multiselect(
        ticketHistory.get("ticketId"),
        customerTicketJoin.get("customerId"),
        customerTicketJoin.get("fullName"),
        lockerAssignJoin.get("lockerCode"),
        lockerAssignJoin.get("lockerName"),
        ticketHistory.get("startDateTime"),
        ticketHistory.get("endDateTime")).where(condition);
    return em.createQuery(query).getResultList();
  }

  private List<TicketEntity> getTicketById(Integer ticketId) {
    em.clear();
    CriteriaBuilder builder = em.getCriteriaBuilder();
    CriteriaQuery query = builder.createQuery(TicketEntity.class);
    var ticket = query.from(TicketEntity.class);
    query.select(ticket).where(builder.equal(ticket.get("ticketId"), ticketId));

    return em.createQuery(query).setFirstResult(0).setMaxResults(1).getResultList();
  }

  private List<AssignmentEntity> getLockerCodeByTicketId(Integer ticketId) {
    em.clear();
    CriteriaBuilder builder = em.getCriteriaBuilder();
    CriteriaQuery query = builder.createQuery(AssignmentEntity.class);
    var assign = query.from(AssignmentEntity.class);
    query.select(assign).where(builder.equal(assign.get("ticketId"), ticketId));
    return em.createQuery(query).setFirstResult(0).setMaxResults(1).getResultList();
  }



  @Override
  @Transactional
  public Integer updateTicket(newTicketDetailDTO tk) {
    CriteriaBuilder builder = em.getCriteriaBuilder();
    var ticket = getTicketById(tk.getTicketId()).get(0);
    // ------ Update Remarks -----------
    CriteriaUpdate<TicketEntity> update =
        builder.createCriteriaUpdate(TicketEntity.class);
    var rootTicket = update.from(TicketEntity.class);
    update.set(rootTicket.get("remark"), tk.getRemark())
        .where(builder.equal(rootTicket.get("ticketId"), tk.getTicketId()));
    var succeeded = em.createQuery(update).executeUpdate();
    // -----------------------------
    // ------- Update Customer -----
    var customerUpdate = builder.createCriteriaUpdate(CustomerEntity.class);
    var rootCustomer = customerUpdate.from(CustomerEntity.class);
    customerUpdate.set(rootCustomer.get("fullName"), tk.getFullName())
        .where(builder.equal(rootCustomer.get("customerId"),
            ticket.getCustomerEntity().getCustomerId()));
    customerUpdate.set(rootCustomer.get("phoneNumber"), tk.getPhoneNumber())
        .where(builder.equal(rootCustomer.get("customerId"),
            ticket.getCustomerEntity().getCustomerId()));
    customerUpdate.set(rootCustomer.get("gender"), tk.getGender())
        .where(builder.equal(rootCustomer.get("customerId"),
            ticket.getCustomerEntity().getCustomerId()));

    succeeded = em.createQuery(customerUpdate).executeUpdate();
    // ------------------------------
    // ------- Update Locker -----
    AssignmentEntity extract = getLockerCodeByTicketId(tk.getTicketId()).get(0);

    String[] values = extract.getLockerCode().split("\\s*,\\s*");
    var lockerDelete = builder.createCriteriaDelete(AssignmentEntity.class);
    var rootLocker = lockerDelete.from(AssignmentEntity.class);
    // // Get the locker code size in entity with the same ticketId
    CriteriaQuery<AssignmentEntity> query = builder.createQuery(AssignmentEntity.class);
    Root<AssignmentEntity> assign = query.from(AssignmentEntity.class);
    query.select(assign.get("ticketId"))
        .where(builder.equal(assign.get("ticketId"), tk.getTicketId()));
    List<Predicate> predicates = new ArrayList<>();
    // body size < size in entity
    if (em.createQuery(query).getResultList().size() > values.length) {
      for (int i = 0; i < values.length; i++) {
        Predicate ticketIdEqual = builder.equal(rootLocker.get("ticketId"), tk.getTicketId());
        Predicate lockerCodeNotEqual = builder.notEqual(rootLocker.get("lockerCode"), values[i]);
        predicates.add(ticketIdEqual);
        predicates.add(lockerCodeNotEqual);
        Predicate condition = builder.and(predicates.toArray(new Predicate[predicates.size()]));
        lockerDelete.where(condition);
      }
    } else {
      for (int i = 0; i < values.length; i++) {
        Predicate ticketIdEqual = builder.equal(rootLocker.get("ticketId"), tk.getTicketId());
        Predicate lockerCodeNotEqual = builder.equal(rootLocker.get("lockerCode"), values[i]);
        predicates.add(ticketIdEqual);
        predicates.add(lockerCodeNotEqual);
        Predicate condition = builder.and(predicates.toArray(new Predicate[predicates.size()]));
        if (condition.isNegated()) {
          succeeded =
              ticketRepository.insertLockerAssign(tk.getTicketId(), tk.getLockerCode());
        }
      }
    }
    predicates.clear();
    Predicate ticketIdEqual = builder.equal(rootLocker.get("ticketId"), tk.getTicketId());
    Predicate dateCondition = builder.isNull(rootTicket.get("endDateTime"));
    predicates.add(ticketIdEqual);
    predicates.add(dateCondition);
    Predicate lastCondition = builder.and(predicates.toArray(new Predicate[predicates.size()]));
    if (!lastCondition.isNegated()) {
      succeeded = ticketRepository.returnTicket(tk.getTicketId());
    }
    return succeeded;

  }

}
