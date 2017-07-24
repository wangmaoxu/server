package com.nantimes.vicloth.controllers;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nantimes.vicloth.model.vo.ViclothView;
import com.nantimes.vicloth.service.IViclothService;

@Controller
@RequestMapping("/vicloth")
public class ViclothController {
	@Autowired
	IViclothService _vIViclothService;
	
	@RequestMapping(value="/subject",method=RequestMethod.GET)
	@ResponseBody
	public List<ViclothView> loadBySubject(@RequestParam String subject){
		String subjectUtf8=null;
		try {
			subjectUtf8 =new String(subject.getBytes("iso8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return _vIViclothService.selectBySubject(subjectUtf8);
		
	}

}
