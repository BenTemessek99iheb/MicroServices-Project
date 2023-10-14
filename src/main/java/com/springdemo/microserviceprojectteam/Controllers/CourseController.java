package com.springdemo.microserviceprojectteam.Controllers;

import com.springdemo.microserviceprojectteam.Model.Course;
import com.springdemo.microserviceprojectteam.Services.ICourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
@CrossOrigin(origins = "http://localhost:3000")
public class CourseController {
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private ICourseService courseService;

    @GetMapping
    public ResponseEntity<List<Course>> getCourses() {
        try {
            List<Course> courses = courseService.getCourses();
            if (courses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        try {
            logger.info("Received request to add a course: {}", course);
            Course newCourse = courseService.addCourse(course);
            logger.info("Course added successfully: {}", newCourse);
            return new ResponseEntity<>(newCourse, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error while adding a course", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Course> updateCourse(@RequestBody Course course) {
        try {
            Course updatedCourse = courseService.updateCourse(course);
            if (updatedCourse != null) {
                return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable("id") Long id) {
        try {
            courseService.deleteCourse(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable("id") Long id) {
        try {
            Optional<Course> course = courseService.getCourseById(id);
            if (course.isPresent()) {
                return new ResponseEntity<>(course.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/name/{name}")
    public ResponseEntity<List<Course>> getCourseByName(@PathVariable("name") String name) {
        try {
            List<Course> courses = courseService.getCourseByName(name);
            if (courses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/category/{category}")
    public ResponseEntity<List<Course>> getCourseByCategory(@PathVariable("category") String category) {
        try {
            List<Course> courses = courseService.getCourseByCategory(category);
            if (courses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}