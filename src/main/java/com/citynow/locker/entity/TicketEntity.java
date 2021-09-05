package com.citynow.locker.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ticket")
public class TicketEntity {
  @JsonIgnore
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer ticketId;
  @Column(name = "start_date_time")
  private LocalDate startDateTime;
  @Column(name = "end_date_time", nullable = true)
  private LocalDate endDateTime;
  @Column(name = "remark")
  private String remark;
  // @Column(name = "customer_id")
  // private Integer customerId;
  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "customer_id")
  private CustomerEntity customerEntity;
  @JsonIgnore
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "locker_assign", joinColumns = {
      @JoinColumn(name = "ticket_id", referencedColumnName = "id", nullable = false, updatable = false) }, inverseJoinColumns = {
          @JoinColumn(name = "locker_code", referencedColumnName = "locker_code", nullable = false, updatable = false) })
  private Set<LockerEntity> lockers = new HashSet<>();

  // @Column(name = "customer_id")
  // private Integer customerId;

  public TicketEntity(LocalDate startDateTime, String remark, CustomerEntity customerEntity) {
    this.startDateTime = startDateTime;
    this.remark = remark;
    this.customerEntity = customerEntity;
    this.endDateTime = null;
  }
}
