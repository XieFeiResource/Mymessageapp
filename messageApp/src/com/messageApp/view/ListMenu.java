package com.messageApp.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
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
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import com.message.model.Messagebox;
import com.message.model.User;

import javax.swing.JButton;
import java.awt.Font;

public class ListMenu extends JFrame {
	private User loginuser;
	private JPanel contentPane;
	private JTree tree_1;
	private JTree tree_2;
	private ChatMenu chat;
	private ChatMenu chat1;
	private JTextArea textArea;//昵称
	private JLabel lblNewLabel;//头像
	private JTextArea textArea_1;//签名
	private JButton button;
	private JButton button_1;
	private ChatMenu  c;//点击群名时创建的一个窗口
	private ResearchMenu research;
	private ModifyMenu modifymenu;
	public static Messagebox message = null;;
	private Map<String, ChatMenu> allchatmenu = new HashMap<>();
	private Map<String, ChatMenu> allgroupmenu = new HashMap<>();
	public static DefaultTreeModel model;
	public static DefaultMutableTreeNode root;
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

	public JTree getTree_1() {
		return tree_1;
	}

	/**
	 * Create the frame.
	 */
	public ListMenu(User loginuser) {
		this.loginuser = loginuser;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources/images/tittle.jpeg"));
		setTitle("蜡笔畅聊");
		setResizable(false);// 不可改变窗体大小
		setSize(318, 633);
		setLocationRelativeTo(null);// 使Jframe运行时在桌面正中间显示
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 104, 312, 457);
		contentPane.add(tabbedPane);

		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("会话", new ImageIcon("resources/images/会话.png"), scrollPane, "会话");

		JTree tree = new JTree();
		scrollPane.setViewportView(tree);

		JScrollPane scrollPane_1 = new JScrollPane();
		tabbedPane.addTab("联系人", new ImageIcon("resources/images/联系人.png"), scrollPane_1, "联系人");

		root = new DefaultMutableTreeNode("root");
		Showfriendlist(loginuser);//抽象为方法，便于之后用
		tree_1 = new JTree(root);
		tree_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (e.getButton() == 1 && e.getClickCount() == 2) {
					TreePath path = ListMenu.this.tree_1.getSelectionPath();
					DefaultMutableTreeNode lastNode = (DefaultMutableTreeNode) path.getLastPathComponent();
					if (lastNode.isLeaf()) {
						// 上面是解析用户双击之后判断是不是双击的某一个用户名上的这个Node
						String username = lastNode.toString();
						String account = username.substring(username.indexOf("[") + 1, username.lastIndexOf("]"));
						if (allchatmenu.containsKey(account)) {
							allchatmenu.get(account).setVisible(true);
						} else {
							User receiver = new User();
							receiver.setAccount(account);
							receiver.setNicheng(username);
							chat = new ChatMenu(ListMenu.this.loginuser, receiver);
							chat.setVisible(true);
							allchatmenu.put(account, chat);
						}
					}
				}
			}
		});
		tree_1.setRootVisible(false);
		scrollPane_1.setViewportView(tree_1);

		JScrollPane scrollPane_2 = new JScrollPane();
		tabbedPane.addTab("群聊", new ImageIcon("resources/images/群聊.png"), scrollPane_2, "群聊");

		DefaultMutableTreeNode  root1=new DefaultMutableTreeNode("root");
		
		for(String groupName :loginuser.getMyGroups().keySet())
		{
			DefaultMutableTreeNode  group=new DefaultMutableTreeNode(groupName);
			root1.add(group);
		}
		tree_2 = new JTree(root1);
		tree_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2&&e.getButton()==1) {
					System.out.println("laile");
					ChatMenu  c=new ChatMenu(ListMenu.this.loginuser,tree_2.getSelectionPath().getLastPathComponent().toString());
					allgroupmenu.put(tree_2.getSelectionPath().getLastPathComponent().toString(), c);
					System.out.println("^^^  "+allgroupmenu.size());
					c.setVisible(true);
				}
			}
		});
		tree_2.setRootVisible(false);
		scrollPane_2.setViewportView(tree_2);

		lblNewLabel = new JLabel(new ImageIcon(loginuser.getImagepath()));
		lblNewLabel.setBounds(0, 0, 86, 100);
		lblNewLabel.setBorder(BorderFactory.createLineBorder(Color.red));
		contentPane.add(lblNewLabel);

		 textArea = new JTextArea(loginuser.getNicheng());
		textArea.setEditable(false);
		textArea.setBorder(BorderFactory.createLineBorder(Color.red));
		textArea.setBounds(86, 0, 108, 32);
		contentPane.add(textArea);

		textArea_1 = new JTextArea(loginuser.getQianming());
		textArea_1.setEditable(false);
		textArea_1.setBorder(BorderFactory.createLineBorder(Color.red));
		textArea_1.setBounds(86, 42, 168, 61);
		contentPane.add(textArea_1);
		
		button = new JButton("添加好友");
		button.setFont(new Font("黑体", Font.PLAIN, 14));
		button.setBounds(0, 571, 97, 23);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				research=new ResearchMenu(ListMenu.this ,ListMenu.this.loginuser);
				research.setVisible(true);
			}
		});
		contentPane.add(button);
		
		button_1 = new JButton("查找");
		button_1.setBounds(107, 571, 68, 23);
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("dianjiele ");
				research=new ResearchMenu();
				research.setVisible(true);
			}
		});
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("修改资料");
		button_2.setFont(new Font("黑体", Font.PLAIN, 12));
		button_2.setBounds(181, 571, 93, 23);
		button_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modifymenu=new ModifyMenu(ListMenu.this.loginuser);
				modifymenu.setVisible(true);
			}
		});
		contentPane.add(button_2);

		// 开一个线程用来接收其他用户发送来的消息
		(new Thread() {
			@Override
			public void run() {
				try {
					while ((message = (Messagebox) LoginMenu.in.readObject()) != null) {
						System.out.println("fromservermessage"+message);
						if(message.getMessagetype().equals("modifymessage")) {
							textArea.setText(message.getSender().getNicheng());
							lblNewLabel.setIcon(new ImageIcon(message.getSender().getImagepath()));
							textArea_1.setText(message.getSender().getQianming());
						}
						if(message.getMessagetype().equals("addfriend")) {
							DefaultMutableTreeNode friendgroup = new DefaultMutableTreeNode("新加好友");
							DefaultMutableTreeNode friend = new DefaultMutableTreeNode(
									message.getSender().getNicheng() + "[" + message.getSender().getAccount() + "]");
							friendgroup.add(friend);
							System.out.println(friend);
							root.add(friendgroup);
							tree_1.updateUI();
						}
						if(message.getMessagetype().equals("groupmessage")) {//以群名为单位开启窗口，而不像是和chatmessage一样以发送者为单位开启窗口。
						if (allgroupmenu.containsKey(message.getSender().getQianming())) {
							allgroupmenu.get(message.getSender().getQianming()).getTextArea().append(message.getSender().getNicheng() + "\t" + message.getTime()
								+ "\r\n" + message.getContent() + "\r\n" + "\r\n");
							allgroupmenu.get(message.getSender().getQianming()).setVisible(true);
						} else {
							chat1=new ChatMenu(ListMenu.this.loginuser, message.getSender().getQianming());
							chat1.getTextArea().append(message.getSender().getNicheng() + "\t" + message.getTime()
							+ "\r\n" + message.getContent() + "\r\n" + "\r\n");
							chat1.setVisible(true);
							allgroupmenu.put(message.getSender().getQianming(), chat1);
							System.out.println(allgroupmenu.size());
						}
						}else {
						
						if(message.getMessagetype().equals("research")) {
							if(message.getSender()!=null) {
								research.getTextArea_1().setText("查询结果如下！");
								research.getTextField_1().setText(message.getSender().getNicheng());
								research.getLblNewLabel().setIcon(new ImageIcon(message.getSender().getImagepath()));
								research.getTextArea().setText(message.getSender().getQianming());
							}else {
								research.getTextArea_1().setText("该账户不存在！");
							}
						}else {
						if (allchatmenu.containsKey(message.getSender().getAccount())) {
							if(message.getMessagetype().equals("chatmessage")) {
								chat.getTextArea().append(message.getSender().getNicheng() + "\t" + message.getTime()
								+ "\r\n" + message.getContent() + "\r\n" + "\r\n");
								allchatmenu.get(message.getSender().getAccount()).setVisible(true);
							}else if(message.getMessagetype().equals("shake")) {//判断来自服务器的消息类型，区别chatmesage.
								allchatmenu.get(message.getSender().getAccount()).setVisible(true);//先显示在开始抖动
								allchatmenu.get(message.getSender().getAccount()).shakestart();
							}
						} else {
							chat = new ChatMenu(ListMenu.this.loginuser, message.getSender());
							if(message.getMessagetype().equals("chatmessage")) {
								chat.getTextArea().append(message.getSender().getNicheng() + "\t" + message.getTime()
								+ "\r\n" + message.getContent() + "\r\n" + "\r\n");
								chat.setVisible(true);
							}else if(message.getMessagetype().equals("shake")) {
								chat.setVisible(true);
								chat.shakestart();
							}
							allchatmenu.put(message.getSender().getAccount(), chat);
						}
						}
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}

	public void Showfriendlist(User loginuser) {
		Map<String, HashSet<User>> friends = loginuser.getFriend();
		if(friends!=null) {
		Set<String> zuming = friends.keySet();
		Iterator<String> it = zuming.iterator();
		while (it.hasNext()) {
			String groupname = it.next();
			DefaultMutableTreeNode group = new DefaultMutableTreeNode(groupname);
			HashSet<User> members = friends.get(groupname);
			Iterator<User> it1 = members.iterator();
			while (it1.hasNext()) {
				User user = it1.next();
				DefaultMutableTreeNode friend = new DefaultMutableTreeNode(
						user.getNicheng() + "[" + user.getAccount() + "]");
				group.add(friend);
			}
			root.add(group);
		}
		}
	}
}
