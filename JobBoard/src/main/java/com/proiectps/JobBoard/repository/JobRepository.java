package com.proiectps.JobBoard.repository;

import com.proiectps.JobBoard.model.Company;
import com.proiectps.JobBoard.model.Job;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends CrudRepository<Job, Long> {
    Iterable<Job> findByTitleContainingIgnoreCase(String title);
    List<Job> findTop10ByOrderByPostedAtDesc();

}
