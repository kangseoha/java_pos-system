package saleByMenu;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dbconn.DBConn;
import employee.EmpFrame;
import inventory.InventoryFrame;
import menu.MenuFrame;
import order.TableFrame;
import sale.SaleFrame;
import schedule.ScheduleFrame;

public class SaleByMenuFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	DefaultTableModel model;
	private double sum;
	private List<Double> scnum = new ArrayList<Double>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SaleByMenuFrame frame = new SaleByMenuFrame();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SaleByMenuFrame() {
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

		String[] colNames = { "메뉴", "총판매금액", "금액비중(%)", "판매비중(%)" };
		model = new DefaultTableModel(colNames, 0);
		panel_3.setLayout(new BorderLayout(0, 0));
		JTable table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		panel_3.add(scrollPane, BorderLayout.CENTER);

		addModelData();

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
			new SaleFrame();
			this.dispose();
		}
	}

	private void addModelData() {

		DBConn conn = DBConn.getInstance();
		double cnum = conn.selectTotalCustomer();
		List<SaleByMenu> smcList = conn.selectSaleStatusByMenuCount();
		for (int i = 0; i < smcList.size(); i++) {
			SaleByMenu smc = smcList.get(i);
			scnum.add(smc.getSumPrice() / cnum * 100);
		}
		List<SaleByMenu> smList = conn.selectSaleStatusByMenu();
		for (int i = 0; i < smList.size(); i++) {
			SaleByMenu sm = smList.get(i);
			sum += sm.getSumPrice();
		}
		for (int i = 0; i < smList.size(); i++) {
			SaleByMenu sm = smList.get(i);
			Object[] obj = new Object[4];
			obj[0] = sm.getMenu();
			obj[1] = sm.getSumPrice();
			obj[2] = Math.round((sm.getSumPrice() / sum * 100));
			obj[3] = Math.round(scnum.get(i));
			model.addRow(obj);

		}
	}
}