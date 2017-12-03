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
import java.util.Set;

import com.message.model.Messagebox;
import com.message.model.User;

public class DatabaseOperation {
	/*
	 * 修改用戶信息的方法
	 */
	public static Boolean Updatedata(User user) {
		try {
			ObjectOutputStream os = new ObjectOutputStream(
					new FileOutputStream("Databases/" + user.getAccount() + ".qq"));
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
		File file = new File("Databases/" + user.getAccount() + ".qq");
		if (file.exists())
			return false;
		return Updatedata(user);
	}

	/*
	 * 用戶登露的方法
	 */
	public static User Login(String account, String password) {
		User user = null;
		File file = new File("Databases/" + account + ".qq");
		if (!file.exists())
			return null;
		else {
			try {
				ObjectInputStream is = new ObjectInputStream(new FileInputStream("Databases/" + account + ".qq"));
				user = (User) is.readObject();
				if (user.getAccount().equals(account) && user.getPassword().equals(password)) {
					return user;
				} else
					return null;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
	}

	public static User searchFriendsByCondition(String account) {
		User user = null;
		File file = new File("Databases/" + account + ".qq");
		if (!file.exists())
			return null;
		ObjectInputStream is = null;
		try {
			is = new ObjectInputStream(new FileInputStream("Databases/" + account + ".qq"));
			user = (User) is.readObject();
			return user;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static Messagebox addFriend(Messagebox m) {
		if(m.getReceiver()!= null) {
		Map<String, HashSet<User>> friend = m.getSender().getFriend();//获取发送者的好友列表
		HashSet<User> fri = new HashSet<>();
		fri.add(m.getReceiver());
		friend.put("新加好友", fri);
		
		Map<String, HashSet<User>> friend1 = m.getReceiver().getFriend();//获取接收者的好友列表
		HashSet<User> fri1 = new HashSet<>();
		fri1.add(m.getSender());
		friend1.put("新加好友", fri1);
		
		//将改好的好友列表信息，更新到发送，接受者的好友列表信息，即覆盖原有的好友列表信息
		m.getSender().setFriend(friend);
		m.getReceiver().setFriend(friend1);
		//将更新的信息写入到数据库
		ObjectOutputStream out;
		try {
			out = new ObjectOutputStream(
					new FileOutputStream("databases/" + m.getSender().getAccount() + ".qq"));
			out.writeObject(m.getSender());
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			out = new ObjectOutputStream(
					new FileOutputStream("databases/" + m.getReceiver().getAccount() + ".qq"));
			out.writeObject(m.getReceiver());
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
		}else {
			return null;
		}
	}

	public static void main(String[] args) {
		User user1 = new User("111111", "111111", "张三", 3, "男", "resources/tu/tx1.jpg", "你好张三");
		User user2 = new User("444444", "444444", "李四", 4, "男", "resources/tu/tx2.jpg", "你好李四");
		User user3 = new User("333333", "333333", "王五", 5, "男", "resources/tu/tx3.jpg", "你好王五");
		User user4 = new User("555555", "555555", "五哥", 6, "男", "resources/tu/tx1.jpg", "五哥最刚！");
		Map<String, HashSet<User>> friend = new HashMap<>();
		HashSet<User> value = new HashSet<>();
		HashSet<User> value2 = new HashSet<>();
		value2.add(user4);
		value.add(user2);
		//value.add(user3);
		friend.put("大学室友", value);
		friend.put("好友", value2);
		user1.setFriend(friend);

		Map<String, HashSet<User>> friend1 = new HashMap<>();
		HashSet<User> value1 = new HashSet<>();
		HashSet<User> value3 = new HashSet<>();
		value3.add(user4);
		value1.add(user2);
		//value1.add(user1);
		friend1.put("大学同学", value1);
		friend1.put("好友", value3);
		user3.setFriend(friend1);
		// 给user1配置群聊信息
		Map<String, HashSet<User>> myGroups = new HashMap<>();

		HashSet<User> qun1Friends = new HashSet<>();

		qun1Friends.add(user2);
		qun1Friends.add(user3);

		myGroups.put("吃鸡大队", qun1Friends);

		HashSet<User> qun2Friends = new HashSet<>();

		qun2Friends.add(user2);
		qun2Friends.add(user3);
		qun2Friends.add(user1);

		myGroups.put("王者车队", qun2Friends);
		user1.setMyGroups(myGroups);
		// 给user3配置群聊信息
		Map<String, HashSet<User>> myGroups1 = new HashMap<>();

		HashSet<User> qun1Friends1 = new HashSet<>();

		qun1Friends1.add(user1);
		qun1Friends1.add(user2);

		myGroups1.put("吃鸡大队", qun1Friends1);

		HashSet<User> qun2Friends2 = new HashSet<>();

		qun2Friends2.add(user1);
		qun2Friends2.add(user2);

		myGroups1.put("王者车队", qun2Friends2);
		user3.setMyGroups(myGroups1);

		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("databases/" + user1.getAccount() + ".qq"));
			out.writeObject(user1);
			out.flush();
			out.close();

			out = new ObjectOutputStream(new FileOutputStream("databases/" + user2.getAccount() + ".qq"));
			out.writeObject(user2);
			out.flush();
			out.close();

			out = new ObjectOutputStream(new FileOutputStream("databases/" + user3.getAccount() + ".qq"));
			out.writeObject(user3);
			out.flush();
			out.close();

			out = new ObjectOutputStream(new FileOutputStream("databases/" + user4.getAccount() + ".qq"));
			out.writeObject(user4);
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
