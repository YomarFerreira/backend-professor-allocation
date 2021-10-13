package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.repository.DepartmentRepository;


@Service
public class DepartmentService {

    private final DepartmentRepository departRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        super();
        this.departRepository = departmentRepository;
    }

    public Department create(Department department) {
        department.setId(null);
        return save(department);
    }

    public Department update(Department department) {
        Long id = department.getId();
        if (id != null && departRepository.existsById(id)) {
            return save(department);
        } else {
            return null;
        }
    }

    private Department save(Department department) {
        department = departRepository.save(department);
        return department;
    }

    public void deleteById(Long id) {
        if (id != null && departRepository.existsById(id)) {
        	departRepository.deleteById(id);
        }
    }

    public void deleteAll() {
    	departRepository.deleteAllInBatch();
    }

    public List<Department> findAll(String name) {
        if (name == null) {
            return departRepository.findAll();
        } else {
            return departRepository.findByNameContainingIgnoreCase(name);
        }
    }

    public Department findById(Long id) {
        return departRepository.findById(id).orElse(null);
    }

}
