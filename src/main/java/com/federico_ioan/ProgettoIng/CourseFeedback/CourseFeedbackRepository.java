package com.federico_ioan.ProgettoIng.CourseFeedback;

import org.springframework.data.repository.CrudRepository;

public interface CourseFeedbackRepository extends CrudRepository<CourseFeedback, Long> {

	CourseFeedback findByCourseId(Long courseId);

}
