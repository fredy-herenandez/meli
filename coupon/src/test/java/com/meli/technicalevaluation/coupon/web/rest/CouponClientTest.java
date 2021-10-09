package com.meli.technicalevaluation.coupon.web.rest;

import com.meli.technicalevaluation.coupon.dto.CouponRequestDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CouponClientTest {

    @Autowired
    private TestRestTemplate client;

    @LocalServerPort
    private int port;

    @Test
    @Order(1)
    void optimizeClientCoupon() {
        Set<String> itemsSet = new HashSet<>();
        itemsSet.add("MLA811601011");
        itemsSet.add("MLA811601010");
        itemsSet.add("MLA816019440");
        itemsSet.add("MLA816019441");
        itemsSet.add("MLA816019442");
        itemsSet.add("MLA816019443");

        CouponRequestDto request = new CouponRequestDto();
        request.setItemIds(itemsSet);
        request.setAmount(25000F);

        ResponseEntity<String> response = client.postForEntity(createEndPoint("/"), request, String.class);

        String json = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        assert json != null;
        assertTrue(json.contains("{\"item_ids\":[\"MLA811601010\",\"MLA816019441\",\"MLA816019443\"],\"total\":20447.04}"));

    }

    @Test
    @Order(2)
    void topFavoritesItems() {
        ResponseEntity<String> response = client.getForEntity(createEndPoint("/stats"), String.class);

        String json = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        assert json != null;
        assertTrue(json.contains("[{\"MLA816019440\":32},{\"MLA811601011\":32},{\"MLA816019442\":26},{\"MLA811601010\":21},{\"MLA816019441\":17}]"));

    }

    private String createEndPoint(String uri) {
        return "http://localhost:" + port + uri;
    }

}