package com.nantimes.vicloth.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nantimes.vicloth.mapping.HairMapper;
import com.nantimes.vicloth.mapping.PoseMapper;
import com.nantimes.vicloth.mapping.ViclothMapper;
import com.nantimes.vicloth.pojo.Hair;
import com.nantimes.vicloth.pojo.Pose;
import com.nantimes.vicloth.pojo.Vicloth;
import com.nantimes.vicloth.service.IUpdateService;

@Service("updeteService")
public class UpdateServiceImpl implements IUpdateService{
	@Resource
	HairMapper _hairMapper;
	
	@Resource 
	PoseMapper _poseMapper;
	
	@Resource
	ViclothMapper _viclothMapper;

	@Override
	public void updateHair(List<Hair> hairs) {
		_hairMapper.insertOrUpdate(hairs);
		
	}

	@Override
	public void updatePose(List<Pose> poses) {
		_poseMapper.insertOrUpdate(poses);
		
	}

	@Override
	public void updateVicloth(List<Vicloth> vicloths) {
		_viclothMapper.insertOrUpdate(vicloths);
		
	}

}
