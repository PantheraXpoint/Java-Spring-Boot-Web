package com.citynow.locker.repository;

import com.citynow.locker.entity.CustomerEntity;
import com.citynow.locker.entity.TicketEntity;
import com.citynow.locker.entity.dto.CustomerDTO;
import com.citynow.locker.entity.dto.NewCustomerDTO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Root;
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
}

