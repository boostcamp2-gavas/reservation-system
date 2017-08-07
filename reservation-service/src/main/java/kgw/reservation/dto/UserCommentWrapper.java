package kgw.reservation.dto;

import java.util.List;

public class UserCommentWrapper {
	private List<UserComment> userCommentList;
	private CommentStats commentStats;
	
	public List<UserComment> getUserCommentList() {
		return userCommentList;
	}
	public void setUserCommentList(List<UserComment> userCommentList) {
		this.userCommentList = userCommentList;
	}
	@Override
	public String toString() {
		return "UserCommentWrapper [userCommentList=" + userCommentList + ", commentStats=" + commentStats + "]";
	}
	public CommentStats getCommentStats() {
		return commentStats;
	}
	public void setCommentStats(CommentStats commentStats) {
		this.commentStats = commentStats;
	}
	
}
