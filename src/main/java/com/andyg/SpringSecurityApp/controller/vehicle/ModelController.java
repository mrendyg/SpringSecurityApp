package com.andyg.SpringSecurityApp.controller.vehicle;

import com.andyg.SpringSecurityApp.persistence.entity.vehicle.ModelEntity;
import com.andyg.SpringSecurityApp.persistence.repository.ModelRepository;
import com.andyg.SpringSecurityApp.service.vehicle.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/model")
@PreAuthorize("denyAll()")
public class ModelController {

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private ModelService modelService;

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('READ')")
    public List<ModelEntity> modelList(){
        return modelService.getsListModel();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('READ')")
    public ModelEntity getIdModel(@PathVariable Long id){
        return modelService.getsIdModel(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN') or hasRole('DEVELOPER')")
    public ModelEntity createModel(@RequestBody ModelEntity model){
        return modelService.createsModel(model);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAuthority('UPDATE')")
    public ModelEntity updateModel(@PathVariable Long id, @RequestBody ModelEntity model){
        return modelService.updatesModel(id, model);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('DEVELOPER') or hasRole('ADMIN')")
    public void deleteModel(@PathVariable Long id){
        modelService.deletesModel(id);
    }




}
