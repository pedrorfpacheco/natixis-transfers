package com.natixis.transfers.domain;

import lombok.Data;

@Data
public class TransferRequest {
    private String id;
    private double value;
    private String date;
}
