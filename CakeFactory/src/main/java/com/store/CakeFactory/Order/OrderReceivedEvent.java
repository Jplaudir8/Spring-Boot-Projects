package com.store.CakeFactory.Order;

import com.store.CakeFactory.Basket.BasketItem;
import lombok.Data;

import java.util.Collection;

@Data
public class OrderReceivedEvent {

    private final String deliveryAddress;
    private final Collection<BasketItem> items;

    public OrderReceivedEvent(String deliveryAddress, Collection<BasketItem> items) {
        this.deliveryAddress = deliveryAddress;
        this.items = items;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public Collection<BasketItem> getItems() {
        return items;
    }
}