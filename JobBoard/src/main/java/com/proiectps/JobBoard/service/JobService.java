package com.proiectps.JobBoard.service;

import com.proiectps.JobBoard.model.Job;
import com.proiectps.JobBoard.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface JobService {

    public List<Job> findAll();

    public Job findById(Long id) ;

    public Job save(Job job);

    public void deleteById(Long id);

    public List<Job> findByTitleContainingIgnoreCase(String title);
}
