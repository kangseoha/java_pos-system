package order;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bill.Bill;
import bill.BillFrame;
import dbconn.DBConn;
import employee.EmpFrame;
import inventory.InventoryFrame;
import login.LoginFrame;
import menu.MenuFrame;
import sale.Sale;
import sale.SaleFrame;
import schedule.ScheduleFrame;
import java.awt.Component;
import javax.swing.JComboBox;

public class TableFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private int row = -1;
	static int num = -1; // 테이블 구분할 번호
	static JTextField tfSumSale1;// 총 금액을 표시할 TextField
	static JTextField tfSumSale2;
	static JTextField tfSumSale3;
	static JTextField tfSumSale4;
	static DefaultTableModel model1;
	static DefaultTableModel model2;
	static DefaultTableModel model3;
	static DefaultTableModel model4;
	JTable table1;
	JTable table2;
	JTable table3;
    JTable table4;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TableFrame frame = new TableFrame();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TableFrame() {

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
		panel_3.setLayout(null);

		String[] colNames = { "메뉴", "갯수", "금액" };
		model1 = new DefaultTableModel(colNames, 0);
		table1 = new JTable(model1);
		JScrollPane scrollPane = new JScrollPane(table1);
		scrollPane.setBounds(19, 45, 219, 94);
		panel_3.add(scrollPane);

		JButton btnPayment1 = new JButton("결제");
		btnPayment1.setBounds(16, 165, 69, 23);
		panel_3.add(btnPayment1);

		JButton btnOrder1 = new JButton("주문");
		btnOrder1.setBounds(95, 165, 69, 23);
		panel_3.add(btnOrder1);

		JButton btnCancle1 = new JButton("취소");
		btnCancle1.setBounds(169, 165, 69, 23);
		panel_3.add(btnCancle1);

		JLabel lblSumSale1 = new JLabel("총주문금액");
		lblSumSale1.setBounds(18, 147, 87, 15);
		panel_3.add(lblSumSale1);

		tfSumSale1 = new JTextField();
		tfSumSale1.setBounds(97, 144, 141, 21);
		panel_3.add(tfSumSale1);
		tfSumSale1.setColumns(10);
		
		
		
	
		model2 = new DefaultTableModel(colNames, 0);
		table2 = new JTable(model2);
		JScrollPane scrollPane_1 = new JScrollPane(table2);
		scrollPane_1.setBounds(326, 45, 219, 94);
		panel_3.add(scrollPane_1);
		
		JLabel lblSumSale2 = new JLabel("총주문금액");
		lblSumSale2.setBounds(325, 147, 87, 15);
		panel_3.add(lblSumSale2);
		
		tfSumSale2 = new JTextField();
		tfSumSale2.setColumns(10);
		tfSumSale2.setBounds(404, 144, 141, 21);
		panel_3.add(tfSumSale2);
		
		JButton btnPayment2 = new JButton("결제");
		btnPayment2.setBounds(323, 165, 69, 23);
		panel_3.add(btnPayment2);
		
		JButton btnOrder2 = new JButton("주문");
		btnOrder2.setBounds(402, 165, 69, 23);
		panel_3.add(btnOrder2);
		
		JButton btnCancle2 = new JButton("취소");
		btnCancle2.setBounds(476, 165, 69, 23);
		panel_3.add(btnCancle2);
		
		
		model3 = new DefaultTableModel(colNames, 0);
		table3 = new JTable(model3);
		JScrollPane scrollPane_2 = new JScrollPane(table3);
		scrollPane_2.setBounds(19, 228, 219, 94);
		panel_3.add(scrollPane_2);
		
		JLabel lblSumSale3 = new JLabel("총주문금액");
		lblSumSale3.setBounds(18, 332, 87, 15);
		panel_3.add(lblSumSale3);
		
		tfSumSale3 = new JTextField();
		tfSumSale3.setColumns(10);
		tfSumSale3.setBounds(97, 329, 141, 21);
		panel_3.add(tfSumSale3);
		
		JButton btnPayment3 = new JButton("결제");
		btnPayment3.setBounds(16, 350, 69, 23);
		panel_3.add(btnPayment3);
		
		JButton btnOrder3 = new JButton("주문");
		btnOrder3.setBounds(95, 350, 69, 23);
		panel_3.add(btnOrder3);
		
		JButton btnCancle3 = new JButton("취소");
		btnCancle3.setBounds(169, 350, 69, 23);
		panel_3.add(btnCancle3);
		
		
		
		model4 = new DefaultTableModel(colNames, 0);
		table4 = new JTable(model4);
		JScrollPane scrollPane_3 = new JScrollPane(table4);
		scrollPane_3.setBounds(326, 228, 219, 94);
		panel_3.add(scrollPane_3);
		
		JLabel lblSumSale4 = new JLabel("총주문금액");
		lblSumSale4.setBounds(325, 332, 87, 15);
		panel_3.add(lblSumSale4);
		
		tfSumSale4 = new JTextField();
		tfSumSale4.setColumns(10);
		tfSumSale4.setBounds(404, 329, 141, 21);
		panel_3.add(tfSumSale4);
		
		JButton btnPayment4 = new JButton("결제");
		btnPayment4.setBounds(323, 350, 69, 23);
		panel_3.add(btnPayment4);
		
		JButton btnOrder4 = new JButton("주문");
		btnOrder4.setBounds(402, 350, 69, 23);
		panel_3.add(btnOrder4);
		
		JButton btnCancle4 = new JButton("취소");
		btnCancle4.setBounds(476, 350, 69, 23);
		panel_3.add(btnCancle4);
		
		String[] str = {"0","1","2","3","4"};
		

		btnPayment1.addActionListener(this);
		btnCancle1.addActionListener(this);

		btnOrder.addActionListener(this);
		btnSale.addActionListener(this);
		btnMenu.addActionListener(this);
		btnInventory.addActionListener(this);
		btnEmployee.addActionListener(this);
		btnSchedule.addActionListener(this);
		btnClose.addActionListener(this);
		
		
		btnPayment1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				payment1();
			}

		});

		btnOrder1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				num = 1;
				System.out.println(num);
				new OrderFrame();

			}
		});
		
		btnCancle1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cancle1();

			}
		});
		
		btnPayment2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				payment2();
			}

		});

		btnOrder2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				num = 2;
				System.out.println(num);
				new OrderFrame();

			}
		});
		
		btnCancle2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cancle2();

			}
		});
		
		btnPayment3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				payment3();
			}

		});

		btnOrder3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				num = 3;
				System.out.println(num);
				new OrderFrame();

			}
		});
		
		btnCancle3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cancle3();

			}
		});
		
		btnPayment4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				payment4();
			}

		});

		btnOrder4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				num = 4;
				System.out.println(num);
				new OrderFrame();

			}
		});
		
		btnCancle4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cancle4();

			}
		});
		
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
			new LoginFrame();
			this.dispose();
		}
	}
	private void cancle1() {
		    DBConn conn = DBConn.getInstance();
			List<Sale> SaleList = conn.selectSaleAll();
			for (int i = 1; i < model1.getRowCount()+1; i++) {
				Sale sale = SaleList.get(SaleList.size() - i);
				conn.deleteSale(sale);
			}
			model1.setNumRows(0);
			tfSumSale1.setText("");
			OrderFrame.sum = 0;
			OrderFrame.list1.clear();	
	
   }
	private void cancle2() {
	    DBConn conn = DBConn.getInstance();
		List<Sale> SaleList = conn.selectSaleAll();
		for (int i = 1; i < model2.getRowCount()+1; i++) {
			Sale sale = SaleList.get(SaleList.size() - i);
			conn.deleteSale(sale);
		}
		model2.setNumRows(0);
		tfSumSale2.setText("");
		OrderFrame.sum = 0;
		OrderFrame.list2.clear();	

}
	private void cancle3() {
	    DBConn conn = DBConn.getInstance();
		List<Sale> SaleList = conn.selectSaleAll();
		for (int i = 1; i < model3.getRowCount()+1; i++) {
			Sale sale = SaleList.get(SaleList.size() - i);
			conn.deleteSale(sale);
		}
		model3.setNumRows(0);
		tfSumSale3.setText("");
		OrderFrame.sum = 0;
		OrderFrame.list3.clear();	

}
	private void cancle4() {
	    DBConn conn = DBConn.getInstance();
		List<Sale> SaleList = conn.selectSaleAll();
		for (int i = 1; i < model4.getRowCount()+1; i++) {
			Sale sale = SaleList.get(SaleList.size() - i);
			conn.deleteSale(sale);
		}
		model4.setNumRows(0);
		tfSumSale4.setText("");
		OrderFrame.sum = 0;
		OrderFrame.list4.clear();	

}
   
    private void payment1() {
	    if (model1.getRowCount() == 5) {
			DBConn conn = DBConn.getInstance();
			Bill bill = new Bill();
			bill.setMenu1(model1.getValueAt(0, 0) + "");
			bill.setCount1((model1.getValueAt(0, 1) + ""));
			bill.setPrice1((model1.getValueAt(0, 2) + ""));
			bill.setMenu2(model1.getValueAt(1, 0) + "");
			bill.setCount2((model1.getValueAt(1, 1) + ""));
			bill.setPrice2((model1.getValueAt(1, 2) + ""));
			bill.setMenu3(model1.getValueAt(2, 0) + "");
			bill.setCount3((model1.getValueAt(2, 1) + ""));
			bill.setPrice3((model1.getValueAt(2, 2) + ""));
			bill.setMenu4(model1.getValueAt(3, 0) + "");
			bill.setCount4((model1.getValueAt(3, 1) + ""));
			bill.setPrice4((model1.getValueAt(3, 2) + ""));
			bill.setMenu5(model1.getValueAt(4, 0) + "");
			bill.setCount5((model1.getValueAt(4, 1) + ""));
			bill.setPrice5((model1.getValueAt(4, 2) + ""));
			bill.setSumprice(Integer.parseInt(tfSumSale1.getText()));
			conn.insertBill(bill);
			new BillFrame();
			model1.setNumRows(0);
			tfSumSale1.setText("");
			OrderFrame.sum = 0;
			OrderFrame.list1.clear();

		} else if (model1.getRowCount() == 4) {

			DBConn conn = DBConn.getInstance();
			Bill bill = new Bill();
			bill.setMenu1(model1.getValueAt(0, 0) + "");
			bill.setCount1((model1.getValueAt(0, 1) + ""));
			bill.setPrice1((model1.getValueAt(0, 2) + ""));
			bill.setMenu2(model1.getValueAt(1, 0) + "");
			bill.setCount2((model1.getValueAt(1, 1) + ""));
			bill.setPrice2((model1.getValueAt(1, 2) + ""));
			bill.setMenu3(model1.getValueAt(2, 0) + "");
			bill.setCount3((model1.getValueAt(2, 1) + ""));
			bill.setPrice3((model1.getValueAt(2, 2) + ""));
			bill.setMenu4(model1.getValueAt(3, 0) + "");
			bill.setCount4((model1.getValueAt(3, 1) + ""));
			bill.setPrice4((model1.getValueAt(3, 2) + ""));
			bill.setSumprice(Integer.parseInt(tfSumSale1.getText()));
			conn.insertBill(bill);
			new BillFrame();
			model1.setNumRows(0);
			tfSumSale1.setText("");
			OrderFrame.sum = 0;
			OrderFrame.list1.clear();

		} else if (model1.getRowCount() == 3) {

			DBConn conn = DBConn.getInstance();
			Bill bill = new Bill();
			bill.setMenu1(model1.getValueAt(0, 0) + "");
			bill.setCount1((model1.getValueAt(0, 1) + ""));
			bill.setPrice1((model1.getValueAt(0, 2) + ""));
			bill.setMenu2(model1.getValueAt(1, 0) + "");
			bill.setCount2((model1.getValueAt(1, 1) + ""));
			bill.setPrice2((model1.getValueAt(1, 2) + ""));
			bill.setMenu3(model1.getValueAt(2, 0) + "");
			bill.setCount3((model1.getValueAt(2, 1) + ""));
			bill.setPrice3((model1.getValueAt(2, 2) + ""));
			bill.setSumprice(Integer.parseInt(tfSumSale1.getText()));
			conn.insertBill(bill);
			new BillFrame();
			model1.setNumRows(0);
			tfSumSale1.setText("");
			OrderFrame.sum = 0;
			OrderFrame.list1.clear();

		} else if (model1.getRowCount() == 2) {

			DBConn conn = DBConn.getInstance();
			Bill bill = new Bill();
			bill.setMenu1(model1.getValueAt(0, 0) + "");
			bill.setCount1((model1.getValueAt(0, 1) + ""));
			bill.setPrice1((model1.getValueAt(0, 2) + ""));
			bill.setMenu2(model1.getValueAt(1, 0) + "");
			bill.setCount2((model1.getValueAt(1, 1) + ""));
			bill.setPrice2((model1.getValueAt(1, 2) + ""));
			bill.setSumprice(Integer.parseInt(tfSumSale1.getText()));
			conn.insertBill(bill);
			new BillFrame();
			model1.setNumRows(0);
			tfSumSale1.setText("");
			OrderFrame.sum = 0;
			OrderFrame.list1.clear();

		} else if (model1.getRowCount() == 1) {

			DBConn conn = DBConn.getInstance();
			Bill bill = new Bill();
			bill.setMenu1(model1.getValueAt(0, 0) + "");
			bill.setCount1((model1.getValueAt(0, 1) + ""));
			bill.setPrice1((model1.getValueAt(0, 2) + ""));
			bill.setSumprice(Integer.parseInt(tfSumSale1.getText()));
			conn.insertBill(bill);
			new BillFrame();
			model1.setNumRows(0);
			tfSumSale1.setText("");
			OrderFrame.sum = 0;
			OrderFrame.list1.clear();

		} else if (model1.getRowCount() == 0) {
			JOptionPane.showMessageDialog(null, "주문을 먼저 하셔야합니다.");
		} else if (model1.getRowCount() == 6) {
			JOptionPane.showMessageDialog(null, "주문은 최대 5개까지 가능합니다.");
		}
	   
	   
   }
    private void payment2() {
	   if (model2.getRowCount() == 5) {
			DBConn conn = DBConn.getInstance();
			Bill bill = new Bill();
			bill.setMenu1(model2.getValueAt(0, 0) + "");
			bill.setCount1((model2.getValueAt(0, 1) + ""));
			bill.setPrice1((model2.getValueAt(0, 2) + ""));
			bill.setMenu2(model2.getValueAt(1, 0) + "");
			bill.setCount2((model2.getValueAt(1, 1) + ""));
			bill.setPrice2((model2.getValueAt(1, 2) + ""));
			bill.setMenu3(model2.getValueAt(2, 0) + "");
			bill.setCount3((model2.getValueAt(2, 1) + ""));
			bill.setPrice3((model2.getValueAt(2, 2) + ""));
			bill.setMenu4(model2.getValueAt(3, 0) + "");
			bill.setCount4((model2.getValueAt(3, 1) + ""));
			bill.setPrice4((model2.getValueAt(3, 2) + ""));
			bill.setMenu5(model2.getValueAt(4, 0) + "");
			bill.setCount5((model2.getValueAt(4, 1) + ""));
			bill.setPrice5((model2.getValueAt(4, 2) + ""));
			bill.setSumprice(Integer.parseInt(tfSumSale2.getText()));
			conn.insertBill(bill);
			new BillFrame();
			model2.setNumRows(0);
			tfSumSale2.setText("");
			OrderFrame.sum = 0;
			OrderFrame.list2.clear();

		} else if (model2.getRowCount() == 4) {

			DBConn conn = DBConn.getInstance();
			Bill bill = new Bill();
			bill.setMenu1(model2.getValueAt(0, 0) + "");
			bill.setCount1((model2.getValueAt(0, 1) + ""));
			bill.setPrice1((model2.getValueAt(0, 2) + ""));
			bill.setMenu2(model2.getValueAt(1, 0) + "");
			bill.setCount2((model2.getValueAt(1, 1) + ""));
			bill.setPrice2((model2.getValueAt(1, 2) + ""));
			bill.setMenu3(model2.getValueAt(2, 0) + "");
			bill.setCount3((model2.getValueAt(2, 1) + ""));
			bill.setPrice3((model2.getValueAt(2, 2) + ""));
			bill.setMenu4(model2.getValueAt(3, 0) + "");
			bill.setCount4((model2.getValueAt(3, 1) + ""));
			bill.setPrice4((model2.getValueAt(3, 2) + ""));
			bill.setSumprice(Integer.parseInt(tfSumSale2.getText()));
			conn.insertBill(bill);
			new BillFrame();
			model2.setNumRows(0);
			tfSumSale2.setText("");
			OrderFrame.sum = 0;
			OrderFrame.list2.clear();

		} else if (model2.getRowCount() == 3) {

			DBConn conn = DBConn.getInstance();
			Bill bill = new Bill();
			bill.setMenu1(model2.getValueAt(0, 0) + "");
			bill.setCount1((model2.getValueAt(0, 1) + ""));
			bill.setPrice1((model2.getValueAt(0, 2) + ""));
			bill.setMenu2(model2.getValueAt(1, 0) + "");
			bill.setCount2((model2.getValueAt(1, 1) + ""));
			bill.setPrice2((model2.getValueAt(1, 2) + ""));
			bill.setMenu3(model2.getValueAt(2, 0) + "");
			bill.setCount3((model2.getValueAt(2, 1) + ""));
			bill.setPrice3((model2.getValueAt(2, 2) + ""));
			bill.setSumprice(Integer.parseInt(tfSumSale2.getText()));
			conn.insertBill(bill);
			new BillFrame();
			model2.setNumRows(0);
			tfSumSale2.setText("");
			OrderFrame.sum = 0;
			OrderFrame.list2.clear();

		} else if (model2.getRowCount() == 2) {

			DBConn conn = DBConn.getInstance();
			Bill bill = new Bill();
			bill.setMenu1(model2.getValueAt(0, 0) + "");
			bill.setCount1((model2.getValueAt(0, 1) + ""));
			bill.setPrice1((model2.getValueAt(0, 2) + ""));
			bill.setMenu2(model2.getValueAt(1, 0) + "");
			bill.setCount2((model2.getValueAt(1, 1) + ""));
			bill.setPrice2((model2.getValueAt(1, 2) + ""));
			bill.setSumprice(Integer.parseInt(tfSumSale2.getText()));
			conn.insertBill(bill);
			new BillFrame();
			model2.setNumRows(0);
			tfSumSale2.setText("");
			OrderFrame.sum = 0;
			OrderFrame.list2.clear();

		} else if (model2.getRowCount() == 1) {

			DBConn conn = DBConn.getInstance();
			Bill bill = new Bill();
			bill.setMenu1(model2.getValueAt(0, 0) + "");
			bill.setCount1((model2.getValueAt(0, 1) + ""));
			bill.setPrice1((model2.getValueAt(0, 2) + ""));
			bill.setSumprice(Integer.parseInt(tfSumSale2.getText()));
			conn.insertBill(bill);
			new BillFrame();
			model2.setNumRows(0);
			tfSumSale2.setText("");
			OrderFrame.sum = 0;
			OrderFrame.list2.clear();

		} else if (model2.getRowCount() == 0) {
			JOptionPane.showMessageDialog(null, "주문을 먼저 하셔야합니다.");
		} else if (model2.getRowCount() == 6) {
			JOptionPane.showMessageDialog(null, "주문은 최대 5개까지 가능합니다.");
		}
	   
	   
   }
    private void payment3() {
	   if (model3.getRowCount() == 5) {
			DBConn conn = DBConn.getInstance();
			Bill bill = new Bill();
			bill.setMenu1(model3.getValueAt(0, 0) + "");
			bill.setCount1((model3.getValueAt(0, 1) + ""));
			bill.setPrice1((model3.getValueAt(0, 2) + ""));
			bill.setMenu2(model3.getValueAt(1, 0) + "");
			bill.setCount2((model3.getValueAt(1, 1) + ""));
			bill.setPrice2((model3.getValueAt(1, 2) + ""));
			bill.setMenu3(model3.getValueAt(2, 0) + "");
			bill.setCount3((model3.getValueAt(2, 1) + ""));
			bill.setPrice3((model3.getValueAt(2, 2) + ""));
			bill.setMenu4(model3.getValueAt(3, 0) + "");
			bill.setCount4((model3.getValueAt(3, 1) + ""));
			bill.setPrice4((model3.getValueAt(3, 2) + ""));
			bill.setMenu5(model3.getValueAt(4, 0) + "");
			bill.setCount5((model3.getValueAt(4, 1) + ""));
			bill.setPrice5((model3.getValueAt(4, 2) + ""));
			bill.setSumprice(Integer.parseInt(tfSumSale3.getText()));
			conn.insertBill(bill);
			new BillFrame();
			model3.setNumRows(0);
			tfSumSale3.setText("");
			OrderFrame.sum = 0;
			OrderFrame.list3.clear();

		} else if (model3.getRowCount() == 4) {

			DBConn conn = DBConn.getInstance();
			Bill bill = new Bill();
			bill.setMenu1(model3.getValueAt(0, 0) + "");
			bill.setCount1((model3.getValueAt(0, 1) + ""));
			bill.setPrice1((model3.getValueAt(0, 2) + ""));
			bill.setMenu2(model3.getValueAt(1, 0) + "");
			bill.setCount2((model3.getValueAt(1, 1) + ""));
			bill.setPrice2((model3.getValueAt(1, 2) + ""));
			bill.setMenu3(model3.getValueAt(2, 0) + "");
			bill.setCount3((model3.getValueAt(2, 1) + ""));
			bill.setPrice3((model3.getValueAt(2, 2) + ""));
			bill.setMenu4(model3.getValueAt(3, 0) + "");
			bill.setCount4((model3.getValueAt(3, 1) + ""));
			bill.setPrice4((model3.getValueAt(3, 2) + ""));
			bill.setSumprice(Integer.parseInt(tfSumSale3.getText()));
			conn.insertBill(bill);
			new BillFrame();
			model3.setNumRows(0);
			tfSumSale3.setText("");
			OrderFrame.sum = 0;
			OrderFrame.list3.clear();

		} else if (model3.getRowCount() == 3) {

			DBConn conn = DBConn.getInstance();
			Bill bill = new Bill();
			bill.setMenu1(model3.getValueAt(0, 0) + "");
			bill.setCount1((model3.getValueAt(0, 1) + ""));
			bill.setPrice1((model3.getValueAt(0, 2) + ""));
			bill.setMenu2(model3.getValueAt(1, 0) + "");
			bill.setCount2((model3.getValueAt(1, 1) + ""));
			bill.setPrice2((model3.getValueAt(1, 2) + ""));
			bill.setMenu3(model3.getValueAt(2, 0) + "");
			bill.setCount3((model3.getValueAt(2, 1) + ""));
			bill.setPrice3((model3.getValueAt(2, 2) + ""));
			bill.setSumprice(Integer.parseInt(tfSumSale3.getText()));
			conn.insertBill(bill);
			new BillFrame();
			model3.setNumRows(0);
			tfSumSale3.setText("");
			OrderFrame.sum = 0;
			OrderFrame.list3.clear();

		} else if (model3.getRowCount() == 2) {

			DBConn conn = DBConn.getInstance();
			Bill bill = new Bill();
			bill.setMenu1(model3.getValueAt(0, 0) + "");
			bill.setCount1((model3.getValueAt(0, 1) + ""));
			bill.setPrice1((model3.getValueAt(0, 2) + ""));
			bill.setMenu2(model3.getValueAt(1, 0) + "");
			bill.setCount2((model3.getValueAt(1, 1) + ""));
			bill.setPrice2((model3.getValueAt(1, 2) + ""));
			bill.setSumprice(Integer.parseInt(tfSumSale3.getText()));
			conn.insertBill(bill);
			new BillFrame();
			model3.setNumRows(0);
			tfSumSale3.setText("");
			OrderFrame.sum = 0;
			OrderFrame.list3.clear();

		} else if (model3.getRowCount() == 1) {

			DBConn conn = DBConn.getInstance();
			Bill bill = new Bill();
			bill.setMenu1(model3.getValueAt(0, 0) + "");
			bill.setCount1((model3.getValueAt(0, 1) + ""));
			bill.setPrice1((model3.getValueAt(0, 2) + ""));
			bill.setSumprice(Integer.parseInt(tfSumSale3.getText()));
			conn.insertBill(bill);
			new BillFrame();
			model3.setNumRows(0);
			tfSumSale3.setText("");
			OrderFrame.sum = 0;
			OrderFrame.list3.clear();

		} else if (model3.getRowCount() == 0) {
			JOptionPane.showMessageDialog(null, "주문을 먼저 하셔야합니다.");
		} else if (model3.getRowCount() == 6) {
			JOptionPane.showMessageDialog(null, "주문은 최대 5개까지 가능합니다.");
		}
	   
	   
   }
    private void payment4() {
	   if (model4.getRowCount() == 5) {
			DBConn conn = DBConn.getInstance();
			Bill bill = new Bill();
			bill.setMenu1(model4.getValueAt(0, 0) + "");
			bill.setCount1((model4.getValueAt(0, 1) + ""));
			bill.setPrice1((model4.getValueAt(0, 2) + ""));
			bill.setMenu2(model4.getValueAt(1, 0) + "");
			bill.setCount2((model4.getValueAt(1, 1) + ""));
			bill.setPrice2((model4.getValueAt(1, 2) + ""));
			bill.setMenu3(model4.getValueAt(2, 0) + "");
			bill.setCount3((model4.getValueAt(2, 1) + ""));
			bill.setPrice3((model4.getValueAt(2, 2) + ""));
			bill.setMenu4(model4.getValueAt(3, 0) + "");
			bill.setCount4((model4.getValueAt(3, 1) + ""));
			bill.setPrice4((model4.getValueAt(3, 2) + ""));
			bill.setMenu5(model4.getValueAt(4, 0) + "");
			bill.setCount5((model4.getValueAt(4, 1) + ""));
			bill.setPrice5((model4.getValueAt(4, 2) + ""));
			bill.setSumprice(Integer.parseInt(tfSumSale4.getText()));
			conn.insertBill(bill);
			new BillFrame();
			model4.setNumRows(0);
			tfSumSale4.setText("");
			OrderFrame.sum = 0;
			OrderFrame.list4.clear();

		} else if (model4.getRowCount() == 4) {

			DBConn conn = DBConn.getInstance();
			Bill bill = new Bill();
			bill.setMenu1(model4.getValueAt(0, 0) + "");
			bill.setCount1((model4.getValueAt(0, 1) + ""));
			bill.setPrice1((model4.getValueAt(0, 2) + ""));
			bill.setMenu2(model4.getValueAt(1, 0) + "");
			bill.setCount2((model4.getValueAt(1, 1) + ""));
			bill.setPrice2((model4.getValueAt(1, 2) + ""));
			bill.setMenu3(model4.getValueAt(2, 0) + "");
			bill.setCount3((model4.getValueAt(2, 1) + ""));
			bill.setPrice3((model4.getValueAt(2, 2) + ""));
			bill.setMenu4(model4.getValueAt(3, 0) + "");
			bill.setCount4((model4.getValueAt(3, 1) + ""));
			bill.setPrice4((model4.getValueAt(3, 2) + ""));
			bill.setSumprice(Integer.parseInt(tfSumSale4.getText()));
			conn.insertBill(bill);
			new BillFrame();
			model4.setNumRows(0);
			tfSumSale4.setText("");
			OrderFrame.sum = 0;
			OrderFrame.list4.clear();

		} else if (model4.getRowCount() == 3) {

			DBConn conn = DBConn.getInstance();
			Bill bill = new Bill();
			bill.setMenu1(model4.getValueAt(0, 0) + "");
			bill.setCount1((model4.getValueAt(0, 1) + ""));
			bill.setPrice1((model4.getValueAt(0, 2) + ""));
			bill.setMenu2(model4.getValueAt(1, 0) + "");
			bill.setCount2((model4.getValueAt(1, 1) + ""));
			bill.setPrice2((model4.getValueAt(1, 2) + ""));
			bill.setMenu3(model4.getValueAt(2, 0) + "");
			bill.setCount3((model4.getValueAt(2, 1) + ""));
			bill.setPrice3((model4.getValueAt(2, 2) + ""));
			bill.setSumprice(Integer.parseInt(tfSumSale4.getText()));
			conn.insertBill(bill);
			new BillFrame();
			model4.setNumRows(0);
			tfSumSale4.setText("");
			OrderFrame.sum = 0;
			OrderFrame.list4.clear();

		} else if (model4.getRowCount() == 2) {

			DBConn conn = DBConn.getInstance();
			Bill bill = new Bill();
			bill.setMenu1(model4.getValueAt(0, 0) + "");
			bill.setCount1((model4.getValueAt(0, 1) + ""));
			bill.setPrice1((model4.getValueAt(0, 2) + ""));
			bill.setMenu2(model4.getValueAt(1, 0) + "");
			bill.setCount2((model4.getValueAt(1, 1) + ""));
			bill.setPrice2((model4.getValueAt(1, 2) + ""));
			bill.setSumprice(Integer.parseInt(tfSumSale4.getText()));
			conn.insertBill(bill);
			new BillFrame();
			model4.setNumRows(0);
			tfSumSale4.setText("");
			OrderFrame.sum = 0;
			OrderFrame.list4.clear();

		} else if (model4.getRowCount() == 1) {

			DBConn conn = DBConn.getInstance();
			Bill bill = new Bill();
			bill.setMenu1(model4.getValueAt(0, 0) + "");
			bill.setCount1((model4.getValueAt(0, 1) + ""));
			bill.setPrice1((model4.getValueAt(0, 2) + ""));
			bill.setSumprice(Integer.parseInt(tfSumSale4.getText()));
			conn.insertBill(bill);
			new BillFrame();
			model4.setNumRows(0);
			tfSumSale4.setText("");
			OrderFrame.sum = 0;
			OrderFrame.list4.clear();

		} else if (model4.getRowCount() == 0) {
			JOptionPane.showMessageDialog(null, "주문을 먼저 하셔야합니다.");
		} else if (model4.getRowCount() == 6) {
			JOptionPane.showMessageDialog(null, "주문은 최대 5개까지 가능합니다.");
		}
	   
	   
   }
}
