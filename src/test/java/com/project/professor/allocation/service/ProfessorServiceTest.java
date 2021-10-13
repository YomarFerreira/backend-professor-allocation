package com.project.professor.allocation.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Professor;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class ProfessorServiceTest {

	@Autowired
	ProfessorService profService;

	@Test
	public void save() {
		Professor professor = new Professor();
		professor.setId(null);
		professor.setName("Professor 1");
		professor.setCpf("111.111.111-11");
		professor.setDepartmentId(1L);

		professor = profService.create(professor);

		System.out.println(professor);
	}

	@Test
	public void update() {
		Professor professor = new Professor();
		professor.setId(1L);
		professor.setName("Professor 2");
		professor.setCpf("222.222.222-22");
		professor.setDepartmentId(1L);

		professor = profService.update(professor);

		System.out.println(professor);
	}

	@Test
	public void deleteById() {
		Long id = 1L;
		profService.deleteById(id);

		Professor professor = profService.findById(id);
		System.out.println(professor);
	}

	@Test
	public void deleteAll() {
		profService.deleteAll();

		List<Professor> professors = profService.findAll(null);
		professors.forEach(System.out::println);
	}

	@Test
	public void findAll() {
		List<Professor> professors = profService.findAll(null);

		professors.forEach(System.out::println);
	}

	@Test
	public void findAllByName() {
		String name = "professor";
		List<Professor> professors = profService.findAll(name);

		professors.forEach(System.out::println);
	}

	@Test
	public void findByDepartment() {
		Long departmentId = 1L;
		List<Professor> professors = profService.findByDepartment(departmentId);

		professors.forEach(System.out::println);
	}

	@Test
	public void findById() {
		Long id = 1L;
		Professor professor = profService.findById(id);

		System.out.println(professor);
	}

}
