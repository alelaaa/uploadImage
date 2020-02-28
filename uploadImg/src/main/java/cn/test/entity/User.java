package cn.test.entity;

public class User {

	private Integer id;
	private String userName;
	private String userImg;
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
	public String getUserImg() {
		return userImg;
	}
	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", userImg=" + userImg + "]";
	}
	
	
	
}
