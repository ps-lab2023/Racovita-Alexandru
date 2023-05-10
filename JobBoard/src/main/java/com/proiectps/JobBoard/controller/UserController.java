package com.proiectps.JobBoard.controller;

import com.proiectps.JobBoard.RegistrationRequest;
import com.proiectps.JobBoard.model.User;
import com.proiectps.JobBoard.service.UserService;
import com.proiectps.JobBoard.service.impl.EmailServiceImpl;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200/")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private EmailServiceImpl emailSenderService;
    private static final String RECAPTCHA_SECRET_KEY = "6Ld4fO8lAAAAAIa899rDZ_fc7siGExY31yQdgAj4";
    private static final String RECAPTCHA_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";

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
/*
@PostMapping("/register")
public ResponseEntity<User> register(@RequestBody RegistrationRequest registrationRequest) throws MessagingException {
    User user = registrationRequest.getUser();
    String recaptchaResponse = registrationRequest.getRecaptchaResponse();

    if (!validateRecaptcha(recaptchaResponse)) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
    if (user == null) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User data is missing");
    }
    emailSenderService.sendMailWithAttachment(user.getEmail(),
            "This is email body.",
            "This email subject",
            "C:\\Users\\Home\\Desktop\\Proiect PS\\JobBoard\\src\\main\\resources\\test");

    return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
}

    private boolean validateRecaptcha(String recaptchaResponse) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/x-www-form-urlencoded");

        RestTemplate restTemplate = new RestTemplate();
        String requestBody = "secret=" + RECAPTCHA_SECRET_KEY + "&response=" + recaptchaResponse;

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                RECAPTCHA_VERIFY_URL,
                HttpMethod.POST,
                requestEntity,
                String.class);

        String response = responseEntity.getBody();

        // Check if the reCAPTCHA API response contains "success": true
        return response != null && response.contains("\"success\": true");
    }*/
    //generate findByUsername
    @GetMapping("/findByUsername")
    public ResponseEntity<User> findByUsername(@PathVariable String username){
        User user=userService.findByUsername(username);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }
}
