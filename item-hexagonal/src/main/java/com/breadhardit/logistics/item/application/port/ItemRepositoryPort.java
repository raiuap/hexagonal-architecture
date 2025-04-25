package com.breadhardit.logistics.item.application.port;

import com.breadhardit.logistics.item.domain.Item;

import java.util.List;
import java.util.Optional;

public interface ItemRepositoryPort {
    List<Item> getItems();
    Optional<Item> getItemById(String id);
    void deleteItem(String id);
    void updateItem(Item item);
    String createItem(Item item);
}
