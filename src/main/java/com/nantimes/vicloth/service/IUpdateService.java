package com.nantimes.vicloth.service;

import java.util.List;

import com.nantimes.vicloth.pojo.Hair;
import com.nantimes.vicloth.pojo.Pose;
import com.nantimes.vicloth.pojo.Vicloth;

public interface IUpdateService {
	public void updateHair(List<Hair> hairs);
	
	public void updatePose(List<Pose> poses);
	
	
	public void updateVicloth(List<Vicloth> vicloths);

}
