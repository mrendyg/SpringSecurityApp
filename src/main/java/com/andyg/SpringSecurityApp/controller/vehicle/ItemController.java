package com.andyg.SpringSecurityApp.controller.vehicle;

import com.andyg.SpringSecurityApp.persistence.entity.vehicle.ItemEntity;
import com.andyg.SpringSecurityApp.persistence.repository.ItemRepository;
import com.andyg.SpringSecurityApp.service.vehicle.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/item")
@PreAuthorize("denyAll()")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('READ')")
    public List<ItemEntity> itemList(){
        return itemService.getsListItem();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('READ')")
    public ItemEntity getItemId(@PathVariable Long id){
        return itemService.getsItem(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('CREATE')")
    public ItemEntity createItem(@RequestBody ItemEntity item){
        return itemService.createsItem(item);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasRole('ADMIN') or hasRole('DEVELOPER')")
    public ItemEntity updateItem(@PathVariable Long id, @RequestBody ItemEntity item){
        return itemService.updatesItem(id, item);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN') or hasRole('DEVELOPER')")
    public void deleteItem(@PathVariable Long id){
        itemService.deletesItem(id);
    }
}
