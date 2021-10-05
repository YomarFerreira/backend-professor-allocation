package com.project.professor.allocation.entity;

import java.time.DayOfWeek;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Alllocation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(value=EnumType.STRING)
	@Column(name= "day", nullable=false, unique=true)
	private DayOfWeek day;
	
	@Temporal(value=TemporalType.TIME)
	@Column(name= "start", nullable=false)
	private Date start;
	
	@Temporal(value=TemporalType.TIME)
	@Column(name= "end", nullable=false)
	private Date end;

	@Column(name= "course_id", nullable=false)
	private Long courseId;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "course_id", insertable = false, updatable = false, nullable = false)
	private Course course;
	
	@Column(name= "professor_id", nullable=false)
	private Long professorId;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "professor_id", insertable = false, updatable = false, nullable = false)
	private Professor professor;
	
	
	
	public Alllocation(Long id, DayOfWeek day, Date start, Date end) {
		super();
		this.id = id;
		this.day = day;
		this.start = start;
		this.end = end;
	}
	
	
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public DayOfWeek getDay() {	return day;	}
	public void setDay(DayOfWeek day) { this.day = day; }
	public Date getStart() { return start; }
	public void setStart(Date start) { this.start = start; }
	public Date getEnd() { return end; }
	public void setEnd(Date end) { this.end = end; }


	@Override
	public String toString() {
		return "Alllocation [id=" + id + ", day=" + day + ", start=" + start + ", end=" + end + "]";
	}
	
	
}
