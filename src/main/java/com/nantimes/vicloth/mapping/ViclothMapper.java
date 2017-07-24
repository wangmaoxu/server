package com.nantimes.vicloth.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nantimes.vicloth.pojo.Vicloth;

public interface ViclothMapper {
	
	void insertOrUpdate(@Param("vicloth") List<Vicloth> vicloths);
	
	Vicloth findByRole(@Param("roleId") String roleId);
	
	List<Vicloth> findBySubject(@Param("subject") String subject);


}
