package com.breadhardit.logistics.item.application.query;

import com.breadhardit.logistics.item.application.port.ItemRepositoryPort;
import com.breadhardit.logistics.item.domain.Item;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

import java.util.List;
@SuperBuilder
public class GetAllItemsQuery {
    @NonNull
    private ItemRepositoryPort itemRepository;
    public List<Item> execute() {
        return itemRepository.getItems();
    }
}
