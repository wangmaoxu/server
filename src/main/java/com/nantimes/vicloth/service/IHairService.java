package com.nantimes.vicloth.service;

import java.util.List;

import com.nantimes.vicloth.model.vo.HairView;

public interface IHairService {
	
	public List<HairView> selectByGender(String gender,String color);

}
