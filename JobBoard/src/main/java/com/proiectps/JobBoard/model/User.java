package com.proiectps.JobBoard.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@XmlRootElement
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(name = "is_online")
    private boolean isOnline;

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public enum Role {
        USER, ADMIN
    }
    //isadmin

    public boolean isAdmin() {
        return role == Role.ADMIN;
    }
    /*
    @ManyToMany
    @JoinTable(
            name = "user_bookmarked_jobs",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "job_id")
    )
    private List<Job> bookmarkedJobs = new ArrayList<>();
*/
    @JsonIgnoreProperties("users")
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "fjobs_user",
            joinColumns = {
                    @JoinColumn(name = "job_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id")
            })
    private List<Job> favoriteJobs;

    //getFavoriteJobs
    public List<Job> getFavoriteJobs() {
        return favoriteJobs;
    }
    //setFavoriteJobs
    public void setFavoriteJobs(List<Job> favoriteJobs) {
        this.favoriteJobs = favoriteJobs;
    }

}

