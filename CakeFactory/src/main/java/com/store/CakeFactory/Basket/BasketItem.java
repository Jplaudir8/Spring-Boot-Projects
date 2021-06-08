package com.store.CakeFactory.Basket;

import com.store.CakeFactory.Catalog.persistence.Item;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BasketItem {
    public Item item;
    public int qty;

    public BasketItem(Item item, int qty) {
        this.item = item;
        this.qty = qty;
    }

    BigDecimal getTotal() {
        return this.item.getPrice().multiply(BigDecimal.valueOf(qty));
    }

    public Item getItem() {
        return item;
    }

    public int getQty() {
        return qty;
    }
}
