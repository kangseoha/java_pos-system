package bill;

import java.sql.Timestamp;

public class Bill {
	private int bno;
	private String menu1;
	private String count1;
	private String price1;
	private String menu2;
	private String count2;
	private String price2;
	private String menu3;
	private String count3;
	private String price3;
	private String menu4;
	private String count4;
	private String price4;
	private String menu5;
	private String count5;
	private String price5;
	private int sumprice;
	private Timestamp reg_date;

	/**
	 * @return the bno
	 */
	public int getBno() {
		return bno;
	}

	/**
	 * @param bno the bno to set
	 */
	public void setBno(int bno) {
		this.bno = bno;
	}

	/**
	 * @return the menu1
	 */
	public String getMenu1() {
		return menu1;
	}

	/**
	 * @param menu1 the menu1 to set
	 */
	public void setMenu1(String menu1) {
		this.menu1 = menu1;
	}

	/**
	 * @return the count1
	 */
	public String getCount1() {
		return count1;
	}

	/**
	 * @param count1 the count1 to set
	 */
	public void setCount1(String count1) {
		this.count1 = count1;
	}

	/**
	 * @return the price1
	 */
	public String getPrice1() {
		return price1;
	}

	/**
	 * @param price1 the price1 to set
	 */
	public void setPrice1(String price1) {
		this.price1 = price1;
	}

	/**
	 * @return the menu2
	 */
	public String getMenu2() {
		return menu2;
	}

	/**
	 * @param menu2 the menu2 to set
	 */
	public void setMenu2(String menu2) {
		this.menu2 = menu2;
	}

	/**
	 * @return the count2
	 */
	public String getCount2() {
		return count2;
	}

	/**
	 * @param count2 the count2 to set
	 */
	public void setCount2(String count2) {
		this.count2 = count2;
	}

	/**
	 * @return the price2
	 */
	public String getPrice2() {
		return price2;
	}

	/**
	 * @param price2 the price2 to set
	 */
	public void setPrice2(String price2) {
		this.price2 = price2;
	}

	/**
	 * @return the menu3
	 */
	public String getMenu3() {
		return menu3;
	}

	/**
	 * @param menu3 the menu3 to set
	 */
	public void setMenu3(String menu3) {
		this.menu3 = menu3;
	}

	/**
	 * @return the count3
	 */
	public String getCount3() {
		return count3;
	}

	/**
	 * @param count3 the count3 to set
	 */
	public void setCount3(String count3) {
		this.count3 = count3;
	}

	/**
	 * @return the price3
	 */
	public String getPrice3() {
		return price3;
	}

	/**
	 * @param price3 the price3 to set
	 */
	public void setPrice3(String price3) {
		this.price3 = price3;
	}

	/**
	 * @return the menu4
	 */
	public String getMenu4() {
		return menu4;
	}

	/**
	 * @param menu4 the menu4 to set
	 */
	public void setMenu4(String menu4) {
		this.menu4 = menu4;
	}

	/**
	 * @return the count4
	 */
	public String getCount4() {
		return count4;
	}

	/**
	 * @param count4 the count4 to set
	 */
	public void setCount4(String count4) {
		this.count4 = count4;
	}

	/**
	 * @return the price4
	 */
	public String getPrice4() {
		return price4;
	}

	/**
	 * @param price4 the price4 to set
	 */
	public void setPrice4(String price4) {
		this.price4 = price4;
	}

	/**
	 * @return the menu5
	 */
	public String getMenu5() {
		return menu5;
	}

	/**
	 * @param menu5 the menu5 to set
	 */
	public void setMenu5(String menu5) {
		this.menu5 = menu5;
	}

	/**
	 * @return the count5
	 */
	public String getCount5() {
		return count5;
	}

	/**
	 * @param count5 the count5 to set
	 */
	public void setCount5(String count5) {
		this.count5 = count5;
	}

	/**
	 * @return the price5
	 */
	public String getPrice5() {
		return price5;
	}

	/**
	 * @param price5 the price5 to set
	 */
	public void setPrice5(String price5) {
		this.price5 = price5;
	}

	/**
	 * @return the sumprice
	 */
	public int getSumprice() {
		return sumprice;
	}

	/**
	 * @param sumprice the sumprice to set
	 */
	public void setSumprice(int sumprice) {
		this.sumprice = sumprice;
	}

	/**
	 * @return the reg_date
	 */
	public Timestamp getReg_date() {
		return reg_date;
	}

	/**
	 * @param reg_date the reg_date to set
	 */
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}

	@Override
	public String toString() {
		return "Bill [bno=" + bno + ", menu1=" + menu1 + ", count1=" + count1 + ", price1=" + price1 + ", menu2="
				+ menu2 + ", count2=" + count2 + ", price2=" + price2 + ", menu3=" + menu3 + ", count3=" + count3
				+ ", price3=" + price3 + ", menu4=" + menu4 + ", count4=" + count4 + ", price4=" + price4 + ", menu5="
				+ menu5 + ", count5=" + count5 + ", price5=" + price5 + ", sumprice=" + sumprice + ", reg_date="
				+ reg_date + "]";
	}

}
