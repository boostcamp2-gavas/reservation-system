package connect.reservation.service;

import java.sql.Timestamp;

import connect.reservation.domain.User;
import connect.reservation.dto.NaverLoginUser;

public interface UserService {
	public User getSnsUser(String snsId);
	public int addSnsUser(NaverLoginUser user);
	public int updateSnsUser(String snsId, String nickname, String profile);
	public Timestamp getDate();
	public User getUserInfo(int userId);
}
