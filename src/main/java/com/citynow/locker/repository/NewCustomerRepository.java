package com.citynow.locker.repository;

import com.citynow.locker.entity.CustomerEntity;
import com.citynow.locker.entity.dto.CustomerDTO;
import com.citynow.locker.entity.dto.NewCustomerDTO;

import java.util.List;

public interface NewCustomerRepository {
    List<NewCustomerDTO> getAllCustomer();
    List<CustomerEntity> searchCustomer(Integer userId, String fullName, String phoneNum);
}
