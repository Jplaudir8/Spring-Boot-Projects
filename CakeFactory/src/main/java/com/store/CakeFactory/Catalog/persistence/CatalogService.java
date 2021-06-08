package com.store.CakeFactory.Catalog.persistence;

public interface CatalogService {
    Iterable<Item> getItems();

    Item getItemBySKU(String sku);
}
