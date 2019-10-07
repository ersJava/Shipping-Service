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
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceRepositoryTest {

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

    @Test
    public void getInvoicesByCustomerIdTest() {

        Invoice invoice = new Invoice();

        invoice.setCustomerId(1);
        invoice.setShiptoZip("11102");
        invoice.setPurchaseDate(LocalDate.parse("2019-12-14"));
        invoice.setTotalCost(new BigDecimal("26.12"));
        invoice.setSalesTax(new BigDecimal("1.35"));
        invoice.setSurcharge(new BigDecimal("8.50"));

        //Save the invoice in the database
        invoice = invoiceDao.save(invoice);

        Invoice invoice1 = new Invoice();

        invoice1.setCustomerId(1);
        invoice1.setShiptoZip("11105");
        invoice1.setPurchaseDate(LocalDate.parse("2019-12-16"));
        invoice1.setTotalCost(new BigDecimal("12.13"));
        invoice1.setSalesTax(new BigDecimal("1.38"));
        invoice1.setSurcharge(new BigDecimal("9.99"));

        //Save the invoice in the database
        invoice1 = invoiceDao.save(invoice1);

        List<Invoice> invoiceList = invoiceDao.getInvoicesByCustomerId(1);

        assertEquals(2, invoiceList.size());

    }
}