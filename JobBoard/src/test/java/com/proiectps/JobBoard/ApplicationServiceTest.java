package com.proiectps.JobBoard;

import com.proiectps.JobBoard.model.Application;
import com.proiectps.JobBoard.model.Job;
import com.proiectps.JobBoard.model.User;
import com.proiectps.JobBoard.repository.ApplicationRepository;
import com.proiectps.JobBoard.service.impl.ApplicationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ApplicationServiceTest {

    private static final Long USER_ID = 1L;
    private static final Long JOB_ID = 1L;

    private ApplicationServiceImpl applicationServiceImpl;

    @Mock
    private ApplicationRepository applicationRepository;

    private Application application;

    @BeforeEach
    void init() {
        initMocks(this);

        User user = new User();
        user.setId(USER_ID);
        user.setUsername("testuser");

        Job job = new Job();
        job.setId(JOB_ID);
        job.setTitle("Software Developer");

        application = new Application();
        application.setUser(user);
        application.setJob(job);
        application.setApplicationDate(LocalDate.now());

        when(applicationRepository.findById(1L)).thenReturn(Optional.of(application));
        when(applicationRepository.findByUserId(USER_ID)).thenReturn(java.util.Collections.singletonList(application));
    }

    @Test
    void givenExistingUserId_whenFindByUserId_thenFindApplications() {
        applicationServiceImpl = new ApplicationServiceImpl(applicationRepository);

        Iterable<Application> applications = applicationServiceImpl.findByUserId(USER_ID);

        assertNotNull(applications);
        assertEquals(application.getUser().getId(), USER_ID);
    }

    @Test
    void givenExistingId_whenFindById_thenFindOne() {
        applicationServiceImpl = new ApplicationServiceImpl(applicationRepository);

        Application foundApplication = applicationServiceImpl.findById(1L);

        assertNotNull(foundApplication);
        assertEquals(application.getId(), foundApplication.getId());
    }
}
