package com.nantimes.vicloth.pojo;

public class Pose {
	private int id;
	private String roleId;
	private String motion;
	
	public Pose() {
	
	}
	public Pose(int id,String roleId,String motion) {
		this.id=id;
		this.roleId = roleId;
		this.motion = motion;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMotion() {
		return motion;
	}
	public void setMotion(String motion) {
		this.motion = motion;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	

}
