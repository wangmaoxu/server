package com.nantimes.vicloth.model.vo;


import java.util.Date;

import com.nantimes.vicloth.model.VResponse;
import com.nantimes.vicloth.pojo.User;



public class LoginUserVM extends VResponse{

	private int id;

	private String name;

	private int sex;
	
	private String phone;
	
	private String email;
	

	private String createtime;
	
	public LoginUserVM(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	

}
