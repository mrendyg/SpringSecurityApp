package com.andyg.SpringSecurityApp.controller;

import com.andyg.SpringSecurityApp.persistence.entity.user.UserEntity;
import com.andyg.SpringSecurityApp.persistence.entity.vehicle.MarcaEntity;
import com.andyg.SpringSecurityApp.persistence.repository.MarcaRepository;
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
    private MarcaRepository marcaRepository;

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN') or hasRole('DEVELOPER')")
    public List<MarcaEntity> userList() {
        return marcaRepository.findAll();
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN') or hasRole('DEVELOPER')")
    public MarcaEntity getMarcaId (@PathVariable long id){
        return marcaRepository.findById(id).orElse(null);
    }


    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN') or hasRole('DEVELOPER')")
    public MarcaEntity createMarca(@RequestBody MarcaEntity marca){
        return marcaRepository.save(marca);
    }


    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasRole('ADMIN') or hasRole('DEVELOPER')")
    public MarcaEntity updateMarca(@PathVariable long id, @RequestBody MarcaEntity marca){
        MarcaEntity updatedMarca = marcaRepository.findById(id).get();
        updatedMarca.setName(marca.getName());
        updatedMarca.setDescription(marca.getDescription());
        return marcaRepository.save(updatedMarca);
    }

    /*
    //Borrado de usuarios
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('DEVELOPER')")
    public void deleteUser(@PathVariable long id){
        userService.deletesUser(id);
    }
     */

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('DEVELOPER')")
    public void deleteMarca(@PathVariable long id){
        Optional<MarcaEntity> optionalMarca = marcaRepository.findById(id);
        if (optionalMarca.isPresent()){
            MarcaEntity deletedMarca = optionalMarca.get();
            marcaRepository.delete(deletedMarca);
        } else {
            throw new EntityNotFoundException("Usuario no encontrado");
        }
    }
}
