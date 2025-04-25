package com.breadhardit.logistics.item.repository.mapper;

import com.breadhardit.logistics.item.business.model.Item;
import com.breadhardit.logistics.item.repository.entity.ItemEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemEntityMapper {
    ItemEntity fromDomain(Item in);

    Item toDomain(ItemEntity in);
}
