package com.nantimes.vicloth.service;

import com.nantimes.vicloth.model.vo.BodyView;
import com.nantimes.vicloth.model.vo.UserView;
import com.nantimes.vicloth.pojo.Body;

public interface IBodyService {
	public BodyView find(int id);
	
	public UserView update(BodyView body);
	

}
