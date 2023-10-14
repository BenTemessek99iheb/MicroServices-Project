package com.springdemo.microserviceprojectteam.Repository;

import com.springdemo.microserviceprojectteam.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    public List<Course> findByName(String name);
    public List<Course> findByCategory(String category);
}
