package com.curso.spring.entity;

public class AuditoriaDetails {

	private String createdBy;
	private String roleName;
	
	public AuditoriaDetails() {
		
	}

	public AuditoriaDetails(String createdBy, String roleName) {
		super();
		this.createdBy = createdBy;
		this.roleName = roleName;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
	
}
