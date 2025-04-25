package com.breadhardit.logistics.item.infrastructure.persistence.jpa.repository.mapper;

import com.breadhardit.logistics.item.domain.Item;
import com.breadhardit.logistics.item.infrastructure.persistence.jpa.repository.entity.ItemEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemEntityMapper {
    ItemEntity fromDomain(Item in);

    Item toDomain(ItemEntity in);
}
