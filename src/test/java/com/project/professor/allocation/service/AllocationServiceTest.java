package com.project.professor.allocation.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Allocation;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class AllocationServiceTest {

	SimpleDateFormat sdf = new SimpleDateFormat("HH:mmZ");

	@Autowired
	AllocationService allocService;

	@Test
	public void save() throws ParseException {
		Allocation saveAlloc = new Allocation();
		saveAlloc.setId(null);
		saveAlloc.setDay(DayOfWeek.WEDNESDAY);
		saveAlloc.setStart(sdf.parse("19:00-0300"));
		saveAlloc.setEnd(sdf.parse("20:00-0300"));
		saveAlloc.setProfessorId(1L);
		saveAlloc.setCourseId(1L);

		saveAlloc = allocService.create(saveAlloc);

		System.out.println(saveAlloc);
	}

	@Test
	public void update() throws ParseException {
		Allocation updateAlloc = new Allocation();
		updateAlloc.setId(1L);
		updateAlloc.setDay(DayOfWeek.MONDAY);
		updateAlloc.setStart(sdf.parse("19:00-0300"));
		updateAlloc.setEnd(sdf.parse("20:00-0300"));
		updateAlloc.setProfessorId(1L);
		updateAlloc.setCourseId(1L);

		updateAlloc = allocService.update(updateAlloc);

		System.out.println(updateAlloc);
	}

	@Test
	public void deleteById() {
		Long id = 1L;

		allocService.deleteById(id);
	}

	@Test
	public void deleteAll() {
		allocService.deleteAll();
	}

	@Test
	public void findAll() {
		List<Allocation> allocations = allocService.findAll();
		allocations.forEach(System.out::println);
	}

	@Test
	public void findByProfessor() {
		Long id = 1L;
		List<Allocation> allocations = allocService.findByProfessor(id);
		allocations.forEach(System.out::println);
	}

	@Test
	public void findByCourse() {
		Long id = 1L;
		List<Allocation> allocations = allocService.findByCourse(id);
		allocations.forEach(System.out::println);
	}

	@Test
	public void findById() {
		Long id = 1L;
		Allocation allocations = allocService.findById(id);
		System.out.println(allocations);
	}

}
