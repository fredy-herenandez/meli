package com.meli.technicalevaluation.coupon.services.impl;

import com.meli.technicalevaluation.coupon.dto.ItemApiDto;
import com.meli.technicalevaluation.coupon.services.IItemGetApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Slf4j
@Service
public class ItemGetApiImpl implements IItemGetApi {
    @Override
    public Object setItem(String idItem) {
        final String url = "https://api.mercadolibre.com/items/";
        RestTemplate restTemplate = new RestTemplate();
        try {
            var resp = restTemplate.getForObject(url + idItem, HashMap.class);
            assert resp != null;
            return new ItemApiDto(idItem, Float.valueOf(String.format("%.02f", Float.valueOf(resp.get("price").toString()))));
        }catch (Exception e){
            log.error("API https://api.mercadolibre.com/items/ response Not Found for item: " + idItem);
            return null;
        }
    }

}