package com.natixis.transfers.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tax {


    private TypeTax typeTax;

    private Double taxValue;
}
