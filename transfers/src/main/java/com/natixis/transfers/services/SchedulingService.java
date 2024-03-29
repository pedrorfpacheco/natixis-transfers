package com.natixis.transfers.services;

import com.natixis.transfers.domain.Tax;
import com.natixis.transfers.domain.Transfer;
import com.natixis.transfers.domain.TransferRequest;
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

    public Transfer scheduleTransfer(final double value, final LocalDate date) {
        if (value <= 0) {
            throw new IllegalArgumentException("Invalid transfer value: " + value + " it has to be greater than 0.");
        }

        final Tax tax = taxService.calculateTax(value, date);

        final Transfer transfer = new Transfer(null, value, LocalDate.now(), date, tax);

        try {
            transferRepository.save(transfer);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving transfer: " + e.getMessage());
        }

        return transfer;
    }

    public Transfer updateTransfer(final TransferRequest transferRequest) {
        final Transfer transfer = transferRepository.findById(transferRequest.getId()).orElseThrow(() -> new IllegalArgumentException("Transfer not found"));

        final LocalDate date = LocalDate.parse(transferRequest.getDate());

        final Tax tax = taxService.calculateTax(transferRequest.getValue(), date);
        transfer.setValue(transferRequest.getValue());
        transfer.setDate(date);
        transfer.setTax(tax);

        transferRepository.save(transfer);

        return transfer;
    }

    public List<Transfer> getTransfers() {
        return transferRepository.findAll();
    }

    public Transfer getTransferById(final String id) {
        return transferRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Transfer not found"));
    }

    public List<Transfer> getTransfersByDate(final LocalDate date) {
        return transferRepository.findByDate(date);
    }

    public void deleteTransfer(final String id) {
        transferRepository.deleteById(id);
    }
}
