package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.AllocationRepository;

@Service
public class AllocationService {

	private final AllocationRepository allocRepository;
	private final ProfessorService profesService;
	private final CourseService cursService;

	public AllocationService(AllocationRepository allocRepository, ProfessorService profesService,
			CourseService cursService) {
		super();
		this.allocRepository = allocRepository;
		this.profesService = profesService;
		this.cursService = cursService;
	}

	public Allocation create(Allocation allocation) {
		allocation.setId(null);
		Allocation createAlloc = save(allocation);
		return createAlloc;
	}

	public Allocation update(Allocation allocation) {
		if (allocRepository.existsById(allocation.getId())) {
			Allocation updateAlloc = save(allocation);
			return updateAlloc;
		}
		return null;
	}

	private Allocation save(Allocation allocation) {
		Allocation saveAlloc = allocRepository.save(allocation);
		Professor professor = profesService.findById(saveAlloc.getProfessorId());
		saveAlloc.setProfessor(professor);
		Course course = cursService.findById(saveAlloc.getCourseId());
		saveAlloc.setCourse(course);
		return saveAlloc;
	}

	public void deleteById(Long allocationId) {
		if (allocRepository.existsById(allocationId)) {
			allocRepository.deleteById(allocationId);
		}
	}

	public void deleteAll() {
		allocRepository.deleteAllInBatch();
	}

	public List<Allocation> findAll() {
		List<Allocation> allocations = allocRepository.findAll();
		return allocations;
	}

	public Allocation findById(Long id) {
		Allocation allocation = allocRepository.findById(id).orElse(null);
		return allocation;
	}

	public List<Allocation> findByProfessor(Long professorId) {
		List<Allocation> allocationProf = allocRepository.findByProfessorId(professorId);
		return allocationProf;
	}

	public List<Allocation> findByCourse(Long courseId) {
		List<Allocation> allocationCours = allocRepository.findByCourseId(courseId);
		return allocationCours;
	}

}
