package com.breadhardit.logistics.item.infrastructure.persistence.jpa.repository;

import com.breadhardit.logistics.item.application.port.ItemRepositoryPort;
import com.breadhardit.logistics.item.domain.Item;
import com.breadhardit.logistics.item.infrastructure.persistence.jpa.repository.entity.ItemEntity;
import com.breadhardit.logistics.item.infrastructure.persistence.jpa.repository.mapper.ItemEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ItemRepositoryAdapter implements ItemRepositoryPort {
    final ItemEntityMapper itemEntityMapper;
    final ItemMongoDBRepository itemMongoDBRepository;

    @Override
    public Optional<Item> getItemByName(String name) {
        List<ItemEntity> items = itemMongoDBRepository.findByName(name);
        return items.isEmpty() ? Optional.empty() : items.stream().findFirst().map(itemEntityMapper::toDomain);
    }

    @Override
    public List<Item> getItems() {
        return itemMongoDBRepository.findAll().stream().map(itemEntityMapper::toDomain).toList();
    }

    @Override
    public Optional<Item> getItemById(String id) {
        return itemMongoDBRepository.findById(id).map(itemEntityMapper::toDomain);
    }

    @Override
    public void deleteItem(String id) {
        itemMongoDBRepository.deleteById(id);
    }

    @Override
    public void updateItem(Item item) {
        itemMongoDBRepository.save(itemEntityMapper.fromDomain(item));
    }

    @Override
    public String createItem(Item item) {
        itemMongoDBRepository.save(itemEntityMapper.fromDomain(item));
        return item.getId();
    }
}
