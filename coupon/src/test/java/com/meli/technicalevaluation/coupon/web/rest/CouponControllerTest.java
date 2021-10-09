package com.meli.technicalevaluation.coupon.web.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.technicalevaluation.coupon.datatest.DataMock;
import com.meli.technicalevaluation.coupon.dto.CouponRequestDto;
import com.meli.technicalevaluation.coupon.services.ICouponItemsService;
import com.meli.technicalevaluation.coupon.services.ITopItemsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.anySet;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.when;

@WebMvcTest(CouponController.class)
@ActiveProfiles("test")
class CouponControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ICouponItemsService couponService;

    @MockBean
    private ITopItemsService TopService;

    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void optimizeClientCoupon() throws Exception {

        Set<String> itemsSet = new HashSet<>();
        itemsSet.add("MLA811601011");
        itemsSet.add("MLA811601010");
        itemsSet.add("MLA816019440");
        itemsSet.add("MLA816019441");
        itemsSet.add("MLA816019442");
        itemsSet.add("MLA816019443");

        when(couponService.optimizeCoupon(anySet(),anyFloat())).thenReturn(DataMock.couponServiceResponse());

        CouponRequestDto request = new CouponRequestDto();
        request.setItemIds(itemsSet);
        request.setAmount(25000F);

        mvc.perform(post("/").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.item_ids.[0]").value("MLA811601010"))
                .andExpect(jsonPath("$.item_ids.[1]").value("MLA816019441"))
                .andExpect(jsonPath("$.item_ids.[2]").value("MLA816019443"))
                .andExpect(jsonPath("$.total").value(20447.04));

    }

    @Test
    void topFavoritesItems() throws Exception {

        when(TopService.listTop()).thenReturn(DataMock.listTopResponse());

        mvc.perform(get("/stats").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(TopService.listTop())));
    }
}