package order;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import dbconn.DBConn;
import employee.EmpFrame;
import inventory.InventoryFrame;
import menu.Menu;
import menu.MenuFrame;
import sale.Sale;
import sale.SaleFrame;
import schedule.ScheduleFrame;

public class OrderFrame extends JFrame implements ActionListener {

	private JPanel contentPaneM;
	private JTextField tfMenu;
	private JTextField tfPrice;
	private JComboBox cbCount;
	private LineBorder bb = new LineBorder(Color.BLACK, 1, true);
	static List<Integer> list1 = new ArrayList<Integer>();// 메뉴의 금액을 List에 담는다.
	static List<Integer> list2 = new ArrayList<Integer>();
	static List<Integer> list3 = new ArrayList<Integer>();
	static List<Integer> list4 = new ArrayList<Integer>();
	static int sum = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderFrame frame = new OrderFrame();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public OrderFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 693, 515);
		contentPaneM = new JPanel();
		contentPaneM.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPaneM.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPaneM);

		JPanel panel = new JPanel();

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

		contentPaneM.add(panel, BorderLayout.WEST);

		JPanel panelM_1 = new JPanel();
		contentPaneM.add(panelM_1, BorderLayout.SOUTH);

		JPanel panelM_2 = new JPanel();
		panelM_1.add(panelM_2);

		JButton btnClose = new JButton("닫기");
		panelM_2.add(btnClose);

		JPanel panelM_3 = new JPanel();
		contentPaneM.add(panelM_3, BorderLayout.CENTER);
		panelM_3.setLayout(new BorderLayout(0, 0));

		JPanel panel_4 = new JPanel();

		panelM_3.add(panel_4, BorderLayout.WEST);

		JPanel panel_5 = new JPanel();
		panel_5.setLayout(new GridLayout(1, 2, 0, 0));

		JButton btnOrder1 = new JButton("주문");
		panel_5.add(btnOrder1);

		JButton btnCancle = new JButton("취소");
		panel_5.add(btnCancle);

		JPanel panel_7 = new JPanel();
		panel_7.setLayout(null);

		JLabel lblMenu = new JLabel("메뉴명");
		lblMenu.setSize(40, 50);
		lblMenu.setLocation(0, 0);
		panel_7.add(lblMenu);

		tfMenu = new JTextField();
		tfMenu.setSize(75, 50);
		tfMenu.setLocation(40, 0);
		tfMenu.setColumns(10);
		panel_7.add(tfMenu);

		JLabel lblCount = new JLabel("수량");
		lblCount.setSize(40, 50);
		lblCount.setLocation(0, 50);
		panel_7.add(lblCount);

		String[] count = { "1", "2", "3", "4", "5" };
		cbCount = new JComboBox(count);
		cbCount.setSize(75, 50);
		cbCount.setLocation(40, 50);
		panel_7.add(cbCount);

		DBConn conn = DBConn.getInstance();
		List<Menu> MenuList = conn.selectMenuAll();

		cbCount.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for (int i = 0; i < MenuList.size(); i++) {
					if (tfMenu.getText().equals(MenuList.get(i).getMenu()))
						tfPrice.setText((cbCount.getSelectedIndex() + 1) * MenuList.get(i).getPrice() + "");
				}
			}
		});

		JLabel lblPrice = new JLabel("금액");
		lblPrice.setLocation(0, 100);
		lblPrice.setSize(40, 50);
		panel_7.add(lblPrice);

		tfPrice = new JTextField();
		tfPrice.setSize(75, 50);
		tfPrice.setLocation(40, 100);
		panel_7.add(tfPrice);
		tfPrice.setColumns(10);
		panel_4.setLayout(new BorderLayout(0, 0));
		panel_4.add(panel_5, BorderLayout.SOUTH);
		panel_4.add(panel_7);

		JPanel panel_6 = new JPanel();
		panelM_3.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new GridLayout(3, 3, 0, 0));
		
		URL url1 = getClass().getClassLoader().getResource("order/1.jpg");
		ImageIcon icon1 = new ImageIcon(url1);
		JLabel lblMenu1 = new JLabel(icon1);
		panel_6.add(lblMenu1);
		lblMenu1.setBorder(bb);
		lblMenu1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

				tfMenu.setText(MenuList.get(0).getMenu());
				tfPrice.setText(MenuList.get(0).getPrice() + "");
			}
		});
		
		URL url2 = getClass().getClassLoader().getResource("order/2.jpg");

		ImageIcon menuicon2 = new ImageIcon(url2);
		JLabel lblMenu2 = new JLabel(menuicon2);
		panel_6.add(lblMenu2);
		lblMenu2.setBorder(bb);
		lblMenu2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				tfMenu.setText(MenuList.get(1).getMenu());
				tfPrice.setText(MenuList.get(1).getPrice() + "");
			}
		});
		
		URL url3 = getClass().getClassLoader().getResource("order/3.jpg");
		ImageIcon menuicon3 = new ImageIcon(url3);
		JLabel lblMenu3 = new JLabel(menuicon3);
		panel_6.add(lblMenu3);
		lblMenu3.setBorder(bb);
		lblMenu3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				tfMenu.setText(MenuList.get(2).getMenu());
				tfPrice.setText(MenuList.get(2).getPrice() + "");
			}
		});
		
		URL url4 = getClass().getClassLoader().getResource("order/4.jpg");
		ImageIcon menuicon4 = new ImageIcon(url4);
		JLabel lblMenu4 = new JLabel(menuicon4);
		panel_6.add(lblMenu4);
		lblMenu4.setBorder(bb);
		lblMenu4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				tfMenu.setText(MenuList.get(3).getMenu());
				tfPrice.setText(MenuList.get(3).getPrice() + "");
			}
		});
		
		URL url5 = getClass().getClassLoader().getResource("order/5.jpg");
		ImageIcon menuicon5 = new ImageIcon(url5);
		JLabel lblMenu5 = new JLabel(menuicon5);
		panel_6.add(lblMenu5);
		lblMenu5.setBorder(bb);
		lblMenu5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				tfMenu.setText(MenuList.get(4).getMenu());
				tfPrice.setText(MenuList.get(4).getPrice() + "");
			}
		});

		URL url6 = getClass().getClassLoader().getResource("order/6.jpg");
		ImageIcon menuicon6 = new ImageIcon(url6);
		JLabel lblMenu6 = new JLabel(menuicon6);
		panel_6.add(lblMenu6);
		lblMenu6.setBorder(bb);
		lblMenu6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				tfMenu.setText(MenuList.get(5).getMenu());
				tfPrice.setText(MenuList.get(5).getPrice() + "");
			}
		});

		URL url7 = getClass().getClassLoader().getResource("order/7.jpg");
		ImageIcon menuicon7 = new ImageIcon(url7);
		JLabel lblMenu7 = new JLabel(menuicon7);
		panel_6.add(lblMenu7);
		lblMenu7.setBorder(bb);
		lblMenu7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				tfMenu.setText(MenuList.get(6).getMenu());
				tfPrice.setText(MenuList.get(6).getPrice() + "");
			}
		});

		URL url8 = getClass().getClassLoader().getResource("order/8.jpg");
		ImageIcon menuicon8 = new ImageIcon(url8);
		JLabel lblMenu8 = new JLabel(menuicon8);
		panel_6.add(lblMenu8);
		lblMenu8.setBorder(bb);
		lblMenu8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				tfMenu.setText(MenuList.get(7).getMenu());
				tfPrice.setText(MenuList.get(7).getPrice() + "");
			}
		});

		URL url9 = getClass().getClassLoader().getResource("order/9.jpg");
		ImageIcon menuicon9 = new ImageIcon(url9);
		JLabel lblMenu9 = new JLabel(menuicon9);
		panel_6.add(lblMenu9);
		lblMenu9.setBorder(bb);
		lblMenu9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				tfMenu.setText(MenuList.get(8).getMenu());
				tfPrice.setText(MenuList.get(8).getPrice() + "");
			}
		});

		btnCancle.addActionListener(this);
		btnOrder.addActionListener(this);
		btnOrder1.addActionListener(this);

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
			new TableFrame();
			this.dispose();
		} else if (command.equals("주문")) {// 주문버튼을 누르면 DB에 저장 by projectlee
			DBConn conn = DBConn.getInstance();
			Order order = new Order();
			order.setMenu(tfMenu.getText());
			order.setCount(Integer.parseInt(cbCount.getSelectedItem() + ""));
			order.setPrice(Integer.parseInt((tfPrice.getText())));
			conn.insertOrder(order);

			if (TableFrame.num == 1) {
				sum = 0;
				int aaa = Integer.parseInt(tfPrice.getText());
				list1.add(aaa);
				for (int i = 0; i < list1.size(); i++) {
					sum += list1.get(i);
					TableFrame.tfSumSale1.setText(sum + "");
				}
				List<Sale> list = conn.selectSaleAll();
				Sale sale = list.get(list.size() - 1);
				Object[] obj = new Object[3];
				obj[0] = sale.getMenu();
				obj[1] = sale.getCount();
				obj[2] = sale.getPrice();
				TableFrame.model1.addRow(obj);
				dispose();
			}
			if (TableFrame.num == 2) {

				sum = 0;
				int aaa = Integer.parseInt(tfPrice.getText());
				list2.add(aaa);
				for (int i = 0; i < list2.size(); i++) {
					sum += list2.get(i);
					TableFrame.tfSumSale2.setText(sum + "");
				}
				List<Sale> list = conn.selectSaleAll();
				Sale sale = list.get(list.size() - 1);
				Object[] obj = new Object[3];
				obj[0] = sale.getMenu();
				obj[1] = sale.getCount();
				obj[2] = sale.getPrice();
				TableFrame.model2.addRow(obj);
				dispose();
			}
			if (TableFrame.num == 3) {

				sum = 0;
				int aaa = Integer.parseInt(tfPrice.getText());
				list3.add(aaa);
				for (int i = 0; i < list2.size(); i++) {
					sum += list3.get(i);
					TableFrame.tfSumSale3.setText(sum + "");
				}
				List<Sale> list = conn.selectSaleAll();
				Sale sale = list.get(list.size() - 1);
				Object[] obj = new Object[3];
				obj[0] = sale.getMenu();
				obj[1] = sale.getCount();
				obj[2] = sale.getPrice();
				TableFrame.model3.addRow(obj);
				dispose();
			}
			if (TableFrame.num == 4) {

				sum = 0;
				int aaa = Integer.parseInt(tfPrice.getText());
				list4.add(aaa);
				for (int i = 0; i < list4.size(); i++) {
					sum += list4.get(i);
					TableFrame.tfSumSale4.setText(sum + "");
				}
				List<Sale> list = conn.selectSaleAll();
				Sale sale = list.get(list.size() - 1);
				Object[] obj = new Object[3];
				obj[0] = sale.getMenu();
				obj[1] = sale.getCount();
				obj[2] = sale.getPrice();
				TableFrame.model4.addRow(obj);
				dispose();
			}

		} else if (command.equals("취소")) {// 취소버튼을 누르면 초기화 by projectlee
			tfMenu.setText("");
			cbCount.setSelectedIndex(0);
			tfPrice.setText("");
		}

	}
}
