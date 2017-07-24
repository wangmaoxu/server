package com.nantimes.vicloth.serviceImpl;

import javax.annotation.Resource;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import com.nantimes.vicloth.mapping.UserMapper;
import com.nantimes.vicloth.model.vo.LoginUserVM;
import com.nantimes.vicloth.pojo.User;
import com.nantimes.vicloth.service.ILoginService;

@Service("LoginService")
public class LoginServiceImpl implements ILoginService {
	@Resource
	private UserMapper _userDao;
	
	Mapper _mapper;
	
	private  LoginServiceImpl() {
		_mapper = new DozerBeanMapper();
	}



	@Override
	public Boolean CheckPassword(String sqlpassword, String password)  {
		if(sqlpassword.equals(password)){
			return true;
		}
		return false;
	}



	@Override
	public LoginUserVM Login(String username, String password) {
		User user = _userDao.FindSingle(username);
		
		if(user !=null && CheckPassword(user.getPassword(), password)){
			LoginUserVM loginUserVM = _mapper.map(user, LoginUserVM.class);
			loginUserVM.setMessage("200");
			return loginUserVM;
			
		}
		LoginUserVM  emptyVM= new LoginUserVM();
		emptyVM.setStatusCode("400");
		emptyVM.setMessage("’À∫≈ªÚ’ﬂ√‹¬Î¥ÌŒÛ ");
	
		return emptyVM;
	}

}
