package com.meli.technicalevaluation.coupon.services;

import java.util.HashMap;
import java.util.List;

public interface ITopItemsService {

    List<HashMap<String, Long>> listTop();

    void updateTop (String itemId);

}