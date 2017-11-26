package com.massage.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;

public class User implements Serializable{
	private String account;
	private String password;
	private String nicheng;
	private String gender;
	private String imagepath;
	private String qianming;
	private Map<String,HashSet<User>> friend;
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNicheng() {
		return nicheng;
	}
	public void setNicheng(String nicheng) {
		this.nicheng = nicheng;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getImagepath() {
		return imagepath;
	}
	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}
	public String getQianming() {
		return qianming;
	}
	public void setQianming(String qianming) {
		this.qianming = qianming;
	}
	public Map<String, HashSet<User>> getFriend() {
		return friend;
	}
	public void setFriend(Map<String, HashSet<User>> friend) {
		this.friend = friend;
	}
	public User(String account, String password, String nicheng, String gender, String imagepath, String qianming) {
		super();
		this.account = account;
		this.password = password;
		this.nicheng = nicheng;
		this.gender = gender;
		this.imagepath = imagepath;
		this.qianming = qianming;
	}
	
	public User(String account, String password) {
		super();
		this.account = account;
		this.password = password;
	}
	public User() {
		super();
	}
	@Override
	public String toString() {
		return "User [acount=" + account + ", password=" + password + ", nicheng=" + nicheng + ", gender=" + gender
				+ ", imagepath=" + imagepath + ", qianming=" + qianming + "]";
	}
	
}
