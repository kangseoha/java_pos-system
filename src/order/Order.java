package order;

import java.sql.Timestamp;

public class Order {
	private int sno;
	private String menu;
	private int count;
	private int price;
	private Timestamp reg_data;

	/**
	 * @return the sno
	 */
	public int getSno() {
		return sno;
	}

	/**
	 * @param sno the sno to set
	 */
	public void setSno(int sno) {
		this.sno = sno;
	}

	/**
	 * @return the menu
	 */
	public String getMenu() {
		return menu;
	}

	/**
	 * @param menu the menu to set
	 */
	public void setMenu(String menu) {
		this.menu = menu;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return the reg_data
	 */
	public Timestamp getReg_data() {
		return reg_data;
	}

	/**
	 * @param reg_data the reg_data to set
	 */
	public void setReg_data(Timestamp reg_data) {
		this.reg_data = reg_data;
	}

	@Override
	public String toString() {
		return "Order [sno=" + sno + ", menu=" + menu + ", count=" + count + ", price=" + price + ", reg_data="
				+ reg_data + "]";
	}

}
