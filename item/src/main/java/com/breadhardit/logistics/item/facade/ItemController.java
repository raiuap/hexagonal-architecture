package com.breadhardit.logistics.item.facade;

import com.breadhardit.logistics.item.facade.dto.request.PatchItemRequestDTO;
import com.breadhardit.logistics.item.facade.dto.request.PostItemRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    @GetMapping
    public ResponseEntity getAllItems() {
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity createItem(@RequestBody PostItemRequestDTO itemDto) {
        String id = UUID.randomUUID().toString();
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri()).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getItemById(@PathVariable Long id) {
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateItem(
            @PathVariable Long id,
            @RequestBody PatchItemRequestDTO itemDto) {
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteItem(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}