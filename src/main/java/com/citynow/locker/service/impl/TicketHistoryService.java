package com.citynow.locker.service.impl;

import java.util.List;
import com.citynow.locker.entity.dto.TicketHistoryDTO;
import java.util.ArrayList;
import com.citynow.locker.entity.TicketEntity;
import com.citynow.locker.repository.TicketRepository;
import com.citynow.locker.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

public class TicketHistoryService implements AppService {

  @Autowired
  private TicketRepository ticketRepository;
}
