package employee;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dbconn.DBConn;
import inventory.InventoryFrame;
import menu.MenuFrame;
import order.TableFrame;
import sale.SaleFrame;
import schedule.ScheduleFrame;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class EmpFrame extends JFrame implements ActionListener {
	DefaultTableModel model;
	int row = -1;
	int eno;
	String msg = null;

	private JPanel contentPane;
	public Emp Empmo;
	public Emp Empdel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmpFrame frame = new EmpFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EmpFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("직원관리");
		setBounds(100, 100, 693, 515);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(8, 1, 0, 0));

		JButton btnOrder = new JButton("주문 관리");
		btnOrder.setForeground(Color.BLACK);
		panel.add(btnOrder);

		JButton btnSale = new JButton("매출 현황");
		panel.add(btnSale);

		JButton btnMenu = new JButton("메뉴 관리");
		panel.add(btnMenu);

		JButton btnInventory = new JButton("재고 관리");
		panel.add(btnInventory);

		JButton btnEmployee = new JButton("직원 관리");
		panel.add(btnEmployee);

		JButton btnSchedule = new JButton("스케줄 관리");
		panel.add(btnSchedule);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);

		JButton btnClose = new JButton("닫기");
		panel_2.add(btnClose);

		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));

		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4, BorderLayout.SOUTH);

		JButton btnEadd = new JButton("추가");
		panel_4.add(btnEadd);

		JButton btnEmodi = new JButton("수정");
		panel_4.add(btnEmodi);

		JButton btnEdel = new JButton("삭제");
		panel_4.add(btnEdel);
		String[] colNames = { "사원 번호", "이름", "직책", "연락처" };

		model = new DefaultTableModel(colNames, 0);
		JTable table = new JTable(model);

		JScrollPane scrollPane = new JScrollPane(table);
		panel_3.add(scrollPane, BorderLayout.CENTER);
		setVisible(true);

		btnEadd.addActionListener(this);
		btnEmodi.addActionListener(this);
		btnEdel.addActionListener(this);

		addModelData();

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				row = table.getSelectedRow();
				eno = Integer.parseInt(table.getModel().getValueAt(row, 0) + "");
				DBConn conn = DBConn.getInstance();
				Empmo = conn.selectEmpOne(eno);
				Empdel = conn.selectEmpOne(eno);
			}

		});

		btnOrder.addActionListener(this);
		btnSale.addActionListener(this);
		btnMenu.addActionListener(this);
		btnInventory.addActionListener(this);
		btnEmployee.addActionListener(this);
		btnSchedule.addActionListener(this);
		btnClose.addActionListener(this);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DBConn conn = DBConn.getInstance();
		String command = e.getActionCommand();
		if (command.equals("추가")) {
			new EmpInputFrame();
		} else if (command.equals("수정")) {
			if (row == -1) {
				msg = "수정할 열을 선택해주세요.";
				JOptionPane.showMessageDialog(null, msg);
			} else
				new EmpModifyFrame().AdminModifyFrameget(Empmo);

		} else if (command.equals("삭제")) {
			if (row == -1) {
				msg = "삭제할 열을 선택해주세요.";
				JOptionPane.showMessageDialog(null, msg);
			} else
				conn.deleteEmp(Empdel);
			    model.setRowCount(0);
			    addModelData();
		} else if (command.equals("주문 관리")) {
			new TableFrame();
			this.dispose();
		} else if (command.equals("매출 현황")) {
			new SaleFrame();
			this.dispose();
		} else if (command.equals("메뉴 관리")) {
			new MenuFrame();
			this.dispose();
		} else if (command.equals("재고 관리")) {
			new InventoryFrame();
			this.dispose();
		} else if (command.equals("직원 관리")) {
			new EmpFrame();
			this.dispose();
		} else if (command.equals("스케줄 관리")) {
			new ScheduleFrame();
			this.dispose();
		} else if (command.equals("닫기")) {
			new TableFrame();
			this.dispose();
		}

	}

	private void addModelData() {
		DBConn conn = DBConn.getInstance();
		List<Emp> list = conn.selectEmpAll();
		for (int i = 0; i < list.size(); i++) {
			Emp emp = list.get(i);
			int eno = emp.getEno();
			Object[] obj = new Object[4];
			obj[0] = emp.getEno();
			obj[1] = emp.getEname();
			obj[2] = emp.getEposition();
			obj[3] = emp.getEtel();
			model.addRow(obj);
		}
	}

}