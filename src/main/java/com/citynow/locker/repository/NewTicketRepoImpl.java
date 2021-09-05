package com.citynow.locker.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import com.citynow.locker.entity.AssignmentEntity;
import com.citynow.locker.entity.CustomerEntity;
import com.citynow.locker.entity.TicketEntity;
import com.citynow.locker.entity.dto.TicketDetailDTO;
import com.citynow.locker.entity.newDto.NewTicketHistoryDTO;
import com.citynow.locker.entity.newDto.newTicketDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class NewTicketRepoImpl implements NewTicketRepository {
  @PersistenceContext
  private EntityManager em;

  @Autowired
  private AssignmentRepository assignmentRepository;

  @Autowired
  private TicketRepository ticketRepository;

  @Override
  public List<NewTicketHistoryDTO> getAllTickets() {
    CriteriaBuilder builder = em.getCriteriaBuilder();
    CriteriaQuery<NewTicketHistoryDTO> query = builder.createQuery(NewTicketHistoryDTO.class);
    Root<TicketEntity> ticket = query.from(TicketEntity.class);
    Join<TicketEntity, CustomerEntity> ticketHistory = ticket.join("customerEntity", JoinType.INNER);
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

    Join<TicketEntity, CustomerEntity> customerTicketJoin = ticketHistory.join("customerEntity", JoinType.INNER);

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
            Predicate fromStartDate = builder.greaterThanOrEqualTo(ticketHistory.get("startDateTime"), startDateTime);
            predicates.add(fromStartDate);
          }
          break;
        case "RETURNED_TICKET":
          Predicate deactivate = builder.isNotNull(ticketHistory.get("endDateTime"));
          predicates.add(deactivate);
          if (startDateTime != null) {
            Predicate fromStartDate = builder.greaterThanOrEqualTo(ticketHistory.get("startDateTime"), startDateTime);
            predicates.add(fromStartDate);
          }
          if (endDateTime != null) {
            Predicate toStartDate = builder.greaterThanOrEqualTo(ticketHistory.get("endDateTime"), endDateTime);
            predicates.add(toStartDate);
          }
          break;
        default:
          if (startDateTime != null) {
            Predicate fromStartDate = builder.greaterThanOrEqualTo(ticketHistory.get("startDateTime"), startDateTime);
            predicates.add(fromStartDate);
          }
          if (endDateTime != null) {
            Predicate toStartDate = builder.lessThanOrEqualTo(ticketHistory.get("endDateTime"), endDateTime);
            predicates.add(toStartDate);
          }
          break;
      }
    }
    Predicate condition = builder.and(predicates.toArray(new Predicate[predicates.size()]));
    query.multiselect(ticketHistory.get("ticketId"), customerTicketJoin.get("customerId"),
        customerTicketJoin.get("fullName"), lockerAssignJoin.get("lockerCode"), lockerAssignJoin.get("lockerName"),
        ticketHistory.get("startDateTime"), ticketHistory.get("endDateTime")).where(condition);
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
    return em.createQuery(query).getResultList();
  }

  @Override
  public newTicketDetailDTO getLockerDetailByTicketId(Integer ticketId) {
    em.clear();
    CriteriaBuilder builder = em.getCriteriaBuilder();
    TicketDetailDTO originalDTO = ticketRepository.getTicketDetailByID(ticketId).get(0);
    List<AssignmentEntity> extract = getLockerCodeByTicketId(ticketId);
    String lockerCodes = "";
    for (AssignmentEntity elem : extract) {
      lockerCodes += elem.getLockerCode() + ",";
    }
    if (!lockerCodes.equals(""))
      lockerCodes.replaceAll(".$", "");
    newTicketDetailDTO returnDTO = new newTicketDetailDTO(originalDTO.getTicketId(), originalDTO.getCustomerId(),
        originalDTO.getFullName(), originalDTO.getType(), originalDTO.getGender(), originalDTO.getPhoneNumber(),
        lockerCodes, originalDTO.getRemark(), originalDTO.getStartDateTime(), originalDTO.getEndDateTime());
    return returnDTO;
  }

  @Override
  @Transactional
  public Integer updateTicket(newTicketDetailDTO tk) {
    CriteriaBuilder builder = em.getCriteriaBuilder();
    var ticket = getTicketById(tk.getTicketId()).get(0);
    // ------ Update Remarks -----------
    CriteriaUpdate<TicketEntity> update = builder.createCriteriaUpdate(TicketEntity.class);
    var rootTicket = update.from(TicketEntity.class);
    update.set(rootTicket.get("remark"), tk.getRemark())
        .where(builder.equal(rootTicket.get("ticketId"), tk.getTicketId()));
    var succeeded = em.createQuery(update).executeUpdate();
    // -----------------------------
    // ------- Update Customer -----
    var customerUpdate = builder.createCriteriaUpdate(CustomerEntity.class);
    var rootCustomer = customerUpdate.from(CustomerEntity.class);
    if (tk.getFullName() != null && !tk.getFullName().isEmpty())
      customerUpdate.set(rootCustomer.get("fullName"), tk.getFullName())
          .where(builder.equal(rootCustomer.get("customerId"), ticket.getCustomerEntity().getCustomerId()));
    if (tk.getPhoneNumber() != null && !tk.getPhoneNumber().isEmpty())
      customerUpdate.set(rootCustomer.get("phoneNumber"), tk.getPhoneNumber())
          .where(builder.equal(rootCustomer.get("customerId"), ticket.getCustomerEntity().getCustomerId()));
    if (tk.getGender() != null)
      customerUpdate.set(rootCustomer.get("gender"), tk.getGender())
          .where(builder.equal(rootCustomer.get("customerId"), ticket.getCustomerEntity().getCustomerId()));
    if (tk.getGender() != null || tk.getPhoneNumber() != null || tk.getFullName() != null)
      succeeded = em.createQuery(customerUpdate).executeUpdate();
    // ------------------------------
    // ------- Update Locker -----
    String[] values = tk.getLockerCode().split("\\s*,\\s*");
    var lockerDelete = builder.createCriteriaDelete(AssignmentEntity.class);
    var rootLocker = lockerDelete.from(AssignmentEntity.class);
    // // Get the locker code size in entity with the same ticketId
    CriteriaQuery<AssignmentEntity> query = builder.createQuery(AssignmentEntity.class);
    Root<AssignmentEntity> assign = query.from(AssignmentEntity.class);
    query.select(assign.get("ticketId")).where(builder.equal(assign.get("ticketId"), tk.getTicketId()));
    List<Predicate> predicates = new ArrayList<>();
    List<AssignmentEntity> extract = getLockerCodeByTicketId(tk.getTicketId());
    List<String> lockerCodes = new ArrayList<>();
    for (AssignmentEntity elem : extract) {
      lockerCodes.add(elem.getLockerCode());
    }
    // body size < size in entity
    System.out.println(values.length);
    for (int i = 0; i < values.length; i++) {
      System.out.println(values[i]);
      if (!lockerCodes.contains(values[i])) {
        System.out.println("Hello");
        AssignmentEntity insert = new AssignmentEntity(tk.getTicketId(), values[i]);
        assignmentRepository.saveAndFlush(insert);
      }
    }
    for (int i = 0; i < values.length; i++) {
      Predicate ticketIdEqual = builder.equal(rootLocker.get("ticketId"), tk.getTicketId());
      Predicate lockerCodeNotEqual = builder.notEqual(rootLocker.get("lockerCode"), values[i]);
      predicates.add(ticketIdEqual);
      predicates.add(lockerCodeNotEqual);
      Predicate condition = builder.and(predicates.toArray(new Predicate[predicates.size()]));
      lockerDelete.where(condition);
      succeeded = em.createQuery(lockerDelete).executeUpdate();
    }
    if (ticket.getEndDateTime() == null && tk.getEndDate() != null) {
      update.set(rootTicket.get("endDateTime"), LocalDate.parse(tk.getEndDate()));
      succeeded = em.createQuery(update).executeUpdate();
    }
    return succeeded;
  }
}
