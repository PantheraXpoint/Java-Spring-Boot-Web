package com.citynow.locker.repository;

import java.util.List;
import com.citynow.locker.entity.CustomerEntity;
import com.citynow.locker.entity.dto.CustomerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
  @Query(nativeQuery = true,
      value = "select id, full_name as fullName, type, gender, phone_number as phoneNumber, "
          + "case when customer.full_name LIKE '%Quan' and customer.type = 0 then 'CTO' "
          + "when customer.full_name LIKE '%Tai' and customer.type = 0 then 'CEO' "
          + "when customer.type = 1 then 'Member' "
          + "else 'Unknown' END as Position "
          + "FROM customer ORDER BY id ASC")
  public List<CustomerDTO> getAllCustomers();

  @Query(nativeQuery = true, value = "select id, full_name, type,gender, phone_number, "
      + "case when public.customer.full_name LIKE '%Quan' and public.customer.type = 0 then 'CTO' "
      + "when public.customer.full_name LIKE '%Tai' and public.customer.type = 0 then 'CEO' "
      + "when public.customer.type = 1 then 'Member' "
      + "else 'Unknown' END as Position"
      + "FROM public.customer where public.customer.id = (:ID)")
  public List<CustomerDTO> getCustomerByID(@Param("ID") Integer ID);

  @Query(nativeQuery = true, value = "select id, full_name, type,gender, phone_number, "
      + "case when public.customer.full_name LIKE '%Quan' and public.customer.type = 0 then 'CTO' "
      + "when public.customer.full_name LIKE '%Tai' and public.customer.type = 0 then 'CEO' "
      + "when public.customer.type = 1 then 'Member' "
      + "else 'Unknown' END as Position"
      + "FROM public.customer where public.customer.full_name LIKE (%:name%);")
  public List<CustomerDTO> getCustomerByName(@Param("name") String name);

  @Query(nativeQuery = true, value = "select id, full_name, type,gender, phone_number, "
      + "case when public.customer.full_name LIKE '%Quan' and public.customer.type = 0 then 'CTO' "
      + "when public.customer.full_name LIKE '%Tai' and public.customer.type = 0 then 'CEO' "
      + "when public.customer.type = 1 then 'Member' "
      + "else 'Unknown' END as Position"
      + "FROM public.customer where public.customer.phone_number LIKE (%phoneNumber%);")
  public List<CustomerDTO> getCustomerByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}
