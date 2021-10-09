package com.meli.technicalevaluation.coupon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class ItemApiDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String itemId;

    private Float amount;

}
