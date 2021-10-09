package com.meli.technicalevaluation.coupon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class CouponResponseDto {

    @ApiModelProperty(value = "List of suggested items for the customer.", name = "item_ids", dataType = "List<String>", example = "[\"MLA811601010\",\"MLA816019441\"]")
    @JsonProperty("item_ids")
    private List<String> items;

    @ApiModelProperty(value = "Optimized coupon amount.", name = "total", dataType = "Float", example = "19947.04")
    @JsonProperty("total")
    private Float amountTotal;

}