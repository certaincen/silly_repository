package hit.edu.Bean;

public class BOM {
	private String Father;
	private String Child;
	private int QP;
	
	public BOM(String father, String child, int qP) {
		super();
		Father = father;
		Child = child;
		QP = qP;
	}
	public String getFather() {
		return Father;
	}
	public void setFather(String father) {
		Father = father;
	}
	public String getChild() {
		return Child;
	}
	public void setChild(String child) {
		Child = child;
	}
	public int getQP() {
		return QP;
	}
	public void setQP(int qP) {
		QP = qP;
	}
	

}
