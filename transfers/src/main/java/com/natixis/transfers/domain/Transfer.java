package com.natixis.transfers.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Transfer {

    @Id
    private String id;

    private double value;

    private LocalDate createDate;

    private LocalDate date;

    private Tax tax;
}
