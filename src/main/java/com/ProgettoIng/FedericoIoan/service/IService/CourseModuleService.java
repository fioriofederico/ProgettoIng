package com.ProgettoIng.FedericoIoan.service.IService;

import com.ProgettoIng.FedericoIoan.model.CourseModule;
import com.ProgettoIng.FedericoIoan.model.dto.CourseModuleDto;

import java.util.List;


public interface CourseModuleService {

    CourseModule createCourseModule(CourseModuleDto courseModule);

    List<CourseModule> findCourseModules(Long courseId);

    CourseModule findCourseModule(Long id);

    CourseModule updateCourseModule(Long id, CourseModuleDto courseModule);

    CourseModule deleteCourseModule(Long id);
}
