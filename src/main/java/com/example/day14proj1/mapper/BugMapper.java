package com.example.day14proj1.mapper;

import com.example.day14proj1.dto.BugResponseDTO;
import com.example.day14proj1.entity.Bug;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BugMapper {
    BugResponseDTO toDto(Bug bug);
}
