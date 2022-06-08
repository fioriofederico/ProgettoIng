package com.federico_ioan.ProgettoIng.Course;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseHelper courseHelper;

    public List<Course> findCourses() {
        return courseHelper.findCourses();
    }

    public Course findCourse(Long id) {
        return courseHelper.findCourse(id);
    }

    public Course createCourse(Course course) {
        Preconditions.checkArgument(!Objects.isNull(course.getName()));
        Preconditions.checkArgument(!Objects.isNull(course.getDuration()));
        Preconditions.checkArgument(!Objects.isNull(course.getOwner()));
        return courseHelper.createCourse(course);
    }

    public Course updateCourse(Course course) {
        Preconditions.checkArgument(!Objects.isNull(course.getId()));
        Preconditions.checkArgument(!Objects.isNull(course.getName()));
        Preconditions.checkArgument(!Objects.isNull(course.getDuration()));
        return courseHelper.updateCourse(course);
    }

    public Course deleteCourse(Long id) {
        Preconditions.checkArgument(!Objects.isNull(id));
        return courseHelper.deleteCourse(id);
    }
}
