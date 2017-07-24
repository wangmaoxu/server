package com.nantimes.vicloth.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import com.nantimes.vicloth.mapping.HairMapper;
import com.nantimes.vicloth.model.vo.HairView;
import com.nantimes.vicloth.pojo.Hair;
import com.nantimes.vicloth.service.IHairService;

@Service("hairService")
public class HairServiceImpl implements IHairService{
	
	@Resource 
	HairMapper _hairMapper;
	
	Mapper _mapper ;
	

	private  HairServiceImpl() {

		_mapper=new DozerBeanMapper();	
	}
	

	@Override
	public List<HairView> selectByGender(String gender, String color) {
		List<Hair> hairs = _hairMapper.FindByColor(color, gender);
		List<HairView> hairViews = new ArrayList<>();
		for(Hair hair:hairs){
			HairView hairView = _mapper.map(hair, HairView.class);
			hairViews.add(hairView);
		}
		
		
		return hairViews;
	}

}
