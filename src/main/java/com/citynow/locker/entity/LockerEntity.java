package com.citynow.locker.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@Table(name = "locker")
public class LockerEntity {
  @Id
  @Column(name = "locker_code")
  private String lockerCode;
  @Column(name = "locker_name")
  private String lockerName;
  @JsonIgnore
  @ManyToMany(mappedBy = "lockers", fetch = FetchType.LAZY)
  private Set<TicketEntity> tickets = new HashSet<>();

}
