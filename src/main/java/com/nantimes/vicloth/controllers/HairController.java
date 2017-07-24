package com.nantimes.vicloth.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nantimes.vicloth.model.vo.HairView;
import com.nantimes.vicloth.service.IHairService;

@Controller
@RequestMapping("/hair")
public class HairController {
	
	@Autowired
	IHairService _hairService;
	
	@RequestMapping(value="/color",method= RequestMethod.GET)
	@ResponseBody
	public List<HairView>  selectByColor(@RequestParam("gender") String gender,@RequestParam("color") String color){
		
			return _hairService.selectByGender(gender, color);
	}

}
