package com.andyg.SpringSecurityApp.controller;

import com.andyg.SpringSecurityApp.persistence.entity.vehicle.ModelEntity;
import com.andyg.SpringSecurityApp.persistence.repository.ModelRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth/model")
@PreAuthorize("denyAll()")
public class ModelController {

    @Autowired
    private ModelRepository modelRepository;

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('READ')")
    public List<ModelEntity> modelList(){
        List<ModelEntity> list = modelRepository.findAll();
        list.sort(Comparator.comparing(ModelEntity::getId));
        return list;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('READ')")
    public ModelEntity getIdModel(@PathVariable Long id){
        return modelRepository.findById(id).orElse(null);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN') or hasRole('DEVELOPER')")
    public ModelEntity createModel(@RequestBody ModelEntity model){
        return modelRepository.save(model);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAuthority('UPDATE')")
    public ModelEntity updateModel(@PathVariable Long id, @RequestBody ModelEntity model){
        ModelEntity updatedModel = modelRepository.findById(id).get();
        updatedModel.setName(model.getName());
        updatedModel.setDescription(model.getDescription());
        updatedModel.setMarca(model.getMarca());
        return modelRepository.save(updatedModel);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('DELETE')")
    public void deleteModel(@PathVariable Long id){
        Optional<ModelEntity> optionalModel = modelRepository.findById(id);
        if (optionalModel.isPresent()){
            ModelEntity deletedModel = optionalModel.get();
            modelRepository.delete(deletedModel);
        } else {
            throw new EntityNotFoundException("Modelo no encontrado");
        }
    }




}
