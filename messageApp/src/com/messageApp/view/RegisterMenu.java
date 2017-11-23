package com.messageApp.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class RegisterMenu extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterMenu frame = new RegisterMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegisterMenu() {
		setTitle("蜡笔畅聊");
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources/images/tittle.jpeg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("用户账户");
		label.setFont(new Font("黑体", Font.PLAIN, 14));
		label.setBounds(30, 41, 64, 21);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(115, 41, 122, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("用户昵称");
		label_1.setFont(new Font("黑体", Font.PLAIN, 14));
		label_1.setBounds(30, 89, 64, 15);
		contentPane.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(115, 86, 122, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_2 = new JLabel("账户密码");
		label_2.setFont(new Font("黑体", Font.PLAIN, 14));
		label_2.setBounds(30, 135, 64, 15);
		contentPane.add(label_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(115, 132, 122, 21);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel = new JLabel("确认密码");
		lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 14));
		lblNewLabel.setBounds(30, 176, 64, 15);
		contentPane.add(lblNewLabel);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(115, 173, 122, 21);
		contentPane.add(passwordField_1);
		
		JLabel label_3 = new JLabel("用户性别");
		label_3.setFont(new Font("黑体", Font.PLAIN, 14));
		label_3.setBounds(30, 218, 64, 15);
		contentPane.add(label_3);
		
		JRadioButton radioButton = new JRadioButton("男");
		radioButton.setBounds(115, 214, 42, 23);
		contentPane.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("女");
		radioButton_1.setBounds(195, 214, 42, 23);
		contentPane.add(radioButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("用户头像");
		lblNewLabel_1.setFont(new Font("黑体", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(30, 261, 64, 15);
		contentPane.add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(115, 250, 122, 29);
		contentPane.add(comboBox);
		
		JLabel label_4 = new JLabel("个性签名");
		label_4.setFont(new Font("黑体", Font.PLAIN, 14));
		label_4.setBounds(30, 305, 64, 15);
		contentPane.add(label_4);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(115, 289, 122, 45);
		contentPane.add(textArea);
		
		JLabel lblNewLabel_2 = new JLabel(new ImageIcon("resources/images/111.gif"));
		lblNewLabel_2.setBounds(244, 23, 190, 331);
		contentPane.add(lblNewLabel_2);
		
		JButton button = new JButton("注册");
		button.setFont(new Font("黑体", Font.PLAIN, 14));
		button.setBounds(30, 364, 93, 23);
		contentPane.add(button);
	}
}
