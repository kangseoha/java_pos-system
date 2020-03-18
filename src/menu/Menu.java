package menu;

public class Menu {
	private int mno;
	private String menu;
	private int price;

	/**
	 * @return the mno
	 */
	public int getMno() {
		return mno;
	}

	/**
	 * @param mno the mno to set
	 */
	public void setMno(int mno) {
		this.mno = mno;
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
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param pirce the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Menu [mno=" + mno + ", menu=" + menu + ", pirce=" + price + "]";
	}

}
