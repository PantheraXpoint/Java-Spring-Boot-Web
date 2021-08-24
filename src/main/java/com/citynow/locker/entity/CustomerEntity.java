package com.citynow.locker.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class CustomerEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private Integer customerId;
  @Column(name = "email")
  private String email;
  @Column(name = "password")
  private String password;
  @Column(name = "full_name")
  private String fullName;
  @Column(name = "type")
  private Integer type;
  @Column(name = "gender")
  private Integer gender;
  @Column(name = "phone_number")
  private String phoneNumber;
  @OneToMany(mappedBy = "customerId")
  private List<TicketEntity> tickets;
}
