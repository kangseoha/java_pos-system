package employee;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dbconn.DBConn;

public class EmpModifyFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField tfEname;
	private JTextField tfEposition;
	private JTextField tfEno;
	private JPasswordField passwordField;
	private JTextField tfTel;
	private JTextField tfpw;
	private String position[] = { "A1", "A2", "A3" }; // 수정사항 2
	private JComboBox<String> cbEposition; // 수정사항3
	private JLabel lblEEno;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		new EmpModifyFrame();
	}

	/**
	 * Create the frame.
	 */
	public EmpModifyFrame() {
		setTitle("사원 수정");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);

		JButton btnModi = new JButton("수정");

		panel.add(btnModi);

		JButton btnClose = new JButton("닫기");
		panel.add(btnClose);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(5, 2, 0, 0));

		JLabel lblEno = new JLabel("사원번호");
		panel_1.add(lblEno);

		lblEEno = new JLabel();
		panel_1.add(lblEEno);

//		tfEno = new JTextField();
//		panel_1.add(tfEno);
//		tfEno.setColumns(10);

		JLabel lblpw = new JLabel("비밀번호");
		panel_1.add(lblpw);

		tfpw = new JTextField();
		panel_1.add(tfpw);

		JLabel lblEname = new JLabel("이름");
		panel_1.add(lblEname);

		tfEname = new JTextField();
		panel_1.add(tfEname);
		tfEname.setColumns(10);

		JLabel lblEposition = new JLabel("직책");
		panel_1.add(lblEposition);

		cbEposition = new JComboBox<String>(position); // 수정사항1
		panel_1.add(cbEposition); // 수정사항 1

//		tfEposition = new JTextField();
//		panel_1.add(tfEposition);
//		tfEposition.setColumns(10);

		JLabel lblTel = new JLabel("연락처");
		panel_1.add(lblTel);

		tfTel = new JTextField();
		panel_1.add(tfTel);
		tfTel.setColumns(10);
		setVisible(true);

		btnModi.addActionListener(this);
		btnClose.addActionListener(this);

	}

	public void AdminModifyFrameget(Emp Empmo) {
		lblEEno.setText(Empmo.getEno() + "");
		tfpw.setText(Empmo.getEpw());
		tfEname.setText(Empmo.getEname());
		tfTel.setText(Empmo.getEtel());
	}

	public void actionPerformed(ActionEvent e) {
		String comm = e.getActionCommand();
		DBConn conn = DBConn.getInstance();
		String msg = null;

		if (comm.equals("수정")) {
			Emp emp = new Emp();
			if (!(tfpw.getText().equals("") || tfEname.getText().equals("") || tfTel.getText().equals(""))) {

				emp.setEno(Integer.parseInt(lblEEno.getText()));
				emp.setEpw(tfpw.getText());
				emp.setEname(tfEname.getText());
				emp.setEposition(cbEposition.getSelectedItem() + "");
				emp.setEtel(tfTel.getText());
				conn.updateEmp(emp);
				new EmpFrame();
				this.dispose();

			} else {
				msg = "정보를 모두 입력하세요. ";
				JOptionPane.showMessageDialog(null, msg);
			}

		} else {
			this.dispose();

		}
	}
}
