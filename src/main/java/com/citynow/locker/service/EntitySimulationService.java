/**
 * Locker Spring Boot Website Project
 */
package com.citynow.locker.service;

import java.util.List;
import com.citynow.locker.entity.EntitySimulation;

public interface EntitySimulationService {

  List<EntitySimulation> getAll();

  EntitySimulation getById(Integer id);

}
