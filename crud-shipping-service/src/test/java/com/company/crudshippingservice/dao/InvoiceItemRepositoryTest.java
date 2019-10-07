package com.company.crudshippingservice.dao;

import com.company.crudshippingservice.model.Invoice;
import com.company.crudshippingservice.model.InvoiceItem;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceItemRepositoryTest {

    @Autowired
    InvoiceItemRepository invoiceItemRepository;

    @Autowired
    InvoiceRepository invoiceRepository;

    @Before
    public void setUp() throws Exception {

        invoiceItemRepository.deleteAll();
        invoiceRepository.deleteAll();

    }

    @Test
    public void addGetUpdateDeleteInvoiceItemTest(){

        Invoice invoice = new Invoice();

        invoice.setCustomerId(1);
        invoice.setShiptoZip("11102");
        invoice.setPurchaseDate(LocalDate.parse("2019-12-14"));
        invoice.setTotalCost(new BigDecimal("26.12"));
        invoice.setSalesTax(new BigDecimal("1.35"));
        invoice.setSurcharge(new BigDecimal("8.50"));

        //Save the invoice in the database
        invoice = invoiceRepository.save(invoice);

        InvoiceItem invoiceItem = new InvoiceItem();

        invoiceItem.setInvoiceItemId(100);
        invoiceItem.setInvoiceId(invoice.getInvoiceId());
        invoiceItem.setItemName("pillow");
        invoiceItem.setItemDescription("A fluffy cloud like pillow for sweet dreaming");
        invoiceItem.setWeight(2);
        invoiceItem.setShipCost(new BigDecimal("14.99"));

        // Save the invoice item in the database
        invoiceItem = invoiceItemRepository.save(invoiceItem);

        // getting the invoice item from the database
        Optional<InvoiceItem> fromService = invoiceItemRepository.findById(invoiceItem.getInvoiceItemId());

        // making sure that they are equal
        assertEquals(invoiceItem, fromService.get());

        // update the invoice item
        invoiceItem.setWeight(3);
        invoiceItemRepository.save(invoiceItem);

        // get the updated invoice from the database
        fromService = invoiceItemRepository.findById(invoiceItem.getInvoiceItemId());

        assertEquals(invoiceItem, fromService.get());

        invoiceItemRepository.delete(invoiceItem);

        fromService = invoiceItemRepository.findById(invoiceItem.getInvoiceItemId());

        assertNull(fromService.orElse(null));


    }
}