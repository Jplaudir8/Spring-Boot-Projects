package com.store.CakeFactory.Catalog.persistence;

import com.store.CakeFactory.Catalog.persistence.Item;

public interface CatalogService {
    Iterable<Item> getItems();
}
