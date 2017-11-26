package com.message.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import com.massage.model.User;

public class DatabaseOperation {
	/*
	 * 修改用戶信息的方法
	 */
	public static Boolean Updatedata(User user) {
		try {
			ObjectOutputStream os=new ObjectOutputStream(new FileOutputStream("Databases/"+user.getAccount()+".qq"));
			os.writeObject(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	/*
	 * 注冊用戶的方法
	 */
	public static Boolean Register(User user) {
		File file=new File("Databases/"+user.getAccount()+".qq");
		if(file.exists()) return false;
		return Updatedata(user);
	}
	/*
	 * 用戶登露的方法
	 */
	public static User Login(String account,String password) {
		User user=null;
		File file=new File("Databases/"+account+".qq");
		if(!file.exists()) return null;
		try {
			ObjectInputStream is=new ObjectInputStream(new FileInputStream("Databases/"+account+".qq"));
			user=(User) is.readObject();
			if(user.getAccount().equals(account)&&user.getPassword().equals(password)) {
				return user;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}
	public static User searchFriendsByCondition(String account) {
		User user=null;
		File file=new File("Databases/"+account+".qq");
		if(!file.exists()) return null;
		ObjectInputStream is=null;
		try {
			is = new ObjectInputStream(new FileInputStream("Databases/"+account+".qq"));
			user=(User) is.readObject();
			return user;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static Boolean addFriend(User user, String account,String zuming) {
		User searcheduser=searchFriendsByCondition(account);
		Map<String, HashSet<User>> friend=user.getFriend();
		HashSet<User> fri=new HashSet<>();
		fri.add(searcheduser);
		friend=new HashMap<>();
		friend.put(zuming, fri);
		return true;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User user=new User("88888888", "88888888","企鹅" , "男", "resources/Usertuxiang/", "逗比蜡笔");
		//Register(user);
		//System.out.println(Login("88888888","88888888"));
		//System.out.println(searchFriendsByCondition("88888888"));
	}

}
