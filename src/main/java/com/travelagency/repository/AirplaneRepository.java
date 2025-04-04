package com.travelagency.repository;

import com.travelagency.model.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirplaneRepository extends JpaRepository<Airplane, Integer> {
} 