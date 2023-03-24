package com.proiectps.JobBoard.service;

import com.proiectps.JobBoard.model.Category;
import com.proiectps.JobBoard.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface CategoryService {

    public List<Category> findAll();

    public Category findById(Long id) ;

    public Category save(Category category) ;

    public void deleteById(Long id) ;
    public Category findByName(String name) ;

    // Implement additional business logic here
}
