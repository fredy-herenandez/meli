package com.meli.technicalevaluation.coupon;

import com.meli.technicalevaluation.coupon.datatest.DataMock;
import com.meli.technicalevaluation.coupon.dto.CouponResponseDto;
import com.meli.technicalevaluation.coupon.model.entity.FavoriteItem;
import com.meli.technicalevaluation.coupon.model.repository.IFavoriteItemsRepository;
import com.meli.technicalevaluation.coupon.services.ICouponItemsService;
import com.meli.technicalevaluation.coupon.services.IItemGetApi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CouponItemsServiceTest {

    @MockBean
    IFavoriteItemsRepository repository;

    @MockBean
    IItemGetApi api;

    @Autowired
    ICouponItemsService service;

    @Test
    void updateTop() {

        when(repository.findById("MLA816019444")).thenReturn(DataMock.persistFavoriteItem1());
        when(repository.findById("MLA811601011")).thenReturn(DataMock.persistFavoriteItem2());
        when(repository.findById("MLA816019440")).thenReturn(DataMock.persistFavoriteItem3());
        when(repository.findById("MLA811601010")).thenReturn(DataMock.persistFavoriteItem4());
        when(repository.findById("MLA816019442")).thenReturn(DataMock.persistFavoriteItem5());
        when(repository.findById("MLA816019441")).thenReturn(DataMock.persistFavoriteItem6());
        when(repository.findById("MLA816019443")).thenReturn(DataMock.persistFavoriteItem7());

        when(api.setItem("MLA816019444")).thenReturn(DataMock.apiItem1());
        when(api.setItem("MLA811601011")).thenReturn(DataMock.apiItem2());
        when(api.setItem("MLA816019440")).thenReturn(DataMock.apiItem3());
        when(api.setItem("MLA811601010")).thenReturn(DataMock.apiItem4());
        when(api.setItem("MLA816019442")).thenReturn(DataMock.apiItem5());
        when(api.setItem("MLA816019441")).thenReturn(DataMock.apiItem6());
        when(api.setItem("MLA816019443")).thenReturn(DataMock.apiItem7());

        Set<String> itemsSet = new HashSet<>();
        itemsSet.add("MLA811601011");
        itemsSet.add("MLA811601010");
        itemsSet.add("MLA816019440");
        itemsSet.add("MLA816019441");
        itemsSet.add("MLA816019442");
        itemsSet.add("MLA816019443");

        CouponResponseDto couponOptimized = service.optimizeCoupon(itemsSet, 25000F);
        assertEquals(20447.04F, couponOptimized.getAmountTotal());

        verify(repository, times(1)).findById("MLA811601011");
        verify(repository, times(1)).findById("MLA811601010");
        verify(repository, times(1)).findById("MLA816019440");
        verify(repository, times(1)).findById("MLA816019441");
        verify(repository, times(1)).findById("MLA816019442");
        verify(repository, times(1)).findById("MLA816019443");

        FavoriteItem itm = repository.findById("MLA811601011").orElse(null);
        FavoriteItem itm2 = repository.findById("MLA811601010").orElse(null);
        FavoriteItem itm3 = repository.findById("MLA816019440").orElse(null);
        FavoriteItem itm4 = repository.findById("MLA816019441").orElse(null);
        FavoriteItem itm5 = repository.findById("MLA816019442").orElse(null);
        FavoriteItem itm6 = repository.findById("MLA816019443").orElse(null);

        assert itm != null;
        verify(repository, times(1)).save(itm);
        assert itm2 != null;
        verify(repository, times(1)).save(itm2);
        assert itm3 != null;
        verify(repository, times(1)).save(itm3);
        assert itm4 != null;
        verify(repository, times(1)).save(itm4);
        assert itm5 != null;
        verify(repository, times(1)).save(itm5);
        assert itm6 != null;
        verify(repository, times(1)).save(itm6);

    }

}
