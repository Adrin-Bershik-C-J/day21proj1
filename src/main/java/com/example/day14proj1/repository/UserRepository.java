package com.example.day14proj1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.day14proj1.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
