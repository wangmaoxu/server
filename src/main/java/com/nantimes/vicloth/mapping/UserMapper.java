package com.nantimes.vicloth.mapping;



import com.nantimes.vicloth.pojo.User;




public interface UserMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	
	User FindSingle(String account) ;
	

}