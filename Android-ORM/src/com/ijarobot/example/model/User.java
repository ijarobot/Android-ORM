package com.ijarobot.example.model;

import java.io.Serializable;

import com.ijarobot.orm.annotation.Column;
import com.ijarobot.orm.annotation.Id;
import com.ijarobot.orm.annotation.Table;

/**
 * 用户
 * @author ijarobot (java2eer@gmail.com)
 * 
 * */
@Table(name="e_user")
public class User implements Serializable {

	private static final long serialVersionUID = -6774157612742552848L;

	@Id
	@Column(name="userID", lenght = 0, type = "")
	private int userID;
	
	@Column(name="userName", lenght = 0, type = "")
	private String 	userName;
	
	@Column(name="password", lenght = 0, type = "")
	private String 	password;
	
	@Column(name="signature", lenght = 0, type = "")
	private String 	signature;
	
	@Column(name="userDesc", lenght = 0, type = "")
	private String 	userDesc;
	
	
	@Column(name="face", lenght = 0, type = "")
	private String face;
	
	@Column(name="cityID", lenght = 0, type = "")
	private int cityID;
	
	@Column(name="cityName", lenght = 0, type = "")
	private String cityName;
	
	/** 是否自动登录  **/
	@Column(name="autoLogin", lenght = 0, type = "")
	private Boolean autoLogin;
	
	/** 是否当前用户  **/
	@Column(name="currUser", lenght = 0, type = "")
	private Boolean	currUser;

	/** 自动加载图片 */
	@Column(name="autoDownPic", lenght = 0, type = "")
	private Boolean	autoDownPic;
	
	private String citySlug;
	private int postsNum;
	private Boolean admin;
	private Boolean superMod;
	private int userLevel;
	private Boolean authed;
	private long lastVisit;
	private int credit;
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getUserDesc() {
		return userDesc;
	}
	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}
	public String getFace() {
		return face;
	}
	public void setFace(String face) {
		this.face = face;
	}
	public int getCityID() {
		return cityID;
	}
	public void setCityID(int cityID) {
		this.cityID = cityID;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public Boolean getAutoLogin() {
		return autoLogin;
	}
	public void setAutoLogin(Boolean autoLogin) {
		this.autoLogin = autoLogin;
	}
	public Boolean getCurrUser() {
		return currUser;
	}
	public void setCurrUser(Boolean currUser) {
		this.currUser = currUser;
	}
	public Boolean getAutoDownPic() {
		return autoDownPic;
	}
	public void setAutoDownPic(Boolean autoDownPic) {
		this.autoDownPic = autoDownPic;
	}
	public String getCitySlug() {
		return citySlug;
	}
	public void setCitySlug(String citySlug) {
		this.citySlug = citySlug;
	}
	public int getPostsNum() {
		return postsNum;
	}
	public void setPostsNum(int postsNum) {
		this.postsNum = postsNum;
	}
	public Boolean getAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	public Boolean getSuperMod() {
		return superMod;
	}
	public void setSuperMod(Boolean superMod) {
		this.superMod = superMod;
	}
	public int getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}
	public Boolean getAuthed() {
		return authed;
	}
	public void setAuthed(Boolean authed) {
		this.authed = authed;
	}
	public long getLastVisit() {
		return lastVisit;
	}
	public void setLastVisit(long lastVisit) {
		this.lastVisit = lastVisit;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}

}
