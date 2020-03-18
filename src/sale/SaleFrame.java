package sale;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bill.Bill;
import dbconn.DBConn;
import employee.EmpFrame;
import inventory.InventoryFrame;
import menu.MenuFrame;
import order.TableFrame;
import saleByMenu.SaleByMenuFrame;
import schedule.ScheduleFrame;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;

public class SaleFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField tfTotalSum;
	private JTextField tfTotalCustomer;
	private JTextField tfAvgPrice;
	private JTable table;
	DefaultTableModel model;
	int row = -1;
	int eno;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SaleFrame frame = new SaleFrame();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SaleFrame() {
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

		JButton btnByMenu = new JButton("메뉴별 판매현황");
		panel_2.add(btnByMenu);

		JButton btnClose = new JButton("닫기");
		panel_2.add(btnClose);

		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.CENTER);

		String[] colNames = { "주문번호", "주문일시", "주문금액" };

		model = new DefaultTableModel(colNames, 0);

		panel_3.setLayout(new BorderLayout(0, 0));
		addModelData();
		JTable table = new JTable(model);

		JScrollPane scrollPane = new JScrollPane(table);

		panel_3.add(scrollPane);

		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4, BorderLayout.SOUTH);
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel totalSum = new JLabel("총판매수익");
		totalSum.setBackground(Color.RED);
		panel_4.add(totalSum);

		tfTotalSum = new JTextField();
		panel_4.add(tfTotalSum);
		tfTotalSum.setColumns(10);
		DBConn conn = DBConn.getInstance();
		tfTotalSum.setText(conn.selectSaleSum() + "");

		JLabel totalCustomer = new JLabel("총고객수");
		panel_4.add(totalCustomer);

		tfTotalCustomer = new JTextField();
		panel_4.add(tfTotalCustomer);
		tfTotalCustomer.setColumns(10);
		DBConn conn1 = DBConn.getInstance();
		tfTotalCustomer.setText(conn1.selectSaleTotalCustomer() + "");

		JLabel avgPrice = new JLabel("객단가 계산");
		panel_4.add(avgPrice);

		tfAvgPrice = new JTextField();
		panel_4.add(tfAvgPrice);

		tfAvgPrice.setColumns(10);

		DBConn conn2 = DBConn.getInstance();
		tfAvgPrice.setText(conn2.selectSaleCustomerTransaction() + "");
		btnByMenu.addActionListener(this);
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
		} else if (command.equals("메뉴별 판매현황")) {
			new SaleByMenuFrame();
			this.dispose();

		} else if (command.equals("닫기")) {
			new TableFrame();
			this.dispose();
		}
	}

	private void addModelData() {
		DBConn conn = DBConn.getInstance();
		List<Bill> list = conn.SelelctBillAll();
		for (int i = 0; i < list.size(); i++) {
			Bill bill = list.get(i);

			Object[] obj = new Object[3];
			obj[0] = bill.getBno();
			obj[1] = bill.getReg_date();
			obj[2] = bill.getSumprice();
			model.addRow(obj);
		}
	}

}