package com.proiectps.JobBoard.service;

import com.proiectps.JobBoard.model.Application;
import com.proiectps.JobBoard.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface ApplicationService {

    public List<Application> findAll() ;

    public Application findById(Long id) ;
    public Application save(Application application);

    public void deleteById(Long id) ;

    public List<Application> findByUserId(Long userId) ;

}
