package com.andyg.SpringSecurityApp.service.vehicle;

import com.andyg.SpringSecurityApp.persistence.entity.vehicle.VehicleEntity;
import com.andyg.SpringSecurityApp.persistence.repository.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<VehicleEntity> getsListVehicle (){
        return vehicleRepository.findAll();
    }

    public VehicleEntity getsIdVehicle(long id){
        return vehicleRepository.findById(id).orElse(null);
    }

    public VehicleEntity createsVehicle(VehicleEntity vehicle){
        return vehicleRepository.save(vehicle);
    }

    public VehicleEntity updatesVehicle(long id, VehicleEntity vehicle){
        VehicleEntity updatedVehicle = vehicleRepository.findById(id).get();
        updatedVehicle.setVin(vehicle.getVin());
        updatedVehicle.setModel(vehicle.getModel());
        return vehicleRepository.save(vehicle);
    }

    public void deletesVehicle(long id){
        Optional<VehicleEntity> optionalVehicle = vehicleRepository.findById(id);
        if(optionalVehicle.isPresent()){
            VehicleEntity deletedVehicle = optionalVehicle.get();
            vehicleRepository.delete(deletedVehicle);
        } else {
            throw new EntityNotFoundException("Vehiculo no encontrado");
        }
    }
}
