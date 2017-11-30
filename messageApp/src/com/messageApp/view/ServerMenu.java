package com.messageApp.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.crypto.Data;

import com.message.control.DatabaseOperation;
import com.message.model.Messagebox;
import com.message.model.User;
import com.message.serverconfig.SocketConfig;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class ServerMenu extends JFrame {
	private Allbuttonlistener buttonlistener;
	private JPanel contentPane;
	private JButton button;
	private ServerSocket server;
	private Map<String, ObjectOutputStream> allonlineuser = new HashMap<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerMenu frame = new ServerMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// 初始化监听器
	{
		buttonlistener = new Allbuttonlistener();
	}

	/**
	 * Create the frame.
	 */
	public ServerMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 676, 442);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("在线用户列表");
		lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 10, 179, 28);
		contentPane.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 91, 278, 188);
		contentPane.add(scrollPane);

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		JLabel lblNewLabel_1 = new JLabel("\t登录IP");
		lblNewLabel_1.setFont(new Font("黑体", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 75, 103, 15);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("用户昵称");
		lblNewLabel_2.setFont(new Font("黑体", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(198, 75, 73, 15);
		contentPane.add(lblNewLabel_2);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setToolTipText("基本控制");
		tabbedPane.setBounds(10, 283, 640, 110);
		contentPane.add(tabbedPane);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("基本控制", null, panel_1, null);
		panel_1.setLayout(null);

		button = new JButton("开启服务器");
		button.setBackground(Color.LIGHT_GRAY);
		button.setFont(new Font("黑体", Font.PLAIN, 14));
		button.setBounds(35, 21, 109, 23);
		button.addActionListener(buttonlistener);
		panel_1.add(button);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("高级控制", null, panel_2, null);
		panel_2.setLayout(null);

		JLabel label = new JLabel("所有用户发送的消息列表");
		label.setFont(new Font("黑体", Font.PLAIN, 14));
		label.setBounds(366, 17, 191, 15);
		contentPane.add(label);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(308, 47, 342, 250);
		contentPane.add(scrollPane_1);

		JTextArea textArea_1 = new JTextArea();
		scrollPane_1.setViewportView(textArea_1);
	}

	/*
	 * 为所有button添加一个监听器
	 */
	class Allbuttonlistener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == button) {
				try {
					server = new ServerSocket(SocketConfig.port);// 开启服务器
					JOptionPane.showMessageDialog(ServerMenu.this, "服务器开启成功！", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
					button.setEnabled(false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				new Thread() {
					public void run() {
						while (true) {
							try {
								Socket c = server.accept();
								System.out.println(c.getInetAddress());
								ObjectOutputStream out = new ObjectOutputStream(c.getOutputStream());// 一定要是out在上，不然会报错
								ObjectInputStream in = new ObjectInputStream(c.getInputStream());
								CurrentClientStread thisClientThread = new CurrentClientStread(in, out);
								thisClientThread.start();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				}.start();
			}
		}
	}

	class CurrentClientStread extends Thread {
		private ObjectInputStream in;
		private ObjectOutputStream out;
		private Messagebox m;

		public CurrentClientStread(ObjectInputStream in, ObjectOutputStream out) {
			this.in = in;
			this.out = out;
		}

		@Override
		public void run() {
			try {
				while (true) {
					m = (Messagebox) in.readObject();
					System.out.println(m);
					if (m.getMessagetype().equals("login")) {
						Dologinmessage(m);
					} else if (m.getMessagetype().equals("register")) {
						Doregistermessage(m);
					} else if (m.getMessagetype().equals("chatmessage")) {
						Dochatmessage(m);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		private void Dologinmessage(Messagebox m) {
			User loginuser = DatabaseOperation.Login(m.getSender().getAccount(), m.getSender().getPassword());
			if (loginuser != null) {
				allonlineuser.put(loginuser.getAccount(), out);
			}
			System.out.println(allonlineuser.size());
			Messagebox resultmessagebox = new Messagebox();
			resultmessagebox.setReceiver(loginuser);
			try {
				out.writeObject(resultmessagebox);
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		private void Doregistermessage(Messagebox m) {
			Boolean result = DatabaseOperation.Register(m.getSender());
			Messagebox resultmessagebox = new Messagebox();
			resultmessagebox.setContent(result.toString());
			try {
				out.writeObject(resultmessagebox);
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		private void Dochatmessage(Messagebox m) {
			User receiver = m.getReceiver();
			String account = receiver.getAccount();
			User receiver1 = DatabaseOperation.searchFriendsByCondition(account);
			ObjectOutputStream out = allonlineuser.get(account);
			try {
				Messagebox message = new Messagebox();
				message.setContent(m.getContent());
				message.setTime(new Date().toLocaleString());
				message.setSender(m.getSender());
				out.writeObject(message);
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}