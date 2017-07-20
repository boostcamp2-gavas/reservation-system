package connect.reservation.domain;

public class Users {
	
	private int id;
	private String username;
	private String email;
	private String tel;
	private String nickname;
	private String snsId;
	private String snsType;
	private String snsProfile;
	private int adminFlag;
	private String createDate;
	private String modifyDate;
	
	public Users() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public int getAdminFlag() {
		return adminFlag;
	}
	public void setAdminFlag(int adminFlag) {
		this.adminFlag = adminFlag;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	
	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", email=" + email + ", tel=" + tel + ", nickname="
				+ nickname + ", snsId=" + snsId + ", snsType=" + snsType + ", snsProfile=" + snsProfile + ", adminFlag="
				+ adminFlag + ", createDate=" + createDate + ", modifyDate=" + modifyDate + "]";
	}
	
}
