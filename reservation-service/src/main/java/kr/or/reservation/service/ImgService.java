package kr.or.reservation.service;

import java.util.List;

import kr.or.reservation.dto.ImgDTO;

public interface ImgService {
	public List<ImgDTO> selectList(int id);
	public ImgDTO selectOne(long fileId);
}
