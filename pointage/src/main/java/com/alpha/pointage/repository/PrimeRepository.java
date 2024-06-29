package com.alpha.pointage.repository;

import com.alpha.pointage.entity.PrimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrimeRepository extends JpaRepository<PrimeEntity,Long> {


}
