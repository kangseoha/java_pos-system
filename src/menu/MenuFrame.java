package menu;

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
import employee.EmpInputFrame;
import inventory.InventoryFrame;
import order.TableFrame;
import sale.SaleFrame;
import schedule.ScheduleFrame;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class MenuFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField tfMenu;
	private JTextField tfPrice;
	private DefaultTableModel model;
	private int row = -1;
	private int mno;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuFrame frame = new MenuFrame();
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
	public MenuFrame() {
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

		String[] colNames = { "번호", "메뉴", "가격", };
		model = new DefaultTableModel(colNames, 0);
		JTable table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				TableModel model = table.getModel();
				row = table.getSelectedRow();
				tfMenu.setText(model.getValueAt(row, 1) + "");
				tfPrice.setText(model.getValueAt(row, 2).toString());
				mno = row + 1;
			}
		});

		panel_3.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane(table);
		panel_3.add(scrollPane);

		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4, BorderLayout.SOUTH);

		JLabel lblMenu = new JLabel("메뉴");
		panel_4.add(lblMenu);

		tfMenu = new JTextField();
		panel_4.add(tfMenu);
		tfMenu.setColumns(10);

		JLabel lblPrice = new JLabel("가격");
		panel_4.add(lblPrice);

		tfPrice = new JTextField();
		panel_4.add(tfPrice);
		tfPrice.setColumns(10);

		JButton btnUpdate = new JButton("수정");
		panel_4.add(btnUpdate);

		JButton btnDelete = new JButton("삭제");
		panel_4.add(btnDelete);

		addModelData();

		btnOrder.addActionListener(this);
		btnSale.addActionListener(this);
		btnMenu.addActionListener(this);
		btnInventory.addActionListener(this);
		btnEmployee.addActionListener(this);
		btnSchedule.addActionListener(this);
		btnClose.addActionListener(this);

		btnUpdate.addActionListener(this);
		btnDelete.addActionListener(this);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		DBConn conn = DBConn.getInstance();
		String command = e.getActionCommand();
		if (command.equals("입력")) {
			new EmpInputFrame();
		} else if (command.equals("수정")) {
			Menu menu = new Menu();
			menu.setMno(mno);
			menu.setMenu(tfMenu.getText());
			menu.setPrice(Integer.parseInt(tfPrice.getText()));
			conn.updateMenu(menu);
			model.setNumRows(0);
			addModelData();
			
		} else if (command.equals("삭제")) {
			conn.deleteMenu(mno);
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
		List<Menu> menuList = conn.selectMenuAll();
		for (int i = 0; i < menuList.size(); i++) {
			Menu menu = menuList.get(i);
			Object[] obj = new Object[3];
			obj[0] = menu.getMno();
			obj[1] = menu.getMenu();
			obj[2] = menu.getPrice();
			model.addRow(obj);
		}
	}

}
