package com.electric.henryElect.services;

import com.electric.henryElect.model.Address;
import com.electric.henryElect.model.LightMeter;
import com.electric.henryElect.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Address getAddressByLightMeter(LightMeter lightMeter) {
        return addressRepository.getOne(lightMeter);
    }


    public Address addAddress(Address direccion) {
        return addressRepository.save(direccion);
    }

//    public Address editAddress(Address direccion) {
//
//    }

    public void deleteAddressByLightMeter(LightMeter lightMeter) {
        addressRepository.deleteById(lightMeter);
    }
}
