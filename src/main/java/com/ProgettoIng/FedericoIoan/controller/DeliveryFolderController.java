package com.ProgettoIng.FedericoIoan.controller;

import com.ProgettoIng.FedericoIoan.model.DeliveryFolder;
import com.ProgettoIng.FedericoIoan.model.dto.DeliveryFolderDto;
import com.ProgettoIng.FedericoIoan.service.DeliveryFolderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses/{courseId}/delivery_folders")
public class DeliveryFolderController {

    @Autowired
    private DeliveryFolderServiceImpl deliveryFolderService;

    @PostMapping
    public ResponseEntity<?> createDeliveryFolder(@RequestBody DeliveryFolderDto deliveryFolderDto) {
        try {
            DeliveryFolder createdDeliveryFolder =deliveryFolderService.createDeliveryFolder(deliveryFolderDto);
            return ResponseEntity.ok(createdDeliveryFolder);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getDeliveryFolders(@PathVariable Long courseId) {
        try {
            List<DeliveryFolder> deliveryFolders = deliveryFolderService.findDeliveryFolders(courseId);
            return ResponseEntity.ok(deliveryFolders);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDeliveryFolder(@PathVariable Long id) {
        try {
            DeliveryFolder deliveryFolder = deliveryFolderService.findDeliveryFolder(id);
            return ResponseEntity.ok(deliveryFolder);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDeliveryFolder(@PathVariable Long id,
                                                               @RequestBody DeliveryFolderDto deliveryFolderDto) {
        try  {
            DeliveryFolder updatedDeliveryFolder = deliveryFolderService.updateDeliveryFolder(id, deliveryFolderDto);
            return ResponseEntity.ok(updatedDeliveryFolder);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDeliveryFolder(@PathVariable Long id) {
        try {
            DeliveryFolder deletedDeliveryFolder = deliveryFolderService.deleteDeliveryFolder(id);
            return ResponseEntity.ok(deletedDeliveryFolder);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
