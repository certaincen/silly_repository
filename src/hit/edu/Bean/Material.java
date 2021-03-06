package hit.edu.Bean;

public class Material implements Comparable<Material>{
	public Material(String name, int lT, int sT, int sS, int lLC, String lSR,
			int lS) {
		super();
		Name = name;
		LT = lT;
		ST = sT;
		SS = sS;
		LLC = lLC;
		this.setLSR(lSR);
		LS = lS;
	}
	public Material()
	{
		
	}
	//public final static int LFL = 1;
//	public final static int FOQ = 2;
	//public final static int POQ = 3;
	private String Name;
	private int LT;
	private int ST;
	private int SS;
	private int LLC;
	private int LSR;
	private int LS;
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
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
        public String getStringLSR(){
		if (LSR == 1)
			 return "LFL";
		if (LSR == 2)
			 return "FOQ";
		if (LSR == 3)
			 return "POQ";
                return "Error";
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
