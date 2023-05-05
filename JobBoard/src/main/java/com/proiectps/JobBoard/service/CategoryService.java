package com.proiectps.JobBoard.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proiectps.JobBoard.model.Category;
import com.proiectps.JobBoard.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public interface CategoryService {

    public List<Category> findAll();

    public Category findById(Long id) ;

    public Category save(Category category) ;

    public void deleteById(Long id) ;
    public Category findByName(String name) ;

    // Implement additional business logic here
}
