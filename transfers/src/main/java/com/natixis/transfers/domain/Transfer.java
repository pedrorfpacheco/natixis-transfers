package com.natixis.transfers.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.Instant;

@Data
@AllArgsConstructor
public class Transfer {

    @Id
    private Long id;

    private Long value;

    private Instant createDate;

    private Instant date;

    private Tax tax;
}
