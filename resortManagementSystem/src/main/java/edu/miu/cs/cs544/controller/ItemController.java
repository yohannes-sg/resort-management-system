package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.DTO.ItemDTO;
import edu.miu.cs.cs544.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> getItemById(@PathVariable Long id) {
        ItemDTO itemDTO = itemService.getItemById(id);
        return ResponseEntity.ok(itemDTO);
    }

    @GetMapping
    public ResponseEntity<List<ItemDTO>> getAllItems(@PathVariable Long reservationId) {
        List<ItemDTO> items = itemService.getAllItemsForReservation(reservationId);
        return ResponseEntity.ok(items);
    }

    @PostMapping("/create")
    public ResponseEntity<ItemDTO> createItem(@RequestBody ItemDTO itemDTO) {
        ItemDTO createdItem = itemService.createItem(itemDTO);
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<ItemDTO> updateItem(
            @PathVariable Long reservationId, @PathVariable Long itemId, @RequestBody ItemDTO itemDTO) {
        ItemDTO updatedItem = itemService.updateItem(reservationId, itemId, itemDTO);
        if (updatedItem != null) {
            return ResponseEntity.ok(updatedItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<String> deleteItem(
            @PathVariable Long reservationId, @PathVariable Long itemId) {
        boolean isDeleted = itemService.deleteItem(reservationId, itemId);
        if (isDeleted) {
            return ResponseEntity.ok("Item with ID: " + itemId + " has been deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item with ID: " + itemId + " not found.");
        }
    }

    // Other endpoints for managing items...
}