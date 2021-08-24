package com.citynow.locker.entity;

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
@Table(name = "locker")
public class LockerEntity {
  @Id
  @Column(name = "locker_code")
  private String lockerCode;
  @Column(name = "locker_name")
  private String lockerName;
}
