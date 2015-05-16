package hit.edu.Bean;

import java.util.ArrayList;

public class Inventory {
	private String Name;
	private int OH;
	private int AL;
	private ArrayList<Integer> Schedule = new ArrayList<Integer>();
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
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
	public ArrayList<Integer> getSchedule() {
		return Schedule;
	}
	public void setSchedule(ArrayList<Integer> schedule) {
		Schedule = schedule;
	}
	
}
