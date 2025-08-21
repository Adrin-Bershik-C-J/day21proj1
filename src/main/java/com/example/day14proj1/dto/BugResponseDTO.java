package com.example.day14proj1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BugResponseDTO {

    private Long id;
    private String title;
    private String status;
    private String assignee;
    private String project;

}
