package com.project.professor.allocation.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Course;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class CourseServiceTest {

    @Autowired
    CourseService coursService;

    @Test
    public void save() {
        Course course = new Course();
        course.setId(null);
        course.setName("Course 1");
        course = coursService.create(course);

        System.out.println(course);
    }
    
    @Test
    public void update() {
        Course course = new Course();
        course.setId(1L);
        course.setName("Course 2");
        course = coursService.update(course);

        System.out.println(course);
    }
    
    @Test
    public void deleteById() {
        Long id = 1L;
        coursService.deleteById(id);
    }
    
    @Test
    public void deleteAll() {
    	coursService.deleteAll();
    }    
    
    @Test
    public void findAll() {
        List<Course> courses = coursService.findAll(null);
        
        courses.forEach(System.out::println);
    }
    
    @Test
    public void findAllByName() {
        String name = "course";
        List<Course> courses = coursService.findAll(name);
        
        courses.forEach(System.out::println);
    }
    
    @Test
    public void findById() {
        Long id = 1L;
        Course course = coursService.findById(id);
        
        System.out.println(course);
    }

}