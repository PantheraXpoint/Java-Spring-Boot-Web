/**
 * Locker Spring Boot Website Project
 */
package com.citynow.locker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Simulator")
@Getter
@Setter
public class EntitySimulation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private short id;

  @Column(nullable = false)
  private String name;

  @Column(name = "password", nullable = false)
  private String pwd;
}
