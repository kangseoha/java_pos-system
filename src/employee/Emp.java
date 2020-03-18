package employee;

public class Emp {
	int eno;
	String epw;
	String ename;
	String eposition;
	String etel;

	public int getEno() {
		return eno;
	}

	public void setEno(int eno) {
		this.eno = eno;
	}

	public String getEpw() {
		return epw;
	}

	public void setEpw(String epw) {
		this.epw = epw;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getEposition() {
		return eposition;
	}

	public void setEposition(String eposition) {
		this.eposition = eposition;
	}

	public String getEtel() {
		return etel;
	}

	public void setEtel(String etel) {
		this.etel = etel;
	}

	@Override
	public String toString() {
		return "Emp [eno=" + eno + ", epw=" + epw + ", ename=" + ename + ", eposition=" + eposition + ", etel=" + etel
				+ "]";
	}

}