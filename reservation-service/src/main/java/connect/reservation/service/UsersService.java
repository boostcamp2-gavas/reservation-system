package connect.reservation.service;

import connect.reservation.domain.Users;
import connect.reservation.dto.NaverLoginUser;

public interface UsersService {
	public Users getSnsUser(String snsId);
	public int addSnsUser(NaverLoginUser user);
	public int updateSnsUser(String snsId, String nickname, String profile);
	
	public Users getUserInfo(int userId);
}
