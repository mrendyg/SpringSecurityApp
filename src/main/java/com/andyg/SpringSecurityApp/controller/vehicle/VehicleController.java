package com.andyg.SpringSecurityApp.controller.vehicle;

import com.andyg.SpringSecurityApp.persistence.entity.vehicle.VehicleEntity;
import com.andyg.SpringSecurityApp.persistence.repository.VehicleRepository;
import com.andyg.SpringSecurityApp.service.vehicle.VehicleService;
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
    private VehicleService vehicleService;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('READ')")
    @ResponseStatus(HttpStatus.OK)
    public List<VehicleEntity> getListVehicle(){
        return vehicleService.getsListVehicle();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    @ResponseStatus(HttpStatus.OK)
    public VehicleEntity getIdVehicle(@PathVariable Long id){
        return vehicleService.getsIdVehicle(id);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE')")
    @ResponseStatus(HttpStatus.CREATED)
    public VehicleEntity createVehicle(@RequestBody VehicleEntity vehicle){
        return vehicleService.createsVehicle(vehicle);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('DEVELOPER')")
    @ResponseStatus(HttpStatus.CREATED)
    public VehicleEntity updateVehicle(@PathVariable Long id, @RequestBody VehicleEntity vehicle){
        return vehicleService.updatesVehicle(id, vehicle);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('DEVELOPER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVehicle(@PathVariable Long id){
        vehicleService.deletesVehicle(id);
    }

}
