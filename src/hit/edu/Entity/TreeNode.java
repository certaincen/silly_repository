package hit.edu.Entity;

import java.util.ArrayList;

import hit.edu.Bean.MPS;
import hit.edu.Bean.Material;

public class TreeNode {
	private Material MaterialNode;
	private MPS MPSNode;
	private ArrayList<TreeNode> son;
	private TreeNode father;
	private int QP;
	
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
	}
	public TreeNode() {
		// TODO Auto-generated constructor stub
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
	public int getQP() {
		return QP;
	}
	public void setQP(int qP) {
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
	

}
