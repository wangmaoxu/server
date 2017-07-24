package com.nantimes.vicloth.mapping;

import org.apache.ibatis.annotations.Param;

import com.nantimes.vicloth.pojo.Body;


public interface BodyMapper {
	int insert(Body body);
	
	Body selectByPrimaryKey(Integer id);
	
	int updateByPrimaryKeySelective(Body body);

	int updateByPrimaryKey(Body body);
	
	int updateHair(@Param("id") int id,@Param("hair")String hair);

}
