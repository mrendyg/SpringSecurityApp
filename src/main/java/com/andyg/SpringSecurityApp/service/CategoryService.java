package com.andyg.SpringSecurityApp.service;

import com.andyg.SpringSecurityApp.persistence.entity.vehicle.CategoryEntity;
import com.andyg.SpringSecurityApp.persistence.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryEntity> getsCategoryList(){
        return categoryRepository.findAll();
    }

    public CategoryEntity getsIdCategory(long id){
        return categoryRepository.findById(id).orElse(null);
    }

    public CategoryEntity createsCategory(CategoryEntity category){
        return categoryRepository.save(category);
    }
}
