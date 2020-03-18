package inventory;

public class Inventory {
	private int ino;
	private String element;
	private int count;

	/**
	 * @return the ino
	 */
	public int getIno() {
		return ino;
	}

	/**
	 * @param ino the ino to set
	 */
	public void setIno(int ino) {
		this.ino = ino;
	}

	/**
	 * @return the element
	 */
	public String getElement() {
		return element;
	}

	/**
	 * @param element the element to set
	 */
	public void setElement(String element) {
		this.element = element;
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

	@Override
	public String toString() {
		return "Inventory [ino=" + ino + ", element=" + element + ", count=" + count + "]";
	}

}
