package native_jdbc_programing.ch01;

public class MathEx {
	private int a;
	private int b;
	public MathEx() {
		// TODO Auto-generated constructor stub
	}
	public MathEx(int a, int b) {
		this.a = a;
		this.b = b;
	}
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	public int add() {
		return a+b;
	}
	public int sub() {
		return a>b ? a-b : b-a;
	}

}
