package com.citynow.locker.repository;

import com.citynow.locker.entity.AssignmentEntity;
import com.citynow.locker.entity.LockerAssignId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepository extends JpaRepository<AssignmentEntity, LockerAssignId> {

}
