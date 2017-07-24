package connect.reservation.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import connect.reservation.dao.UsersDao;
import connect.reservation.domain.Users;
import connect.reservation.dto.NaverLoginUser;
import connect.reservation.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService {
	
	private UsersDao usersDao;
	
	
	@Autowired
	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Users getSnsUser(String snsId) {
		return usersDao.selectBySnsId(snsId);
	}
	
	@Override
	@Transactional(readOnly = false)
	public int addSnsUser(NaverLoginUser naverUser) {
		Users user = new Users();
		
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

	public String getDate(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
	}
	
	@Override
	public int updateSnsUser(String snsId, String nickname, String profile) {
		return usersDao.updateSnsUser(snsId, nickname, profile, getDate());
	}
	
	@Override
	public Users getUserInfo(int userId) {
		return usersDao.getUserInfo(userId);
	}
}
