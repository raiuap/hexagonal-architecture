package com.breadhardit.logistics.item.facade.dto.request;

import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@SuperBuilder
@Jacksonized
public class PostItemRequestDTO {
    final String name;
}
