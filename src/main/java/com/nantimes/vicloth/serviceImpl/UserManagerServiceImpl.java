package com.nantimes.vicloth.serviceImpl;


import javax.annotation.Resource;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import com.nantimes.vicloth.mapping.BodyMapper;
import com.nantimes.vicloth.mapping.UserMapper;
import com.nantimes.vicloth.model.vo.LoginUserVM;
import com.nantimes.vicloth.model.vo.UserView;
import com.nantimes.vicloth.pojo.Body;
import com.nantimes.vicloth.pojo.User;
import com.nantimes.vicloth.service.IUserManagerService;


@Service("userManagerService") 
public class UserManagerServiceImpl implements IUserManagerService {

	@Resource
	UserMapper _UserDao;
	
	@Resource BodyMapper _BodyDao;


	Mapper _mapper ;
	

	private  UserManagerServiceImpl() {

		_mapper=new DozerBeanMapper();	
	}

	


	@Override
	public UserView Find(int id) {
		// TODO Auto-generated method stub
		User model = _UserDao.selectByPrimaryKey(id);
		return (UserView) _mapper.map(model,UserView.class);
	}

	@Override
	public void Delete(int id) {
		// TODO Auto-generated method stub
		_UserDao.deleteByPrimaryKey(id);
	}

	@Override
	public LoginUserVM Add(UserView userView){
		// TODO Auto-generated method stub
		LoginUserVM loginUserVM = new LoginUserVM();
		
		
		if (_UserDao.FindSingle(userView.getAccount()) == null) {
			User model= (User) _mapper.map(userView,User.class);
			_UserDao.insert(model);
			Body body = new Body();
			body.setId(model.getId());
			_BodyDao.insert(body);
			loginUserVM.setStatusCode("200");
			loginUserVM.setMessage("µ«¬º≥…π¶");
			loginUserVM.setId(model.getId());
			
		}else {
			loginUserVM.setStatusCode("400");
			loginUserVM.setMessage("’ÀªßªÚ√‹¬Î¥ÌŒÛ");
		}

		return loginUserVM;
	}

}
