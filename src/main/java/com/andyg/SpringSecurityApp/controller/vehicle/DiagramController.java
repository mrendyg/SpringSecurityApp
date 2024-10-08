package com.andyg.SpringSecurityApp.controller.vehicle;

import com.andyg.SpringSecurityApp.persistence.entity.vehicle.DiagramEntity;
import com.andyg.SpringSecurityApp.persistence.repository.DiagramRepository;
import com.andyg.SpringSecurityApp.service.vehicle.DiagramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/diagram")
@PreAuthorize("denyAll()")
public class DiagramController {

    @Autowired
    private DiagramService diagramService;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('READ')")
    @ResponseStatus(HttpStatus.OK)
    public List<DiagramEntity> diagramList(){
        return diagramService.diagramList();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    @ResponseStatus(HttpStatus.OK)
    public DiagramEntity getDiagram(@PathVariable Long id){
        return diagramService.getsIdDiagram(id);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE')")
    @ResponseStatus(HttpStatus.CREATED)
    public DiagramEntity createDiagram(@RequestBody DiagramEntity diagram){
        return diagramService.createsDiagram(diagram);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public DiagramEntity updateDiagram(@RequestBody DiagramEntity diagram, @PathVariable Long id){
       return diagramService.updatesDiagram(id, diagram);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('DEVELOPER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDiagram(@PathVariable Long id){
       diagramService.deletesDiagram(id);
    }


}
