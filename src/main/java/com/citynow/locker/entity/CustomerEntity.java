package com.citynow.locker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.boot.context.properties.ConstructorBinding;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ConstructorBinding()
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
  @Column(name = "type", nullable = false)
  private Integer type;
  @Column(name = "gender")
  private Integer gender;
  @Column(name = "phone_number")
  private String phoneNumber;

  public CustomerEntity(String fullName, Integer gender, String phoneNumber, Integer type) {
    this.fullName = fullName;
    this.gender = gender;
    this.phoneNumber = phoneNumber;
    this.type = type;
  }
}
