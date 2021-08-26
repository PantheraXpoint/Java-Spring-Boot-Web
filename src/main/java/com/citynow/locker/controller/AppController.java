package com.citynow.locker.controller;

import java.time.LocalDate;
import java.util.List;
import com.citynow.locker.entity.CustomerEntity;
import com.citynow.locker.entity.TicketEntity;
import com.citynow.locker.entity.dto.CustomerDTO;
import com.citynow.locker.entity.dto.TicketDetailDTO;
import com.citynow.locker.entity.dto.TicketHistoryDTO;
import com.citynow.locker.entity.newDto.NewCustomerDTO;
import com.citynow.locker.entity.newDto.NewTicketHistoryDTO;
import com.citynow.locker.entity.newDto.newTicketDetailDTO;
import com.citynow.locker.repository.CustomerRepository;
import com.citynow.locker.repository.NewCustomerRepository;
import com.citynow.locker.repository.NewTicketRepository;
import com.citynow.locker.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class AppController {
  @Autowired
  private TicketRepository ticketRepository;

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private NewCustomerRepository newCustomerRepository;

  @Autowired
  private NewTicketRepository newTicketRepository;

  @GetMapping("/ticket/lockercode-sort")
  public List<TicketHistoryDTO> getByOrderByLockerCode() {
    return ticketRepository.getByOrderByLockerCode();
  }

  @GetMapping("/ticket")
  public List<NewTicketHistoryDTO> getAllTickets() {
    return newTicketRepository.getAllTickets();
  }

  @GetMapping("/ticket/history")
  public List<TicketHistoryDTO> getAllSortedTickets(
      @RequestParam(value = "customerId", required = false) String customerId) {
    if (customerId != null && !customerId.isEmpty()) {
      var id = Integer.parseInt(customerId);
      return ticketRepository.getTicketHistoryByID(id);
    } else
      return ticketRepository.getAllSortedTickets();// ticketRepository.findAllByOrderByStartDateTimeAsc();
  }



  @GetMapping("/customer/all")
  public List<CustomerDTO> getAllCustomers() {
    return customerRepository.getAllCustomers();
  }

  @GetMapping("/customer/search")
  public List<NewCustomerDTO> searchCustomer(
      @RequestParam(value = "customerId", required = false) Integer userId,
      @RequestParam(value = "fullName", required = false) String fullName,
      @RequestParam(value = "phoneNum", required = false) String phoneNum) {
    return newCustomerRepository.searchCustomer(userId, fullName, phoneNum);
  }

  @GetMapping("/ticket/history/search")
  public List<NewTicketHistoryDTO> searchTicketHistory(
      @RequestParam(value = "mode", required = false) String paramMode,
      @RequestParam(value = "startDateTime", required = false) String startDateTime,
      @RequestParam(value = "toDateTime", required = false) String endDateTime) {
    LocalDate startDate = null, endDate = null;
    if (!startDateTime.isEmpty()) {
      startDate = LocalDate.parse(startDateTime);
    }
    if (!endDateTime.isEmpty()) {
      endDate = LocalDate.parse(endDateTime);
    }
    return newTicketRepository.searchTicketHistory(paramMode, startDate, endDate);
  }

  @GetMapping("ticket/detail")
  public List<TicketDetailDTO> getTicketDetailList(@RequestParam("ticketId") Integer ticketId) {
    return ticketRepository.getTicketDetailByID(ticketId);
  }

  @PutMapping(value = "ticket/update")
  public Integer returnTicket(@RequestBody newTicketDetailDTO payload) {
    // TODO: process PUT request
    System.out.println(payload.getTicketId());
    return newTicketRepository.updateTicket(payload);
  }
}
