package com.electric.henryElect.repository;

import com.electric.henryElect.model.Address;
import com.electric.henryElect.model.LightMeter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, LightMeter> {
}
