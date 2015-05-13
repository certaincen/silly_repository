package hit.edu.Bean;

public class Material implements Comparable<Material>{
	private String Name;
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	private int LT;
	private int ST;
	private int SS;
	private int LLC;
	public int getST() {
		return ST;
	}
	public void setST(int sT) {
		ST = sT;
	}
	public int getSS() {
		return SS;
	}
	public void setSS(int sS) {
		SS = sS;
	}
	public int getLLC() {
		return LLC;
	}
	public void setLLC(int lLC) {
		LLC = lLC;
	}
	public String getLSR() {
		return LSR;
	}
	public void setLSR(String lSR) {
		LSR = lSR;
	}
	public int getLS() {
		return LS;
	}
	public void setLS(int lS) {
		LS = lS;
	}
	private String LSR;
	private int LS;
	public int getLT() {
		return LT;
	}
	public void setLT(int lT) {
		LT = lT;
	}
	@Override
	public int compareTo(Material x) {
		return this.LLC-x.getLLC();
	}

}
