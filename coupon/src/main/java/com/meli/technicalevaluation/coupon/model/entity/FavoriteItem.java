package com.meli.technicalevaluation.coupon.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "favorite_items")
public class FavoriteItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "item_id")
    private String id;

    private Long times;
}