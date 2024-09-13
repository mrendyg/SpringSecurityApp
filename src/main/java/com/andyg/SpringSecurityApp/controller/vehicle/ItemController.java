package com.andyg.SpringSecurityApp.controller.vehicle;

import com.andyg.SpringSecurityApp.persistence.entity.vehicle.CategoryEntity;
import com.andyg.SpringSecurityApp.persistence.entity.vehicle.ItemEntity;
import com.andyg.SpringSecurityApp.persistence.repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth/item")
@PreAuthorize("denyAll()")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('READ')")
    public List<ItemEntity> itemList(){
        return itemRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('READ')")
    public ItemEntity getItemId(@PathVariable Long id){
        return itemRepository.findById(id).orElse(null);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CREATE')")
    public ItemEntity createItem(@RequestBody ItemEntity item){
        return itemRepository.save(item);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAuthority('CREATE')")
    public ItemEntity updateItem(@PathVariable Long id, @RequestBody ItemEntity item){
        ItemEntity updatedItem = itemRepository.findById(id).get();
        updatedItem.setCodigo(item.getCodigo());
        updatedItem.setDescription(item.getDescription());
        updatedItem.setImage(item.getImage());
        updatedItem.setDiagrams(item.getDiagrams());
        return itemRepository.save(updatedItem);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN') or hasRole('DEVELOPER')")
    public void deleteItem(@PathVariable Long id){
        Optional<ItemEntity> optionalItem = itemRepository.findById(id);
        if (optionalItem.isPresent()){
            ItemEntity deletedItem = optionalItem.get();
            itemRepository.delete(deletedItem);
        } else {
            throw new EntityNotFoundException("Item no encontrado");
        }
    }
}
