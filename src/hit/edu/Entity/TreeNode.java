package hit.edu.Entity;

import java.util.ArrayList;

import hit.edu.Bean.Material;

public class TreeNode {
	private Material node;
	private ArrayList<TreeNode> son;
	private int QP;
	public TreeNode(Material in)
	{
		node = in;
	}
	public TreeNode() {
		// TODO Auto-generated constructor stub
	}
	public Material getNode() {
		return node;
	}
	public void setNode(Material node) {
		this.node = node;
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
	

}
