package com.company.crudshippingservice.dao;

import com.company.crudshippingservice.model.Invoice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceItemRepositoryTest {

    @Autowired
    InvoiceRepository invoiceDao;

    @Before
    public void setUp() throws Exception {

        invoiceDao.deleteAll();
    }

    @Test
    public void addGetUpdateDeleteInvoiceTest(){
        Invoice invoice = new Invoice();

        invoice.setCustomerId(1);
        invoice.setShiptoZip("11102");
        invoice.setPurchaseDate(LocalDate.parse("2019-12-14"));
        invoice.setTotalCost(new BigDecimal("26.12"));
        invoice.setSalesTax(new BigDecimal("1.35"));
        invoice.setSurcharge(new BigDecimal("8.50"));

        //Save the invoice in the database
        invoice = invoiceDao.save(invoice);

        //Getting the invoice from the database
        Optional<Invoice> fromService = invoiceDao.findById(invoice.getInvoiceId());

        //making sure that they are equal
        assertEquals(invoice,fromService.get());

        //Update The invoice
        invoice.setShiptoZip("11103");

        invoiceDao.save(invoice);

        //get the updated invoice from the db
        fromService = invoiceDao.findById(invoice.getInvoiceId());

        assertEquals(invoice, fromService.get());

        //delete
        invoiceDao.delete(invoice);

        fromService = invoiceDao.findById(invoice.getInvoiceId());

        assertNull(fromService.orElse(null));
    }
}