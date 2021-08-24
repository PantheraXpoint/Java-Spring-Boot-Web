package com.citynow.locker.repository;

import com.citynow.locker.entity.CustomerEntity;
import com.citynow.locker.entity.TicketEntity;
import com.citynow.locker.entity.dto.CustomerDTO;
import com.citynow.locker.entity.dto.NewCustomerDTO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NewCustomerRepoImpl implements NewCustomerRepository{
    @PersistenceContext
    private EntityManager em;
    @Override
    public List<NewCustomerDTO> getAllCustomer() {
//        Hibernate Query Language - Criteria API
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<NewCustomerDTO> query  = builder.createQuery(NewCustomerDTO.class);
//        SQL: From customer
        Root<CustomerEntity> customer = query.from(CustomerEntity.class);
//        customer.join("tickets");

//        query.select(customer);
//        select * from customer
        query.select( builder.construct( NewCustomerDTO.class,  customer.get("fullName"),customer.get("gender")) );
        List<NewCustomerDTO> result = em.createQuery(query).getResultList();
        return  result;
    }

    @Override
    public List<CustomerEntity> searchCustomer(Integer userId, String fullName, String phoneNum) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<CustomerEntity> query = builder.createQuery(CustomerEntity.class);
        Root<CustomerEntity> customer = query.from(CustomerEntity.class);
        List<Predicate> predicates = new ArrayList<>();
//      Predicate means condition
        if (userId != null) {
            Predicate equalUserId = builder.equal(customer.get("customerId"), userId);
            predicates.add(equalUserId);
        }
        if (fullName != null && !fullName.isEmpty()) {
            Predicate hasFullNameLike = builder.like(customer.get("fullName"),"%"+fullName+"%");
            predicates.add(hasFullNameLike);
        }
        if (phoneNum != null && !phoneNum.isEmpty())
        {
            Predicate hasPhoneNumberLike = builder.like(customer.get("phoneNumber"),"%"+phoneNum+"%");
            predicates.add(hasPhoneNumberLike);
        }
        Predicate condition = builder.and(predicates.toArray(new Predicate[predicates.size()]));
        query.select(customer).where(condition);
        return em.createQuery(query).getResultList();
    }


}

