package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.ProfessorRepository;

@Service
public class ProfessorService {

	private final ProfessorRepository profRepository;
	private final DepartmentService departService;

	public Professor create(Professor professor) {
		professor.setId(null);
		return save(professor);
	}

	public Professor update(Professor professor) {
		Long id = professor.getId();
		if (id != null && profRepository.existsById(id)) {
			return save(professor);
		} else {
			return null;
		}
	}

	private Professor save(Professor professor) {
		professor = profRepository.save(professor);

		Department department = departService.findById(professor.getDepartmentId());
		professor.setDepartment(department);

		return professor;
	}

	public void deleteById(Long id) {
		if (id != null && profRepository.existsById(id)) {
			profRepository.deleteById(id);
		}
	}

	public void deleteAll() {
		profRepository.deleteAllInBatch();
	}

	public ProfessorService(ProfessorRepository professorRepository, DepartmentService departmentService) {
		super();
		this.profRepository = professorRepository;
		this.departService = departmentService;
	}

	public List<Professor> findAll(String name) {
		if (name == null) {
			return profRepository.findAll();
		} else {
			return profRepository.findByNameContainingIgnoreCase(name);
		}
	}

	public Professor findById(Long id) {
		return profRepository.findById(id).orElse(null);
	}

	public List<Professor> findByDepartment(Long departmentId) {
		return profRepository.findByDepartmentId(departmentId);
	}

}
