package com.message.model;

import java.io.Serializable;

public class Messagebox implements Serializable{
	private User sender;
	private User receiver;
	private String messagetype;
	private String time;
	private String content;
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public User getReceiver() {
		return receiver;
	}
	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}
	public String getMessagetype() {
		return messagetype;
	}
	public void setMessagetype(String messagetype) {
		this.messagetype = messagetype;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Messagebox(User sender, User receiver, String messagetype, String time, String content) {
		super();
		this.sender = sender;
		this.receiver = receiver;
		this.messagetype = messagetype;
		this.time = time;
		this.content = content;
	}
	
	public Messagebox(User sender, String messagetype) {
		super();
		this.sender = sender;
		this.messagetype = messagetype;
	}
	public Messagebox() {
		super();
	}
	@Override
	public String toString() {
		return "Massagebox [sender=" + sender + ", receiver=" + receiver + ", messagetype=" + messagetype + ", time="
				+ time + ", content=" + content + "]";
	}
	
}
