package com.riverplant.expertus.defi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Employees_projects {
	
	@Column(name = "project_id", nullable = false)
	private Long project_id;
	@Column(name = "employee_id", nullable = false)
	private Long employee_id;
	@Id
	@GeneratedValue
	private Long id;
	
	public Long getProject_id() {
		return project_id;
	}
	public void setProject_id(Long project_id) {
		this.project_id = project_id;
	}
	public Long getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(Long employee_id) {
		this.employee_id = employee_id;
	}
	
	
}
