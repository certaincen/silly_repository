package hit.edu.Entity;

import java.util.ArrayList;
import java.util.HashMap;

import hit.edu.Bean.MPS;
import hit.edu.Bean.Material;
import hit.edu.Util.DBFunc;

public class TreeNode {
	private Material MaterialNode;
	private MPS MPSNode;
	private ArrayList<TreeNode> son = new ArrayList<TreeNode>();
	private TreeNode father;
	private HashMap<String, Integer> QP = new HashMap<String, Integer>();
	private int inDegree;
	private int isCalFlag;
	
	public MPS getMPSNode() {
		return MPSNode;
	}
	public void setMPSNode(MPS mPSNode) {
		MPSNode = mPSNode;
	}
	public TreeNode(Material MaterialIn, MPS mpsIn)
	{
		MaterialNode = MaterialIn;
		MPSNode = mpsIn;
		this.inDegree = 0;
		this.isCalFlag = 0;
	}
	public TreeNode() {
		// TODO Auto-generated constructor stub
	}
	public int getIsCalFlag() {
		return isCalFlag;
	}
	public void setIsCalFlag(int isCalFlag) {
		this.isCalFlag = isCalFlag;
	}
	public Material getNode() {
		return MaterialNode;
	}
	public void setNode(Material mNode) {
		this.MaterialNode = mNode;
	}
	public ArrayList<TreeNode> getSon() {
		return son;
	}
	public void setSon(ArrayList<TreeNode> son) {
		this.son = son;
	}

	public HashMap<String, Integer> getQP() {
		return QP;
	}
	public void addQP(String key, int value)
	{
		QP.put(key, value);
	}
	public void setQP(HashMap<String, Integer> qP) {
		QP = qP;
	}
	public void addSon(TreeNode tmp) {
		this.son.add(tmp);	
	}
	public TreeNode getFather() {
		return father;
	}
	public void setFather(TreeNode father) {
		this.father = father;
	}
	
	public int getInDegree() {
		return inDegree;
	}
	public void setInDegree(int inDegree) {
		this.inDegree = inDegree;
	}
	public void writetoDB()
	{
		for (TreeNode i :this.son)
		{
			//System.out.println(i.toString());
			DBFunc dbfunc = new DBFunc();
			if (dbfunc.MPS_Insert(i.getMPSNode(), i.getMPSNode().getPAB().length) == -1)
			{
				dbfunc.MPS_Update(i.getMPSNode(), i.getMPSNode().getPAB().length);
			}
			i.MPSNode.show();
			i.writetoDB();
		}
	}

}
