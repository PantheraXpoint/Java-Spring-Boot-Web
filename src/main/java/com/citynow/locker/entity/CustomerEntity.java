package com.citynow.locker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class CustomerEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer customerId;
  @Column(name = "email")
  private String email;
  @JsonIgnore
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

}
