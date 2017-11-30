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

public class ChatMenu extends JFrame {

	private JPanel contentPane;
	private final JScrollPane scrollPane = new JScrollPane();
	private JButton btnNewButton;
	private JTextArea textArea_1;
	private JTextArea textArea;
	private User user;
	private User receiver;
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

	}

	/**
	 * Create the frame.
	 */
	public ChatMenu() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources/images/tittle.jpeg"));
		setTitle("蜡笔畅聊");
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
				chatmessagebox.setContent(chatmessage);
				chatmessagebox.setSender(user);
				chatmessagebox.setReceiver(receiver);
				chatmessagebox.setMessagetype("chatmessage");
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
			}
		}

	}
}
