package com.meli.technicalevaluation.coupon;

import com.meli.technicalevaluation.coupon.datatest.DataMock;
import com.meli.technicalevaluation.coupon.model.repository.IFavoriteItemsRepository;
import com.meli.technicalevaluation.coupon.services.ITopItemsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class TopItemsServiceTest {

	@MockBean
	IFavoriteItemsRepository repository;

	@Autowired
	ITopItemsService topService;

	@Test
	void updateTop() {

		when(repository.findById("MLA816019444")).thenReturn(DataMock.persistFavoriteItem1());
		when(repository.findById("MLA811601011")).thenReturn(DataMock.persistFavoriteItem2());
		when(repository.findById("MLA816019440")).thenReturn(DataMock.persistFavoriteItem3());
		when(repository.findById("MLA811601010")).thenReturn(DataMock.persistFavoriteItem4());
		when(repository.findById("MLA816019442")).thenReturn(DataMock.persistFavoriteItem5());
		when(repository.findById("MLA816019441")).thenReturn(DataMock.persistFavoriteItem6());
		when(repository.findById("MLA816019443")).thenReturn(DataMock.persistFavoriteItem7());

		topService.updateTop("MLA816019444");
		topService.updateTop("MLA816019440");
		topService.updateTop("MLA811601010");
		topService.updateTop("MLA816019441");
		topService.updateTop("MLA816019443");

		assertEquals(12L, Objects.requireNonNull(repository.findById("MLA816019444").orElse(null)).getTimes());
		assertEquals(31L, Objects.requireNonNull(repository.findById("MLA811601011").orElse(null)).getTimes());
		assertEquals(32L, Objects.requireNonNull(repository.findById("MLA816019440").orElse(null)).getTimes());
		assertEquals(21L, Objects.requireNonNull(repository.findById("MLA811601010").orElse(null)).getTimes());
		assertEquals(25L, Objects.requireNonNull(repository.findById("MLA816019442").orElse(null)).getTimes());
		assertEquals(17L, Objects.requireNonNull(repository.findById("MLA816019441").orElse(null)).getTimes());
		assertEquals(3L, Objects.requireNonNull(repository.findById("MLA816019443").orElse(null)).getTimes());

		verify(repository, times(2)).findById("MLA816019444");
		verify(repository, times(1)).findById("MLA811601011");
		verify(repository, times(2)).findById("MLA816019440");
		verify(repository, times(2)).findById("MLA811601010");
		verify(repository, times(1)).findById("MLA816019442");
		verify(repository, times(2)).findById("MLA816019441");
		verify(repository, times(2)).findById("MLA816019443");

	}

	@Test
	void listTop() {

		when(repository.listTop()).thenReturn(DataMock.persistTopItems());

		List<HashMap<String, Long>> top = topService.listTop();
		assertFalse(top.isEmpty());
		assertEquals(5, top.size());

		verify(repository, times(1)).listTop();

	}

}
