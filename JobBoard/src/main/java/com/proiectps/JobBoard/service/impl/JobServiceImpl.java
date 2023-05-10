package com.proiectps.JobBoard.service.impl;

import com.proiectps.JobBoard.ResourceNotFoundException;
import com.proiectps.JobBoard.model.Job;
import com.proiectps.JobBoard.model.User;
import com.proiectps.JobBoard.repository.JobRepository;
import com.proiectps.JobBoard.repository.UserRepository;
import com.proiectps.JobBoard.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;
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
    /*
    public Job bookmarkJob(Long userId, Long jobId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new ResourceNotFoundException("Job not found"));

        if (user.getBookmarkedJobs().contains(job)) {
            // If the job is already bookmarked, unbookmark it
            user.getBookmarkedJobs().remove(job);
            job.getBookmarkedByUsers().remove(user);
        } else {
            // Otherwise, bookmark the job
            user.getBookmarkedJobs().add(job);
            job.getBookmarkedByUsers().add(user);
        }

        userRepository.save(user);
        return jobRepository.save(job);
    }*/
    // Implement additional business logic here
}
