package com.federico_ioan.ProgettoIng.CourseDeliveryFolder;

import org.springframework.data.repository.CrudRepository;

public interface CourseDeliveryFolderRepository extends CrudRepository<CourseDeliveryFolder, Long>{

	CourseDeliveryFolder findByCourseId(Long courseId);


}
