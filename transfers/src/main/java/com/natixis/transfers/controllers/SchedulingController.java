package com.natixis.transfers.controllers;

import com.natixis.transfers.domain.Transfer;
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



    @GetMapping
    public ResponseEntity<List<Transfer>> getAllTransfers() {
        List<Transfer> transfers = schedulingService.getTransfers();

        return new ResponseEntity<>(transfers, HttpStatus.OK);
    }
}
