package com.meli.technicalevaluation.coupon.datatest;

import com.meli.technicalevaluation.coupon.dto.CouponResponseDto;
import com.meli.technicalevaluation.coupon.dto.ItemApiDto;
import com.meli.technicalevaluation.coupon.model.entity.FavoriteItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class DataMock {

    public static Optional<FavoriteItem> persistFavoriteItem1() {
        return Optional.of(new FavoriteItem("MLA816019444",11L));
    }

    public static Optional<FavoriteItem> persistFavoriteItem2() {
        return Optional.of(new FavoriteItem("MLA811601011",31L));
    }

    public static Optional<FavoriteItem> persistFavoriteItem3() {
        return Optional.of(new FavoriteItem("MLA816019440",31L));
    }

    public static Optional<FavoriteItem> persistFavoriteItem4() {
        return Optional.of(new FavoriteItem("MLA811601010",20L));
    }

    public static Optional<FavoriteItem> persistFavoriteItem5() {
        return Optional.of(new FavoriteItem("MLA816019442",25L));
    }

    public static Optional<FavoriteItem> persistFavoriteItem6() {
        return Optional.of(new FavoriteItem("MLA816019441",16L));
    }

    public static Optional<FavoriteItem> persistFavoriteItem7() {
        return Optional.of(new FavoriteItem("MLA816019443",2L));
    }

    public static List<FavoriteItem> persistTopItems() {
        List<FavoriteItem> topItems = new ArrayList<>();
        topItems.add(persistFavoriteItem2().orElse(null));
        topItems.add(persistFavoriteItem3().orElse(null));
        topItems.add(persistFavoriteItem5().orElse(null));
        topItems.add(persistFavoriteItem4().orElse(null));
        topItems.add(persistFavoriteItem6().orElse(null));
        return topItems;
    }

    public static ItemApiDto apiItem1() {
        return new ItemApiDto("MLA816019444", 1620F);
    }

    public static ItemApiDto apiItem2() {
        return new ItemApiDto("MLA811601011", 10000F);
    }

    public static ItemApiDto apiItem3() {
        return new ItemApiDto("MLA816019440", (float) 116352.03);
    }

    public static ItemApiDto apiItem4() {
        return new ItemApiDto("MLA811601010", 19699F);
    }

    public static ItemApiDto apiItem5() {
        return new ItemApiDto("MLA816019442", 8900F);
    }

    public static ItemApiDto apiItem6() {
        return new ItemApiDto("MLA816019441", (float) 248.04);
    }

    public static ItemApiDto apiItem7() {
        return new ItemApiDto("MLA816019443", 500F);
    }

    public static CouponResponseDto couponServiceResponse() {
        List items = new ArrayList();
        items.add("MLA811601010");
        items.add("MLA816019441");
        items.add("MLA816019443");
        return new CouponResponseDto(items, (float) 20447.04);
    }

    public static List<HashMap<String, Long>> listTopResponse() {
        List<HashMap<String, Long>> response = new ArrayList<>();
        HashMap<String, Long> resp = new HashMap<>();
        resp.put("MLA816019441", 31L);
        resp.put("MLA811601011", 29L);
        resp.put("MLA816019440", 23L);
        resp.put("MLA816019443", 21L);
        resp.put("MLA816019442", 16L);
        return response;
    }

}
