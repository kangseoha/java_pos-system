package schedule;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dbconn.DBConn;
import employee.Emp;
import employee.EmpFrame;

import inventory.InventoryFrame;
import menu.MenuFrame;

import order.TableFrame;
import sale.SaleFrame;

import javax.swing.JLabel;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class ScheduleFrame extends JFrame implements ActionListener {

	private JPanel panel;
	private JPanel panelM_2;
	private JLabel paint;
	private JPanel panel1;
//	private JTextField tfName;
	private JTextField tfStart_time;
	private JTextField tfEnd_time;
	private JComboBox<String> cbName;
	private JComboBox<String> cbMonth;
	private JComboBox<String> cbDay;
	private String[] months = new String[12];
	private String schedule_time[] = { " ", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22",
			"일급" };
	private Color paintColor[] = { Color.BLUE, Color.RED, Color.YELLOW, Color.pink, Color.GREEN, Color.ORANGE,
			Color.CYAN };
	private JPanel contentPaneM;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScheduleFrame frame = new ScheduleFrame();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ScheduleFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 515);
		contentPaneM = new JPanel();
		contentPaneM.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPaneM.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPaneM);

		JPanel paneMl = new JPanel();
		contentPaneM.add(paneMl, BorderLayout.WEST);
		paneMl.setLayout(new GridLayout(8, 1, 0, 0));

		JButton btnOrder = new JButton("주문 관리");
		btnOrder.setForeground(Color.BLACK);
		paneMl.add(btnOrder);

		JButton btnSale = new JButton("매출 현황");
		paneMl.add(btnSale);

		JButton btnMenu = new JButton("메뉴 관리");
		paneMl.add(btnMenu);

		JButton btnInventory = new JButton("재고 관리");
		paneMl.add(btnInventory);

		JButton btnEmployee = new JButton("직원 관리");
		paneMl.add(btnEmployee);

		JButton btnSchedule = new JButton("스케줄 관리");
		paneMl.add(btnSchedule);
		JPanel panelM_1 = new JPanel();
		contentPaneM.add(panelM_1, BorderLayout.SOUTH);

		panelM_2 = new JPanel();
		panelM_1.add(panelM_2);

		JButton btnClose = new JButton("닫기");
		panelM_2.add(btnClose);

		JPanel contentPane = new JPanel();
		contentPaneM.add(contentPane, BorderLayout.CENTER);
		contentPane.setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);

		panel1 = new JPanel();
		contentPane.add(panel1, BorderLayout.SOUTH);

		DBConn conn = DBConn.getInstance();
		List<Schedule> list = conn.selectSchedule();
		panel.setLayout(new GridLayout(list.size() + 1, 13, 0, 0));

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("직원 이름");
		panel_1.add(lblNewLabel);

		cbName = new JComboBox<String>();
		setScheduleName();
		panel_1.add(cbName);

		JLabel label = new JLabel("근무 시간");
		panel_1.add(label);
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("클릭");
			}
		});
		tfStart_time = new JTextField();
		tfStart_time.setColumns(3);
		panel_1.add(tfStart_time);

		JLabel label_1 = new JLabel("~");
		panel_1.add(label_1);

		tfEnd_time = new JTextField();
		tfEnd_time.setColumns(3);
		panel_1.add(tfEnd_time);

		JButton btnInput = new JButton("입력");
		panel_1.add(btnInput);

		JButton btnModify = new JButton("수정");
		panel_1.add(btnModify);

		JButton btnDelete = new JButton("삭제");
		panel_1.add(btnDelete);

		cbName.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				String Name = (cbName.getSelectedItem() + "");
				DBConn conn = DBConn.getInstance();
				Schedule schedule = conn.selectScheduleOne(cbName.getSelectedItem() + "");
				try {
					tfStart_time.setText(schedule.getStart_time() + "");
					tfEnd_time.setText(schedule.getEnd_time() + "");
				} catch (NullPointerException e1) {
					tfStart_time.setText("");
					tfEnd_time.setText("");
				}
			}
		});

		showSchedule();


		btnInput.addActionListener(this);
		btnModify.addActionListener(this);
		btnDelete.addActionListener(this);

		btnOrder.addActionListener(this);
		btnSale.addActionListener(this);
		btnMenu.addActionListener(this);
		btnInventory.addActionListener(this);
		btnEmployee.addActionListener(this);
		btnSchedule.addActionListener(this);
		btnClose.addActionListener(this);
		
		
		btnInput.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panel.removeAll();
				panel1.removeAll();
			}
		});
	
		
		setVisible(true);
	}

	private void setScheduleName() {
		DBConn conn = DBConn.getInstance();
		List<Emp> list = conn.selectEmpAll();
		String[] scheduleName = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			scheduleName[i] = list.get(i).getEname();

		}
		cbName = new JComboBox<String>(scheduleName);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DBConn conn = DBConn.getInstance();
		String command = e.getActionCommand();

		if (command.equals("입력")) {

			Schedule schedule = new Schedule();
			schedule.setEname(cbName.getSelectedItem() + "");
			schedule.setStart_time(Integer.parseInt(tfStart_time.getText()));
			schedule.setEnd_time(Integer.parseInt(tfEnd_time.getText()));
			conn.insertSchedule(schedule);
			panel.removeAll();
			panel1.removeAll();
			

		} else if (command.equals("수정")) {
			Schedule schedule = new Schedule();
			schedule.setEname(cbName.getSelectedItem() + "");
			schedule.setStart_time(Integer.parseInt(tfStart_time.getText()));
			schedule.setEnd_time(Integer.parseInt(tfEnd_time.getText()));
			conn.updateSchedule(schedule);
		
			
		} else if (command.equals("삭제")) {

			conn.deleteSchedule(cbName.getSelectedItem() + "");

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

	private void showSchedule() {
		DBConn conn = DBConn.getInstance();
		List<Schedule> list = conn.selectSchedule();
		int sumAll = 0;
		
		
		for (int i = 0; i < schedule_time.length; i++) {
			panel.setLayout(new GridLayout(list.size() + 1, 13, 0, 0));
			panel.add(new JLabel(schedule_time[i]));
		}
		for (int i = 0; i < list.size(); i++) {
			int count = 0;
			Schedule s = list.get(i);
			panel.add(new JLabel(s.getEname()));
			for (int j = 11; j < 23; j++) {
				if (j >= s.getStart_time() && j < s.getEnd_time()) {
					paint = new JLabel("");
					paint.setOpaque(true);
					paint.setBackground(paintColor[i]);
					panel.add(paint);
					count++;
				} else {
					System.out.println("");
					panel.add(new JLabel(""));
				}

			}

			Emp emplist = conn.selectEmpPosition(s.getEname());

			String grade = emplist.getEposition();

			if (grade.equals("A1")) {
				int sumA1 = count * 8350;
				panel.add(new JLabel(sumA1 / 10000 + "만" + sumA1 % 10000 + "원"));
				sumAll += sumA1;
			} else if (grade.equals("A2")) {
				double sumA2 = count * 8350 * 1.2;
				panel.add(new JLabel((int) (sumA2 / 10000) + "만" + (int) (sumA2 % 10000) + "원"));
				sumAll += sumA2;
			} else if (grade.equals("A3")) {
				double sumA3 = count * 8350 * 1.5;
				panel.add(new JLabel((int) (sumA3 / 10000) + "만" + (int) (sumA3 % 10000) + "원"));
				sumAll += sumA3;
			}
		}

		panel1.add(new JLabel("오늘의 인건비 총액 : " + sumAll / 10000 + "만" + sumAll % 10000 + "원"));

	}
}