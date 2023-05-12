package com.proiectps.JobBoard.repository;
import com.proiectps.JobBoard.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);  // Add this line

    List<User> findAll();
    User findByUsernameAndPassword(String username, String password);
}
