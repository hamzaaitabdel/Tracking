package entities;

public class Graphinfo {
	public static int count=1;
	public int index=1;
	private int day;
	private String month;
	private int operations ;
	private int height;
	
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Graphinfo(int day, int operations,String month) {
		super();
		this.day = day;
		this.month=month;
		this.operations = operations;
		index=count++;
	}
	public Graphinfo() {
		index=count++;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getOperations() {
		return operations;
	}
	
	public void setOperations(int operations) {
		this.operations = operations;
	}
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	@Override
	public String toString() {
		return "Graphinfo [day=" + day +"month= "+month+", operations=" + operations + "]";
	}
	
	
}
