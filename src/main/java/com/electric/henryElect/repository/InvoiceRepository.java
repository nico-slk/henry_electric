package com.electric.henryElect.repository;

import com.electric.henryElect.model.Address;
import com.electric.henryElect.model.Client;
import com.electric.henryElect.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    Client findClientByAddress(Address address);
}
