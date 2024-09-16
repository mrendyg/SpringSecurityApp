package com.andyg.SpringSecurityApp.controller.vehicle;

import com.andyg.SpringSecurityApp.persistence.entity.vehicle.VehicleEntity;
import com.andyg.SpringSecurityApp.persistence.repository.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth/vehicle")
@PreAuthorize("denyAll()")
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('READ')")
    @ResponseStatus(HttpStatus.OK)
    public List<VehicleEntity> getsListVehicle(){
        return vehicleRepository.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    @ResponseStatus(HttpStatus.OK)
    public VehicleEntity getIdVehicle(@PathVariable Long id){
        return vehicleRepository.findById(id).orElse(null);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE')")
    @ResponseStatus(HttpStatus.OK)
    public VehicleEntity createVehicle(@RequestBody VehicleEntity vehicle){
        return vehicleRepository.save(vehicle);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('DEVELOPER')")
    @ResponseStatus(HttpStatus.CREATED)
    public VehicleEntity updateVehicle(@RequestBody VehicleEntity vehicle, @PathVariable Long id){
        VehicleEntity updatedVehicle = vehicleRepository.findById(id).get();
        updatedVehicle.setVin(vehicle.getVin());
        updatedVehicle.setModel(vehicle.getModel());
        return vehicleRepository.save(vehicle);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('DEVELOPER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVehicle(@PathVariable Long id){
        Optional<VehicleEntity> optionalVehicle = vehicleRepository.findById(id);
        if(optionalVehicle.isPresent()){
            VehicleEntity deletedVehicle = optionalVehicle.get();
            vehicleRepository.delete(deletedVehicle);
        } else {
            throw new EntityNotFoundException("Vehiculo no encontrado");
        }
    }

}
