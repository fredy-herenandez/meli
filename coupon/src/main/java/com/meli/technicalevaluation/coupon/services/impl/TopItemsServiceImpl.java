package com.meli.technicalevaluation.coupon.services.impl;

import com.meli.technicalevaluation.coupon.model.entity.FavoriteItem;
import com.meli.technicalevaluation.coupon.model.repository.IFavoriteItemsRepository;
import com.meli.technicalevaluation.coupon.services.ITopItemsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class TopItemsServiceImpl implements ITopItemsService {

    private final IFavoriteItemsRepository itemsFavorites;

    public TopItemsServiceImpl(IFavoriteItemsRepository itemsFavorites) {
        this.itemsFavorites = itemsFavorites;
    }

    @Override
    public List<HashMap<String, Long>> listTop() {
        List<HashMap<String, Long>> response = new ArrayList<>();
        List<FavoriteItem> items = itemsFavorites.listTop();
        for (FavoriteItem itm : items) {
            HashMap<String, Long> resp = new HashMap<>();
            resp.put(itm.getId(), itm.getTimes());
            response.add(resp);
        }
        return response;
    }

    @Override
    public void updateTop(String itemId) {
        FavoriteItem item = itemsFavorites.findById(itemId).orElse(null);
        if (item != null) {
            item.setTimes(item.getTimes() + 1);
            itemsFavorites.save(item);
        } else {
            FavoriteItem itm = new FavoriteItem();
            itm.setId(itemId);
            itm.setTimes(Long.valueOf("1"));
            itemsFavorites.save(itm);
        }
    }
}