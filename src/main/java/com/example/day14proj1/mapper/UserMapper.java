package com.example.day14proj1.mapper;

import org.mapstruct.Mapper;

import com.example.day14proj1.dto.UserRequestDTO;
import com.example.day14proj1.dto.UserResponseDTO;
import com.example.day14proj1.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserRequestDTO user);

    UserResponseDTO toDto(User user);
}
