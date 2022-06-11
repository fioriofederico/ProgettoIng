package com.ProgettoIng.FedericoIoan.repository;

import com.ProgettoIng.FedericoIoan.model.Course;
import com.ProgettoIng.FedericoIoan.model.DeliveryFolder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryFolderRepository extends JpaRepository<DeliveryFolder, Long> {
    public List<DeliveryFolder> findDeliveryFoldersByCourse(Course course);
}
