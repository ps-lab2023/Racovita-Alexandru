package com.proiectps.JobBoard;

import com.proiectps.JobBoard.model.Job;
import com.proiectps.JobBoard.repository.JobRepository;
import com.proiectps.JobBoard.service.impl.JobServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class JobServiceTest {

    private static final String TITLE = "Software Engineer";
    private static final String TITLE_NOT = "Title not existing";

    private JobServiceImpl jobServiceImpl;

    @Mock
    private JobRepository jobRepository;

    private Job job;

    @BeforeEach
    void init() {
        initMocks(this);
        job = new Job();
        job.setTitle(TITLE);
        when(jobRepository.findById(1L)).thenReturn(Optional.of(job));
        when(jobRepository.findByTitleContainingIgnoreCase(TITLE)).thenReturn(Arrays.asList(job));
    }

    @Test
    void givenExistingTitle_whenFindByTitleContainingIgnoreCase_thenFindOne() {
        jobServiceImpl = new JobServiceImpl(jobRepository);

        List<Job> jobs = jobServiceImpl.findByTitleContainingIgnoreCase(TITLE);

        assertNotNull(jobs);
        assertEquals(1, jobs.size());
        assertEquals(TITLE, jobs.get(0).getTitle());
    }

    @Test
    void givenNonExistingTitle_whenFindByTitleContainingIgnoreCase_thenEmptyList() {
        when(jobRepository.findByTitleContainingIgnoreCase(TITLE_NOT)).thenReturn(Arrays.asList());

        jobServiceImpl = new JobServiceImpl(jobRepository);
        List<Job> jobs = jobServiceImpl.findByTitleContainingIgnoreCase(TITLE_NOT);

        assertNotNull(jobs);
        assertEquals(0, jobs.size());
    }
}
