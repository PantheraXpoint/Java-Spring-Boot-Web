/**
 * Locker Spring Boot Website Project
 */
package com.citynow.locker.controller;

import com.citynow.locker.service.EntitySimulationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

  private final EntitySimulationService entitySimulationService;

  HomeController(EntitySimulationService entitySimulationService) {
    this.entitySimulationService = entitySimulationService;
  }

  @RequestMapping(method = RequestMethod.GET)
  String get() {
    return null;
  }

  @RequestMapping(method = RequestMethod.POST)
  String post() {
    return null;
  }
}
