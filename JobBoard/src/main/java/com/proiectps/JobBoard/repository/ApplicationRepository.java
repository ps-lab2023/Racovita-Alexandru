package com.proiectps.JobBoard.repository;

import com.proiectps.JobBoard.model.Application;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationRepository extends CrudRepository<Application, Long> {
    Iterable<Application> findByUserId(Long userId);
}
