package com.meli.technicalevaluation.coupon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CouponRequestDto {

    @ApiModelProperty(value = "List of customer's favorite items.", name = "item_ids", dataType = "Set<String>", example = "[\"MLA811601011\",\"MLA811601010\",\"MLA816019440\",\"MLA816019441\",\"MLA816019442\",\"MLA816019443\",\"MLA811601010\",\"MLA816019444\"]")
    @JsonProperty("item_ids")
    private Set<String> itemIds;

    @ApiModelProperty(value = "Customer coupon amount.", name = "amount", dataType = "Float", example = "20000")
    private Float amount;

}