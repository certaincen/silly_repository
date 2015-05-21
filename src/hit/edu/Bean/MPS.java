package hit.edu.Bean;

import java.util.ArrayList;

public class MPS {
	public MPS(String name, int[] gR, int[] sR,
			int[] pOH, int[] pAB,
			int[] nR, int[] pORC,
			int[] pOR, int oH, int aL, int lT, int sT, int sS,
			int lSR, int lS) {
		super();
		Name = name;
		GR = gR;
		SR = sR;
		POH = pOH;
		PAB = pAB;
		NR = nR;
		PORC = pORC;
		POR = pOR;
		OH = oH;
		AL = aL;
		LT = lT;
		ST = sT;
		SS = sS;
		LSR = lSR;
		LS = lS;
	}
	private String Name;
/*	private ArrayList<Integer> GR =  new ArrayList<Integer>();
	private ArrayList<Integer> SR = new ArrayList<Integer>();
	private ArrayList<Integer> POH = new ArrayList<Integer>();
	private ArrayList<Integer> PAB = new ArrayList<Integer>();
	private ArrayList<Integer> NR = new ArrayList<Integer>();
	private ArrayList<Integer> PORC = new ArrayList<Integer>();
	private ArrayList<Integer> POR = new ArrayList<Integer>();*/
	int[] GR = new int[13];
	int[] SR = new int[13];
	int[] POH = new int[13];
	int[] PAB = new int[13];
	int[] NR = new int[13];
	int[] PORC = new int[13];
	int[] POR = new int[13];
	
	private int OH;
	private int AL;
	private int LT;
	private int ST;
	private int SS;
	private int LSR;
	private int LS;
	

	public int getLS() {
		return LS;
	}
	public void setLS(int lS) {
		LS = lS;
	}
	public void copyFormMatrial(Material m)
	{
		LT = m.getLT();
		ST = m.getST();
		
	}
	public MPS(String mName, int t)
	{
		Name = mName;
		int[] GR = new int[t];
		int[] SR = new int[t];
		int[] POH = new int[t];
		int[] PAB = new int[t];
		int[] NR = new int[t];
		int[] PORC = new int[t];
		int[] POR = new int[t];
	}
	public MPS()
	{
		
	}

	public int[] getGR() {
		return GR;
	}
	public void setGR(int gR, int index) {
		GR[index] = gR;
	}
	public int[] getSR() {
		return SR;
	}
	public void setSR(int sR, int index) {
		SR[index] = sR;
	}
	public int[] getPOH() {
		return POH;
	}
	public void setPOH(int pOH, int index) {
		POH[index] = pOH;
	}
	public int[] getPAB() {
		return PAB;
	}
	public void setPAB(int pAB, int index) {
		PAB[index] = pAB;
	}
	public int[] getNR() {
		return NR;
	}
	public void setNR(int nR, int index) {
		NR[index] = nR;
	}
	public int[] getPORC() {
		return PORC;
	}
	public void setPORC(int pORC, int index) {
		PORC[index] = pORC;
	}
	public int[] getPOR() {
		return POR;
	}
	public void setPOR(int pOR, int index) {
		POR[index] = pOR;
	}
	public int getOH() {
		return OH;
	}
	public void setOH(int oH) {
		OH = oH;
	}
	public int getAL() {
		return AL;
	}
	public void setAL(int aL) {
		AL = aL;
	}
	public int getLT() {
		return LT;
	}
	public void setLT(int lT) {
		LT = lT;
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
	public int getLSR() {
		return LSR;
	}
	public void setLSR(int lsr) {
		LSR = lsr;
	}
	public void copyFromMaterialAndInventory(Material material, Inventory inventory) {
				this.LT = material.getLT();
				this.ST = material.getST();
				this.SS = material.getSS();
				this.LS = material.getLS();
				this.LSR = material.getLSR();
				this.OH = inventory.getOH();
				this.AL = inventory.getAL();
				for (int i=0;i<inventory.getSchedule().length;i++)
				{
					SR[i] = inventory.getSchedule()[i];
				}			
	}
	public void show() {

		System.out.println("Name\t" + Name);
		System.out.println("OH\t" + OH);
		System.out.println("AL\t" + AL);
		System.out.println("LT\t" + LT);
		System.out.println("ST\t" + ST);
		System.out.println("SS\t" + SS);
		System.out.println("LSR\t" + LSR);
		System.out.println("LS\t" + LS);
		for (int i=0;i<GR.length;i++)
		System.out.print(GR[i]+"\t");
		System.out.print("\n");
		for (int i=0;i<SR.length;i++)
			System.out.print(SR[i]+"\t");
		System.out.print("\n");
		for (int i=0;i<POH.length;i++)
			System.out.print(POH[i]+"\t");
		System.out.print("\n");
		for (int i=0;i<PAB.length;i++)
			System.out.print(PAB[i]+"\t");
		System.out.print("\n");
		for (int i=0;i<NR.length;i++)
			System.out.print(NR[i]+"\t");
		System.out.print("\n");
		for (int i=0;i<PORC.length;i++)
			System.out.print(PORC[i]+"\t");
		System.out.print("\n");
		for (int i=0;i<POR.length;i++)
			System.out.print(POR[i]+"\t");
		System.out.print("\n");

		
		
		//System.out.println(this.toString());
		
	}
	public void setGR(int[] gR) {
		GR = gR;
	}
	public void setSR(int[] sR) {
		SR = sR;
	}
	public void setPOH(int[] pOH) {
		POH = pOH;
	}
	public void setPAB(int[] pAB) {
		PAB = pAB;
	}
	public void setNR(int[] nR) {
		NR = nR;
	}
	public void setPORC(int[] pORC) {
		PORC = pORC;
	}
	public void setPOR(int[] pOR) {
		POR = pOR;
	}
	public void addGR(int t, int i) {
		GR[i] += t;
		//System.out.println(i);
		
	}
	public void setName(String name)
	{
		this.Name = name;
	}
	public String getName() {
		return Name;
	}
}
