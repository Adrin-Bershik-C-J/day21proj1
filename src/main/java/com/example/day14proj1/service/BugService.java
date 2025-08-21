package com.example.day14proj1.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.day14proj1.dto.BugResponseDTO;
import com.example.day14proj1.entity.Bug;
import com.example.day14proj1.mapper.BugMapper;
import com.example.day14proj1.repository.BugRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BugService {

    private final BugRepository bugRepository;
    private final BugMapper bugMapper;

    public List<BugResponseDTO> filterBugs(String status, String assignee, String project) {
        return bugRepository.findAll().stream().filter(b -> status == null || b.getStatus().equalsIgnoreCase(status))
                .filter(b -> assignee == null || b.getAssignee().equalsIgnoreCase(assignee))
                .filter(b -> project == null || b.getProject().equalsIgnoreCase(project))
                .map(b -> new BugResponseDTO(b.getId(), b.getTitle(), b.getStatus(), b.getAssignee(), b.getProject()))
                .collect(Collectors.toList());
    }

    public Page<BugResponseDTO> getAllBugs(Pageable pageable) {
        return bugRepository.findAll(pageable)
                .map(b -> new BugResponseDTO(
                        b.getId(),
                        b.getTitle(),
                        b.getStatus(),
                        b.getAssignee(),
                        b.getProject()));
    }

    public BugResponseDTO createBug(Bug bug) {
        return bugMapper.toDto(bugRepository.save(bug));
    }

    public BugResponseDTO deleteBug(Long id) {
        Bug b = bugRepository.findById(id)
                .map(bug -> {
                    bugRepository.delete(bug);
                    return bug;
                })
                .orElseThrow(() -> new RuntimeException("Bug not found with id: " + id));
        return bugMapper.toDto(b);
    }

    public BugResponseDTO updateBug(Long id, Bug updatedBug) {
        Bug b = bugRepository.findById(id)
                .map(existingBug -> {
                    existingBug.setTitle(updatedBug.getTitle());
                    existingBug.setStatus(updatedBug.getStatus());
                    existingBug.setAssignee(updatedBug.getAssignee());
                    existingBug.setProject(updatedBug.getProject());
                    return bugRepository.save(existingBug);
                })
                .orElseThrow(() -> new RuntimeException("Bug not found with id: " + id));
        return bugMapper.toDto(b);
    }

}
