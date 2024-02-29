package com.natixis.transfers.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "transfers")
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
