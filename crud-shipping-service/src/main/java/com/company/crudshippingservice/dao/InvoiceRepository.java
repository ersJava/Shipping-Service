package com.company.crudshippingservice.dao;

import com.company.crudshippingservice.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,Integer> {
    List<Invoice> getInvoicesByCustomerId(int customerId);
}
