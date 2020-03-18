package employee;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dbconn.DBConn;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class EmpInputFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField tfEname;
	private JTextField tfEno;
	private JTextField tfTel;
	private JTextField tfpw;
	private String position[] = { "A1", "A2", "A3" };
	private JComboBox<String> cbEposition;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmpInputFrame frame = new EmpInputFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EmpInputFrame() {
		setTitle("사원 추가");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);

		JButton btnadd = new JButton("추가");

		panel.add(btnadd);

		JButton btnClose = new JButton("닫기");
		panel.add(btnClose);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(5, 2, 0, 0));

		JLabel lblEno = new JLabel("사원번호");
		panel_1.add(lblEno);

		tfEno = new JTextField();
		panel_1.add(tfEno);
		tfEno.setColumns(10);

		JLabel lblpw = new JLabel("비밀번호");
		panel_1.add(lblpw);

		tfpw = new JTextField();
		panel_1.add(tfpw);

		JLabel lblEname = new JLabel("이름");
		panel_1.add(lblEname);

		tfEname = new JTextField();
		panel_1.add(tfEname);
		tfEname.setColumns(10);

		JLabel lblEposition = new JLabel("직급");
		panel_1.add(lblEposition);

		cbEposition = new JComboBox<String>(position);
		panel_1.add(cbEposition);

		JLabel lblTel = new JLabel("연락처");
		panel_1.add(lblTel);

		tfTel = new JTextField();
		panel_1.add(tfTel);
		tfTel.setColumns(10);
		setVisible(true);

		btnadd.addActionListener(this);
		btnClose.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comm = e.getActionCommand();
		DBConn conn = DBConn.getInstance();
		String msg = null;

		if (comm.equals("추가")) {
			try {
				Emp emp = new Emp();
				if (!(tfEno == null || tfpw.getText().equals("") || tfEname.getText().equals("")
						|| tfTel.getText().equals(""))) {
					if (conn.Check(Integer.parseInt(tfEno.getText()))) {
						if (tfEno.getText().matches("^[0-9]*$")) {

							emp.setEno(Integer.parseInt(tfEno.getText()));
							emp.setEpw(tfpw.getText());
							emp.setEname(tfEname.getText());
							emp.setEposition(cbEposition.getSelectedItem() + "");
							emp.setEtel(tfTel.getText());
							conn.insertEmp(emp);
							this.dispose();
							new EmpFrame();
							

						} else {
							msg = "사원번호는 숫자만 유효합니다. ";
							JOptionPane.showMessageDialog(null, msg);
						}
					} else {
						msg = "입력하신 사원번호를 사용할 수 없습니다. ";
						JOptionPane.showMessageDialog(null, msg);
					}
				} else {
					msg = "정보를 모두 입력하세요. ";
					JOptionPane.showMessageDialog(null, msg);
				}
			} catch (Exception e1) {
				msg = "사원번호는 숫자만 입력하세요.";
				JOptionPane.showMessageDialog(null, msg);
			}
		} else {
			this.dispose();
		}

	}
}