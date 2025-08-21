package com.example.day14proj1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.day14proj1.entity.Bug;

public interface BugRepository extends JpaRepository<Bug, Long> {
    
}