package com.breadhardit.logistics.item.infrastructure.rest.mapper;

import com.breadhardit.logistics.item.domain.Item;
import com.breadhardit.logistics.item.infrastructure.rest.dto.request.PostItemRequestDTO;
import com.breadhardit.logistics.item.infrastructure.rest.dto.response.GetItemResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemFacadeMapper {
    GetItemResponseDTO fromDomain(Item in);
    Item fromPostItemRequestDTO(PostItemRequestDTO itemDto);
}
