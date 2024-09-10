package com.andyg.SpringSecurityApp.controller;

import com.andyg.SpringSecurityApp.persistence.entity.vehicle.CategoryEntity;
import com.andyg.SpringSecurityApp.persistence.repository.CategoryRepository;
import com.andyg.SpringSecurityApp.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/auth/category")
@PreAuthorize("denyAll()")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    @PreAuthorize("hasRole('ADMIN') or hasRole('DEVELOPER')")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryEntity> categoryList (){
        return categoryService.getsCategoryList();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('DEVELOPER')")
    @ResponseStatus(HttpStatus.OK)
    public CategoryEntity getCategory(@PathVariable Long id){
        return categoryRepository.findById(id).orElse(null);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN') or hasRole('DEVELOPER')")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryEntity createCategory(@RequestBody CategoryEntity category){
        return categoryService.createsCategory(category);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('DEVELOPER')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CategoryEntity updateCategory(@PathVariable Long id, @RequestBody CategoryEntity category){
        CategoryEntity updatedCategory = categoryRepository.findById(id).get();
        updatedCategory.setName(category.getName());
        return categoryRepository.save(updatedCategory);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('DEVELOPER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory (@PathVariable Long id){
        Optional<CategoryEntity> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()){
            CategoryEntity deletedMarca = optionalCategory.get();
            categoryRepository.delete(deletedMarca);
        } else {
            throw new EntityNotFoundException("Categoria no encontrada");
        }
    }

}
