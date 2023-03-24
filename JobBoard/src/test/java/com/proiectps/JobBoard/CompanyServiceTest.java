package com.proiectps.JobBoard;

import com.proiectps.JobBoard.model.Company;
import com.proiectps.JobBoard.repository.CompanyRepository;
import com.proiectps.JobBoard.service.impl.CompanyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CompanyServiceTest {

    private static final String NAME = "Awesome Company";
    private static final String NAME_NOT = "Non-existing Company";

    private CompanyServiceImpl companyServiceImpl;

    @Mock
    private CompanyRepository companyRepository;

    private Company company;

    @BeforeEach
    void init() {
        initMocks(this);
        company = new Company();
        company.setName(NAME);
        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));
        when(companyRepository.findByName(NAME)).thenReturn(company);
    }

    @Test
    void givenExistingName_whenFindByName_thenFindOne() {
        companyServiceImpl = new CompanyServiceImpl(companyRepository);

        Company foundCompany = companyServiceImpl.findByName(NAME);

        assertNotNull(foundCompany);
        assertEquals(NAME, foundCompany.getName());
    }

    @Test
    void givenNonExistingName_whenFindByName_thenNull() {
        when(companyRepository.findByName(NAME_NOT)).thenReturn(null);

        companyServiceImpl = new CompanyServiceImpl(companyRepository);
        Company foundCompany = companyServiceImpl.findByName(NAME_NOT);

        assertNull(foundCompany);
    }
}
