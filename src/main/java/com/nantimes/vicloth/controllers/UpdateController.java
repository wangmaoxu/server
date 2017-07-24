package com.nantimes.vicloth.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nantimes.vicloth.common.utils.XLSParser;
import com.nantimes.vicloth.model.VResponse;
import com.nantimes.vicloth.pojo.Hair;
import com.nantimes.vicloth.pojo.Pose;
import com.nantimes.vicloth.pojo.Vicloth;
import com.nantimes.vicloth.service.IUpdateService;

@Controller()
@RequestMapping("/update")
public class UpdateController {
	
	@Autowired
	IUpdateService updateService;
	
	VResponse _vResponse;
	
	private  UpdateController() {
		_vResponse = new VResponse();
	}
	
	@ResponseBody
	@RequestMapping(value="/hair", method = RequestMethod.GET)
	public VResponse updateHair(){
		List<Hair> hairs = XLSParser.getAllHairByExcel("/home/wmx/asset/hair.xls");
		
		updateService.updateHair(hairs);
		
		return _vResponse;
	} 
	
	@ResponseBody
	@RequestMapping(value="/pose", method = RequestMethod.GET)
	public VResponse updatePose(){
		List<Pose> poses = XLSParser.getAllPoseByExcel("/home/wmx/asset/pose.xls");
		
		updateService.updatePose(poses);
		
		return _vResponse;
	} 
	
	
	@ResponseBody
	@RequestMapping(value="/vicloth",method = RequestMethod.GET)
	public VResponse updateVicloth(){
		
		List<Vicloth> vicloths = XLSParser.getAllViclothByExcel("/home/wmx/asset/vicloth.xls");
		updateService.updateVicloth(vicloths);
		return _vResponse;
	}
}
