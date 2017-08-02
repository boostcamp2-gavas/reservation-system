package kr.or.reservation.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.reservation.dao.UserDao;
import kr.or.reservation.dao.UserReservationDao;
import kr.or.reservation.dto.ReservationTypeCountDTO;
import kr.or.reservation.dto.UserReservationDTO;
import kr.or.reservation.service.UserReservationService;

@Service
public class UserReservationImpl implements UserReservationService {

	Logger log = Logger.getLogger(this.getClass());

	UserReservationDao userReservationDao;
	UserDao userDao;

	// DB에서 가져올떄 ENUM으로 수정해 볼것.
	protected enum Type {
		EXPECTATION(0), CONFIRMED(1), USED(2), CANCELLATION(3);
		protected int type;

		Type(int type) {
			this.type = type;
		}

		int getType() {
			return type;
		}
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setUserReservationDao(UserReservationDao userReservationDao) {
		this.userReservationDao = userReservationDao;
	}

	@Override
	public List<UserReservationDTO> selectReservationByType(int userId, int type) {
		// TODO Auto-generated method stub
		if (userId <= 0 || type < 0) {
			return null;
		}
		return userReservationDao.selectReservationByType(userId, type);

	}

	@Override
	public boolean cancelReservation(int userId, int reservationId) {
		if (userId <= 0 || reservationId <= 0) {
			return false;
		}
		log.info(" userID ::  " + userId + "  reservationId :: " + reservationId);
		return userReservationDao.cancelReservation(userId, reservationId);
	}

	public Map<String, Integer> selectTypeCount(int userId) {
		if (userId <= 0) {
			return null;
		}
		List<ReservationTypeCountDTO> list = userReservationDao.selectTypeCount(userId);
		convertToMap(list);
		return convertToMap(list);
	}

	// 넘겨 받은 List로 알맞게 변환
	private Map<String, Integer> convertToMap(List<ReservationTypeCountDTO> list) {
		int type, count;
		int all = 0, expectataion = 0, used = 0, cancellation = 0;

		Map<String, Integer> map = new HashMap<>();
		for (ReservationTypeCountDTO dto : list) {
			type = dto.getReservationType();
			count = dto.getCount();
			all += count;
			log.info(Type.EXPECTATION.equals(type));
			log.info("신청 code " + Type.EXPECTATION.getType());
			log.info("type code " + type);

			// Type.EXPECTATION.ordinal()
			if (Type.EXPECTATION.getType() == type || Type.CONFIRMED.getType() == type) {
				expectataion += count;
			} else if (Type.USED.getType() == type) {
				used += count;
			} else {
				cancellation += count;
			}
		}
		map.put("All", all);
		map.put("EXPECTATION", expectataion);
		map.put("USED", used);
		map.put("CANCELLATION", cancellation);
		return map;

	}
	@Override
	public List<UserReservationDTO> selectReservationAll(int userId){
		if (userId <= 0 ) {
			return null;
		}
		return userReservationDao.selectReservationAll(userId);
	}

}
