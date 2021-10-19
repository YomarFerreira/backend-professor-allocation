package com.project.professor.allocation.service;

import java.util.Date;
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
		} else {
			return null;
		}
	}

	private Allocation save(Allocation allocation) {

		if (!conflictTime(allocation) && !errorTime(allocation)) {
			Professor professor = profesService.findById(allocation.getProfessorId());
			Course course = cursService.findById(allocation.getCourseId());
			Allocation saveAlloc = allocRepository.save(allocation);
			saveAlloc.setProfessor(professor);
			saveAlloc.setCourse(course);
			return saveAlloc;
        } else {
			throw new RuntimeException();
        }		
		
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
	
	
	private boolean conflictTime(Allocation alloc) { //Horário inicial ou final estiver colidindo com horário do mesmo professor
		boolean conflictTime = false;
	
		Professor prof = profesService.findById(alloc.getProfessorId());
		List <Allocation> listAllocations = allocRepository.findByProfessorId(prof.getId());

		if (!listAllocations.isEmpty()) {
			for(int i=0; i <= listAllocations.size(); i++){
				if ((listAllocations.get(i).getDay().equals(alloc.getDay()))) {
					//estiver enter o horario inicia e o horario final (inclusive)
					Date timeStartList = listAllocations.get(i).getStart();
					Date timeEndList = listAllocations.get(i).getEnd();
					Date timeStartNew = alloc.getStart();
					Date timeEndNew = alloc.getEnd();
					//((Arg1.compareTo(Arg2)>=0)) Arg1<Arg2(retorna<0) Arg1>Arg2(retorna>0) Arg1=Arg2(retorna=0)
					if (((timeStartNew.compareTo(timeStartList))>=0 && (timeStartNew.compareTo(timeEndList))<=0) || 
						((timeEndNew.compareTo(timeStartList))>=0 && (timeEndNew.compareTo(timeEndList))<=0)){
						conflictTime = true;	
						break;
					}
				}
			}
		}
		return conflictTime;
	}	
	
	
	private boolean errorTime(Allocation alloc) { //Horário nullo ou h.inicial maior que h.final
		boolean errorTime = false;
		
				Date timeStart = alloc.getStart();
				Date timeEnd = alloc.getEnd();
				//((Arg1.compareTo(Arg2)>=0)) Arg1<Arg2(retorna<0) Arg1>Arg2(retorna>0) Arg1=Arg2(retorna=0)
				if (timeStart == null || timeEnd == null || timeStart.compareTo(timeEnd)>0){
					errorTime = true;
				}
		return errorTime;
	}	
	
}
