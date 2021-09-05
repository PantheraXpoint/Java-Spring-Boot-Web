package com.citynow.locker.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;

import com.citynow.locker.entity.ApiResponseEntity;
import com.citynow.locker.entity.AssignmentEntity;
import com.citynow.locker.entity.CustomerEntity;
import com.citynow.locker.entity.TicketEntity;
import com.citynow.locker.entity.dto.CustomerDTO;
import com.citynow.locker.entity.dto.TicketHistoryDTO;
import com.citynow.locker.entity.newDto.NewCustomerAssign;
import com.citynow.locker.entity.newDto.NewCustomerDTO;
import com.citynow.locker.entity.newDto.NewTicketHistoryDTO;
import com.citynow.locker.entity.newDto.newTicketDetailDTO;
import com.citynow.locker.repository.AssignmentRepository;
import com.citynow.locker.repository.CustomerRepository;
import com.citynow.locker.repository.LockerRepository;
import com.citynow.locker.repository.NewCustomerRepository;
import com.citynow.locker.repository.NewTicketRepository;
import com.citynow.locker.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
public class AppController {

  @PersistenceContext
  private EntityManager em;

  @Autowired
  private TicketRepository ticketRepository;

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private NewCustomerRepository newCustomerRepository;

  @Autowired
  private NewTicketRepository newTicketRepository;

  @Autowired
  private AssignmentRepository newAssignmentRepository;

  @Autowired
  private LockerRepository newLockerRepository;

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
  public List<NewCustomerDTO> searchCustomer(@RequestParam(value = "customerId", required = false) Integer userId,
      @RequestParam(value = "fullName", required = false) String fullName,
      @RequestParam(value = "phoneNumber", required = false) String phoneNum) {
    return newCustomerRepository.searchCustomer(userId, fullName, phoneNum);
  }

  @GetMapping("/ticket/history/search")
  public List<NewTicketHistoryDTO> searchTicketHistory(@RequestParam(value = "mode", required = false) String paramMode,
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
  public newTicketDetailDTO getTicketDetailList(@RequestParam("ticketId") Integer ticketId) {
    return newTicketRepository.getLockerDetailByTicketId(ticketId);
  }

  @Transactional
  @PutMapping(value = "ticket/update")
  public Integer updateTicket(@RequestBody newTicketDetailDTO payload) {
    // TODO: process PUT request
    // System.out.println(payload.getEndDate());
    return newTicketRepository.updateTicket(payload);
    // return 0;
  }

  // @GetMapping(value="locker/check")
  // public ApiResponseEntity checkLockerExists(@RequestParam String lockerCode)
  // // Check if lockerCode exist
  // newLockerCodeRepo.checkLockerExists() ;
  // }

  @PostMapping(value = "ticket/create")
  public ApiResponseEntity<NewCustomerAssign> createTicket(@RequestBody NewCustomerAssign payload) {
    List<NewCustomerAssign> list = new ArrayList<NewCustomerAssign>();
    if (!newLockerRepository.existsById(payload.getLockerCode())) {
      return new ApiResponseEntity<NewCustomerAssign>(HttpStatus.BAD_REQUEST, list, "No locker code in the database");
    }
    if (payload.getCustomerId() == null) {
      // insert new customer
      if (payload.getFullName() == null || payload.getPhoneNumber() == null || payload.getGender() == null) {
        return new ApiResponseEntity<NewCustomerAssign>(HttpStatus.BAD_REQUEST, list,
            "Fullname, phoneNumber, Gender must not be null");
      } else {
        CustomerEntity newCustomer = new CustomerEntity(payload.getFullName(), payload.getGender(),
            payload.getPhoneNumber(), 2);

        CustomerEntity insertedCustomer = customerRepository.save(newCustomer);
        customerRepository.flush();
        // create a new ticket
        TicketEntity newTicket = new TicketEntity(payload.getStartDateTime(), payload.getRemark(), insertedCustomer);
        TicketEntity insertedTicket = ticketRepository.save(newTicket);
        ticketRepository.flush();
        // create locker_assign with new ticket
        AssignmentEntity newAssignment = new AssignmentEntity(insertedTicket.getTicketId(), payload.getLockerCode());
        AssignmentEntity insertedAssignment = newAssignmentRepository.save(newAssignment);
        return new ApiResponseEntity<NewCustomerAssign>(HttpStatus.OK, list, "OK");
      }
    } else {
      // query the identified customer
      // create a new ticket with the input customerId
      // create locker_assign with new ticket
      CustomerEntity newCustomer = customerRepository.getById(payload.getCustomerId());
      CustomerEntity insertedCustomer = customerRepository.save(newCustomer);
      customerRepository.flush();
      TicketEntity newTicket = new TicketEntity(payload.getStartDateTime(), payload.getRemark(), insertedCustomer);
      TicketEntity insertedTicket = ticketRepository.save(newTicket);
      ticketRepository.flush();
      AssignmentEntity newAssignment = new AssignmentEntity(insertedTicket.getTicketId(), payload.getLockerCode());
      AssignmentEntity insertedAssignment = newAssignmentRepository.save(newAssignment);
      payload.setCustomerId(insertedCustomer.getCustomerId());
      payload.setRemark(insertedTicket.getRemark());
      list.add(payload);
      return new ApiResponseEntity<NewCustomerAssign>(HttpStatus.OK, list, "OK");
    }
  }
}
