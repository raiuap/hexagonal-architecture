package com.breadhardit.logistics.item.facade.dto.response;

import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@SuperBuilder
@Jacksonized
public class GetItemResponseDTO {
    final String id;
    final String name;
}
