package com.citynow.locker.repository;

import com.citynow.locker.entity.CustomerEntity;
import com.citynow.locker.entity.dto.CustomerDTO;
import com.citynow.locker.entity.dto.NewCustomerDTO;

import java.util.List;

public interface NewCustomerRepository {
    public List<NewCustomerDTO> getAllCustomer();
}
