package com.meli.technicalevaluation.coupon.domain;

import com.meli.technicalevaluation.coupon.dto.ItemApiDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecursionCoupon {

    private Coupon recursion;
    private Coupon optimizedCoupon;
    private List<ItemApiDto> itemsUser;

    public void optimizeCoupon(Coupon recursion, List<ItemApiDto> itemsUser, boolean isFull,
                               Coupon optimizedCoupon) {

        this.optimizedCoupon = optimizedCoupon;
        this.recursion = recursion;
        this.itemsUser = itemsUser;

        if (isFull) {
            if (this.recursion.getAmountUserItems() > this.optimizedCoupon.getAmountUserItems()) {
                this.optimizedCoupon.getItems().clear();
                this.optimizedCoupon.setAmountUserItems((float) 0);

                this.optimizedCoupon.getItems().addAll(this.recursion.getItems());
                this.optimizedCoupon.setAmountUserItems(this.recursion.getAmountUserItems());

            }

        } else {
            for (ItemApiDto userFavoriteItemDto : this.itemsUser) {
                if (!this.recursion.existElement(userFavoriteItemDto)) {
                    validateRecursion(userFavoriteItemDto);
                }
            }
        }
    }


    public void validateRecursion(ItemApiDto userFavoriteItemDto) {
        if (this.recursion.getAmount() >= this.recursion.getAmountUserItems() + userFavoriteItemDto.getAmount()) {
            this.recursion.addItem(userFavoriteItemDto);
            this.optimizeCoupon(this.recursion, this.itemsUser, false, this.optimizedCoupon);
            this.recursion.deleteItem(userFavoriteItemDto);
        } else {
            this.optimizeCoupon(this.recursion, this.itemsUser, true, this.optimizedCoupon);
        }
    }
}
