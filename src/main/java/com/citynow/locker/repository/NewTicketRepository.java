package com.citynow.locker.repository;

import java.time.LocalDate;
import java.util.List;
import com.citynow.locker.entity.newDto.NewTicketHistoryDTO;
import com.citynow.locker.entity.newDto.newTicketDetailDTO;

public interface NewTicketRepository {
  List<NewTicketHistoryDTO> searchTicketHistory(String paramMode, LocalDate startDateTime, LocalDate endDateTime);

  List<NewTicketHistoryDTO> getAllTickets();

  newTicketDetailDTO getLockerDetailByTicketId(Integer ticketId);

  Integer updateTicket(newTicketDetailDTO tk);
}
