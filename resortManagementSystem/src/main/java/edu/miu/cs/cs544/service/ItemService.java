package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.DTO.ItemDTO;
import edu.miu.cs.cs544.domain.Item;
import edu.miu.cs.cs544.repository.ItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ModelMapper modelMapper; // Example: Using ModelMapper for mapping

    public ItemDTO getItemById(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Item not found"));
        return convertToDTO(item);
    }

    public List<ItemDTO> getAllItemsForReservation(Long reservationId) {
        List<Item> items = itemRepository.findByReservationId(reservationId);
        return items.stream()
                .map(item -> modelMapper.map(item, ItemDTO.class))
                .collect(Collectors.toList());
    }

    public ItemDTO createItem(ItemDTO itemDTO) {
        Item item = convertToEntity(itemDTO);
        // Perform validations or additional logic before saving
        Item savedItem = itemRepository.save(item);
        return convertToDTO(savedItem);
    }

    public ItemDTO updateItem(Long reservationId, Long itemId, ItemDTO updatedItemDTO) {
        Optional<Item> optionalItem = itemRepository.findByIdAndReservationId(itemId, reservationId);
        if (optionalItem.isPresent()) {
            Item existingItem = optionalItem.get();

            // Update existing item fields
//            existingItem.setName(updatedItemDTO.getName());
//            existingItem.setDescription(updatedItemDTO.getDescription());
//            existingItem.setNightlyRate(updatedItemDTO.getNightlyRate());
            existingItem.setNumberOfOccupants(updatedItemDTO.getNumberOfOccupants());
            // Update other fields as needed

            // Save the updated item
            Item updatedItem = itemRepository.save(existingItem);
            return modelMapper.map(updatedItem, ItemDTO.class);
        } else {
            return null; // Handle the case when the item or reservation is not found
        }
    }

    public boolean deleteItem(Long reservationId, Long itemId) {
        Optional<Item> optionalItem = itemRepository.findByIdAndReservationId(itemId, reservationId);
        if (optionalItem.isPresent()) {
            Item itemToDelete = optionalItem.get();
            itemRepository.delete(itemToDelete);
            return true;
        } else {
            return false; // Handle the case when the item or reservation is not found
        }
    }

    private ItemDTO convertToDTO(Item item) {
        return modelMapper.map(item, ItemDTO.class);
    }

    private Item convertToEntity(ItemDTO itemDTO) {
        return modelMapper.map(itemDTO, Item.class);
    }
}