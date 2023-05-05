package com.proiectps.JobBoard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationDTO {
    private Long id;
    private Long userId;
    private Long jobId;
    private LocalDate applicationDate;
}
