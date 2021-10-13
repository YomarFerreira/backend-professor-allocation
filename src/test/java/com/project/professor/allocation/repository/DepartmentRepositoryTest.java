package com.project.professor.allocation.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Department;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations="classpath:application.properties")
public class DepartmentRepositoryTest {

	@Autowired
	DepartmentRepository deptoRepository;
	
	@Test
	public void create() {
		Department createDepartment = new Department(null, "Mamute"); 
		Department insertDepartment = deptoRepository.save(createDepartment);
		System.out.println(insertDepartment);
	}
	
	@Test
	public void update() {
		Department changeDepartment = new Department(1L, "Mamute2"); 
		Department updateDepartment = deptoRepository.save(changeDepartment );
		System.out.println(updateDepartment);
	}
	
	@Test
	public void deleteById() {
		if(deptoRepository.existsById(1L)) {
			deptoRepository.deleteById(1L);
		}
	}
	
    @Test
    public void deleteAll() {
    	deptoRepository.deleteAllInBatch();
    }
	
    
    @Test
    public void findAll() {
        List<Department> departments = deptoRepository.findAll();
        departments.forEach(System.out::println);
    }
    
	
	@Test
	public void findById() {
		Department findDepartment = deptoRepository.findById(2L).orElse(null);
		System.out.println(findDepartment);
	}
	
	@Test
	public void findByNameContaining() {
		List <Department> list = deptoRepository.findByNameContainingIgnoreCase("mamute");
		System.out.println(list);
	}
	
	
	
	
	
}
