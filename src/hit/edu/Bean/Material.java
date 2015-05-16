package hit.edu.Bean;

public class Material implements Comparable<Material>{
	//public final static int LFL = 1;
//	public final static int FOQ = 2;
	//public final static int POQ = 3;
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
	public int getLSR() {
		return LSR;
	}
	public void setLSR(String lsr) {
		if (lsr.equals("LFL"))
		LSR = 1;
		if (lsr.equals("FOQ"))
			LSR = 2;
		if (lsr.equals("POQ"))
			LSR = 3;
	}
	public int getLS() {
		return LS;
	}
	public void setLS(int lS) {
		LS = lS;
	}
	private int LSR;
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
