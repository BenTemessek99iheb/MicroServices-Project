package com.springdemo.microserviceprojectteam.Services;

import com.springdemo.microserviceprojectteam.Model.Course;
import com.springdemo.microserviceprojectteam.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements ICourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Override
    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }


    @Override
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public List<Course> getCourseByName(String name) {
        return courseRepository.findByName(name);
    }


    @Override
    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> getCourseByCategory(String category) {
        return courseRepository.findByCategory(category);
    }
}
