package com.andyg.SpringSecurityApp.service.vehicle;

import com.andyg.SpringSecurityApp.persistence.repository.VehicleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;



}
