package com.meli.technicalevaluation.coupon.web.rest;

import com.meli.technicalevaluation.coupon.dto.CouponRequestDto;
import com.meli.technicalevaluation.coupon.dto.CouponResponseDto;
import com.meli.technicalevaluation.coupon.services.ICouponItemsService;
import com.meli.technicalevaluation.coupon.services.ITopItemsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpStatus.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class CouponController {

    private final ICouponItemsService coupon;

    private final ITopItemsService top;

    public CouponController(ICouponItemsService coupon, ITopItemsService top) {
        this.coupon = coupon;
        this.top = top;
    }

    @PostMapping("/")
    @ResponseStatus(OK)
    @ApiOperation("Returns a list of items that optimize the customer's use of the coupon, based on a list of favorite items.")
    @ApiResponse(code = 200, message = "Ok", response = CouponResponseDto.class)
    public CouponResponseDto optimizeClientCoupon(
            @ApiParam(value = "Two fields, amount and item_ids.", required = true) @RequestBody CouponRequestDto request) {

        return coupon.optimizeCoupon(request.getItemIds(), request.getAmount());
    }

    @GetMapping("/stats")
    @ApiOperation("Returns top five favorite items for customers.")
    @ApiResponse(code = 200, message = "Ok")
    public List<HashMap<String, Long>> topFavoritesItems() {
        return top.listTop();
    }
}