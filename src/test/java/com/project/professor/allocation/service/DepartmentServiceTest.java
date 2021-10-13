package com.project.professor.allocation.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Department;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class DepartmentServiceTest {
	
    @Autowired
    DepartmentService departService;
    
    @Test
    public void save() {
        Department department = new Department();
        department.setId(null);
        department.setName("Department 1");
        
        department = departService.create(department);
        
        System.out.println(department);
    }
    
    @Test
    public void update() {
        Department department = new Department();
        department.setId(1L);
        department.setName("Department 2");
        
        department = departService.update(department);
        
        System.out.println(department);
    }
    
    @Test
    public void deleteById() {
        Long id = 1L;
        departService.deleteById(id);
    }
    
    @Test
    public void deleteAll() {
    	departService.deleteAll();
    }
    
    @Test
    public void findAll() {
        List<Department> departments = departService.findAll(null);
        departments.forEach(System.out::println);
    }
    
    @Test
    public void findAllByName() {
        String name = "department";
        List<Department> departments = departService.findAll(name);
        
        departments.forEach(System.out::println);
    }
    
    @Test
    public void findById() {
        Long id = 1L;
        Department department = departService.findById(id);
        
        System.out.println(department);
    }

}
