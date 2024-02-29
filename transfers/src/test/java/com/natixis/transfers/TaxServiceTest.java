package com.natixis.transfers;

import com.natixis.transfers.domain.Tax;
import com.natixis.transfers.domain.TypeTax;
import com.natixis.transfers.services.TaxService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaxServiceTest {

    private TaxService taxService;

    @BeforeEach
    void setUp() {
        taxService = new TaxService();
    }

    @Test
    void testCalculateValueTax() {
        final TypeTax typeA = TypeTax.A;
        final TypeTax typeB = TypeTax.B;
        final TypeTax typeC = TypeTax.C;

        final double valueA = 1000.0;
        final double valueB = 2000.0;
        final double valueC = 3000.0;

        final LocalDate now = LocalDate.now();
        final LocalDate dateBefore = now.minusDays(10);
        final LocalDate dateB = now.plusDays(1);
        final LocalDate dateC1 = now.plusDays(11);
        final LocalDate dateC2 = now.plusDays(21);
        final LocalDate dateC3 = now.plusDays(31);
        final LocalDate dateC4 = now.plusDays(41);

        final Tax taxA = new Tax(typeA, 33.0);
        final Tax taxB = new Tax(typeB, 180.0);
        final Tax taxC1 = new Tax(typeC, 246.0);
        final Tax taxC2 = new Tax(typeC, 207.00000000000003);
        final Tax taxC3 = new Tax(typeC, 141.0);
        final Tax taxC4 = new Tax(typeC, 51.00000000000001);

        assertEquals(taxA.getTypeTax(), taxService.calculateTax(valueA, now).getTypeTax());
        assertEquals(taxA.getTaxValue(), taxService.calculateTax(valueA, now).getTaxValue());

        assertEquals(taxB.getTypeTax(), taxService.calculateTax(valueB, dateB).getTypeTax());
        assertEquals(taxB.getTaxValue(), taxService.calculateTax(valueB, dateB).getTaxValue());

        assertEquals(taxC1.getTypeTax(), taxService.calculateTax(valueC, dateC1).getTypeTax());
        assertEquals(taxC1.getTaxValue(), taxService.calculateTax(valueC, dateC1).getTaxValue());

        assertEquals(taxC2.getTypeTax(), taxService.calculateTax(valueC, dateC2).getTypeTax());
        assertEquals(taxC2.getTaxValue(), taxService.calculateTax(valueC, dateC2).getTaxValue());

        assertEquals(taxC3.getTypeTax(), taxService.calculateTax(valueC, dateC3).getTypeTax());
        assertEquals(taxC3.getTaxValue(), taxService.calculateTax(valueC, dateC3).getTaxValue());

        assertEquals(taxC4.getTypeTax(), taxService.calculateTax(valueC, dateC4).getTypeTax());
        assertEquals(taxC4.getTaxValue(), taxService.calculateTax(valueC, dateC4).getTaxValue());

        assertThrows(IllegalArgumentException.class, () -> taxService.calculateTax(valueA, dateBefore));
        assertThrows(IllegalArgumentException.class, () -> taxService.calculateTax(valueA, dateB));

        assertThrows(IllegalArgumentException.class, () -> taxService.calculateTax(valueB, now));
        assertThrows(IllegalArgumentException.class, () -> taxService.calculateTax(valueB, dateC1));

        assertThrows(IllegalArgumentException.class, () -> taxService.calculateTax(valueC, now));
    }
}
