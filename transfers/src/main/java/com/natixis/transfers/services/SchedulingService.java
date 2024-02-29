package com.natixis.transfers.services;

import com.natixis.transfers.domain.Tax;
import com.natixis.transfers.domain.Transfer;
import com.natixis.transfers.repos.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SchedulingService {

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private TaxService taxService;

    public Transfer scheduleTransfer(final long value, final LocalDate date) {
        final Tax tax = taxService.calculateTax(value, date);

        final Transfer transfer = new Transfer(null, value, LocalDate.now(), date, tax);

        try {
            transferRepository.save(transfer);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving transfer: " + e.getMessage());
        }

        return transfer;
    }

    public List<Transfer> getTransfers() {
        return transferRepository.findAll();
    }

    public List<Transfer> getTransfersByDate(final LocalDate date) {
        return transferRepository.findByDate(date);
    }

}
