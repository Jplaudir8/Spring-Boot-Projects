package com.store.CakeFactory.persistence;

import com.store.CakeFactory.Catalog.persistence.CatalogServiceImpl;
import com.store.CakeFactory.Catalog.persistence.Item;
import com.store.CakeFactory.Catalog.persistence.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;

public class JpaCatalogServiceTest {
//    @Autowired
//    TestEntityManager testEntityManager;
//
//    @Autowired
//    ItemRepository itemRepository;
//
//    private CatalogServiceImpl jpaCatalogService;
//
//    @BeforeEach
//    void setup() {
//        this.jpaCatalogService = new CatalogServiceImpl(this.itemRepository);
//    }
//
//    @Test
//    @DisplayName("returns data from the database")
//    void returnsDataFromDatabase() {
//        String expectedTitle = "Victoria Sponge";
//        saveTestItem(expectedTitle, BigDecimal.valueOf(5.55));
//
//        Iterable<Item> items = jpaCatalogService.getItems();
//
//        org.assertj.core.api.Assertions.assertThat(items).anyMatch(item -> expectedTitle.equals(item.getTitle()));
//    }
//
//    private void saveTestItem(String title, BigDecimal price) {
//        Item itemEntity = new Item(title, price);
//        itemEntity.sku = "test-item-1";
//
//        testEntityManager.persistAndFlush(itemEntity);
//    }
}
