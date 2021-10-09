package com.meli.technicalevaluation.coupon;

import com.meli.technicalevaluation.coupon.model.entity.FavoriteItem;
import com.meli.technicalevaluation.coupon.model.repository.IFavoriteItemsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
public class JpaIntegrationTest {

    @Autowired
    IFavoriteItemsRepository repository;

    @Test
    void testFavoriteItemFindById() {
        Optional<FavoriteItem> item = repository.findById("MLA816019444");
        assertTrue(item.isPresent());
        assertEquals(11L, item.orElse(null).getTimes());
    }

    @Test
    void testFavoriteItemFindAll() {
        List<FavoriteItem> allItem = repository.findAll();
        assertFalse(allItem.isEmpty());
        assertEquals(7, allItem.size());
    }

    @Test
    void testFavoriteItemListTop() {
        List<FavoriteItem> top = repository.listTop();
        assertFalse(top.isEmpty());
        assertEquals(5, top.size());
    }

    @Test
    void testFavoriteItemSave() {
        FavoriteItem newItemTop = new FavoriteItem("TEST16019888",1L);
        FavoriteItem itemTop = repository.save(newItemTop);
        assertEquals("TEST16019888", itemTop.getId());
        assertEquals(1L, itemTop.getTimes());
    }

    @Test
    void testFavoriteItemUpdate() {
        FavoriteItem item = repository.findById("MLA816019444").orElse(null);
        assertNotNull(item);
        assertEquals(11L, item.getTimes());
        item.setTimes(item.getTimes() + 1);
        FavoriteItem itemUpdated = repository.save(item);
        assertEquals(12L, itemUpdated.getTimes());
    }

}
