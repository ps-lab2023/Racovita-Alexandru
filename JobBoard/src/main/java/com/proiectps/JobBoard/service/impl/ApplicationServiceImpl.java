package com.proiectps.JobBoard.service.impl;

import com.proiectps.JobBoard.model.Application;
import com.proiectps.JobBoard.repository.ApplicationRepository;
import com.proiectps.JobBoard.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public List<Application> findAll() {
        return (List<Application>) applicationRepository.findAll();
    }

    public Application findById(Long id) {
        return applicationRepository.findById(id).orElse(null);
    }

    public Application save(Application application) {
        return applicationRepository.save(application);
    }

    public void deleteById(Long id) {
        applicationRepository.deleteById(id);
    }

    public List<Application> findByUserId(Long userId) {
        return (List<Application>) applicationRepository.findByUserId(userId);
    }

    // Implement additional business logic here
}
