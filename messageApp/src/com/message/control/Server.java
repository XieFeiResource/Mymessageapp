package com.message.control;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.message.serverconfig.SocketConfig;

public class Server {
	private ServerSocket server;
	public Server() {
		try {
			server=new ServerSocket(SocketConfig.port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void StartService() {
		try {
			Socket client=server.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		
	}
}
