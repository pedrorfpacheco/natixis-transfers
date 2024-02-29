package com.natixis.transfers.controllers;

import com.natixis.transfers.domain.Transfer;
import com.natixis.transfers.domain.TransferRequest;
import com.natixis.transfers.services.SchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/schedule")
public class SchedulingController {

    @Autowired
    private SchedulingService schedulingService;

    @PostMapping("/{value}/{dateString}")
    public ResponseEntity<?> createTransfer(@PathVariable final long value, @PathVariable final String dateString) {
        LocalDate date = LocalDate.parse(dateString);

        try {
            Transfer transfer = schedulingService.scheduleTransfer(value, date);

            return new ResponseEntity<>(transfer, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateTransfer(@RequestBody final TransferRequest transferRequest) {

        try {
            Transfer transfer = schedulingService.updateTransfer(transferRequest);

            return new ResponseEntity<>(transfer, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTransferById(@PathVariable final String id) {
        try {
            Transfer transfer = schedulingService.getTransferById(id);

            return new ResponseEntity<>(transfer, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/date/{dateString}")
    public ResponseEntity<?> getTransfersByDate(@PathVariable final String dateString) {
        try {
            List<Transfer> transfers = schedulingService.getTransfersByDate(LocalDate.parse(dateString));

            return new ResponseEntity<>(transfers, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Transfer>> getAllTransfers() {
        List<Transfer> transfers = schedulingService.getTransfers();

        return new ResponseEntity<>(transfers, HttpStatus.OK);
    }
}
