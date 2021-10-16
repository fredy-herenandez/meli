package com.meli.technicalevaluation.coupon.services.impl;

import com.meli.technicalevaluation.coupon.domain.Coupon;
import com.meli.technicalevaluation.coupon.domain.RecursionCoupon;
import com.meli.technicalevaluation.coupon.dto.CouponResponseDto;
import com.meli.technicalevaluation.coupon.dto.ItemApiDto;
import com.meli.technicalevaluation.coupon.services.ICouponItemsService;
import com.meli.technicalevaluation.coupon.services.IItemGetApi;
import com.meli.technicalevaluation.coupon.services.ITopItemsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CouponItemsServiceImpl implements ICouponItemsService {

    private final ITopItemsService topItems;
    private final IItemGetApi listItems;
    private final RecursionCoupon recursions;

    public CouponItemsServiceImpl(IItemGetApi listItems, RecursionCoupon recursions, ITopItemsService topItems) {
        this.listItems = listItems;
        this.recursions = recursions;
        this.topItems = topItems;
    }

    @Override
    public CouponResponseDto optimizeCoupon(Set<String> idItems, Float amount) {

        Coupon base = new Coupon(amount);
        Coupon optimized = new Coupon(amount);
        List<ItemApiDto> items = new ArrayList<>();
        List<String> itemsRequest = new ArrayList<>();

        for(String idItem : idItems){
            ItemApiDto itemApi = (ItemApiDto) listItems.setItem(idItem);
            if (itemApi != null){
                items.add(itemApi);
                topItems.updateTop(itemApi.getItemId());
            }
        }

        recursions.optimizeCoupon(base, items, false, optimized);

        for(ItemApiDto id : optimized.getItems()) {
            itemsRequest.add(id.getItemId());
        }

        return new CouponResponseDto(itemsRequest,optimized.getAmountUserItems());
    }
}