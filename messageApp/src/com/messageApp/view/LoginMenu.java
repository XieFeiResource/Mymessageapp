package com.messageApp.view;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.event.MouseInputListener;

import com.message.serverconfig.SocketConfig;

public class LoginMenu extends JFrame{
	private JLabel jb;
	private JLabel jb1;
	private JLabel jb2;
	private JLabel jb3;
	private JLabel jb4;
	private JLabel jb5;
	private JPanel c;
	private JComboBox jf;
	private JPasswordField jf1;
	private JCheckBox jc;
	private JCheckBox jc1;
	private JButton jbt;

	public LoginMenu() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setUndecorated(true);//使Jframe的上面的菜单隐藏
		setSize(280, 480);
		setVisible(true);
		setLocationRelativeTo(null);//使Jframe运行时在桌面正中间显示
		setLayout(null);
		c = (JPanel)getContentPane(); // 获取JFrame面板
		c.setOpaque(false);//把JPanel设置为透明 这样就不会遮住后面的背景 这样就能在JPanel随意加组件了
		MouseEventListener mouseListener = new MouseEventListener(this);
		c.addMouseListener(mouseListener);//给Jframe添加随鼠标移动事件
		c.addMouseMotionListener(mouseListener);
		init();
		setBackground(); // 调用背景方法
		paintComponents(getGraphics());
		printAll(getGraphics());
	}

	public void setBackground() {
		ImageIcon icon = new ImageIcon("resources/images/beijin.jpg"); //添加图片
		JLabel background = new JLabel(icon);
		this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));//把背景图片添加到分层窗格的最底层作为背景
		background.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
	}
	public void init() {
		jb = new JLabel("用户名: ");
		jb.setBounds(25, 220, 60, 25);
		c.add(jb);

		ImageIcon icon = new ImageIcon("resources/images/chacha.jpg");
		jb4 = new JLabel(icon);
		jb4.setBounds(250, 2, icon.getIconWidth(), icon.getIconHeight());
		jb4.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {

			}

			public void mousePressed(MouseEvent e) {

			}

			public void mouseExited(MouseEvent e) {

			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		c.add(jb4);
		ImageIcon icon1 = new ImageIcon("resources/images/suoxiao.jpg");
		jb5 = new JLabel(icon1);
		jb5.setBounds(218, 2, icon1.getIconWidth(), icon1.getIconHeight());
		jb5.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
				
			}
			
			public void mousePressed(MouseEvent e) {
				
			}
			
			public void mouseExited(MouseEvent e) {
				
			}
			
			public void mouseEntered(MouseEvent e) {
				
			}
			
			public void mouseClicked(MouseEvent e) {
				LoginMenu.this.setExtendedState(JFrame.ICONIFIED);
			}
		});
		c.add(jb5);

		jf = new JComboBox();
		jf.setEditable(true);
		jf.setBounds(75, 220, 170, 25);
		c.add(jf);

		jb1 = new JLabel("密码 : ");
		jb1.setBounds(25, 250, 60, 25);
		c.add(jb1);

		jf1 = new JPasswordField();
		jf1.setBounds(75, 250, 170, 25);
		c.add(jf1);

		jc = new JCheckBox();
		jc.setBounds(30, 285, 18, 15);
		c.add(jc);

		jb2 = new JLabel("记住密码 ");
		jb2.setBounds(52, 279, 60, 25);
		c.add(jb2);

		jc1 = new JCheckBox();
		jc1.setBounds(165, 285, 18, 15);
		c.add(jc1);

		jb3 = new JLabel("自动登录 ");
		jb3.setBounds(185, 279, 60, 25);
		c.add(jb3);

		jbt = new JButton("登录");
		jbt.setBackground(getBackground().GREEN);
		jbt.setBounds(25, 310, 80, 30);
		Mouseclickevent actionListener=new Mouseclickevent();
		jbt.addActionListener(actionListener);
		c.add(jbt);
		
		jbt = new JButton("注册");
		jbt.setBackground(getBackground().GREEN);
		jbt.setBounds(172, 310, 80, 30);
		c.add(jbt);
	}

	class MouseEventListener implements MouseInputListener {
		Point origin;
		LoginMenu frame;

		public MouseEventListener(LoginMenu frame) {
			this.frame = frame;
			origin = new Point();
		}

		@Override
		public void mouseClicked(MouseEvent e) {
		}
		@Override
		public void mousePressed(MouseEvent e) {
			origin.x = e.getX();
			origin.y = e.getY();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			this.frame.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
		}
		@Override
		public void mouseExited(MouseEvent e) {
			this.frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			Point p = this.frame.getLocation();
			this.frame.setLocation(p.x + (e.getX() - origin.x), p.y + (e.getY() - origin.y));
		}

		@Override
		public void mouseMoved(MouseEvent e) {
		}
	}
	class Mouseclickevent implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				Socket client=new Socket(SocketConfig.serverIP, SocketConfig.port);//点击登录，连接服务器
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			LoginMenu.this.dispose();
			ListMenu listmenu=new ListMenu();
			listmenu.setVisible(true);
		}
		
	}
public static void main(String[] args) {
	LoginMenu m=new LoginMenu();
}
}
