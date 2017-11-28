package com.message.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.message.model.User;

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
		else {
		try {
			ObjectInputStream is=new ObjectInputStream(new FileInputStream("Databases/"+account+".qq"));
			user=(User) is.readObject();
			if(user.getAccount().equals(account)&&user.getPassword().equals(password)) {
				return user;
			}else	return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		}
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
		User user1=new User("111111", "111111", "张三", 3, "男", "resources/Usertuxiang", "你好张三");
		User user2=new User("444444", "444444", "李四", 4, "男", "resources/Usertuxiang", "你好李四");
	
		try {
			ObjectOutputStream  out=new ObjectOutputStream(new FileOutputStream("databases/"+user1.getAccount()+".qq"));
			out.writeObject(user1);
			out.flush();
			out.close();
			
			out=new ObjectOutputStream(new FileOutputStream("databases/"+user2.getAccount()+".qq"));
			out.writeObject(user2);
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
