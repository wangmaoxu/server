package com.nantimes.vicloth.pojo;

public class Vicloth {
	private int id;
	private String subject;
	private String url; 
	private String role;
	private int priority;
	private String roleId;
	private String themeName;
	private String color;
	private String gender;
	private int assort =0;
	private String enable ;
	private String hat;
	
	
	public Vicloth(){
		
	}
	
	public Vicloth(int id,String subject, String url, String role
			,int priority,String roleId,String themeName
			,String color,String gender,int assort,String enable,String hat){
		this.id = id;
		this.subject = subject;
		this.url = url;
		this.role = role;
		this.roleId = roleId;
		this.priority = priority;
		this.themeName = themeName;
		this.color = color;
		this.gender = gender;
		this.assort = assort;
		this.enable = enable;
		this.hat = hat;
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getThemeName() {
		return themeName;
	}
	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAssort() {
		return assort;
	}

	public void setAssort(int assort) {
		this.assort = assort;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getHat() {
		return hat;
	}

	public void setHat(String hat) {
		this.hat = hat;
	}
	
	
	

}
