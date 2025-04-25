package com.breadhardit.logistics.item.application.query;

import com.breadhardit.logistics.item.application.port.ItemRepositoryPort;
import com.breadhardit.logistics.item.domain.Item;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

import java.util.Optional;

@SuperBuilder
public class GetItemByIdQuery {
    @NonNull
    final ItemRepositoryPort itemRepository;
    @NonNull
    final String id;
    public Optional<Item> execute() {
        return itemRepository.getItemById(id);
    }
}
