package com.meli.technicalevaluation.coupon.model.repository;

import com.meli.technicalevaluation.coupon.model.entity.FavoriteItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IFavoriteItemsRepository extends JpaRepository<FavoriteItem, String>{

    @Query(value = "select * from favorite_items fi order by times desc fetch first 5 rows only",nativeQuery = true)
    List<FavoriteItem> listTop();

}