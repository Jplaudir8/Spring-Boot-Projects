package com.store.CakeFactory.Catalog;

import com.store.CakeFactory.Catalog.persistence.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping("/")
    public ModelAndView landingPage() {
        return new ModelAndView("index", Map.of("items", this.catalogService.getItems()));
    }
}
