package connect.reservation.service;

import connect.reservation.domain.Users;
import connect.reservation.dto.NaverLoginUser;

public interface UsersService {
	public Users getSnsUser(int snsId);
	public int addSnsUser(NaverLoginUser user);
}
