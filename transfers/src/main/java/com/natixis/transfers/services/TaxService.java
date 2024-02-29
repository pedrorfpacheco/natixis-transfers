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

    private static final double PERCENTAGE_TAX_A = 0.03;
    private static final double VALUE_TAX_A = 3;

    private static final double PERCENTAGE_TAX_B = 0.09;

    private static final double PERCENTAGE_TAX_C1 = 0.082;
    private static final double PERCENTAGE_TAX_C2 = 0.069;
    private static final double PERCENTAGE_TAX_C3 = 0.047;
    private static final double PERCENTAGE_TAX_C4 = 0.017;


    public Tax calculateTax(final double value, final LocalDate date) {
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
                    return new Tax(type, PERCENTAGE_TAX_A * value + VALUE_TAX_A);
                } else {
                    throw new IllegalArgumentException("Invalid schedule date for Type Tax A: " + date + " it has to be the current date.");
                }
            case B:
                if (diffDays >= 1 && diffDays <= 10) {
                    return new Tax(type, PERCENTAGE_TAX_B * value);
                } else {
                    throw new IllegalArgumentException("Invalid schedule date for Type Tax B: " + date + " it has to be between 1 and 10 days after the current date.");
                }
            case C:
                if (diffDays >= 11 && diffDays <= 20) {
                    return new Tax(type, PERCENTAGE_TAX_C1 * value);
                } else if (diffDays >= 21 && diffDays <= 30) {
                    return new Tax(type, PERCENTAGE_TAX_C2 * value);
                } else if (diffDays >= 31 && diffDays <= 40) {
                    return new Tax(type, PERCENTAGE_TAX_C3 * value);
                } else if (diffDays > 40) {
                    return new Tax(type, PERCENTAGE_TAX_C4 * value);
                } else {
                    throw new IllegalArgumentException("Invalid schedule date for Type Tax C: " + date + " it has to be at least 11 days from today");
                }
            default:
                throw new IllegalArgumentException("Type of Tax invalid: " + type);
        }
    }
}
