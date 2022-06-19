package com.ProgettoIng.FedericoIoan.service;

import com.ProgettoIng.FedericoIoan.model.Course;
import com.ProgettoIng.FedericoIoan.model.DeliveryFolder;
import com.ProgettoIng.FedericoIoan.model.dto.DeliveryFolderDto;
import com.ProgettoIng.FedericoIoan.repository.CourseRepository;
import com.ProgettoIng.FedericoIoan.repository.DeliveryFolderRepository;
import com.ProgettoIng.FedericoIoan.service.IService.DeliveryFolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class DeliveryFolderServiceImpl implements DeliveryFolderService {

    @Autowired
    private DeliveryFolderRepository deliveryFolderRepository;

    @Autowired
    private CourseRepository courseRepository;

    public DeliveryFolder createDeliveryFolder(Long courseId, DeliveryFolderDto deliveryFolder) {

        DeliveryFolder deliveryFolderToCreate = new DeliveryFolder();

        deliveryFolderToCreate.setName(deliveryFolder.getName());
        deliveryFolderToCreate.setStartDeliveryTime(deliveryFolder.getStartDeliveryTime());
        deliveryFolderToCreate.setEndDeliveryTime(deliveryFolder.getEndDeliveryTime());

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));
        deliveryFolderToCreate.setCourse(course);

        return deliveryFolderRepository.save(deliveryFolderToCreate);
    }

    public List<DeliveryFolder> findDeliveryFolders(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        return deliveryFolderRepository.findDeliveryFoldersByCourse(course);
    }

    public DeliveryFolder findDeliveryFolder(Long id) {
        return deliveryFolderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("DeliveryFolder not found"));
    }

    public DeliveryFolder updateDeliveryFolder(Long id, DeliveryFolderDto deliveryFolder) {
        DeliveryFolder deliveryFolderToUpdate = deliveryFolderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("DeliveryFolder not found"));

        deliveryFolderToUpdate.setName(deliveryFolder.getName());
        deliveryFolderToUpdate.setStartDeliveryTime(deliveryFolder.getStartDeliveryTime());
        deliveryFolderToUpdate.setEndDeliveryTime(deliveryFolder.getEndDeliveryTime());

        return deliveryFolderRepository.save(deliveryFolderToUpdate);
    }

    public DeliveryFolder deleteDeliveryFolder(Long id) {
        DeliveryFolder deliveryFolderToDelete = deliveryFolderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("DeliveryFolder not found"));

        deliveryFolderRepository.delete(deliveryFolderToDelete);
        return deliveryFolderToDelete;
    }
}