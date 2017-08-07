package kgw.reservation.domain;

import java.util.Date;

import kgw.reservation.dto.NaverLoginProfile;

public class User {
	private Integer id;
	private String userName;
	private String email;
	private String tel;
	private String nickname;
	private String snsId;
	private String snsType;
	private String snsProfile;
	private Integer adminFlag;
	private Date createDate;
	private Date modifyDate;
	
	public User() {
		
	}
	public User (NaverLoginProfile naverLoginProfile) {
		this.userName = naverLoginProfile.getName();
		this.email = naverLoginProfile.getEmail();
		this.nickname = naverLoginProfile.getNickname();
		this.snsId = naverLoginProfile.getId().toString();
		this.snsType = "Naver";
		this.snsProfile = naverLoginProfile.getProfileImage();
		//사용자 
		this.adminFlag = 0;
		this.createDate = new Date();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSnsId() {
		return snsId;
	}
	public void setSnsId(String snsId) {
		this.snsId = snsId;
	}
	public String getSnsType() {
		return snsType;
	}
	public void setSnsType(String snsType) {
		this.snsType = snsType;
	}
	public String getSnsProfile() {
		return snsProfile;
	}
	public void setSnsProfile(String snsProfile) {
		this.snsProfile = snsProfile;
	}
	public Integer getAdminFlag() {
		return adminFlag;
	}
	public void setAdminFlag(Integer adminFlag) {
		this.adminFlag = adminFlag;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	
}
