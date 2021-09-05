package com.citynow.locker.repository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import com.citynow.locker.entity.CustomerEntity;
import com.citynow.locker.entity.newDto.NewCustomerDTO;
import org.springframework.stereotype.Repository;

@Repository
public class NewCustomerRepoImpl implements NewCustomerRepository {
  @PersistenceContext
  private EntityManager em;

  @Override
  public List<CustomerEntity> getAllCustomer() {
    CriteriaBuilder builder = em.getCriteriaBuilder();
    CriteriaQuery<CustomerEntity> query = builder.createQuery(CustomerEntity.class);
    Root<CustomerEntity> customer = query.from(CustomerEntity.class);
    query.select(
        builder.construct(CustomerEntity.class, customer.get("fullName"), customer.get("gender")));
    List<CustomerEntity> result = em.createQuery(query).getResultList();
    return result;
  }

  @Override
  public List<NewCustomerDTO> searchCustomer(Integer userId, String fullName, String phoneNum) {
    CriteriaBuilder builder = em.getCriteriaBuilder();
    CriteriaQuery<NewCustomerDTO> query = builder.createQuery(NewCustomerDTO.class);
    Root<CustomerEntity> customer = query.from(CustomerEntity.class);
    List<Predicate> predicates = new ArrayList<>();

    if (userId != null) {
      Predicate equalUserId = builder.equal(customer.get("customerId"), userId);
      predicates.add(equalUserId);
    }
    if (fullName != null && !fullName.isEmpty()) {
      Predicate hasFullNameLike = builder.like(customer.get("fullName"), "%" + fullName + "%");
      predicates.add(hasFullNameLike);
    }
    if (phoneNum != null && !phoneNum.isEmpty()) {
      Predicate hasPhoneNumberLike =
          builder.like(customer.get("phoneNumber"), "%" + phoneNum + "%");
      predicates.add(hasPhoneNumberLike);
    }
    Predicate ceoCase = builder.and(
        builder.like(customer.get("fullName"), "%Tai"),
        builder.equal(customer.get("type"), 0));

    Predicate ctoCase = builder.and(
        builder.like(customer.get("fullName"), "%Quan"),
        builder.equal(customer.get("type"), 0));


    Predicate memCase = builder.equal(customer.get("type"), 1);

    Predicate condition = builder.and(predicates.toArray(new Predicate[predicates.size()]));
    query.multiselect(
        customer.get("customerId"),
        customer.get("fullName"),
        customer.get("type"),
        customer.get("gender"),
        customer.get("phoneNumber"),
        builder.selectCase()
            .when(ceoCase, "CEO")
            .when(ctoCase, "CTO")
            .when(memCase, "Member")
            .otherwise("Unknown").alias("position"))
        .where(condition);
    return em.createQuery(query).getResultList();
  }
}
