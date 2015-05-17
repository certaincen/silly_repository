package hit.edu.Bean;

import java.util.ArrayList;

public class MPS {
	public MPS(String name, ArrayList<Integer> gR, ArrayList<Integer> sR,
			ArrayList<Integer> pOH, ArrayList<Integer> pAB,
			ArrayList<Integer> nR, ArrayList<Integer> pORC,
			ArrayList<Integer> pOR, int oH, int aL, int lT, int sT, int sS,
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
	private ArrayList<Integer> GR =  new ArrayList<Integer>();
	private ArrayList<Integer> SR = new ArrayList<Integer>();
	private ArrayList<Integer> POH = new ArrayList<Integer>();
	private ArrayList<Integer> PAB = new ArrayList<Integer>();
	private ArrayList<Integer> NR = new ArrayList<Integer>();
	private ArrayList<Integer> PORC = new ArrayList<Integer>();
	private ArrayList<Integer> POR = new ArrayList<Integer>();
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
	public MPS(String mName)
	{
		Name = mName;
	}
	public MPS()
	{
		
	}
	public void pushGR(int x)
	{
		GR.add(x);
	}
	public void addGRatIndex(int x, int index)
	{
		int tmp = this.getGRatIndex(x);
		GR.remove(index);
		GR.add(tmp+x, index);
		
	}
	public void setGRatIndex(int x, int index)
	{
		GR.add(x, index);
	}
	public int getGRatIndex(int index)
	{
		return GR.get(index);
	}
	public void pushSR(int x)
	{
		SR.add(x);
	}
	public void setSRatIndex(int x, int index)
	{
		SR.add(x, index);
	}
	public int getSRatIndex(int index)
	{
		return SR.get(index);
	}
	public void pushPOH(int x)
	{
		POH.add(x);
	}
	public void setPOHatIndex(int x, int index)
	{
		POH.add(x, index);
	}
	public int getPOHatIndex(int index)
	{
		return POH.get(index);
	}
	public void pushPAB(int x)
	{
		PAB.add(x);
	}
	public void setPABatIndex(int x, int index)
	{
		PAB.add(x, index);
	}
	public int getPABatIndex(int index)
	{
		return PAB.get(index);
	}
	public void pushNR(int x)
	{
		NR.add(x);
	}
	public void setNRatIndex(int x, int index)
	{
		NR.add(x, index);
	}
	public int getNRatIndex(int index)
	{
		return NR.get(index);
	}
	public void pushPORC(int x)
	{
		PORC.add(x);
	}
	public void setPORCatIndex(int x, int index)
	{
		PORC.add(x, index);
	}
	public int getPORCatIndex(int index)
	{
		return PORC.get(index);
	}
	public void pushPOR(int x)
	{
		POR.add(x);
	}
	public void setPORatIndex(int x, int index)
	{
		POR.add(x, index);
	}
	public int getPORatIndex(int index)
	{
		return POR.get(index);
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
				for (int i=0;i<inventory.getSchedule().size();i++)
				{
					SR.add(i, inventory.getSchedule().get(i));
				}			
	}
	public void show() {
		System.out.println(this.toString());
		
	}

	public String getName() {
		return Name;
	}
}
