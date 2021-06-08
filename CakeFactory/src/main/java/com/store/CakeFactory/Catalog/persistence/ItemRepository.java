package com.store.CakeFactory.Catalog.persistence;

import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, String> {
    Item findBySKU(String sku);
}
