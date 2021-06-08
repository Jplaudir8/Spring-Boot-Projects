package com.store.CakeFactory.Order;

import com.store.CakeFactory.Basket.BasketItem;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SimpleOrderReceiver {

    @EventListener
    public void onNewOrder(OrderReceivedEvent event) {
        System.out.println("New order received:");
        System.out.println("Delivery address " + event.getDeliveryAddress());
        for (BasketItem basketItem : event.getItems()) {
            System.out.println(basketItem.getItem().getTitle() + " - " + basketItem.getQty());
        }
    }
}
