package dbconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import bill.Bill;
import employee.Emp;
import inventory.Inventory;
import menu.Menu;
import order.Order;
import sale.Sale;
import saleByMenu.SaleByMenu;
import schedule.Schedule;

public class DBConn {
	private DBConn() {
	}

	private static DBConn instance = new DBConn();

	public static DBConn getInstance() {
		return instance;
	}

	private Connection getConnect() {
		Connection conn = null;
		String driverName = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "pos";
		String passwd = "pos";
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, user, passwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public String login(int eno) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String pw = null;
		String sql = "select epw from employee where eno=?";
		try {
			conn = getConnect();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, eno);
			rs = ps.executeQuery();
			while (rs.next()) {
				pw = rs.getString("epw");

			}
		} catch (

		Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, ps, rs);
		}
		return pw;
	}

	public List<Emp> selectEmpAll() { // AdminFrame 클릭했을때 초기화면 DB자료 전부 뿌려 줌
		List<Emp> list = new ArrayList<Emp>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from employee order by eno";
		try {
			conn = getConnect();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Emp emp = new Emp();
				emp.setEno(rs.getInt("eno"));
				emp.setEname(rs.getString("ename"));
				emp.setEposition(rs.getString("eposition"));
				emp.setEtel(rs.getString("etel"));
				list.add(emp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, ps, rs);
		}
		return list;
	}

	public void deleteEmp(Emp emp) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "delete from employee where eno=?";
		try {
			conn = getConnect();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, emp.getEno());
			int n = ps.executeUpdate();
			if (n == 1) {
				String msg = "삭제가 완료되었습니다.";
				JOptionPane.showMessageDialog(null, msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, ps);
		}
	}

	public void updateEmp(Emp emp) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "update employee set eno=?,epw=?,ename=?,eposition=?,etel=? where eno=?";
		try {
			conn = getConnect();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, emp.getEno());
			ps.setString(2, emp.getEpw());
			ps.setString(3, emp.getEname());
			ps.setString(4, emp.getEposition());
			ps.setString(5, emp.getEtel());
			ps.setInt(6, emp.getEno());

			int n = ps.executeUpdate();
			if (n == 1) {
				String msg = " 정보가 수정 되었습니다.";
				JOptionPane.showMessageDialog(null, msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, ps);
		}
	}

	public void insertEmp(Emp emp) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "insert into Employee values(?,?,?,?,?)";
		try {
			conn = getConnect();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, emp.getEno());
			ps.setString(2, emp.getEpw());
			ps.setString(3, emp.getEname());
			ps.setString(4, emp.getEposition());
			ps.setString(5, emp.getEtel());
			int n = ps.executeUpdate();
			if (n == 1) {
				String msg = emp.getEname() + "님 이(가) 추가 되었습니다."; // 수정사항
				JOptionPane.showMessageDialog(null, msg); // 수정사항
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, ps);
		}
	}

	public Emp selectEmpOne(int eno) {
		Emp emp = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from employee where eno=? ";
		try {
			conn = getConnect();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, eno);
			rs = ps.executeQuery();
			if (rs.next()) {
				emp = new Emp();
				emp.setEno(rs.getInt(1));
				emp.setEpw(rs.getString(2));
				emp.setEname(rs.getString(3));
				emp.setEposition(rs.getString(4));
				emp.setEtel(rs.getString(5));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, ps, rs);
		}

		return emp;

	}

	public Emp selectEno(String ename, String etel) {
		Emp emp = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from employee where ename=? and etel=? ";
		try {
			conn = getConnect();
			ps = conn.prepareStatement(sql);
			ps.setString(1, ename);
			ps.setString(2, etel);
			rs = ps.executeQuery();
			if (rs.next()) { // 줄 이동
				emp = new Emp();
				emp.setEno(rs.getInt(1));
				emp.setEpw(rs.getString(2));
				emp.setEname(rs.getString(3));
				emp.setEposition(rs.getString(4));
				emp.setEtel(rs.getString(5));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, ps, rs);
		}

		return emp;

	}

	public boolean Check(int eno) { // 사원번호의 중복체크
		boolean result = true;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = getConnect();
		String sql = "select * from employee where Eno=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, eno);
			rs = ps.executeQuery();
			if (rs.next())
				result = false;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, ps, rs);
		}
		return result;

	}

	public List<Sale> selectSaleAll1() { // AdminFrame 클릭했을때 초기화면 DB자료 전부 뿌려 줌
		List<Sale> list = new ArrayList<Sale>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from sale order by sno";
		try {
			conn = getConnect();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Sale sale = new Sale();
				sale.setSno(rs.getInt("sno"));
				sale.setMenu(rs.getString("menu"));
				sale.setCount(rs.getInt("count"));
				sale.setPrice(rs.getInt("price"));
				sale.setReg_date(rs.getTimestamp("reg_date"));
				list.add(sale);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, ps, rs);
		}
		return list;
	}

	public List<Menu> selectMenuAll() { // Menu 초기화면 DB자료 전부 뿌려 줌 by projectlee
		List<Menu> menuList = new ArrayList<Menu>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from menu order by mno";
		try {
			conn = getConnect();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Menu menu = new Menu();
				menu.setMno(rs.getInt("mno"));
				menu.setMenu(rs.getString("menu"));
				menu.setPrice(rs.getInt("price"));
				menuList.add(menu);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, ps, rs);
		}
		return menuList;
	}

	public void updateMenu(Menu menu) {// Menu 수정 by projectlee
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "update menu set menu=?, price=? where mno=?";
		try {
			conn = getConnect();
			ps = conn.prepareStatement(sql);
			ps.setString(1, menu.getMenu());
			ps.setInt(2, menu.getPrice());
			ps.setInt(3, menu.getMno());
			int n = ps.executeUpdate();
			if (n == 1)
				System.out.println("데이터 수정 성공");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, ps);
		}

	}

	public void deleteMenu(int mno) {// Menu 삭제 by projectlee
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "delete from menu where mno=?";
		try {
			conn = getConnect();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, mno);
			int n = ps.executeUpdate();
			if (n == 1)
				System.out.println("메뉴 데이터 삭제 성공");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, ps);
		}

	}

	public void insertOrder(Order order) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "insert into sale (sno,menu,count,price) values(sno_seq.nextval,?,?,?)";
		try {
			conn = getConnect();
			ps = conn.prepareStatement(sql);
			ps.setString(1, order.getMenu());
			ps.setInt(2, order.getCount());
			ps.setInt(3, order.getPrice());
			int n = ps.executeUpdate();
			if (n == 1) {
				System.out.println("판매 데이터 입력 성공");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, ps);
		}
	}

	public void deleteSale(Sale sale) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "delete from sale where sno=?";
		try {
			conn = getConnect();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, sale.getSno());
			int n = ps.executeUpdate();
			if (n == 1) {
				System.out.println("판매 데이터 삭제 완료");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, ps);
		}
	}

	public Emp selectEmpPosition(String ename) {
		Emp emp = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from employee where ename=? ";
		try {
			conn = getConnect();
			ps = conn.prepareStatement(sql);
			ps.setString(1, ename);
			rs = ps.executeQuery();
			if (rs.next()) {
				emp = new Emp();
				emp.setEno(rs.getInt(1));
				emp.setEpw(rs.getString(2));
				emp.setEname(rs.getString(3));
				emp.setEposition(rs.getString(4));
				emp.setEtel(rs.getString(5));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, ps, rs);
		}

		return emp;

	}

	public List<Schedule> selectSchedule() {
		List<Schedule> list = new ArrayList<Schedule>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from schedule order by ename";
		try {
			conn = getConnect();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Schedule schedule = new Schedule();
				schedule.setEname(rs.getString("ename"));
				schedule.setStart_time(rs.getInt("start_time"));
				schedule.setEnd_time(rs.getInt("end_time"));
				list.add(schedule);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, ps, rs);
		}
		return list;
	}

	public Schedule selectScheduleOne(String ename) {
		Schedule schedule = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from schedule where ename=? ";
		try {
			conn = getConnect();
			ps = conn.prepareStatement(sql);
			ps.setString(1, ename);
			rs = ps.executeQuery();
			if (rs.next()) {
				schedule = new Schedule();
				schedule.setEname(rs.getString(1));
				schedule.setStart_time(rs.getInt(2));
				schedule.setEnd_time(rs.getInt(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, ps, rs);
		}
		return schedule;
	}

	public void insertSchedule(Schedule schedule) {
		Connection conn = null;
		PreparedStatement ps = null;

		String sql = "insert into schedule(ename,start_time,end_time) values(?,?,?)";

		try {

			conn = getConnect();
			ps = conn.prepareStatement(sql);
			ps.setString(1, schedule.getEname());
			ps.setInt(2, schedule.getStart_time());
			ps.setInt(3, schedule.getEnd_time());

			int n = ps.executeUpdate();
			if (n == 1) {
				String msg = "스케줄 입력 성공했습니다.";
				JOptionPane.showMessageDialog(null, msg);
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			dbClose(conn, ps);
		}
	}

	public void updateSchedule(Schedule schedule) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "update schedule set ename=?, start_time=?, end_time=? where ename=?";
		try {
			conn = getConnect();
			ps = conn.prepareStatement(sql);
			ps.setString(1, schedule.getEname());
			ps.setInt(2, schedule.getStart_time());
			ps.setInt(3, schedule.getEnd_time());
			ps.setString(4, schedule.getEname());
			int n = ps.executeUpdate();

			if (n == 1){
				String msg = "스케줄 수정 성공했습니다.";
				JOptionPane.showMessageDialog(null, msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, ps);
		}

	}

	public void deleteSchedule(String ename) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "delete from schedule where ename=?";
		try {
			conn = getConnect();
			ps = conn.prepareStatement(sql);
			ps.setString(1, ename);
			int n = ps.executeUpdate();
			if (n == 1)
				if (n == 1) {
					String msg = "스케줄 삭제 성공했습니다.";
					JOptionPane.showMessageDialog(null, msg);
				}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, ps);
		}

	}

	public List<Sale> selectSaleAll() { // AdminFrame 클릭했을때 초기화면 DB자료 전부 뿌려 줌
		List<Sale> list = new ArrayList<Sale>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from sale order by sno";
		try {
			conn = getConnect();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Sale sale = new Sale();
				sale.setSno(rs.getInt("sno"));
				sale.setMenu(rs.getString("menu"));
				sale.setCount(rs.getInt("count"));
				sale.setPrice(rs.getInt("price"));
				sale.setReg_date(rs.getTimestamp("reg_date"));
				list.add(sale);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, ps, rs);
		}
		return list;
	}

	public List<SaleByMenu> selectSaleStatusByMenu() { // 메뉴조회 by projectlee 191126
		List<SaleByMenu> smList = new ArrayList<SaleByMenu>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select menu,  sum(price) from sale  group by menu order by sum(price) DESC";
		try {
			conn = getConnect();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				SaleByMenu sm = new SaleByMenu();
				sm.setMenu(rs.getString("menu"));
				sm.setSumPrice(rs.getInt("sum(price)"));
				smList.add(sm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, ps, rs);
		}
		return smList;

	}

	public List<SaleByMenu> selectSaleStatusByMenuCount() { // 메뉴별로 합계조회 by projectlee 191126
		List<SaleByMenu> smList = new ArrayList<SaleByMenu>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select menu, count(*)\r\n" + "from sale\r\n" + "group by menu\r\n" + "order by count(*) desc";
		try {
			conn = getConnect();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				SaleByMenu sm = new SaleByMenu();
				sm.setMenu(rs.getString("menu"));
				sm.setSumPrice(rs.getInt("count(*)"));
				smList.add(sm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, ps, rs);
		}
		return smList;
	}

	public List<Inventory> SelectInventoryAll() { // Inventory by projectlee 191126
		List<Inventory> inList = new ArrayList<Inventory>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from inventory";
		try {
			conn = getConnect();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Inventory in = new Inventory();
				in.setIno(rs.getInt("ino"));
				in.setElement(rs.getString("element"));
				in.setCount(rs.getInt("count"));
				inList.add(in);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, ps, rs);
		}
		return inList;
	}

	public void insertBill(Bill bill) {// bill에 입력
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "insert into bill (bno,menu1,count1,price1,menu2,count2,price2,menu3,count3,price3,menu4,count4,price4,menu5,count5,price5,sumprice)\r\n"
				+ "values(sno_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			conn = getConnect();
			ps = conn.prepareStatement(sql);
			ps.setString(1, bill.getMenu1());
			ps.setString(2, bill.getCount1());
			ps.setString(3, bill.getPrice1());
			ps.setString(4, bill.getMenu2());
			ps.setString(5, bill.getCount2());
			ps.setString(6, bill.getPrice2());
			ps.setString(7, bill.getMenu3());
			ps.setString(8, bill.getCount3());
			ps.setString(9, bill.getPrice3());
			ps.setString(10, bill.getMenu4());
			ps.setString(11, bill.getCount4());
			ps.setString(12, bill.getPrice4());
			ps.setString(13, bill.getMenu5());
			ps.setString(14, bill.getCount5());
			ps.setString(15, bill.getPrice5());
			ps.setInt(16, bill.getSumprice());

			int n = ps.executeUpdate();
			if (n == 1) {
				System.out.println("결제 데이터 입력 성공");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, ps);
		}
	}

	public List<Bill> SelelctBillAll() { // Bill 모두 가져옴 by projectlee 191126
		List<Bill> billList = new ArrayList<Bill>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from bill";
		try {
			conn = getConnect();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Bill bill = new Bill();
				bill.setBno(rs.getInt("bno"));
				bill.setMenu1(rs.getString("menu1"));
				bill.setCount1(rs.getString("Count1"));
				bill.setPrice1(rs.getString("price1"));
				bill.setMenu2(rs.getString("menu2"));
				bill.setCount2(rs.getString("Count2"));
				bill.setPrice2(rs.getString("price2"));
				bill.setMenu3(rs.getString("menu3"));
				bill.setCount3(rs.getString("Count3"));
				bill.setPrice3(rs.getString("price3"));
				bill.setMenu4(rs.getString("menu4"));
				bill.setCount4(rs.getString("Count4"));
				bill.setPrice4(rs.getString("price4"));
				bill.setMenu5(rs.getString("menu5"));
				bill.setCount5(rs.getString("Count5"));
				bill.setPrice5(rs.getString("price5"));
				bill.setSumprice(rs.getInt("sumprice"));
				bill.setReg_date(rs.getTimestamp("reg_date"));
				billList.add(bill);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, ps, rs);
		}
		return billList;
	}
	
	public List<Bill> SelelctBillAllnvl() { // Bill 모두 가져옴 by projectlee 191126
		List<Bill> billList = new ArrayList<Bill>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select  bno, menu1, count1, price1, nvl(menu2,' ') as menu2,nvl(count2,' ') as count2,nvl(price2,' ') as price2,nvl(menu3,' ') as menu3,nvl(count3,' ') as count3,nvl(price3,' ') as price3,nvl(menu4,' ') as menu4, nvl(count4,' ') as count4,nvl(price4,' ') as price4,nvl(menu5,' ') as menu5,nvl(count5,' ') as count5,nvl(price5,' ') as price5,sumprice,reg_date \r\n" + 
				"from bill";
		try {
			conn = getConnect();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Bill bill = new Bill();
				bill.setBno(rs.getInt("bno"));
				bill.setMenu1(rs.getString("menu1"));
				bill.setCount1(rs.getString("Count1"));
				bill.setPrice1(rs.getString("price1"));
				bill.setMenu2(rs.getString("menu2"));
				bill.setCount2(rs.getString("Count2"));
				bill.setPrice2(rs.getString("price2"));
				bill.setMenu3(rs.getString("menu3"));
				bill.setCount3(rs.getString("Count3"));
				bill.setPrice3(rs.getString("price3"));
				bill.setMenu4(rs.getString("menu4"));
				bill.setCount4(rs.getString("Count4"));
				bill.setPrice4(rs.getString("price4"));
				bill.setMenu5(rs.getString("menu5"));
				bill.setCount5(rs.getString("Count5"));
				bill.setPrice5(rs.getString("price5"));
				bill.setSumprice(rs.getInt("sumprice"));
				bill.setReg_date(rs.getTimestamp("reg_date"));
				billList.add(bill);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, ps, rs);
		}
		return billList;
	}

	public void updateInventory(Inventory in) {// Inventory 수정 by projectlee 191126
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "update Inventory set count = ? where ino = ?";
		try {
			conn = getConnect();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, in.getCount());
			ps.setInt(2, in.getIno());
			int n = ps.executeUpdate();
			if (n == 1) {
				System.out.println("수정 성공");
			} else {
				System.out.println("수정 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int selectSaleSum() { // AdminFrame 클릭했을때 초기화면 DB자료 전부 뿌려 줌

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select sum(sumprice) as sumprice from bill ";
		int sumPrice = 0;
		try {
			conn = getConnect();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			sumPrice = rs.getInt("sumprice");
//	       while(rs.next()) {
//	          Sale sale=new Sale();
//	          sale.setSno(rs.getInt("sno"));
//	          sale.setTable(rs.getString("table"));
//	          sale.setMenu(rs.getString("menu"));
//	          sale.setCount(rs.getInt("count"));
//	       
//	       }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, ps, rs);
		}
		return sumPrice;
	}

	public int selectSaleTotalCustomer() { // AdminFrame 클릭했을때 초기화면 DB자료 전부 뿌려 줌

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select count(bno) as countbno from bill ";
		int countBno = 0;
		try {
			conn = getConnect();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			countBno = rs.getInt("countbno");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, ps, rs);
		}
		return countBno;
	}
	
	
	public int selectTotalCustomer() { // AdminFrame 클릭했을때 초기화면 DB자료 전부 뿌려 줌

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select count(sno) as countsno from sale ";
		int countsno = 0;
		try {
			conn = getConnect();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			countsno = rs.getInt("countsno");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, ps, rs);
		}
		return countsno;
	}

	public int selectSaleCustomerTransaction() {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select avg(sumprice) as avgprice from bill ";
		int avgprice = 0;
		try {
			conn = getConnect();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			avgprice = rs.getInt("avgprice");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, ps, rs);
		}
		return avgprice;
	}

	private void dbClose(Connection conn, PreparedStatement ps) {
		try {
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
		}
	}

	private void dbClose(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
		}

	}

}