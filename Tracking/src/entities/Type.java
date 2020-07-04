package entities;

public class Type {
	private int id;
	private String type;
	private int respo1;
	private int respo2;
	private int respo3;
	private int respo4;
	public Type(int id, String type, int respo1, int respo2, int respo3, int respo4) {
		
		this.id = id;
		this.type = type;
		this.respo1 = respo1;
		this.respo2 = respo2;
		this.respo3 = respo3;
		this.respo4 = respo4;
	}
	public Type() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getRespo1() {
		return respo1;
	}
	public void setRespo1(int respo1) {
		this.respo1 = respo1;
	}
	public int getRespo2() {
		return respo2;
	}
	public void setRespo2(int respo2) {
		this.respo2 = respo2;
	}
	public int getRespo3() {
		return respo3;
	}
	public void setRespo3(int respo3) {
		this.respo3 = respo3;
	}
	public int getRespo4() {
		return respo4;
	}
	public void setRespo4(int respo4) {
		this.respo4 = respo4;
	}
	
}
