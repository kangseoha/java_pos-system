package schedule;

public class Schedule {

	private String ename;
	private int start_time;
	private int end_time;

	public Schedule() {
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String object) {
		this.ename = object;
	}

	public int getStart_time() {
		return start_time;
	}

	public void setStart_time(int start_time) {
		this.start_time = start_time;
	}

	public int getEnd_time() {
		return end_time;
	}

	public void setEnd_time(int end_time) {
		this.end_time = end_time;
	}

	@Override
	public String toString() {
		return "Schedule [ ename=" + ename + ", start_time=" + start_time + ", end_time=" + end_time + "]";
	}

}
