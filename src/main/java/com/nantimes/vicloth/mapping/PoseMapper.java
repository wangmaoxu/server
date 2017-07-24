package com.nantimes.vicloth.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nantimes.vicloth.pojo.Pose;

public interface PoseMapper {


	
	Pose findByRole(@Param("roleId") String roleId) ;
	
	void insertOrUpdate(@Param("pose") List<Pose> pose);

}
