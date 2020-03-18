package saleByMenu;

public class SaleByMenu {
	private String menu;
	private int sumPrice;

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
	 * @return the sumPrice
	 */
	public int getSumPrice() {
		return sumPrice;
	}

	/**
	 * @param sumPrice the sumPrice to set
	 */
	public void setSumPrice(int sumPrice) {
		this.sumPrice = sumPrice;
	}

	@Override
	public String toString() {
		return "SaleMenu [menu=" + menu + ", sumPrice=" + sumPrice + "]";
	}

}
