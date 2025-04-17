package com.breadhardit.logistics.item.business.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Item {
    final String id;
    String name;
    Location location;
}
