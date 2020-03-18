package inventory;

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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import dbconn.DBConn;
import employee.EmpFrame;
import menu.MenuFrame;
import order.TableFrame;
import sale.SaleFrame;
import schedule.ScheduleFrame;


import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

public class InventoryFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	DefaultTableModel model;
	private JTextField tfCount;
	private int row = -1;
	private JTextField tfElement;
	private int ino;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InventoryFrame frame = new InventoryFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InventoryFrame() {
		setTitle("매출 관리");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

		String[] colNames = { "재료", "갯수" };
		model = new DefaultTableModel(colNames, 0);
		panel_3.setLayout(new BorderLayout(0, 0));
		JTable table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		panel_3.add(scrollPane, BorderLayout.CENTER);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				TableModel model = table.getModel();
				row = table.getSelectedRow();
				tfElement.setText(model.getValueAt(row, 0) + "");
				tfCount.setText(model.getValueAt(row, 1).toString());
				ino = row + 1;
			}
		});

		addModelData();

		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4, BorderLayout.SOUTH);
		panel_4.setLayout(new GridLayout(1, 4, 0, 0));

		JLabel lbl = new JLabel("재료");
		lbl.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_4.add(lbl);

		tfElement = new JTextField();
		tfElement.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_4.add(tfElement);
		tfElement.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("갯수");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_4.add(lblNewLabel_1);

		tfCount = new JTextField();
		tfCount.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_4.add(tfCount);

		tfCount.setColumns(10);

		JButton btnUpdate = new JButton("수정");
		panel_4.add(btnUpdate);

		btnOrder.addActionListener(this);
		btnSale.addActionListener(this);
		btnMenu.addActionListener(this);
		btnInventory.addActionListener(this);
		btnEmployee.addActionListener(this);
		btnSchedule.addActionListener(this);
		btnClose.addActionListener(this);
		btnUpdate.addActionListener(this);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals("주문 관리")) {
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

		} else if (command.equals("수정")) {
			DBConn conn = DBConn.getInstance();
			Inventory in = new Inventory();
			in.setIno(ino);
			in.setCount(Integer.parseInt(tfCount.getText()));
			conn.updateInventory(in);
			model.setNumRows(0);
			addModelData();

		}
	}

	private void addModelData() {
		DBConn conn = DBConn.getInstance();
		List<Inventory> inList = conn.SelectInventoryAll();
		for (int i = 0; i < inList.size(); i++) {
			Inventory in = inList.get(i);
			Object[] obj = new Object[2];
			obj[0] = in.getElement();
			obj[1] = in.getCount();
			model.addRow(obj);
		}

	}

}