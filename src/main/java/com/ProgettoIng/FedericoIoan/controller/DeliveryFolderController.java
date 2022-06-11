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
    public ResponseEntity<DeliveryFolder> createDeliveryFolder(@RequestBody DeliveryFolderDto deliveryFolderDto) {
        DeliveryFolder createdDeliveryFolder =deliveryFolderService.createDeliveryFolder(deliveryFolderDto);
        return ResponseEntity.ok(createdDeliveryFolder);
    }

    @GetMapping
    public ResponseEntity<List<DeliveryFolder>> findDeliveryFolders(@PathVariable Long courseId) {
        List<DeliveryFolder> deliveryFolders = deliveryFolderService.findDeliveryFolders(courseId);
        return ResponseEntity.ok(deliveryFolders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryFolder> findDeliveryFolder(@PathVariable Long id) {
        DeliveryFolder deliveryFolder = deliveryFolderService.findDeliveryFolder(id);
        return ResponseEntity.ok(deliveryFolder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeliveryFolder> updateDeliveryFolder(@PathVariable Long id,
                                                               @RequestBody DeliveryFolderDto deliveryFolderDto) {
        DeliveryFolder updatedDeliveryFolder = deliveryFolderService.updateDeliveryFolder(id, deliveryFolderDto);
        return ResponseEntity.ok(updatedDeliveryFolder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeliveryFolder> deleteDeliveryFolder(@PathVariable Long id) {
        DeliveryFolder deletedDeliveryFolder = deliveryFolderService.deleteDeliveryFolder(id);
        return ResponseEntity.ok(deletedDeliveryFolder);
    }
}
