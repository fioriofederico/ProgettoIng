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

    public DeliveryFolder createDeliveryFolder(DeliveryFolderDto deliveryFolder) {

        DeliveryFolder deliveryFolderToCreate = new DeliveryFolder();

        deliveryFolderToCreate.setName(deliveryFolder.getName());
        deliveryFolderToCreate.setStartDeliveryTime(deliveryFolder.getStartDeliveryTime());
        deliveryFolderToCreate.setEndDeliveryTime(deliveryFolder.getEndDeliveryTime());

        try {
            Course course = courseRepository.findById(deliveryFolder.getCourseId()).orElseThrow(Exception::new);
            deliveryFolderToCreate.setCourse(course);

            return deliveryFolderRepository.save(deliveryFolderToCreate);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<DeliveryFolder> findDeliveryFolders(Long courseId) {

        try {
            Course course = courseRepository.findById(courseId).orElseThrow(Exception::new);
            return deliveryFolderRepository.findDeliveryFoldersByCourse(course);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public DeliveryFolder findDeliveryFolder(Long id) {
        try {
            return deliveryFolderRepository.findById(id).orElseThrow(Exception::new);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public DeliveryFolder updateDeliveryFolder(Long id, DeliveryFolderDto deliveryFolder) {

            try {
                DeliveryFolder deliveryFolderToUpdate = deliveryFolderRepository.findById(id).orElseThrow(Exception::new);

                deliveryFolderToUpdate.setName(deliveryFolder.getName());
                deliveryFolderToUpdate.setStartDeliveryTime(deliveryFolder.getStartDeliveryTime());
                deliveryFolderToUpdate.setEndDeliveryTime(deliveryFolder.getEndDeliveryTime());

                Course course = courseRepository.findById(deliveryFolder.getCourseId()).orElseThrow(Exception::new);
                deliveryFolderToUpdate.setCourse(course);

                return deliveryFolderRepository.save(deliveryFolderToUpdate);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
    }

    public DeliveryFolder deleteDeliveryFolder(Long id) {
        try {
            DeliveryFolder deliveryFolderToDelete = deliveryFolderRepository.findById(id).orElseThrow(Exception::new);
            deliveryFolderRepository.delete(deliveryFolderToDelete);
            return deliveryFolderToDelete;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
