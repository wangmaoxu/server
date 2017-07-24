package com.nantimes.vicloth.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import com.nantimes.vicloth.mapping.ViclothMapper;
import com.nantimes.vicloth.model.vo.ViclothView;
import com.nantimes.vicloth.pojo.Vicloth;
import com.nantimes.vicloth.service.IViclothService;

@Service("ViclothService")
public class ViclothServiceImpl implements IViclothService{
	@Resource ViclothMapper _ViclothMapper;
	
	Mapper _mapper;
	
	private  ViclothServiceImpl() {
			_mapper = new DozerBeanMapper();
	}

	@Override
	public List<ViclothView> selectBySubject(String subject) {
		List<Vicloth> vicloths = _ViclothMapper.findBySubject(subject);
		List<ViclothView> viclothViews = new ArrayList<>();
		for(Vicloth v: vicloths){
			ViclothView viclothView = _mapper.map(v, ViclothView.class);
			viclothViews.add(viclothView);
		}
		return viclothViews;
	}

}
