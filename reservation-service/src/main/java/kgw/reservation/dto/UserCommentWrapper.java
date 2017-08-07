package kgw.reservation.dto;

import java.util.Collection;

public class UserCommentWrapper {
	private Collection<UserComment> userCommentCollection;
	private CommentStats commentStats;
	
	public Collection<UserComment> getUserCommentCollection() {
		return userCommentCollection;
	}
	public void setUserCommentCollection(Collection<UserComment> userCommentCollection) {
		this.userCommentCollection = userCommentCollection;
	}
	public CommentStats getCommentStats() {
		return commentStats;
	}
	public void setCommentStats(CommentStats commentStats) {
		this.commentStats = commentStats;
	}
	
}
