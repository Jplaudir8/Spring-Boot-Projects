package com.store.CakeFactory.Catalog;

import com.store.CakeFactory.Basket.Basket;
import com.store.CakeFactory.Catalog.persistence.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class CatalogController {

    private CatalogService catalogService;
    private final Basket basket;

    CatalogController(CatalogService catalogService, Basket basket) {
        this.catalogService = catalogService;
        this.basket = basket;
    }

    @GetMapping("/")
    public ModelAndView landingPage() {
        return new ModelAndView("catalog", Map.of("items", this.catalogService.getItems(), "basketTotal", this.basket.getTotalItems()));
    }

}
