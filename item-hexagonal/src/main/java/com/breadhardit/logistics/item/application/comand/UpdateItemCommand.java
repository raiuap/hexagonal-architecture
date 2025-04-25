package com.breadhardit.logistics.item.application.comand;

import com.breadhardit.logistics.item.application.port.ItemRepositoryPort;
import com.breadhardit.logistics.item.domain.InvalidItemException;
import com.breadhardit.logistics.item.domain.Item;
import com.breadhardit.logistics.item.domain.ItemNotFoundException;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import org.springframework.util.StringUtils;

import java.util.UUID;

@SuperBuilder
public class UpdateItemCommand {
    @NonNull
    final ItemRepositoryPort itemRespository;
    @NonNull
    final String id;
    final String name;
    public void handle() throws ItemNotFoundException, InvalidItemException {
        if (!StringUtils.hasText(name))
            throw new InvalidItemException();
        try {
            UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new InvalidItemException();
        }
        if (itemRespository.getItemById(id).isEmpty())
            throw new ItemNotFoundException();
        itemRespository.updateItem(Item.builder().id(id).name(name).build());
    }
}
