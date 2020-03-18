package sale;

import java.sql.Timestamp;

public class Sale {
	int sno;
	String menu;
	int count;
	int price;
	Timestamp reg_date;

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Timestamp getReg_date() {
		return reg_date;
	}

	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}

	

	@Override
	public String toString() {
		return "Sale [sno=" + sno + ", menu=" + menu + ", count=" + count + ", price=" + price + ", reg_date="
				+ reg_date + "]";
	}
}
