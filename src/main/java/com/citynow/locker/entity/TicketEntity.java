package com.citynow.locker.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
  // @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;
  @Column(name = "start_date_time")
  private LocalDate startDateTime;
  @Column(name = "end_date_time", nullable = true)
  private LocalDate endDateTime;
  @Column(name = "remark")
  private String remark;
  @ManyToOne
  @JoinColumn(name = "customer_id")
  private CustomerEntity customerEntity;

  // @Column(name = "customer_id")
  // private Integer customerId;
}
