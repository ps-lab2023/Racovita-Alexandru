package com.proiectps.JobBoard.controller;

import com.proiectps.JobBoard.model.User;
import com.proiectps.JobBoard.service.UserService;
import com.proiectps.JobBoard.service.impl.EmailServiceImpl;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200/")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private EmailServiceImpl emailSenderService;
    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }
    //create the login endpoint
    @PostMapping("/login")
public ResponseEntity<User> login(@RequestBody User user) {
        User authenticatedUser = user; // The authenticated user
        userService.setCurrentUserId(authenticatedUser.getId());
        return ResponseEntity.ok(userService.findByUsernameAndPassword(user.getUsername(), user.getPassword()));
    }
    @GetMapping("/findUser")
    public User findUser(@RequestParam String username,
                            @RequestParam String password){
        User u=userService.findByUsernameAndPassword(username,password);
        return u;
    }
    @GetMapping("/findby/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User user = userService.findById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
        if (!id.equals(user.getId())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(userService.save(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }
//create register endpoint
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) throws MessagingException {
        emailSenderService.sendMailWithAttachment(user.getEmail(),
                "This is email body.",
                "This email subject", "" +
                        "C:\\Users\\Home\\Desktop\\Proiect PS\\JobBoard\\src\\main\\resources\\test");

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }
    // Add additional endpoints here

    //generate findByUsername
    @GetMapping("/findByUsername")
    public ResponseEntity<User> findByUsername(@PathVariable String username){
        User user=userService.findByUsername(username);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }
}
