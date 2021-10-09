package com.meli.technicalevaluation.coupon.services;

import com.meli.technicalevaluation.coupon.dto.CouponResponseDto;

import java.util.Set;


public interface ICouponItemsService {

    CouponResponseDto optimizeCoupon(Set<String> idItems, Float amount);

}
