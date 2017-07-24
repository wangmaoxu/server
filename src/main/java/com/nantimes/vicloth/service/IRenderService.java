package com.nantimes.vicloth.service;

import com.nantimes.vicloth.model.VResponse;
import com.nantimes.vicloth.model.vo.RenderView;

public interface IRenderService {
	public VResponse renderFaceBody(String user ,String _hight,String _waist,String gendr,String hairId);
	
	public RenderView renderByPose(int id,String roleId);
	
	public RenderView renderFullAngle(int id,String roleId,String motion);
	
	public RenderView renderByPoseWithUpdateHair(int id, String roleId, String hair);
	
	public RenderView renderFullAngleWithUpdateHair(int id,String roleId,String motion,String hairId);

}
