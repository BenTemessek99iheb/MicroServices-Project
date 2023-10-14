package com.springdemo.microserviceprojectteam.Services;
import com.springdemo.microserviceprojectteam.Model.Course;
import java.util.List;
import java.util.Optional;

public interface ICourseService {
    Course addCourse(Course course);
    Course updateCourse(Course course);
    void deleteCourse(Long id);
    Optional<Course> getCourseById(Long id);
    List<Course> getCourseByName(String name);
    List<Course> getCourses();
    List<Course> getCourseByCategory(String category);
}
