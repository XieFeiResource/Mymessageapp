package com.messageApp.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;

import com.message.model.Messagebox;
import com.message.model.User;

public class ResearchMenu extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton button;
	private JButton button_1;
	private JTextField textField_1;
	private JTextArea textArea;
	private JLabel lblNewLabel;
	private JTextArea textArea_1;
	private JLabel label_1;
	private User loginuser;
	private ListMenu listmenu;

	public JTextField getTextField_1() {
		return textField_1;
	}

	public JTextField getTextField() {
		return textField;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}

	public JTextArea getTextArea_1() {
		return textArea_1;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResearchMenu frame = new ResearchMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public ResearchMenu(ListMenu listmenu,User loginuser) {
		this();
		this.loginuser=loginuser;
		this.listmenu=listmenu;
	}
	/**
	 * Create the frame.
	 */
	public ResearchMenu() {
		setBounds(100, 100, 450, 300);
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources/images/tittle.jpeg"));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("请输入查找账号");
		label.setFont(new Font("黑体", Font.PLAIN, 14));
		label.setBounds(23, 23, 98, 15);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(140, 20, 154, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(126, 88, 126, 21);
		textField_1.setEditable(false);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		button = new JButton("查找");
		button.setFont(new Font("黑体", Font.PLAIN, 14));
		button.setBounds(310, 19, 93, 23);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String account=textField.getText();
				Messagebox researchbox=new Messagebox();
				researchbox.setContent(account);
				researchbox.setMessagetype("research");
				try {
					LoginMenu.out.writeObject(researchbox);
					LoginMenu.out.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(button);
		
		 lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(23, 88, 86, 100);
		lblNewLabel.setBorder(BorderFactory.createLineBorder(Color.red));
		contentPane.add(lblNewLabel);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(126, 119, 199, 72);
		contentPane.add(textArea);
		
		button_1 = new JButton("添加");
		button_1.setFont(new Font("黑体", Font.PLAIN, 14));
		button_1.setBounds(85, 217, 93, 23);
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//包装发送的消息盒子，包括发送，接收者和消息类型
				if(ListMenu.message.getSender()!=null) {
				Messagebox message=new Messagebox();
				message.setSender(loginuser);
				/*
				 * 此处的ListMenu.message.getSender()是上面通过查找好友，服务器返回的查找信息，
				 * 其中的sender是要加的好友信息
				 */
				message.setReceiver(ListMenu.message.getSender());
				message.setMessagetype("addfriend");
				try {//将消息发送到服务器
					LoginMenu.out.writeObject(message);
					LoginMenu.out.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//现在己方添加
				DefaultMutableTreeNode friend = new DefaultMutableTreeNode(
						ListMenu.message.getSender().getNicheng() + "[" + ListMenu.message.getSender().getAccount() + "]");
				System.out.println("^^^"+friend);
				ListMenu.root.add(friend);
				ResearchMenu.this.listmenu.getTree_1().updateUI();
				
				}else {
					JOptionPane.showMessageDialog(ResearchMenu.this, "该账户不存在，不能添加！", "温馨提示", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		contentPane.add(button_1);
		
		textArea_1 = new JTextArea();
		textArea_1.setBounds(85, 54, 261, 24);
		textArea_1.setBorder(BorderFactory.createLineBorder(Color.red));
		contentPane.add(textArea_1);
		
		label_1 = new JLabel("查询结果");
		label_1.setFont(new Font("黑体", Font.PLAIN, 12));
		label_1.setBounds(23, 48, 54, 30);
		contentPane.add(label_1);
		
	}
}
