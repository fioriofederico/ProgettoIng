package com.ProgettoIng.FedericoIoan.service.IService;

import com.ProgettoIng.FedericoIoan.model.Assignment;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface AssignmentService {

    Assignment uploadAssignment(Long studentId, Long deliveryFolderId, MultipartFile file);

    List<Assignment> findAllAssignments(Long deliveryFolderId);

    Assignment findAssignment(Long id);

    Resource loadAssignment(Long id);

    Assignment deleteAssignment(Long id);

    Assignment scoreAssignment(Long id, Integer score);
}
