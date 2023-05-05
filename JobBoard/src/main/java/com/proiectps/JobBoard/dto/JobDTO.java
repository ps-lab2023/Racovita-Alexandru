package com.proiectps.JobBoard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobDTO {
    private Long id;
    private String title;
    private String description;
    private Long companyId;
    private String companyName;
    private Long categoryId;
    private String categoryName;
}
