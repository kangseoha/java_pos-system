package login;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dbconn.DBConn;
import employee.EmpFrame;
import order.TableFrame;

public class LoginFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField tfEno;
	private JPasswordField pfPw;
	private JPanel panel_1;
	private JButton btnLogin;
	private JButton btnClose;
	private JButton btnAdminFind;
	String msg = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setTitle("로그인");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 180);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 10, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(2, 2, 5, 5));

		JLabel lblEno = new JLabel("사원번호");
		panel.add(lblEno);

		tfEno = new JTextField();
		panel.add(tfEno);
		tfEno.setColumns(10);

		JLabel lblPw = new JLabel("비밀번호");
		panel.add(lblPw);

		pfPw = new JPasswordField();
		panel.add(pfPw);
		pfPw.setColumns(10);

		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);

		btnLogin = new JButton("로그인");
		panel_1.add(btnLogin);

		btnAdminFind = new JButton("사원번호찾기");
		panel_1.add(btnAdminFind);

		btnClose = new JButton("종료");
		panel_1.add(btnClose);

		setVisible(true);

		btnLogin.addActionListener(this);
		btnClose.addActionListener(this);
		btnAdminFind.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String conn = e.getActionCommand();
		String msg = null;
		if (conn.equals("로그인")) {
			DBConn connection = DBConn.getInstance();
			String pw = connection.login(Integer.parseInt(tfEno.getText()));
			if (pfPw.getText().equals(pw)) {
				new TableFrame();
				this.dispose();

			} else {
				msg = "아이디나 비밀번호를 확인하세요. ";
				JOptionPane.showMessageDialog(null, msg);
			}
		} else if (conn.equals("사원번호찾기")) {
			new EmpNoFindFrame();
		} else {
			this.dispose();
		}

	}
}