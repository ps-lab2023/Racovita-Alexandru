package com.proiectps.JobBoard.service;

import com.proiectps.JobBoard.model.User;
import com.proiectps.JobBoard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface UserService {

    public List<User> findAll() ;

    public User findById(Long id) ;

    public User save(User user) ;

    public void deleteById(Long id) ;

    public User findByUsername(String username) ;

    User findByUsernameAndPassword(String username, String password);

    Long getCurrentUserId();
    void setCurrentUserId(Long id);
    void setOnlineStatus(Long userId, boolean status);
    public boolean isCurrentUserAdmin();

    public User findByEmail(String email);
}
