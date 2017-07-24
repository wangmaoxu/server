package com.nantimes.vicloth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nantimes.vicloth.common.utils.TextUitls;
import com.nantimes.vicloth.model.VResponse;
import com.nantimes.vicloth.model.vo.RenderView;
import com.nantimes.vicloth.service.IRenderService;

@Controller
@RequestMapping("/render")
public class RenderController {
	
	@Autowired
	IRenderService _rednerService;
	
	@RequestMapping(value = "/pose",method = RequestMethod.GET)
	@ResponseBody
	
	public RenderView renderByPose(@RequestParam String uuid, @RequestParam String roleId,@RequestParam String hairId){
		
		if(TextUitls.StringNotNull(hairId)){
			return _rednerService.renderByPoseWithUpdateHair(Integer.valueOf(uuid),roleId, hairId);
		}
		return _rednerService.renderByPose(Integer.valueOf(uuid), roleId);
	} 
	
	@RequestMapping(value = "/angle",method = RequestMethod.GET)
	@ResponseBody
	
	public RenderView renderByAngle(@RequestParam String uuid, @RequestParam String roleId,@RequestParam String hairId,@RequestParam String motionId){
		
		if(TextUitls.StringNotNull(hairId)){
			return _rednerService.renderFullAngleWithUpdateHair(Integer.valueOf(uuid),roleId,motionId, hairId);
		}
		return _rednerService.renderFullAngle(Integer.valueOf(uuid), roleId,motionId);
	} 
	
	@RequestMapping(value = "/bodyface",method = RequestMethod.GET)
	@ResponseBody 
	public VResponse renderFaceBody(@RequestParam String user,@RequestParam String height,@RequestParam String waist,@RequestParam String gender,@RequestParam String hairId){
			
		return _rednerService.renderFaceBody(user, height, waist, gender, hairId);
	}
}
