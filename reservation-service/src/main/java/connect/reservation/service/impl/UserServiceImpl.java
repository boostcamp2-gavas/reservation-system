package connect.reservation.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import connect.reservation.dao.UsersDao;
import connect.reservation.domain.User;
import connect.reservation.dto.NaverLoginUser;
import connect.reservation.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private UsersDao usersDao;
	
	
	@Autowired
	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}
	
	@Override
	@Transactional(readOnly = true)
	public User getSnsUser(String snsId) {
		return usersDao.selectBySnsId(snsId);
	}
	
	@Override
	@Transactional(readOnly = false)
	public int addSnsUser(NaverLoginUser naverUser) {
		User user = new User();
		
		user.setUsername(naverUser.getName());
		user.setEmail(naverUser.getEmail());
		user.setNickname(naverUser.getNickname());
		user.setSnsId(naverUser.getId());
		user.setSnsType("Naver");
		user.setSnsProfile(naverUser.getProfileImage());
		user.setAdminFlag(0);
		user.setCreateDate(getDate());
		
		return usersDao.insert(user);
	}
	
	@Override
	public Timestamp getDate(){
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return java.sql.Timestamp.valueOf(sdf.format(timestamp));
	}
	
	@Override
	public int updateSnsUser(String snsId, String nickname, String profile) {
		return usersDao.updateSnsUser(snsId, nickname, profile, getDate());
	}
	
	@Override
	public User getUserInfo(int userId) {
		return usersDao.getUserInfo(userId);
	}
}
