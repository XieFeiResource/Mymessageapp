package com.messageApp.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
public class ChatMenu extends JFrame {

	private JPanel contentPane;
	private final JScrollPane scrollPane = new JScrollPane();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatMenu frame = new ChatMenu();
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
	public ChatMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 517, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		scrollPane.setBounds(0, 0, 327, 227);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 270, 327, 81);
		contentPane.add(scrollPane_1);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setToolTipText("");
		scrollPane_1.setViewportView(textArea_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 361, 327, 31);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("关闭");
		btnNewButton.setBounds(191, 0, 63, 30);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("发送");
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
}
