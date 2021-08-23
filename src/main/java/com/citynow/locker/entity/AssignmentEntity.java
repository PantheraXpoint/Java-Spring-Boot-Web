package com.citynow.locker.entity;

import javax.persistence.Entity;
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
@Table(name = "locker_assign", schema = "public")
public class AssignmentEntity {
  private Integer ticketId;
  private String lockerCode;
}
