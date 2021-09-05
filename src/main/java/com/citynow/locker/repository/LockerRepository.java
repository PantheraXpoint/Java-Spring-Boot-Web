package com.citynow.locker.repository;

import com.citynow.locker.entity.LockerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LockerRepository extends JpaRepository<LockerEntity, String> {

}
