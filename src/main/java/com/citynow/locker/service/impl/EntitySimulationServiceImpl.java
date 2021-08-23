/**
 * Locker Spring Boot Website Project
 */
package com.citynow.locker.service.impl;

import java.util.List;
import com.citynow.locker.entity.EntitySimulation;
import com.citynow.locker.repository.EntitySimulationRepository;
import com.citynow.locker.service.EntitySimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntitySimulationServiceImpl implements EntitySimulationService {

  @Autowired
  private EntitySimulationRepository entitySimulationRepository;

  @Override
  public List<EntitySimulation> getAll() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public EntitySimulation getById(Integer id) {
    // TODO Auto-generated method stub
    return null;
  }

}
