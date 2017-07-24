package com.nantimes.vicloth.service;

import java.util.List;

import com.nantimes.vicloth.model.vo.LoginUserVM;
import com.nantimes.vicloth.model.vo.UserView;




public interface IUserManagerService {

	public UserView Find(int id);

	public void Delete(int id);

	public LoginUserVM Add(UserView view) ;
}
