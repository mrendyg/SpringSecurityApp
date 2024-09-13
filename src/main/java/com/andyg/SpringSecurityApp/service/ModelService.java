package com.andyg.SpringSecurityApp.service;

import com.andyg.SpringSecurityApp.persistence.entity.vehicle.ModelEntity;
import com.andyg.SpringSecurityApp.persistence.repository.ModelRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ModelService {

    @Autowired
    private ModelRepository modelRepository;

    public List<ModelEntity> getsListModel(){
        List<ModelEntity> list = modelRepository.findAll();
        list.sort(Comparator.comparing(ModelEntity::getId));
        return list;
    }

    public ModelEntity getsIdModel(long id){
        return modelRepository.findById(id).orElse(null);
    }

    public ModelEntity createsModel(ModelEntity model){
        return modelRepository.save(model);
    }

    public ModelEntity updatesModel(long id, ModelEntity model){
        ModelEntity updatedModel = modelRepository.findById(id).get();
        updatedModel.setName(model.getName());
        updatedModel.setDescription(model.getDescription());
        updatedModel.setMarca(model.getMarca());
        return modelRepository.save(updatedModel);
    }

    public void deletesModel(long id){
        Optional<ModelEntity> optionalModel = modelRepository.findById(id);
        if (optionalModel.isPresent()){
            ModelEntity deletedModel = optionalModel.get();
            modelRepository.delete(deletedModel);
        } else {
            throw new EntityNotFoundException("Modelo no encontrado");
        }
    }

}
