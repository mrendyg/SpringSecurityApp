package com.andyg.SpringSecurityApp.service;

import com.andyg.SpringSecurityApp.persistence.entity.vehicle.MarcaEntity;
import com.andyg.SpringSecurityApp.persistence.repository.MarcaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class MarcasService {

    @Autowired
    private MarcaRepository marcaRepository;

    public List<MarcaEntity> getsMarcaList(){
        List<MarcaEntity> list = marcaRepository.findAll();
        list.sort(Comparator.comparing(MarcaEntity::getId));
        return list;
    }

    public MarcaEntity getsIdMarca(long id){
        return marcaRepository.findById(id).orElse(null);
    }

    public MarcaEntity createsMarca(MarcaEntity marca){
        return marcaRepository.save(marca);
    }

    public MarcaEntity updatesMarca(long id, MarcaEntity marca){
        MarcaEntity updatedMarca = marcaRepository.findById(id).get();
        updatedMarca.setName(marca.getName());
        updatedMarca.setDescription(marca.getDescription());
        return marcaRepository.save(updatedMarca);
    }
    public void deletesMarca(long id){
        Optional<MarcaEntity> optionalMarca = marcaRepository.findById(id);
        if (optionalMarca.isPresent()){
            MarcaEntity deletedMarca = optionalMarca.get();
            marcaRepository.delete(deletedMarca);
        } else {
            throw new EntityNotFoundException("Marca no encontrada");
        }
    }

}
