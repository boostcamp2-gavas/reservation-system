package connect.reservation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NaverLoginUser {
    private String email;
    private String nickname;
    @JsonProperty("profile_image")
    private String profileImage;
    private String id;
    private String name;
	
    
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
	public String getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
	@Override
	public String toString() {
		return "NaverLoginUser [email=" + email + ", nickname=" + nickname + ", profileImage=" + profileImage + ", id=" + id + ", name=" + name  + "]";
	}
	
}
