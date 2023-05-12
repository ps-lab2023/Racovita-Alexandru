package com.proiectps.JobBoard.service.impl;

import com.proiectps.JobBoard.model.User;
import com.proiectps.JobBoard.repository.UserRepository;
import com.proiectps.JobBoard.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Autowired
    private HttpSession session;

    @Override
    public Long getCurrentUserId() {
        return (Long) session.getAttribute("currentUserId");
    }

    @Override
    public void setCurrentUserId(Long id) {
        session.setAttribute("currentUserId", id);
    }
    @Override
    public boolean isCurrentUserAdmin() {
        Long currentUserId = getCurrentUserId();
        if (currentUserId == null) {
            return false;
        }

        User currentUser = findById(currentUserId);
        return currentUser.isAdmin();
    }
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }
    @Override
    public void setOnlineStatus(Long userId, boolean status) {
        System.out.println("setOnlineStatus called with userId: " + userId + " and status: " + status);

        User user = findById(userId);
        if (user != null) {
            System.out.println("User found: " + user);
            user.setOnline(status);
            userRepository.save(user);
        } else {
            System.out.println("No user found with id: " + userId);
        }
    }
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    // Implement additional business logic here
}
