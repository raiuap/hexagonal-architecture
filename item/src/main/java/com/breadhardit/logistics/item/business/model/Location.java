package com.breadhardit.logistics.item.business.model;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class Location {
    final String id;
    final Integer itemsCapacity;
    String name;
    Integer containingItems;
}
