package com.project.professor.allocation.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Professor;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class ProfessorRepositoryTest {

    @Autowired
    ProfessorRepository profRepository;
	
    @Test
    public void save_create() {

        Professor professor = new Professor();
        professor.setId(null);
        professor.setName("Professor 1");
        professor.setCpf("123.456.789-09");
        professor.setDepartmentId(1L);

        professor = profRepository.save(professor);
        System.out.println(professor);
    }

    @Test
    public void save_update() {
        Professor professor = new Professor();
        professor.setId(1L);
        professor.setName("Professor 2");
        professor.setCpf("123.123.123-87");
        professor.setDepartmentId(1L);

        professor = profRepository.save(professor);
        System.out.println(professor);
    }

    @Test
    public void deleteById() {
        Long id = 1L;

        profRepository.deleteById(id);
    }

    @Test
    public void deleteAll() {
    	profRepository.deleteAllInBatch();
    }
	
    public void findAll() {
        List<Professor> professors = profRepository.findAll();
        professors.forEach(System.out::println);
    }

    @Test
    public void findById() {
        Long id = 1L;

        Professor professor = profRepository.findById(id).orElse(null);
        System.out.println(professor);
    }

    @Test
    public void findByNameContainingIgnoreCase() {
        String name = "Professor";

        List<Professor> professors = profRepository.findByNameContainingIgnoreCase(name);

        professors.forEach(System.out::println);
    }

    @Test
    public void findByDepartmentId() {
        Long departmentId = 1L;

        List<Professor> professors = profRepository.findByDepartmentId(departmentId);

        professors.forEach(System.out::println);
    }
    
	
}
