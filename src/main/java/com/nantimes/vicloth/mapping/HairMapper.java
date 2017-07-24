package com.nantimes.vicloth.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nantimes.vicloth.pojo.Hair;

public interface HairMapper {
	
	public List<Hair> FindByColor(@Param("color") String color,@Param("gender") String gender);
	
	public void insertOrUpdate(@Param("hair") List<Hair> hair);

	
}
