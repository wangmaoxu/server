package com.nantimes.vicloth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nantimes.vicloth.model.VResponse;
import com.nantimes.vicloth.model.vo.LoginUserVM;
import com.nantimes.vicloth.service.ILoginService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	ILoginService _loginService;
	
	
	@RequestMapping(value="/login",method = RequestMethod.GET)
	@ResponseBody
	public LoginUserVM login(@RequestParam String username, @RequestParam String password) {
		
		
		return _loginService.Login(username, password);
	}

}
