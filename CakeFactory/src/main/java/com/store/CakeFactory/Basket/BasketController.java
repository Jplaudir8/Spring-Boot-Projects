package com.store.CakeFactory.Basket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/basket")
public class BasketController {

    private final Basket basket;

    BasketController(Basket basket) {
        this.basket = basket;
    }

    @GetMapping("/basket")
    public ModelAndView basketPage() {
        return new ModelAndView("basket");
    }
}
