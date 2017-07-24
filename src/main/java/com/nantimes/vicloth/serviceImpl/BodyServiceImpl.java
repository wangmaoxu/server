package com.nantimes.vicloth.serviceImpl;

import javax.annotation.Resource;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import com.nantimes.vicloth.mapping.BodyMapper;
import com.nantimes.vicloth.mapping.UserMapper;
import com.nantimes.vicloth.model.vo.BodyView;
import com.nantimes.vicloth.model.vo.UserView;
import com.nantimes.vicloth.pojo.Body;
import com.nantimes.vicloth.pojo.User;
import com.nantimes.vicloth.service.IBodyService;

@Service("bodyService")
public class BodyServiceImpl implements IBodyService{
	
	@Resource
	BodyMapper _bodyMapper;
	
	@Resource
	UserMapper _UserMapper;
	
	Mapper _mapper;
	
	private  BodyServiceImpl() {
		_mapper =new DozerBeanMapper();
	}

	@Override
	public BodyView find(int id) {
		Body body = _bodyMapper.selectByPrimaryKey(id);
		BodyView bodyView = _mapper.map(body, BodyView.class);
	
		return bodyView;
	}

	@Override
	public UserView update(BodyView bodyView) {
		Body body  = _mapper.map(bodyView, Body.class);
		_bodyMapper.updateByPrimaryKey( body);
		User user =_UserMapper.selectByPrimaryKey(body.getId());
		return _mapper.map(user, UserView.class);
		
	}



}
