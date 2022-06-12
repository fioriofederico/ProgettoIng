package com.ProgettoIng.FedericoIoan.repository;

import com.ProgettoIng.FedericoIoan.model.Assignment;
import com.ProgettoIng.FedericoIoan.model.DeliveryFolder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findAllByDeliveryFolder(DeliveryFolder deliveryFolder);
}
