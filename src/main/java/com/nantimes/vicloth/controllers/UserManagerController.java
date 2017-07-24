package com.nantimes.vicloth.controllers;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.nantimes.vicloth.model.VResponse;
import com.nantimes.vicloth.model.vo.LoginUserVM;
import com.nantimes.vicloth.model.vo.UserView;
import com.nantimes.vicloth.service.IUserManagerService;
import org.springframework.http.HttpStatus;


@Controller
@RequestMapping("/user")
public class UserManagerController {

	@Autowired 
	IUserManagerService _userManagerService;

	VResponse _bjuiResponse= new VResponse();


//	@ResponseBody
//	@RequestMapping(value="/add",method = RequestMethod.POST)
//	public BjuiResponse Add(UserView model){
//		try{
//			_userManagerService.AddOrUpdate(model);
//		}
//		catch (Exception e) {
//			_bjuiResponse.setMessage(e.getMessage());
//			_bjuiResponse.setStatusCode("300");
//		}
//		return _bjuiResponse;
//	}
	
	
	
	@RequestMapping(value="/add",method = RequestMethod.POST)
	@ResponseBody
	public LoginUserVM Add(@RequestBody UserView model){
		if(model == null){
			System.out.println(" modle is null");
		}else {
			System.out.println("account:"+model.getAccount());
		}
	
		LoginUserVM loginUserVM = _userManagerService.Add(model);
		return loginUserVM;
			
	}
	




	@ResponseBody
	@RequestMapping(value="/delete")
	public VResponse Delete(int id) {
		try {
			_userManagerService.Delete(id);
		}
		catch (Exception e) {
			_bjuiResponse.setMessage(e.getMessage());
			_bjuiResponse.setStatusCode("300");
		}
		return _bjuiResponse;
	}
}
