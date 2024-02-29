package com.natixis.transfers.services;

import com.natixis.transfers.domain.Tax;
import com.natixis.transfers.domain.TypeTax;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class TaxService {

    private static final double LIMIT_TAX_A = 1000.0;
    private static final double LIMIT_TAX_B = 2000.0;

    protected Tax calculateTax(final long value, final LocalDate date) {
        if (value <= LIMIT_TAX_A) {
            return calculateValueTax(TypeTax.A, value, date);
        } else if (value <= LIMIT_TAX_B) {
            return calculateValueTax(TypeTax.B, value, date);
        } else {
            return calculateValueTax(TypeTax.C, value, date);
        }
    }

    private Tax calculateValueTax(final TypeTax type, final double value, final LocalDate date) {
        if (date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Invalid schedule date:" + date);
        }

        final LocalDate now = LocalDate.now();
        long diffDays = ChronoUnit.DAYS.between(now, date);

        switch (type) {
            case A:
                if (diffDays == 0) {
                    return new Tax(type, 0.03 * value + 3);
                } else {
                    throw new IllegalArgumentException("Invalid schedule date for Type Tax A:" + date + " it has to be the current date.");
                }
            case B:
                if (diffDays >= 1 && diffDays <= 10) {
                    return new Tax(type, 0.09 * value);
                } else {
                    throw new IllegalArgumentException("Invalid schedule date for Type Tax B:" + date + " it has to be between 1 and 10 days after the current date.");
                }
            case C:
                if (diffDays >= 11 && diffDays <= 20) {
                    return new Tax(type, 0.082 * value);
                } else if (diffDays >= 21 && diffDays <= 30) {
                    return new Tax(type, 0.069 * value);
                } else if (diffDays >= 31 && diffDays <= 40) {
                    return new Tax(type, 0.047 * value);
                } else if (diffDays > 40) {
                    return new Tax(type, 0.017 * value);
                } else {
                    throw new IllegalArgumentException("Invalid schedule date for Type Tax C:" + date + " it has to be at least 11 days from today");
                }
            default:
                throw new IllegalArgumentException("Type of Tax invalid: " + type);
        }
    }
}
