package com.natixis.transfers;

import com.natixis.transfers.domain.Tax;
import com.natixis.transfers.domain.Transfer;
import com.natixis.transfers.domain.TypeTax;
import com.natixis.transfers.repos.TransferRepository;
import com.natixis.transfers.services.SchedulingService;
import com.natixis.transfers.services.TaxService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SchedulingServiceTest {

    @InjectMocks
    private SchedulingService schedulingService;

    @Mock
    private TaxService taxService;

    @Mock
    private TransferRepository transferRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testScheduleTransferTypeA() {
        double value = 1000.0;
        LocalDate date = LocalDate.now();
        Tax tax = new Tax(TypeTax.A, 33.0);
        Transfer expectedTransfer = new Transfer(null, value, LocalDate.now(), date, tax);

        when(taxService.calculateTax(value, date)).thenReturn(tax);
        when(transferRepository.save(any(Transfer.class))).thenReturn(expectedTransfer);

        Transfer result = schedulingService.scheduleTransfer(value, date);

        assertNotNull(result);
        assertEquals(expectedTransfer.getValue(), result.getValue());
        assertEquals(expectedTransfer.getDate(), result.getDate());
        assertEquals(expectedTransfer.getTax().getTaxValue(), result.getTax().getTaxValue());

        verify(taxService, times(1)).calculateTax(value, date);
    }

    @Test
    void testScheduleTransferTypeB() {
        double value = 2000.0;
        LocalDate date = LocalDate.now().plusDays(1);
        Tax tax = new Tax(TypeTax.B, 180.0);
        Transfer expectedTransfer = new Transfer(null, value, LocalDate.now(), date, tax);

        when(taxService.calculateTax(value, date)).thenReturn(tax);
        when(transferRepository.save(any(Transfer.class))).thenReturn(expectedTransfer);

        Transfer result = schedulingService.scheduleTransfer(value, date);

        assertNotNull(result);
        assertEquals(expectedTransfer.getValue(), result.getValue());
        assertEquals(expectedTransfer.getDate(), result.getDate());
        assertEquals(expectedTransfer.getTax().getTaxValue(), result.getTax().getTaxValue());

        verify(taxService, times(1)).calculateTax(value, date);
    }

    @Test
    void testScheduleTransferTypeC1() {
        double value = 3000.0;
        LocalDate date = LocalDate.now().plusDays(11);
        Tax tax = new Tax(TypeTax.C, 246.0);
        Transfer expectedTransfer = new Transfer(null, value, LocalDate.now(), date, tax);

        when(taxService.calculateTax(value, date)).thenReturn(tax);
        when(transferRepository.save(any(Transfer.class))).thenReturn(expectedTransfer);

        Transfer result = schedulingService.scheduleTransfer(value, date);

        assertNotNull(result);
        assertEquals(expectedTransfer.getValue(), result.getValue());
        assertEquals(expectedTransfer.getDate(), result.getDate());
        assertEquals(expectedTransfer.getTax().getTaxValue(), result.getTax().getTaxValue());

        verify(taxService, times(1)).calculateTax(value, date);
    }

    @Test
    void testScheduleTransferTypeC2() {
        double value = 3000.0;
        LocalDate date = LocalDate.now().plusDays(21);
        Tax tax = new Tax(TypeTax.C, 207.0);
        Transfer expectedTransfer = new Transfer(null, value, LocalDate.now(), date, tax);

        when(taxService.calculateTax(value, date)).thenReturn(tax);
        when(transferRepository.save(any(Transfer.class))).thenReturn(expectedTransfer);

        Transfer result = schedulingService.scheduleTransfer(value, date);

        assertNotNull(result);
        assertEquals(expectedTransfer.getValue(), result.getValue());
        assertEquals(expectedTransfer.getDate(), result.getDate());
        assertEquals(expectedTransfer.getTax().getTaxValue(), result.getTax().getTaxValue());

        verify(taxService, times(1)).calculateTax(value, date);
    }

    @Test
    void testScheduleTransferTypeC3() {
        double value = 3000.0;
        LocalDate date = LocalDate.now().plusDays(31);
        Tax tax = new Tax(TypeTax.C, 141.0);
        Transfer expectedTransfer = new Transfer(null, value, LocalDate.now(), date, tax);

        when(taxService.calculateTax(value, date)).thenReturn(tax);
        when(transferRepository.save(any(Transfer.class))).thenReturn(expectedTransfer);

        Transfer result = schedulingService.scheduleTransfer(value, date);

        assertNotNull(result);
        assertEquals(expectedTransfer.getValue(), result.getValue());
        assertEquals(expectedTransfer.getDate(), result.getDate());
        assertEquals(expectedTransfer.getTax().getTaxValue(), result.getTax().getTaxValue());

        verify(taxService, times(1)).calculateTax(value, date);
    }

    @Test
    void testScheduleTransferTypeC4() {
        double value = 3000.0;
        LocalDate date = LocalDate.now().plusDays(41);
        Tax tax = new Tax(TypeTax.C, 51.0);
        Transfer expectedTransfer = new Transfer(null, value, LocalDate.now(), date, tax);

        when(taxService.calculateTax(value, date)).thenReturn(tax);
        when(transferRepository.save(any(Transfer.class))).thenReturn(expectedTransfer);

        Transfer result = schedulingService.scheduleTransfer(value, date);

        assertNotNull(result);
        assertEquals(expectedTransfer.getValue(), result.getValue());
        assertEquals(expectedTransfer.getDate(), result.getDate());
        assertEquals(expectedTransfer.getTax().getTaxValue(), result.getTax().getTaxValue());

        verify(taxService, times(1)).calculateTax(value, date);
    }
}