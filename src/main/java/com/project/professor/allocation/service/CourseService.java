package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.repository.CourseRepository;

@Service
public class CourseService {

	private final CourseRepository coursRepository;

	public CourseService(CourseRepository courseRepository) {
		super();
		this.coursRepository = courseRepository;
	}

	public Course create(Course course) {
		course.setId(null);
		return save(course);
	}

	public Course update(Course course) {
		Long id = course.getId();
		if (id != null && coursRepository.existsById(id)) {
			return save(course);
		} else {
			return null;
		}
	}

	private Course save(Course course) {
		course = coursRepository.save(course);
		return course;
	}

	public void deleteById(Long id) {
		if (id != null && coursRepository.existsById(id)) {
			coursRepository.deleteById(id);
		}
	}

	public void deleteAll() {
		coursRepository.deleteAllInBatch();
	}

	public List<Course> findAll(String name) {
		if (name == null) {
			return coursRepository.findAll();
		} else {
			return coursRepository.findByNameContainingIgnoreCase(name);
		}
	}

	public Course findById(Long id) {
		return coursRepository.findById(id).orElse(null);
	}

}
