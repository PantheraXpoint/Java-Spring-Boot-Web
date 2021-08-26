package com.citynow.locker.repository;

import java.util.List;
import javax.transaction.Transactional;
import com.citynow.locker.entity.TicketEntity;
import com.citynow.locker.entity.dto.TicketDetailDTO;
import com.citynow.locker.entity.dto.TicketHistoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TicketRepository extends JpaRepository<TicketEntity, Integer> {

  @Query(nativeQuery = true,
      value = "select tk.id as ticketId, ctm.id as customerId , ctm.full_name as fullName,"
          + "lk.locker_code as lockerCode, lk.locker_name as lockerName, "
          + "tk.start_date_time as startDateTime, tk.end_date_time as endDateTime "
          + "FROM public.customer as ctm, public.locker as lk, public.ticket as tk, public.locker_assign as la "
          + "WHERE ctm.id = tk.customer_id and tk.id = la.ticket_id and la.locker_code = lk.locker_code and ctm.id = (:ID)"
          + "order by tk.end_date_time DESC, tk.start_date_time ASC, substring(lk.locker_code, 5)\\:\\:int ASC")
  public List<TicketHistoryDTO> getTicketHistoryByID(@Param("ID") Integer ID);


  @Query(nativeQuery = true,
      value = "select tk.id as ticketId, tk.customer_id as customerId, cm.full_name as fullName, la.locker_code as lockerCode,l.locker_name as lockerName,"
          + "tk.start_date_time as startDateTime, tk.end_date_time as endDateTime "
          + "from customer as cm, ticket as tk, locker_assign as la, locker as l "
          + "where tk.customer_id = cm.id and tk.id=la.ticket_id and la.locker_code = l.locker_code "
          + "order by tk.end_date_time DESC, tk.start_date_time ASC")
  public List<TicketHistoryDTO> getAllSortedTickets();


  @Query(nativeQuery = true,
      value = "select tk.id as ticketId, tk.customer_id as customerId, cm.full_name as fullName, "
          + "la.locker_code as lockerCode,l.locker_name as lockerName, tk.start_date_time as startDateTime, tk.end_date_time as endDateTime "
          + "from customer as cm, ticket as tk, locker_assign as la, locker as l "
          + "where tk.customer_id = cm.id and tk.id=la.ticket_id and la.locker_code = l.locker_code "
          + "ORDER BY substring(l.locker_code, 5)\\:\\:int ASC")
  public List<TicketHistoryDTO> getByOrderByLockerCode();


  @Query(nativeQuery = true,
      value = "select tk.id as ticketId, tk.customer_id as customerId, ctm.full_name as fullName, "
          + "ctm.type as type, ctm.gender as gender, ctm.phone_number as phoneNumber, lk.locker_code as lockerCode, tk.remark as remark,"
          + "to_char (tk.start_date_time,'YYYY/MM/DD') as startDateTime,"
          + "to_char (tk.end_date_time,'YYYY/MM/DD') as endDateTime "
          + "from ticket as tk, locker as lk, customer as ctm, locker_assign as la "
          + "where tk.customer_id = ctm.id and tk.id=la.ticket_id and la.locker_code = lk.locker_code and tk.id = (:ID)")
  public List<TicketDetailDTO> getTicketDetailByID(@Param("ID") Integer ID);

  @Transactional
  @Modifying
  @Query(value = "UPDATE ticket set end_date_time = now() where id = :id",
      nativeQuery = true)
  public Integer returnTicket(@Param("id") Integer ticket_id);

  @Modifying
  @Query(nativeQuery = true, value = "Insert ")
  public int insertLockerAssign(@Param("ticketId") Integer ticketId,
      @Param("lockerCode") String lockerCode);
}
