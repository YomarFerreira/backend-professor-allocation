package com.project.professor.allocation.service;

import java.text.ParseException;
import java.util.List;

import javax.swing.text.MaskFormatter;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Allocation;
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
		String cpfProf = professor.getCpf();
		try{
			if(validarCpf(cpfProf)) {
			professor = profRepository.save(professor);
			Department department = departService.findById(professor.getDepartmentId());
			professor.setDepartment(department);
			return professor;
			} else {
				return null;
			}
		}catch(Exception e){
			throw new RuntimeException();
		}
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

	
	public static boolean validarCpf(String validarCpf) throws Exception { //validar CPF
		if(validarCpf.isEmpty() || validarCpf==null || validarCpf.length()<11){
			return false;
		}else {
			//Calculando o 1ºnº do Dígito verificador
			int Val1digSumNineNum = 0, Val1digdigcal = 10, Val1DigitoVerificador;
			for(int i=1 ; i<10 ; i++) {
				String Val1digitoCal = validarCpf.substring(i-1,i);
				int Val1cpfDigit = Integer.valueOf(Val1digitoCal), Val1CalcNoveNum = Val1cpfDigit * Val1digdigcal;
				Val1digdigcal = Val1digdigcal-1;
				Val1digSumNineNum = Val1digSumNineNum + Val1CalcNoveNum;
			}
			int Val1DivSomaNoveNum = Val1digSumNineNum % 11;
			if(Val1DivSomaNoveNum == 1 || Val1DivSomaNoveNum == 0) {
				Val1DigitoVerificador = 0;
			}else {
				Val1DigitoVerificador = 11 - Val1DivSomaNoveNum;
			} 
			//Calculando o 2ºnº do Dígito verificador
			int Val2digSumNineNum = 0, Val2digdigcal = 11, Val2DigitoVerificador;
			for(int i=1 ; i<11 ; i++) {
				String Val2digitoCal = validarCpf.substring(i-1,i);
				int Val2cpfDigit = Integer.valueOf(Val2digitoCal), Val2CalcNoveNum = Val2cpfDigit * Val2digdigcal;
				Val2digdigcal = Val2digdigcal-1;
				Val2digSumNineNum = Val2digSumNineNum + Val2CalcNoveNum;
			}
			int Val2DivSomaNoveNum = Val2digSumNineNum % 11;
			if(Val2DivSomaNoveNum == 1 || Val2DivSomaNoveNum == 0) {
				Val2DigitoVerificador = 0;
			}else {
				Val2DigitoVerificador = 11 - Val2DivSomaNoveNum;
			}
			//Verificando Dígito verificador
			String validador = Val1DigitoVerificador +""+ Val2DigitoVerificador, digitosFinaisCpf = validarCpf.substring(9,11);
			if (validador.equals(digitosFinaisCpf)) {
			    try {
			        MaskFormatter mask = new MaskFormatter("###.###.###-##");
			        mask.setValueContainsLiteralCharacters(false);
					String cpfFormatado =  mask.valueToString(validarCpf);
			    } catch (ParseException ex) {
			    	String erro="erro";
			    }
				return true;
			}else {
				return false;
			}
		}
	}
	
}
