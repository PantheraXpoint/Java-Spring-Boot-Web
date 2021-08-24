package com.citynow.locker.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "locker_assign")
public class AssignmentEntity implements Serializable {
  @Id
  @Column(name = "ticket_id")
  private Integer ticketId;
  @Id
  @Column(name = "locker_code")
  private String lockerCode;
}
