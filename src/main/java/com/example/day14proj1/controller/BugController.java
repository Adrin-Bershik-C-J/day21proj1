package com.example.day14proj1.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.day14proj1.dto.BugResponseDTO;
import com.example.day14proj1.entity.Bug;
import com.example.day14proj1.service.BugService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bugs")
@RequiredArgsConstructor
public class BugController {

    private final BugService bugService;

    @GetMapping("/search")
    @Operation(summary = "Filter bugs", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<List<BugResponseDTO>> getFilteredBugs(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String assignee,
            @RequestParam(required = false) String project) {

        List<BugResponseDTO> bugs = bugService.filterBugs(status, assignee, project);
        return ResponseEntity.ok(bugs);
    }

    @Operation(summary = "Get all bugs", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/all")
    public ResponseEntity<Page<BugResponseDTO>> getAllBugs(
            @PageableDefault(size = 5, sort = "title") Pageable pageable) {
        Page<BugResponseDTO> bugsPage = bugService.getAllBugs(pageable);
        return ResponseEntity.ok(bugsPage);
    }

    @Operation(summary = "Create bug(Admin Only)", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/admin/create")
    public ResponseEntity<BugResponseDTO> createBug(@RequestBody Bug bug) {
        return ResponseEntity.ok(bugService.createBug(bug));
    }

    @Operation(summary = "Delete bug(Admin Only)", security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping("/admin/delete")
    public ResponseEntity<BugResponseDTO> deleteBug(@RequestParam Long id) {
        return ResponseEntity.ok(bugService.deleteBug(id));
    }

    @Operation(summary = "Update bug(Admin only)", security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/admin/update/{id}")
    public ResponseEntity<BugResponseDTO> updateBug(@PathVariable Long id, @RequestBody Bug bug) {
        return ResponseEntity.ok(bugService.updateBug(id, bug));
    }

}