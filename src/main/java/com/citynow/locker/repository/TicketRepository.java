package com.citynow.locker.repository;

import java.util.List;
import com.citynow.locker.entity.TicketEntity;
import com.citynow.locker.entity.dto.TicketHistoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TicketRepository extends JpaRepository<TicketEntity, Integer> {
  @Query(nativeQuery = true,
      value = "select tk.id as ticketId, ctm.id as customerId , ctm.full_name as fullName, lk.locker_code as lockerCode, lk.locker_name as lockerName, "
          + "tk.start_date_time as startDateTime, tk.end_date_time as endDateTime, "
          + "FROM public.customer as ctm, public.locker as lk, public.ticket as tk, public.locker_assign as la"
          + "WHERE ctm.id = tk.customer_id and tk.id = la.ticket_id and  la.locker_code = lk.locker_code")
  public List<TicketHistoryDTO> getAllTickets();

  @Query(nativeQuery = true,
      value = "select tk.id as ticketId, ctm.id as customerId , ctm.full_name as fullName, lk.locker_code as lockerCode, lk.locker_name as lockerName, "
          + "tk.start_date_time as startDateTime, tk.end_date_time as endDateTime, "
          + "FROM public.customer as ctm, public.locker as lk, public.ticket as tk, public.locker_assign as la"
          + "WHERE ctm.id = tk.customer_id and tk.id = la.ticket_id and la.locker_code = lk.locker_code and ctm.id = (:ID)")
  public List<TicketHistoryDTO> getTicketHistoryByID(@Param("ID") Integer ID);
}
