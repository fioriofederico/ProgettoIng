package com.federico_ioan.ProgettoIng.CourseModule;


import com.federico_ioan.ProgettoIng.Course.Course;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseModuleRepository extends JpaRepository<CourseModule, Long> {

    List<CourseModule> findByCourse(Course course, Sort sort);
}
