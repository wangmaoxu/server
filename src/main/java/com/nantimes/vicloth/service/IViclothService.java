package com.nantimes.vicloth.service;

import java.util.List;

import com.nantimes.vicloth.model.vo.ViclothView;

public interface IViclothService {
	public List<ViclothView> selectBySubject(String subject);

}
