package com.citynow.locker.controller;

import java.util.ArrayList;
import java.util.List;

import com.citynow.locker.entity.CustomerEntity;
import com.citynow.locker.entity.TicketEntity;
import com.citynow.locker.entity.dto.CustomerDTO;
import com.citynow.locker.entity.dto.NewCustomerDTO;
import com.citynow.locker.entity.dto.TicketHistoryDTO;
import com.citynow.locker.repository.CustomerRepository;
import com.citynow.locker.repository.NewCustomerRepository;
import com.citynow.locker.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
  @Autowired
  private TicketRepository ticketRepository;

  @Autowired
  private NewCustomerRepository newCustomerRepository;
  @Autowired
  private CustomerRepository customerRepository;


  @GetMapping("/ticket/history")
  public List<TicketHistoryDTO> getAllSortedTickets(
  // @RequestParam(name = "mode", required = false) String mode,
  // @RequestParam(name = "startDateTime", required = false) String start,
  // @RequestParam(name = "endDateTime", required = false) String end
  ) {
    return ticketRepository.getAllSortedTickets();// ticketRepository.findAllByOrderByStartDateTimeAsc();
  }

  @GetMapping("/customer/all")
  public List<NewCustomerDTO> getAllCustomers() {
    return newCustomerRepository.getAllCustomer();
//    return customerRepository.getAllCustomers();
  }

  @GetMapping("/customer/search")
  public List<CustomerEntity> searchCustomer
          (@RequestParam(value = "id",required = false) Integer userId,
           @RequestParam(value = "fullName",required = false) String fullName,
           @RequestParam(value = "phoneNum",required = false) String phoneNum){
    return newCustomerRepository.searchCustomer(userId,fullName,phoneNum);
  }

}
