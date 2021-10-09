package com.meli.technicalevaluation.coupon.domain;

import com.meli.technicalevaluation.coupon.dto.ItemApiDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Coupon {

    private Float amount;
    private List<ItemApiDto> items;
    private Float amountUserItems;

    public Coupon(Float amount) {
        super();
        this.amount = amount;
        this.amountUserItems = (float) 0;
        this.items = new ArrayList<>();
    }

    public void addItem(ItemApiDto item) {
        this.items.add(item);
        this.amountUserItems += item.getAmount();
    }

    public void deleteItem(ItemApiDto item) {
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).getItemId().equals(item.getItemId())) {
                this.items.remove(i);
                this.amountUserItems -= item.getAmount();
                break;
            }
        }
    }

    public boolean existElement(ItemApiDto item) {
        for (ItemApiDto userFavoriteItemDto : this.items) {
            if (userFavoriteItemDto != null && userFavoriteItemDto.getItemId().equals(item.getItemId())) {
                return true;
            }
        }
        return false;
    }

}
