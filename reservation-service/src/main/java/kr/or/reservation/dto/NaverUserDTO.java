package kr.or.reservation.dto;

public class NaverUserDTO {
	private String email;
	private String nickname;
	private String profileImage;
	private String age;
	private String gender;
	private String id;
	private String name;
	private String birthday;

	public NaverUserDTO() {
		// TODO Auto-generated constructor stub
	}

	public NaverUserDTO(String email, String nickname, String profileImage, String age, String gender, String id,
			String name, String birthday) {
		super();
		this.email = email;
		this.nickname = nickname;
		this.profileImage = profileImage;
		this.age = age;
		this.gender = gender;
		this.id = id;
		this.name = name;
		this.birthday = birthday;
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

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "email : \"" + email + "\", nickname : \"" + nickname + "\", profileImage : \"" + profileImage
				+ "\", age : \"" + age + "\", gender : \"" + gender + "\", id : \"" + id + "\", name : \"" + name
				+ "\", birthday : \"" + birthday+ "\"";
	}
	
	

}
