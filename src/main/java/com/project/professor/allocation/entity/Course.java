package com.project.professor.allocation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Course {

	@Id
	@JsonProperty(access=JsonProperty.Access.READ_ONLY)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name= "name", nullable=false, unique=true)
	private String name;
	
	
	public Course(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	
	public Course() {}

	
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }


	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + "]";
	}

	
}
