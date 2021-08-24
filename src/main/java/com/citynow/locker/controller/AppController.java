package com.citynow.locker.controller;

import java.util.ArrayList;
import java.util.List;
import com.citynow.locker.entity.TicketEntity;
import com.citynow.locker.entity.dto.CustomerDTO;
import com.citynow.locker.entity.dto.TicketHistoryDTO;
import com.citynow.locker.repository.CustomerRepository;
import com.citynow.locker.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
  @Autowired
  private TicketRepository ticketRepository;

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
  public List<CustomerDTO> getAllCustomers() {
    return customerRepository.getAllCustomers();
  }

}
