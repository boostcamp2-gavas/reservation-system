package kgw.reservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kgw.reservation.dao.UserDao;
import kgw.reservation.domain.User;
import kgw.reservation.dto.NaverLoginProfile;

@Service
@Transactional
public class UserService {
	private UserDao userDao;
	
	@Autowired
	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public User login(NaverLoginProfile naverLoginProfile) {
		User selected = userDao.selectBySnsId(naverLoginProfile.getId());
		if(selected != null )
			return selected;
		// 새로 생성 
		return create(naverLoginProfile);
	}
	
	private User create(NaverLoginProfile naverLoginProfile) {
		User user = new User(naverLoginProfile);
		user.setId(userDao.insert(user));
		return user;
	}
}
