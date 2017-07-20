package kr.or.reservation.dto;

public class NaverUserDTO {
	private String email;
	private String nickname;
	private String snsProfile;
	private String id;
	private String username;
	private int adminFlag;
	private String snsType;

	public NaverUserDTO() {
		// TODO Auto-generated constructor stub
	
	}

	public NaverUserDTO(String email, String nickname, String snsProfile, String id, String username) {
		super();
		this.email = email;
		this.nickname = nickname;
		this.snsProfile = snsProfile;
		this.id = id;
		this.username = username;
		// default 로 설정해줌
		this.adminFlag = 0;
		this.snsType = "naver";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSnsProfile() {
		return snsProfile;
	}

	public void setSnsProfile(String snsProfile) {
		this.snsProfile = snsProfile;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAdminFlag() {
		return adminFlag;
	}

	public void setAdminFlag(int adminFlag) {
		this.adminFlag = adminFlag;
	}

	public String getSnsType() {
		return snsType;
	}

	public void setSnsType(String snsType) {
		this.snsType = snsType;
	}

	@Override
	public String toString() {
		return "email : \"" + email + "\", nickname : \"" + nickname + "\", snsProfile : \"" + snsProfile
				+ "\", id : \"" + id + "\", username : \"" + username + "\", adminFlag : \"" + adminFlag
				+ "\", snsType : \"" + snsType;
	}



}
