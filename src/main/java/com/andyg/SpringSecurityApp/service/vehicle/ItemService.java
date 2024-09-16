package com.andyg.SpringSecurityApp.service.vehicle;

import com.andyg.SpringSecurityApp.persistence.entity.vehicle.ItemEntity;
import com.andyg.SpringSecurityApp.persistence.repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<ItemEntity> getsListItem (){
        return itemRepository.findAll();
    }

    public ItemEntity getsItem(long id){
        return itemRepository.findById(id).orElse(null);
    }

    public ItemEntity createsItem(ItemEntity item){
        return itemRepository.save(item);
    }

    public ItemEntity updatesItem(long id, ItemEntity item){
        ItemEntity updatedItem = itemRepository.findById(id).get();
        updatedItem.setCodigo(item.getCodigo());
        updatedItem.setDescription(item.getDescription());
        updatedItem.setImage(item.getImage());
        updatedItem.setDiagrams(item.getDiagrams());
        return itemRepository.save(updatedItem);
    }

    public void deletesItem(long id){
        Optional<ItemEntity> optionalItem = itemRepository.findById(id);
        if (optionalItem.isPresent()){
            ItemEntity deletedItem = optionalItem.get();
            itemRepository.delete(deletedItem);
        } else {
            throw new EntityNotFoundException("Item no encontrado");
        }
    }

}
