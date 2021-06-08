package com.store.CakeFactory.Catalog.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private ItemRepository itemRepository;

    public CatalogServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Iterable<Item> getItems() {
        return StreamSupport.stream(itemRepository.findAll().spliterator(), false)
                .map(entity -> new Item(entity.title, entity.price))
                .collect(Collectors.toList());
    }

    @Override
    public Item getItemBySKU(String sku) {
        Item entity = itemRepository.findBySKU(sku);
        if (entity == null) {
            return null;
        }
        return mapEntity(entity);
    }

    Item mapEntity(Item entity) {
        return new Item(entity.sku, entity.title, entity.price);
    }

}
