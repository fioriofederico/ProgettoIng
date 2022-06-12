package com.ProgettoIng.FedericoIoan.controller;

import com.ProgettoIng.FedericoIoan.model.Assignment;
import com.ProgettoIng.FedericoIoan.service.AssignmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/courses/{courseId}/delivery_folders/{folderId}/assignments")
public class AssignmentController {

    @Autowired
    private AssignmentServiceImpl assignmentService;

    @PostMapping
    public ResponseEntity<?> createAssignment(@PathVariable Long folderId, @RequestParam("file") MultipartFile file) {
        try {
            // TODO: get student id from JWT token
            Long studuntId = Long.valueOf(3);
            Assignment assignment = assignmentService.uploadAssignment(studuntId, folderId, file);
            return ResponseEntity.ok(assignment);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllAssignments(@PathVariable Long folderId) {
        try {
            List<Assignment> assignments = assignmentService.findAllAssignments(folderId);
            return ResponseEntity.ok(assignments);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAssignment(@PathVariable Long id) {
        try {
            Assignment assignmentDetails = assignmentService.findAssignment(id);
            Resource attachment = assignmentService.loadAssignment(id);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + assignmentDetails.getName() + "\"")
                    .body(attachment);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAssignment(@PathVariable Long id) {
        try {
            Assignment assignment = assignmentService.deleteAssignment(id);
            return ResponseEntity.ok(assignment);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> scoreAssignment(@PathVariable Long id, @RequestBody Integer score) {
        try {
            Assignment assignment = assignmentService.scoreAssignment(id, score);
            return ResponseEntity.ok(assignment);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}