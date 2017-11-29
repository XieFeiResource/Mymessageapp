package com.messageApp.view;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import com.message.model.User;

public class ListMenu extends JFrame {
	private User loginuser;
	private JPanel contentPane;
	private JTree tree_1;
	private ChatMenu chat;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListMenu frame = new ListMenu(null);
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
	public ListMenu(User loginuser) {
		this.loginuser=loginuser;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources/images/tittle.jpeg"));
		setTitle("蜡笔畅聊");
		setResizable(false);//不可改变窗体大小
		setSize(318, 633);
		setLocationRelativeTo(null);//使Jframe运行时在桌面正中间显示
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 104, 312, 503);
		contentPane.add(tabbedPane);
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("会话",new ImageIcon("resources/images/会话.png"), scrollPane, "会话");
		
		JTree tree = new JTree();
		scrollPane.setViewportView(tree);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		tabbedPane.addTab("联系人", new ImageIcon("resources/images/联系人.png"), scrollPane_1, "联系人");
		
		DefaultMutableTreeNode root=new DefaultMutableTreeNode("root");
		Map<String, HashSet<User>> friends=loginuser.getFriend();
		Set<String> zuming=friends.keySet();
		Iterator<String> it=zuming.iterator();
		while(it.hasNext()) {
			String groupname=it.next();
			DefaultMutableTreeNode group=new DefaultMutableTreeNode(groupname);
			HashSet<User> members=friends.get(groupname);
			Iterator<User> it1=members.iterator();
			while(it1.hasNext()) {
				User user=it1.next();
				DefaultMutableTreeNode friend=new DefaultMutableTreeNode(user.getNicheng());
				group.add(friend);
			}
			root.add(group);
		}
		tree_1 = new JTree(root);
		tree_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getButton()==1&&e.getClickCount()==2) {
					TreePath  path=ListMenu.this.tree_1.getSelectionPath();
					DefaultMutableTreeNode lastNode=(DefaultMutableTreeNode)path.getLastPathComponent();
					if(lastNode.isLeaf()) {
						//上面是解析用户双击之后判断是不是双击的某一个用户名上的这个Node
						String username=lastNode.toString();
						System.out.println(username);
						if(chat==null) {
						chat=new ChatMenu();
						chat.setVisible(true);
						}
					}
				}
			}
		});
		tree_1.setRootVisible(false);
		scrollPane_1.setViewportView(tree_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		tabbedPane.addTab("群聊",  new ImageIcon("resources/images/群聊.png"), scrollPane_2, "群聊");
		
		JTree tree_2 = new JTree();
		scrollPane_2.setViewportView(tree_2);
		
		JLabel lblNewLabel = new JLabel(new ImageIcon(loginuser.getImagepath()));
		lblNewLabel.setBounds(0, 0, 86, 100);
		contentPane.add(lblNewLabel);
		
		JTextArea textArea = new JTextArea(loginuser.getNicheng());
		textArea.setEditable(false);
		textArea.setBounds(86, 0, 108, 32);
		contentPane.add(textArea);
		
		JTextArea textArea_1 = new JTextArea(loginuser.getQianming());
		textArea_1.setEditable(false);
		textArea_1.setBounds(86, 39, 176, 61);
		contentPane.add(textArea_1);
	}
}
