package login;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dbconn.DBConn;
import employee.Emp;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;

public class EmpNoFindFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField tfEname;
	private JTextField tfEtel;
	private JLabel lblFindEno;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmpNoFindFrame frame = new EmpNoFindFrame();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EmpNoFindFrame() {
		setTitle("사원번호 찾기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 280, 174);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(3, 2, 0, 0));
		JLabel lblEname = new JLabel("이름");
		lblEname.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblEname);

		tfEname = new JTextField();
		panel.add(tfEname);
		tfEname.setColumns(10);

		JLabel lblEtel = new JLabel("전화번호");
		lblEtel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblEtel);

		tfEtel = new JTextField();
		panel.add(tfEtel);
		tfEtel.setColumns(10);

		JLabel lblNewLabel = new JLabel("사원번호는 ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);

		lblFindEno = new JLabel("");
		panel.add(lblFindEno);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);

		JButton btnFind = new JButton("사원번호찾기");
		panel_1.add(btnFind);

		JButton btnClose = new JButton("닫기");
		panel_1.add(btnClose);
		setVisible(true);

		btnFind.addActionListener(this);
		btnClose.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DBConn conn = DBConn.getInstance();
		String command = e.getActionCommand();
		if (command.equals("사원번호찾기")) {
			Emp emp = conn.selectEno(tfEname.getText(), tfEtel.getText());
			try {
				lblFindEno.setText(emp.getEno() + "");
			} catch (NullPointerException e2) {
				lblFindEno.setText("사원번호 없음");
			}

		} else {
			this.dispose();
		}
	}

}