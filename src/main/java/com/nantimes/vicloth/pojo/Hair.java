package com.nantimes.vicloth.pojo;

public class Hair {
	private int id ;
	private String url;
	private String hairId;
	private String color;
	private String gender;
	private int priority;
	private String enable;
	
	public Hair(){
		
	}
	public Hair(int id, String url, String hairId, String color,String gender, int priority,String enable){
		this.id = id;
		this.url = url;
		this.hairId = hairId;
		this.color = color;
		this.gender = gender;
		this.priority = priority;
		this.enable =enable;
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getHairId() {
		return hairId;
	}
	public void setHairId(String hairId) {
		this.hairId = hairId;
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
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	

}
