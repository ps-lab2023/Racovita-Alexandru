package com.proiectps.JobBoard.service.impl;

import com.proiectps.JobBoard.model.Job;
import com.proiectps.JobBoard.repository.JobRepository;
import com.proiectps.JobBoard.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public List<Job> findAll() {
        return (List<Job>) jobRepository.findAll();
    }

    public Job findById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    public Job save(Job job) {
        return jobRepository.save(job);
    }

    public void deleteById(Long id) {
        jobRepository.deleteById(id);
    }

    public List<Job> findByTitleContainingIgnoreCase(String title) {
        return (List<Job>) jobRepository.findByTitleContainingIgnoreCase(title);
    }

    // Implement additional business logic here
}
