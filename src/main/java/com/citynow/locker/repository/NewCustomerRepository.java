package com.citynow.locker.repository;

import java.util.List;
import com.citynow.locker.entity.CustomerEntity;
import com.citynow.locker.entity.newDto.NewCustomerDTO;
import com.citynow.locker.entity.CustomerEntity;

public interface NewCustomerRepository {
  List<CustomerEntity> getAllCustomer();

  List<NewCustomerDTO> searchCustomer(Integer userId, String fullName, String phoneNum);
}
