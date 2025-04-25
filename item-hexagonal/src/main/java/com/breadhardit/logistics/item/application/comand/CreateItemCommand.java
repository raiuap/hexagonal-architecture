package com.breadhardit.logistics.item.application.comand;

import com.breadhardit.logistics.item.application.port.ItemRepositoryPort;
import com.breadhardit.logistics.item.domain.InvalidItemException;
import com.breadhardit.logistics.item.domain.Item;
import lombok.Builder;
import lombok.NonNull;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Builder
public class CreateItemCommand {
    @NonNull
    final ItemRepositoryPort itemRepository;
    final String name;

    public String handle() throws InvalidItemException {
        if (!StringUtils.hasText(name)) throw new InvalidItemException();
        Item item = Item.builder().id(UUID.randomUUID().toString()).name(name).build();
        itemRepository.createItem(item);
        return item.getId();
    }
}
