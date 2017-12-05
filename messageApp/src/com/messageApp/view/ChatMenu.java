package com.messageApp.view;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import com.message.model.Messagebox;
import com.message.model.User;
import java.awt.Font;

public class ChatMenu extends JFrame {

	private JPanel contentPane;
	private final JScrollPane scrollPane = new JScrollPane();
	private JButton btnNewButton;
	private JTextArea textArea_1;
	private JTextArea textArea;
	private User user;
	private User receiver;
	private JButton button;
	private String quming;
	private AllbuttonListener buttonlistener;

	public JTextArea getTextArea() {
		return textArea;
	}

	{
		buttonlistener = new AllbuttonListener();
	}

	public ChatMenu(User user, User receiver) {
		this();
		this.user = user;
		this.receiver = receiver;
		setTitle("和"+receiver.getNicheng()+"畅聊中");
	}

	public ChatMenu(User user, String quming) {
		this();
		this.user = user;
		this.quming = quming;
		setTitle(user.getNicheng()+"在"+quming+"群聊中");
	}
	/**
	 * Create the frame.
	 */
	public ChatMenu() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources/images/tittle.jpeg"));
		setBounds(100, 100, 517, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		scrollPane.setBounds(0, 0, 327, 227);
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 270, 327, 81);
		contentPane.add(scrollPane_1);

		textArea_1 = new JTextArea();
		textArea_1.setToolTipText("");
		scrollPane_1.setViewportView(textArea_1);

		JPanel panel = new JPanel();
		panel.setBounds(0, 361, 327, 31);
		contentPane.add(panel);
		panel.setLayout(null);

		btnNewButton = new JButton("发送");
		btnNewButton.setBounds(191, 0, 63, 30);
		btnNewButton.addActionListener(buttonlistener);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("关闭");
		btnNewButton_1.setBounds(264, 0, 63, 30);
		panel.add(btnNewButton_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 227, 327, 40);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		button = new JButton("抖动");
		button.setFont(new Font("黑体", Font.PLAIN, 14));
		button.setBounds(0, 10, 67, 23);
		button.addActionListener(buttonlistener);
		panel_1.add(button);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(330, 0, 171, 392);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 171, 392);
		panel_2.add(lblNewLabel);
	}

	class AllbuttonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnNewButton) {
				// 得到发送的信息，包装成messagebox;
				String chatmessage = textArea_1.getText();
				Messagebox chatmessagebox = new Messagebox();
				chatmessagebox.setSender(user);
				if(ChatMenu.this.receiver==null) {//包装群消息有发送者，消息类型，消息内容，群名放在了发送者的签名中。
					chatmessagebox.setMessagetype("groupmessage");
					chatmessagebox.setContent(chatmessage);
					user.setQianming(quming);
				}else {
				chatmessagebox.setContent(chatmessage);
				chatmessagebox.setReceiver(receiver);
				chatmessagebox.setMessagetype("chatmessage");
				}
				// 发送消息到服务器
				try {
					LoginMenu.out.writeObject(chatmessagebox);
					LoginMenu.out.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				textArea.append(user.getNicheng() + "\t" + new Date().toLocaleString() + "\r\n" + chatmessage + "\r\n"
						+ "\r\n");
				textArea_1.setText("");
			}else if(e.getSource()==button) {
				System.out.println("dianjile");
				Messagebox shakebox=new Messagebox();
				shakebox.setSender(user);
				shakebox.setReceiver(receiver);
				shakebox.setMessagetype("shake");
				try {
					LoginMenu.out.writeObject(shakebox);
					LoginMenu.out.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//自身先抖动
				shakestart();
			}
		}

	}
	public void shakestart() {
		Shakewindow shakestart=new Shakewindow();
		shakestart.start();
		
	}
	class Shakewindow extends Thread{
		int currentx=ChatMenu.this.getX();
		int currenty=ChatMenu.this.getY();
		
		int fudu=10;
		@Override
		public void run() {
			ChatMenu.this.setLocation(currentx-fudu, currenty);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ChatMenu.this.setLocation(currentx, currenty+fudu);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ChatMenu.this.setLocation(currentx+fudu, currenty);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ChatMenu.this.setLocation(currentx, currenty-fudu);
			ChatMenu.this.setLocation(currentx, currenty);
		}
	}
}
