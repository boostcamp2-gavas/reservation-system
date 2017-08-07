package kgw.reservation.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kgw.reservation.dao.FileDao;
import kgw.reservation.dao.UserCommentDao;
import kgw.reservation.dto.CommentStats;
import kgw.reservation.dto.FileImage;
import kgw.reservation.dto.UserComment;
import kgw.reservation.dto.UserCommentWrapper;

@Service
@Transactional(readOnly=true)
public class UserCommentService {
	private UserCommentDao userCommentDao;
	private FileDao fileDao;
	
	@Autowired
	public UserCommentService(UserCommentDao userCommentDao, FileDao fileDao) {
		this.userCommentDao = userCommentDao;
		this.fileDao = fileDao;
	}
	// 코멘트리스트+ 해당 제품의 평점 및 댓글 총 갯수를 반환합니다. 
	public UserCommentWrapper getCommentListByProductId(Integer productId, Integer offset, Integer size) {
		UserCommentWrapper userCommentWrapper = new UserCommentWrapper();
		
		List<UserComment> userCommentList= findCommentListWithImage(productId, offset, size);
		CommentStats commentStats = findCommentStatsByProductId(productId);
		
		userCommentWrapper.setUserCommentCollection(userCommentList);
		userCommentWrapper.setCommentStats(commentStats);
		
		return userCommentWrapper;
	}
	// 코멘트리스트를 가져오는데, 이미지가 있다면 추가해서 가져온다. 
	public List<UserComment> findCommentListWithImage(Integer productId, Integer offset, Integer size) {
		List<UserComment> userCommentList= userCommentDao.selectUserCommentByProductId(productId, offset, size);
		List<FileImage> commentFileList = null;
		Set<Integer> userIds = null;
		
		// 찾은 코멘트들의 userId값들을 가져오고, 코멘트들의 이미지를  찾습니다.
		if(userCommentList != null && !userCommentList.isEmpty()) {
			userIds = new HashSet<>();
			
			for(UserComment userComment : userCommentList) {			
				userIds.add(userComment.getUserId());
			}
			commentFileList = fileDao.selectJoinCommentImageByProductIdAndUserId(productId, userIds);
		}
		// 코멘트별로 이미지들이 있으면 설정함.
		if(commentFileList != null && !commentFileList.isEmpty()) {
			Map<Integer, List<FileImage>> commentIdFileImageMap = new HashMap<>();
			for(FileImage commentFile : commentFileList) {
				if(commentIdFileImageMap.get(commentFile.getReservationUserCommentId()) == null)
					commentIdFileImageMap.put(commentFile.getReservationUserCommentId(), new ArrayList<>());
				commentIdFileImageMap.get(commentFile.getReservationUserCommentId()).add(commentFile);
				
			}
			for(UserComment userComment : userCommentList) {
				if(commentIdFileImageMap.get(userComment.getId()) != null) {
					userComment.setCommentImageList(commentIdFileImageMap.get(userComment.getId()));
				}
			}
			
		}
		return userCommentList;
	}
	// 한 프로덕트의 평점과 댓글 총 갯수를 가져온다. 
	public CommentStats findCommentStatsByProductId(Integer productId) {
		return userCommentDao.selectStatsByProductId(productId);
	}
	
}
