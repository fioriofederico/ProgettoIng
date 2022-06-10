package com.ProgettoIng.FedericoIoan.repository;

import com.ProgettoIng.FedericoIoan.model.CourseModule;
import com.ProgettoIng.FedericoIoan.model.VideoDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoDetailsRepository extends JpaRepository<VideoDetails, Long> {
    List<VideoDetails> findByCourseModule(CourseModule courseModule);
}
