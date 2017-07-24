         package com.nantimes.vicloth.service;

import com.nantimes.vicloth.model.vo.LoginUserVM;

public interface ILoginService {
	public LoginUserVM Login(String username, String password) ;

	public Boolean CheckPassword(String sqlpassword, String password) ;

}
