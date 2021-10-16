package com.meli.technicalevaluation.coupon.model.repository;

import com.meli.technicalevaluation.coupon.model.entity.FavoriteItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IFavoriteItemsRepository extends JpaRepository<FavoriteItem, String>{

    @Query(value = "SELECT * FROM favorite_items fi order by 2 DESC LIMIT 5;",nativeQuery = true)
    List<FavoriteItem> listTop();

}