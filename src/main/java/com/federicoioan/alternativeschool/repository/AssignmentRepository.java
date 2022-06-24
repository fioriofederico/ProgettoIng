package com.federicoioan.alternativeschool.repository;

import com.federicoioan.alternativeschool.model.Assignment;
import com.federicoioan.alternativeschool.model.DeliveryFolder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findAllByDeliveryFolder(DeliveryFolder deliveryFolder);
}
