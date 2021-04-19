package com.electric.henryElect.repository;

import com.electric.henryElect.model.LightMeter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LightMeterRepository extends JpaRepository<LightMeter, Integer> {
}
