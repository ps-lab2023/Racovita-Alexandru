package com.proiectps.JobBoard.controller;

import com.proiectps.JobBoard.RegistrationRequest;
import com.proiectps.JobBoard.model.User;
import com.proiectps.JobBoard.service.UserService;
import com.proiectps.JobBoard.service.impl.EmailServiceImpl;
import com.proiectps.JobBoard.service.impl.UserServiceImpl;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200/")
public class UserController {
    @Autowired
    private UserServiceImpl userService;
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
        System.out.println("Login method called with user: " + user);

        User authenticatedUser = userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());; // The authenticated user
        userService.setOnlineStatus(authenticatedUser.getId(), true);
        userService.setCurrentUserId(authenticatedUser.getId());

        User foundUser = userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        System.out.println("Found user: " + foundUser);

        return ResponseEntity.ok(foundUser);
    }

    @PutMapping("/logout")
    public ResponseEntity logout(@RequestParam Long userId) {
        System.out.println("Logout method called with userId: " + userId);
        User authenticatedUser = userService.findById(userId); // The authenticated user
        userService.setOnlineStatus(authenticatedUser.getId(), false);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/admin/onlineStatus")
    public ResponseEntity<List<User>> getOnlineStatus() {

        // If the current user is an admin, return all users and their online status
        return ResponseEntity.ok(userService.findAll());
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
    public ResponseEntity<?> save(@RequestBody User user) {
        if (userService.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("A user with this username already exists");
        }
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
public static boolean isValidEmail(String email) {
    String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";

    Pattern pat = Pattern.compile(emailRegex);
    if (email == null)
        return false;
    return pat.matcher(email).matches();
}

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) throws MessagingException {
        if (!isValidEmail(user.getEmail())) {
            return new ResponseEntity<>("Invalid email format", HttpStatus.BAD_REQUEST);
        }
        if (userService.findByUsername(user.getUsername()) != null) {
           return ResponseEntity.status(HttpStatus.CONFLICT).body("A user with this username already exists");
       }
       if (userService.findByEmail(user.getEmail()) != null) {
           return ResponseEntity.status(HttpStatus.CONFLICT).body("A user with this email already exists");
       }
        emailSenderService.sendMailWithAttachment(user.getEmail(),
                "This is email body.",
                "This email subject", "" +
                        "C:\\Users\\Home\\Desktop\\Proiect PS\\JobBoard\\src\\main\\resources\\test");

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }
    // Add additional endpoints here

    @GetMapping("/admin/onlineStatus/xml")
    public ResponseEntity<String> getOnlineStatusXML() {
        List<User> onlineUsers = userService.findAll().stream().filter(User::isOnline).collect(Collectors.toList());

        StringWriter sw = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            for (User user : onlineUsers) {
                jaxbMarshaller.marshal(user, sw);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_XML).body(sw.toString());
    }

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
