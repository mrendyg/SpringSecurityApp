package com.andyg.SpringSecurityApp.controller;

import com.andyg.SpringSecurityApp.persistence.entity.user.UserEntity;
import com.andyg.SpringSecurityApp.persistence.entity.vehicle.MarcaEntity;
import com.andyg.SpringSecurityApp.persistence.repository.MarcaRepository;
import com.andyg.SpringSecurityApp.service.MarcasService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth/marcas")
@PreAuthorize("denyAll()")
public class MarcasController {

    @Autowired
    private MarcasService marcasService;

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN') or hasRole('DEVELOPER')")
    public List<MarcaEntity> marcaList() {
        return marcasService.getsMarcaList();
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN') or hasRole('DEVELOPER')")
    public MarcaEntity getMarcaId (@PathVariable long id){
        return marcasService.getsIdMarca(id);
    }


    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN') or hasRole('DEVELOPER')")
    public MarcaEntity createMarca(@RequestBody MarcaEntity marca){
        return marcasService.createsMarca(marca);
    }


    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasRole('ADMIN') or hasRole('DEVELOPER')")
    public MarcaEntity updateMarca(@PathVariable long id, @RequestBody MarcaEntity marca){
        return marcasService.updatesMarca(id, marca);
    }


    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('DEVELOPER')")
    public void deleteMarca(@PathVariable long id){
        marcasService.deletesMarca(id);
    }
}
