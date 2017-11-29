package com.messageApp.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.message.model.Messagebox;
import com.message.model.User;

public class RegisterMenu extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JRadioButton radioButton;
	private JComboBox comboBox;
	private JTextArea textArea;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private LoginMenu loginmenu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterMenu frame = new RegisterMenu(null, null, null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public RegisterMenu(ObjectInputStream in, ObjectOutputStream out, LoginMenu loginmenu) {
		this();
		this.out = out;
		this.in = in;
		this.loginmenu = loginmenu;
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
		label.setBounds(30, 10, 64, 21);
		contentPane.add(label);

		textField = new JTextField();
		textField.setBounds(115, 10, 122, 21);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel label_1 = new JLabel("用户昵称");
		label_1.setFont(new Font("黑体", Font.PLAIN, 14));
		label_1.setBounds(30, 52, 64, 15);
		contentPane.add(label_1);

		textField_1 = new JTextField();
		textField_1.setBounds(115, 49, 122, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel label_2 = new JLabel("账户密码");
		label_2.setFont(new Font("黑体", Font.PLAIN, 14));
		label_2.setBounds(30, 90, 64, 15);
		contentPane.add(label_2);

		passwordField = new JPasswordField();
		passwordField.setBounds(115, 87, 122, 21);
		contentPane.add(passwordField);

		JLabel lblNewLabel = new JLabel("确认密码");
		lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 14));
		lblNewLabel.setBounds(30, 127, 64, 15);
		contentPane.add(lblNewLabel);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(115, 124, 122, 21);
		contentPane.add(passwordField_1);

		JLabel label_3 = new JLabel("用户性别");
		label_3.setFont(new Font("黑体", Font.PLAIN, 14));
		label_3.setBounds(30, 205, 64, 15);
		contentPane.add(label_3);

		radioButton = new JRadioButton("男");
		radioButton.setBounds(112, 201, 42, 23);
		contentPane.add(radioButton);

		JRadioButton radioButton_1 = new JRadioButton("女");
		radioButton_1.setBounds(193, 201, 42, 23);
		contentPane.add(radioButton_1);
		ButtonGroup g = new ButtonGroup();
		g.add(radioButton);
		g.add(radioButton_1);

		JLabel label_5 = new JLabel("用户年龄");
		label_5.setFont(new Font("黑体", Font.PLAIN, 14));
		label_5.setBounds(30, 170, 64, 15);
		contentPane.add(label_5);

		textField_2 = new JTextField();
		textField_2.setBounds(115, 167, 122, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("用户头像");
		lblNewLabel_1.setFont(new Font("黑体", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(30, 250, 64, 15);
		contentPane.add(lblNewLabel_1);

		String[] tuxiangpath = { "resources/tu/tx1.jpg", "resources/tu/tx2.jpg", "resources/tu/tx3.jpg" };
		comboBox = new JComboBox(tuxiangpath);
		comboBox.setBounds(115, 230, 122, 49);
		contentPane.add(comboBox);

		JLabel label_4 = new JLabel("个性签名");
		label_4.setFont(new Font("黑体", Font.PLAIN, 14));
		label_4.setBounds(30, 305, 64, 15);
		contentPane.add(label_4);

		textArea = new JTextArea();
		textArea.setBounds(115, 289, 122, 45);
		contentPane.add(textArea);

		JLabel lblNewLabel_2 = new JLabel(new ImageIcon("resources/images/111.gif"));
		lblNewLabel_2.setBounds(244, 23, 190, 331);
		contentPane.add(lblNewLabel_2);

		JButton button = new JButton("注册");
		button.setFont(new Font("黑体", Font.PLAIN, 14));
		button.setBounds(30, 364, 93, 23);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 1.获取表单信息
				String account = textField.getText();
				String nicheng = textField_1.getText();
				String password = passwordField.getText();
				String age = textField_2.getText();
				String gender = radioButton.isSelected() ? "男" : "女";
				String imagepath = (String) comboBox.getSelectedItem();
				String qianming = textArea.getText();
				int age1 = Integer.parseInt(age);
				User registeruser = new User(account, password, nicheng, age1, gender, imagepath, qianming);
				Messagebox registermessage = new Messagebox(registeruser, "register");
				// 3.发送服务器注册。
				try {
					RegisterMenu.this.out.writeObject(registermessage);
					RegisterMenu.this.out.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// 4.接收服务器返回的消息。
				try {
					Messagebox resultmesagebox = (Messagebox) in.readObject();
					String result = resultmesagebox.getContent();
					if (result.equals("false")) {
						JOptionPane.showMessageDialog(RegisterMenu.this, "该用户已存在！请重新输入账户", "温馨提示",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(RegisterMenu.this, "注册成功！去登陆", "温馨提示",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(button);

		JButton button_1 = new JButton("去登陆");
		button_1.setFont(new Font("黑体", Font.PLAIN, 14));
		button_1.setBounds(155, 364, 93, 23);
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				loginmenu.setVisible(true);
				RegisterMenu.this.setVisible(false);
			}
		});
		contentPane.add(button_1);
	}

}
