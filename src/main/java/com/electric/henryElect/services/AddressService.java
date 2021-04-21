package com.electric.henryElect.services;

import com.electric.henryElect.model.Address;
import com.electric.henryElect.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

// POR ID
    public Address getAddressByID(Integer id) {
        return addressRepository.findById(id)
                .orElseThrow(()-> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public Address addAddress(Address direccion) {
        return addressRepository.save(direccion);
    }

    public Address editAddress(Address direccion) {
        Address address = addressRepository.findById(direccion.getId())
                .orElseThrow(()-> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        Address newAddress = new Address();
        newAddress.setId(direccion.getId());
        if(direccion.getNumber() != null){
            newAddress.setNumber(direccion.getNumber());
        } else {
            newAddress.setNumber(address.getNumber());
        }

        if(direccion.getStreet() != null){
            newAddress.setStreet(direccion.getStreet());
        } else {
            newAddress.setStreet(address.getStreet());
        }

        if(direccion.getLightMeterId() != null){
            newAddress.setLightMeterId(direccion.getLightMeterId());
        } else {
            newAddress.setLightMeterId(address.getLightMeterId());
        }

        if(direccion.getClientId() != null){
            newAddress.setClientId(direccion.getClientId());
        } else {
            newAddress.setClientId(address.getClientId());
        }

        return addressRepository.save(newAddress);
    }
    public void deleteAddressByID(Integer id) {
        addressRepository.deleteById(id);
    }

}
