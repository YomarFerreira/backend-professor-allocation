package com.project.professor.allocation.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Course;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class CourseRepositoryTest {

	
    @Autowired
    CourseRepository coursRepository;

    @Test
    public void create() {
        Course course = new Course();
        course.setId(null);
        course.setName("Course 1");
        course = coursRepository.save(course);
        System.out.println(course);
    }

    @Test
    public void update() {
        Course course = new Course();
        course.setId(1L);
        course.setName("Course 2");
        course = coursRepository.save(course);
        System.out.println(course);
    }

    @Test
    public void deleteById() {
        Long id = 1L;
        coursRepository.deleteById(id);
    }

    @Test
    public void deleteAll() {
    	coursRepository.deleteAllInBatch();
    }
    
    @Test
    public void findAll() {
        List<Course> courses = coursRepository.findAll();

        courses.forEach(System.out::println);
    }

    @Test
    public void findById() {
        Long id = 3L;

        Course course = coursRepository.findById(id).orElse(null);

        System.out.println(course);
    }

    @Test
    public void findByNameContainingIgnoreCase() {
        String name = "Mamute";

        List<Course> courses = coursRepository.findByNameContainingIgnoreCase(name);

        courses.forEach(System.out::println);
    }

	
}
