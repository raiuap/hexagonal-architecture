package com.breadhardit.logistics.item.infrastructure.rest;

import com.breadhardit.logistics.item.application.comand.CreateItemCommand;
import com.breadhardit.logistics.item.application.comand.DeleteItemCommand;
import com.breadhardit.logistics.item.application.comand.UpdateItemCommand;
import com.breadhardit.logistics.item.application.port.ItemRepositoryPort;
import com.breadhardit.logistics.item.application.query.GetAllItemsQuery;
import com.breadhardit.logistics.item.application.query.GetItemByIdQuery;
import com.breadhardit.logistics.item.domain.InvalidItemException;
import com.breadhardit.logistics.item.domain.ItemNotFoundException;
import com.breadhardit.logistics.item.domain.Item;
import com.breadhardit.logistics.item.infrastructure.rest.mapper.ItemFacadeMapper;
import com.breadhardit.logistics.item.infrastructure.rest.dto.request.PatchItemRequestDTO;
import com.breadhardit.logistics.item.infrastructure.rest.dto.request.PostItemRequestDTO;
import com.breadhardit.logistics.item.infrastructure.rest.dto.response.GetItemResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    final ItemFacadeMapper itemFacadeMapper;
    final ItemRepositoryPort itemRepositoryPort;
    @GetMapping
    public ResponseEntity getAllItems() {
        List<Item> items = GetAllItemsQuery.builder().itemRepository(itemRepositoryPort).build().execute();
        if (items.isEmpty()) return ResponseEntity.noContent().build();
        List<GetItemResponseDTO> itemsResponse = items.stream().map(itemFacadeMapper::fromDomain).toList();
        return ResponseEntity.ok(itemsResponse);
    }

    @PostMapping
    public ResponseEntity createItem(@RequestBody PostItemRequestDTO itemDto) {
        try {
            String id = CreateItemCommand.builder()
                    .itemRepository(itemRepositoryPort)
                    .name(itemDto.getName())
                    .build().handle();
            return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri()).build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (InvalidItemException e) {
            return ResponseEntity.unprocessableEntity().build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity getItemById(@PathVariable String id) {
        Optional<Item> item = GetItemByIdQuery.builder().itemRepository(itemRepositoryPort).id(id).build().execute();
        return item.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(item.get());
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateItem(
            @PathVariable String id,
            @RequestBody PatchItemRequestDTO itemDto) {
        try {
            UpdateItemCommand.builder().itemRespository(itemRepositoryPort)
                    .id(id)
                    .name(itemDto.getName())
                    .build().handle();
        } catch (ItemNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (InvalidItemException e) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteItem(@PathVariable String id) {
        try {
            DeleteItemCommand.builder().itemRepository(itemRepositoryPort).id(id).build().handle();
        } catch (InvalidItemException e) {
            return ResponseEntity.unprocessableEntity().build();
        } catch (ItemNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}