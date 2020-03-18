package bill;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;

import java.io.OutputStream;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dbconn.DBConn;
import inventory.Inventory;

public class BillFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField tfSumPrice;
	DefaultTableModel model;
	private JTextField tfbno;
	List<String> list = new ArrayList<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		new BillFrame();
	}

	/**
	 * Create the frame.
	 */
	public BillFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);

		JButton btnadd = new JButton("출력");

		panel.add(btnadd);

		JButton btnClose = new JButton("닫기");
		panel.add(btnClose);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		contentPane.add(panel_1, BorderLayout.CENTER);

		String[] colNames = { "메뉴", "갯수", "금액" };
		model = new DefaultTableModel(colNames, 0);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		JTable table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		panel_1.add(scrollPane);

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.NORTH);

		JLabel lblBno = new JLabel("주문번호");
		panel_2.add(lblBno);

		tfbno = new JTextField();
		panel_2.add(tfbno);
		tfbno.setColumns(10);

		JLabel lblSumPrice = new JLabel("총주문금액");
		panel_2.add(lblSumPrice);

		tfSumPrice = new JTextField();
		panel_2.add(tfSumPrice);
		tfSumPrice.setColumns(10);
		setVisible(true);

		btnadd.addActionListener(this);
		btnClose.addActionListener(this);

		addModelData();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		if (command.equals("닫기")) {
		    this.dispose();
		} else if (command.equals("출력")) {
			Inventory();
			add();
		}

	}

	private void addModelData() {
		DBConn conn = DBConn.getInstance();
		List<Bill> billList = conn.SelelctBillAll();
		Bill bill = billList.get(billList.size() - 1);
		Object[] obj1 = new Object[3];
		obj1[0] = bill.getMenu1();
		obj1[1] = bill.getCount1();
		obj1[2] = bill.getPrice1();
		model.addRow(obj1);
		Object[] obj2 = new Object[3];
		obj2[0] = bill.getMenu2();
		obj2[1] = bill.getCount2();
		obj2[2] = bill.getPrice2();
		if (bill.getMenu2() != null) {
			model.addRow(obj2);
		}
		Object[] obj3 = new Object[3];
		obj3[0] = bill.getMenu3();
		obj3[1] = bill.getCount3();
		obj3[2] = bill.getPrice3();
		if (bill.getMenu3() != null) {
			model.addRow(obj3);
		}
		Object[] obj4 = new Object[3];
		obj4[0] = bill.getMenu4();
		obj4[1] = bill.getCount4();
		obj4[2] = bill.getPrice4();
		if (bill.getMenu4() != null) {
			model.addRow(obj4);
		}
		Object[] obj5 = new Object[3];
		obj5[0] = bill.getMenu5();
		obj5[1] = bill.getCount5();
		obj5[2] = bill.getPrice5();
		if (bill.getMenu5() != null) {
			model.addRow(obj5);
		}
		tfSumPrice.setText(bill.getSumprice() + "");
		tfbno.setText(bill.getBno() + "");
	}
	
	private void add(){
	DBConn conn = DBConn.getInstance();
	List<Bill> billList = conn.SelelctBillAllnvl();
	Bill bill = billList.get(billList.size() - 1);
	String str1 = Integer.toString(bill.getBno());
	String str2 = bill.getMenu1();
	String str3 = bill.getCount1();
	String str4 = bill.getPrice1();
	String str5 = bill.getMenu2();
	String str6 = bill.getCount2();
	String str7 = bill.getPrice2();
	String str8 = bill.getMenu3();
	String str9 = bill.getCount3();
	String str10 = bill.getPrice3();
	String str11 = bill.getMenu4();
	String str12 = bill.getCount4();
	String str13 = bill.getPrice4();
	String str14 = bill.getMenu5();
	String str15 = bill.getCount5();
	String str16 = bill.getPrice5();
	String str17 = Integer.toString(bill.getSumprice());
	String str18 = bill.getReg_date()+"";
    list.add(str1);
    list.add(str2);
    list.add(str3);
    list.add(str4);
    list.add(str5);
    list.add(str6);
    list.add(str7);
    list.add(str8);
    list.add(str9);
    list.add(str10);
    list.add(str11);
    list.add(str12);
    list.add(str13);
    list.add(str14);
    list.add(str15);
    list.add(str16);
    list.add(str17);
    list.add(str18);
	try {
		OutputStream output = new FileOutputStream("./bill/bill.txt");
		for(int i =0; i<1; i++) {
		byte [] by = (list.get(i)+"\n").getBytes();
		output.write(by);
		}
		for(int i =1; i<4; i++) {
		byte [] by = (list.get(i)+" ").getBytes();
		output.write(by);
		}
		
	    byte [] by1 = ("\n").getBytes();
		output.write(by1);
		
		for(int i =4; i<7; i++) {
		byte [] by = (list.get(i)+" ").getBytes();
		output.write(by);
		}
		
	    byte [] by2 = ("\n").getBytes();
		output.write(by2);
		
		for(int i =7; i<10; i++) {
		byte [] by = (list.get(i)+" ").getBytes();
		output.write(by);
		}
		
	    byte [] by3 = ("\n").getBytes();
		output.write(by3);
		
		for(int i =10; i<13; i++) {
		byte [] by = (list.get(i)+" ").getBytes();
		output.write(by);
		}
		for(int i =13; i<16; i++) {
		byte [] by = (list.get(i)).getBytes();
		output.write(by);
		}
		for(int i =16; i<18; i++) {
		byte [] by = ("\n"+list.get(i)).getBytes();
		output.write(by);
		
		Desktop.getDesktop().edit(new File("./bill/bill.txt"));
		



		
		}
	}catch (Exception e){
		e.getStackTrace();
	}
	
    
	}


	private void Inventory() {

		if (model.getRowCount() == 5) {
			DBConn conn = DBConn.getInstance();
			if ((model.getValueAt(0, 0) + "").equals("떡볶이")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					for (int j = 0; j < 3; j++) {
						Inventory in = inList1.get(j);
						in.setCount(in.getCount() - 1);
						conn.updateInventory(in);
					}
				}
			} else if ((model.getValueAt(0, 0) + "").equals("치즈떡볶이")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					for (int j = 0; j < 4; j++) {
						Inventory in = inList1.get(j);
						in.setCount(in.getCount() - 1);
						conn.updateInventory(in);
					}
				}
			} else if ((model.getValueAt(0, 0) + "").equals("크림떡볶이")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					for (int j = 0; j < 5; j++) {
						Inventory in = inList1.get(j);
						in.setCount(in.getCount() - 1);
						conn.updateInventory(in);
					}
				}
			} else if ((model.getValueAt(0, 0) + "").equals("김밥")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(5);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(0, 0) + "").equals("순대")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(6);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(0, 0) + "").equals("어묵")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(7);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(0, 0) + "").equals("참치마요컵밥")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(8);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(0, 0) + "").equals("튀김")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(9);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(0, 0) + "").equals("쿨피스")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(10);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			}

			if ((model.getValueAt(1, 0) + "").equals("떡볶이")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					for (int j = 0; j < 3; j++) {
						Inventory in = inList1.get(j);
						in.setCount(in.getCount() - 1);
						conn.updateInventory(in);
					}
				}
			} else if ((model.getValueAt(1, 0) + "").equals("치즈떡볶이")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					for (int j = 0; j < 4; j++) {
						Inventory in = inList1.get(j);
						in.setCount(in.getCount() - 1);
						conn.updateInventory(in);
					}
				}
			} else if ((model.getValueAt(1, 0) + "").equals("크림떡볶이")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					for (int j = 0; j < 5; j++) {
						Inventory in = inList1.get(j);
						in.setCount(in.getCount() - 1);
						conn.updateInventory(in);
					}
				}
			} else if ((model.getValueAt(1, 0) + "").equals("김밥")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(5);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(1, 0) + "").equals("순대")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(6);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(1, 0) + "").equals("어묵")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(7);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(1, 0) + "").equals("참치마요컵밥")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(8);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(1, 0) + "").equals("튀김")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(9);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(1, 0) + "").equals("쿨피스")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(10);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			}

			if ((model.getValueAt(2, 0) + "").equals("떡볶이")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					for (int j = 0; j < 3; j++) {
						Inventory in = inList1.get(j);
						in.setCount(in.getCount() - 1);
						conn.updateInventory(in);
					}
				}
			} else if ((model.getValueAt(2, 0) + "").equals("치즈떡볶이")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					for (int j = 0; j < 4; j++) {
						Inventory in = inList1.get(j);
						in.setCount(in.getCount() - 1);
						conn.updateInventory(in);
					}
				}
			} else if ((model.getValueAt(2, 0) + "").equals("크림떡볶이")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					for (int j = 0; j < 5; j++) {
						Inventory in = inList1.get(j);
						in.setCount(in.getCount() - 1);
						conn.updateInventory(in);
					}
				}
			} else if ((model.getValueAt(2, 0) + "").equals("김밥")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(5);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(2, 0) + "").equals("순대")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(6);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(2, 0) + "").equals("어묵")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(7);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(2, 0) + "").equals("참치마요컵밥")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(8);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(2, 0) + "").equals("튀김")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(9);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(2, 0) + "").equals("쿨피스")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(10);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			}
			if ((model.getValueAt(3, 0) + "").equals("떡볶이")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					for (int j = 0; j < 3; j++) {
						Inventory in = inList1.get(j);
						in.setCount(in.getCount() - 1);
						conn.updateInventory(in);
					}
				}
			} else if ((model.getValueAt(3, 0) + "").equals("치즈떡볶이")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					for (int j = 0; j < 4; j++) {
						Inventory in = inList1.get(j);
						in.setCount(in.getCount() - 1);
						conn.updateInventory(in);
					}
				}
			} else if ((model.getValueAt(3, 0) + "").equals("크림떡볶이")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					for (int j = 0; j < 5; j++) {
						Inventory in = inList1.get(j);
						in.setCount(in.getCount() - 1);
						conn.updateInventory(in);
					}
				}
			} else if ((model.getValueAt(3, 0) + "").equals("김밥")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(5);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(3, 0) + "").equals("순대")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(6);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(3, 0) + "").equals("어묵")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(7);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(3, 0) + "").equals("참치마요컵밥")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(8);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(3, 0) + "").equals("튀김")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(9);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(3, 0) + "").equals("쿨피스")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(10);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			}
			if ((model.getValueAt(4, 0) + "").equals("떡볶이")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					for (int j = 0; j < 3; j++) {
						Inventory in = inList1.get(j);
						in.setCount(in.getCount() - 1);
						conn.updateInventory(in);
					}
				}
			} else if ((model.getValueAt(4, 0) + "").equals("치즈떡볶이")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					for (int j = 0; j < 4; j++) {
						Inventory in = inList1.get(j);
						in.setCount(in.getCount() - 1);
						conn.updateInventory(in);
					}
				}
			} else if ((model.getValueAt(4, 0) + "").equals("크림떡볶이")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					for (int j = 0; j < 5; j++) {
						Inventory in = inList1.get(j);
						in.setCount(in.getCount() - 1);
						conn.updateInventory(in);
					}
				}
			} else if ((model.getValueAt(4, 0) + "").equals("김밥")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(5);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(4, 0) + "").equals("순대")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(6);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(4, 0) + "").equals("어묵")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(7);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(4, 0) + "").equals("참치마요컵밥")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(8);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(4, 0) + "").equals("튀김")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(9);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(4, 0) + "").equals("쿨피스")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(10);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			}

		}

		if (model.getRowCount() == 4) {
			DBConn conn = DBConn.getInstance();
			if ((model.getValueAt(0, 0) + "").equals("떡볶이")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					for (int j = 0; j < 3; j++) {
						Inventory in = inList1.get(j);
						in.setCount(in.getCount() - 1);
						conn.updateInventory(in);
					}
				}
			} else if ((model.getValueAt(0, 0) + "").equals("치즈떡볶이")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					for (int j = 0; j < 4; j++) {
						Inventory in = inList1.get(j);
						in.setCount(in.getCount() - 1);
						conn.updateInventory(in);
					}
				}
			} else if ((model.getValueAt(0, 0) + "").equals("크림떡볶이")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					for (int j = 0; j < 5; j++) {
						Inventory in = inList1.get(j);
						in.setCount(in.getCount() - 1);
						conn.updateInventory(in);
					}
				}
			} else if ((model.getValueAt(0, 0) + "").equals("김밥")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(5);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(0, 0) + "").equals("순대")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(6);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(0, 0) + "").equals("어묵")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(7);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(0, 0) + "").equals("참치마요컵밥")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(8);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(0, 0) + "").equals("튀김")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(9);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(0, 0) + "").equals("쿨피스")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(10);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			}

			if ((model.getValueAt(1, 0) + "").equals("떡볶이")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					for (int j = 0; j < 3; j++) {
						Inventory in = inList1.get(j);
						in.setCount(in.getCount() - 1);
						conn.updateInventory(in);
					}
				}
			} else if ((model.getValueAt(1, 0) + "").equals("치즈떡볶이")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					for (int j = 0; j < 4; j++) {
						Inventory in = inList1.get(j);
						in.setCount(in.getCount() - 1);
						conn.updateInventory(in);
					}
				}
			} else if ((model.getValueAt(1, 0) + "").equals("크림떡볶이")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					for (int j = 0; j < 5; j++) {
						Inventory in = inList1.get(j);
						in.setCount(in.getCount() - 1);
						conn.updateInventory(in);
					}
				}
			} else if ((model.getValueAt(1, 0) + "").equals("김밥")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(5);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(1, 0) + "").equals("순대")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(6);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(1, 0) + "").equals("어묵")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(7);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(1, 0) + "").equals("참치마요컵밥")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(8);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(1, 0) + "").equals("튀김")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(9);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(1, 0) + "").equals("쿨피스")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(10);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			}

			if ((model.getValueAt(2, 0) + "").equals("떡볶이")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					for (int j = 0; j < 3; j++) {
						Inventory in = inList1.get(j);
						in.setCount(in.getCount() - 1);
						conn.updateInventory(in);
					}
				}
			} else if ((model.getValueAt(2, 0) + "").equals("치즈떡볶이")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					for (int j = 0; j < 4; j++) {
						Inventory in = inList1.get(j);
						in.setCount(in.getCount() - 1);
						conn.updateInventory(in);
					}
				}
			} else if ((model.getValueAt(2, 0) + "").equals("크림떡볶이")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					for (int j = 0; j < 5; j++) {
						Inventory in = inList1.get(j);
						in.setCount(in.getCount() - 1);
						conn.updateInventory(in);
					}
				}
			} else if ((model.getValueAt(2, 0) + "").equals("김밥")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(5);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(2, 0) + "").equals("순대")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(6);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(2, 0) + "").equals("어묵")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(7);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(2, 0) + "").equals("참치마요컵밥")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(8);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(2, 0) + "").equals("튀김")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(9);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(2, 0) + "").equals("쿨피스")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(10);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			}
			if ((model.getValueAt(3, 0) + "").equals("떡볶이")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					for (int j = 0; j < 3; j++) {
						Inventory in = inList1.get(j);
						in.setCount(in.getCount() - 1);
						conn.updateInventory(in);
					}
				}
			} else if ((model.getValueAt(3, 0) + "").equals("치즈떡볶이")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					for (int j = 0; j < 4; j++) {
						Inventory in = inList1.get(j);
						in.setCount(in.getCount() - 1);
						conn.updateInventory(in);
					}
				}
			} else if ((model.getValueAt(3, 0) + "").equals("크림떡볶이")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					for (int j = 0; j < 5; j++) {
						Inventory in = inList1.get(j);
						in.setCount(in.getCount() - 1);
						conn.updateInventory(in);
					}
				}
			} else if ((model.getValueAt(3, 0) + "").equals("김밥")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(5);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(3, 0) + "").equals("순대")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(6);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(3, 0) + "").equals("어묵")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(7);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(3, 0) + "").equals("참치마요컵밥")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(8);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(3, 0) + "").equals("튀김")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(9);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(3, 0) + "").equals("쿨피스")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(10);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			}

		}

		if (model.getRowCount() == 3) {
			DBConn conn = DBConn.getInstance();
			if ((model.getValueAt(0, 0) + "").equals("떡볶이")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					for (int j = 0; j < 3; j++) {
						Inventory in = inList1.get(j);
						in.setCount(in.getCount() - 1);
						conn.updateInventory(in);
					}
				}
			} else if ((model.getValueAt(0, 0) + "").equals("치즈떡볶이")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					for (int j = 0; j < 4; j++) {
						Inventory in = inList1.get(j);
						in.setCount(in.getCount() - 1);
						conn.updateInventory(in);
					}
				}
			} else if ((model.getValueAt(0, 0) + "").equals("크림떡볶이")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					for (int j = 0; j < 5; j++) {
						Inventory in = inList1.get(j);
						in.setCount(in.getCount() - 1);
						conn.updateInventory(in);
					}
				}
			} else if ((model.getValueAt(0, 0) + "").equals("김밥")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(5);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(0, 0) + "").equals("순대")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(6);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(0, 0) + "").equals("어묵")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(7);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(0, 0) + "").equals("참치마요컵밥")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(8);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(0, 0) + "").equals("튀김")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(9);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(0, 0) + "").equals("쿨피스")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(10);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			}

			if ((model.getValueAt(1, 0) + "").equals("떡볶이")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					for (int j = 0; j < 3; j++) {
						Inventory in = inList1.get(j);
						in.setCount(in.getCount() - 1);
						conn.updateInventory(in);
					}
				}
			} else if ((model.getValueAt(1, 0) + "").equals("치즈떡볶이")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					for (int j = 0; j < 4; j++) {
						Inventory in = inList1.get(j);
						in.setCount(in.getCount() - 1);
						conn.updateInventory(in);
					}
				}
			} else if ((model.getValueAt(1, 0) + "").equals("크림떡볶이")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					for (int j = 0; j < 5; j++) {
						Inventory in = inList1.get(j);
						in.setCount(in.getCount() - 1);
						conn.updateInventory(in);
					}
				}
			} else if ((model.getValueAt(1, 0) + "").equals("김밥")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(5);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(1, 0) + "").equals("순대")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(6);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(1, 0) + "").equals("어묵")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(7);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(1, 0) + "").equals("참치마요컵밥")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(8);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(1, 0) + "").equals("튀김")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(9);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(1, 0) + "").equals("쿨피스")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(10);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			}

			if ((model.getValueAt(2, 0) + "").equals("떡볶이")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					for (int j = 0; j < 3; j++) {
						Inventory in = inList1.get(j);
						in.setCount(in.getCount() - 1);
						conn.updateInventory(in);
					}
				}
			} else if ((model.getValueAt(2, 0) + "").equals("치즈떡볶이")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					for (int j = 0; j < 4; j++) {
						Inventory in = inList1.get(j);
						in.setCount(in.getCount() - 1);
						conn.updateInventory(in);
					}
				}
			} else if ((model.getValueAt(2, 0) + "").equals("크림떡볶이")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					for (int j = 0; j < 5; j++) {
						Inventory in = inList1.get(j);
						in.setCount(in.getCount() - 1);
						conn.updateInventory(in);
					}
				}
			} else if ((model.getValueAt(2, 0) + "").equals("김밥")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(5);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(2, 0) + "").equals("순대")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(6);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(2, 0) + "").equals("어묵")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(7);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(2, 0) + "").equals("참치마요컵밥")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(8);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(2, 0) + "").equals("튀김")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(9);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(2, 0) + "").equals("쿨피스")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(10);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			}

		}

		if (model.getRowCount() == 2) {
			DBConn conn = DBConn.getInstance();
			if ((model.getValueAt(0, 0) + "").equals("떡볶이")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					for (int j = 0; j < 3; j++) {
						Inventory in = inList1.get(j);
						in.setCount(in.getCount() - 1);
						conn.updateInventory(in);
					}
				}
			} else if ((model.getValueAt(0, 0) + "").equals("치즈떡볶이")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					for (int j = 0; j < 4; j++) {
						Inventory in = inList1.get(j);
						in.setCount(in.getCount() - 1);
						conn.updateInventory(in);
					}
				}
			} else if ((model.getValueAt(0, 0) + "").equals("크림떡볶이")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					for (int j = 0; j < 5; j++) {
						Inventory in = inList1.get(j);
						in.setCount(in.getCount() - 1);
						conn.updateInventory(in);
					}
				}
			} else if ((model.getValueAt(0, 0) + "").equals("김밥")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(5);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(0, 0) + "").equals("순대")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(6);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(0, 0) + "").equals("어묵")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(7);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(0, 0) + "").equals("참치마요컵밥")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(8);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(0, 0) + "").equals("튀김")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(9);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(0, 0) + "").equals("쿨피스")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(10);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			}

			if ((model.getValueAt(1, 0) + "").equals("떡볶이")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					for (int j = 0; j < 3; j++) {
						Inventory in = inList1.get(j);
						in.setCount(in.getCount() - 1);
						conn.updateInventory(in);
					}
				}
			} else if ((model.getValueAt(1, 0) + "").equals("치즈떡볶이")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					for (int j = 0; j < 4; j++) {
						Inventory in = inList1.get(j);
						in.setCount(in.getCount() - 1);
						conn.updateInventory(in);
					}
				}
			} else if ((model.getValueAt(1, 0) + "").equals("크림떡볶이")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					for (int j = 0; j < 5; j++) {
						Inventory in = inList1.get(j);
						in.setCount(in.getCount() - 1);
						conn.updateInventory(in);
					}
				}
			} else if ((model.getValueAt(1, 0) + "").equals("김밥")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(5);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(1, 0) + "").equals("순대")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(6);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(1, 0) + "").equals("어묵")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(7);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(1, 0) + "").equals("참치마요컵밥")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(8);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(1, 0) + "").equals("튀김")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(9);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(1, 0) + "").equals("쿨피스")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(10);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			}

		}

		if (model.getRowCount() == 1) {
			DBConn conn = DBConn.getInstance();
			if ((model.getValueAt(0, 0) + "").equals("떡볶이")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					for (int j = 0; j < 3; j++) {
						Inventory in = inList1.get(j);
						in.setCount(in.getCount() - 1);
						conn.updateInventory(in);
					}
				}
			} else if ((model.getValueAt(0, 0) + "").equals("치즈떡볶이")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					for (int j = 0; j < 4; j++) {
						Inventory in = inList1.get(j);
						in.setCount(in.getCount() - 1);
						conn.updateInventory(in);
					}
				}
			} else if ((model.getValueAt(0, 0) + "").equals("크림떡볶이")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					for (int j = 0; j < 5; j++) {
						Inventory in = inList1.get(j);
						in.setCount(in.getCount() - 1);
						conn.updateInventory(in);
					}
				}
			} else if ((model.getValueAt(0, 0) + "").equals("김밥")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(5);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(0, 0) + "").equals("순대")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(6);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(0, 0) + "").equals("어묵")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(7);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(0, 0) + "").equals("참치마요컵밥")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(8);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(0, 0) + "").equals("튀김")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(9);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			} else if ((model.getValueAt(0, 0) + "").equals("쿨피스")) {
				List<Inventory> inList1 = conn.SelectInventoryAll();
				for (int i = 0; i < Integer.parseInt(model.getValueAt(0, 1) + ""); i++) {
					Inventory in = inList1.get(10);
					in.setCount(in.getCount() - 1);
					conn.updateInventory(in);
				}
			}

		}

	}
}
