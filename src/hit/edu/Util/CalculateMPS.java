package hit.edu.Util;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;

import hit.edu.Bean.MPS;
import hit.edu.Bean.Material;
import hit.edu.Entity.TreeNode;

public class CalculateMPS {
	//public final static int T = 12;
	public int T;
	public CalculateMPS(int t)
	{
		T = t;
	}
	public void mpsDecompose(TreeNode root, Queue<TreeNode> q)
	{
		for (TreeNode s:root.getSon())
		{
			
			s.setInDegree(s.getInDegree()-1);
			if (s.getInDegree()>0)
			{
				continue;
			}
			MPS curMPS = s.getMPSNode();
			//int curPAB = curMPS.getOH() - curMPS.getAL() + max(curMPS.getSRatIndex(0), 0);
			int curPAB = curMPS.getOH() - curMPS.getAL() + max(curMPS.getSR()[0], 0);
			curMPS.setPAB(curPAB, 0);
			for (int i=1;i<=T;i++)
			{
				if (i==1)
				{
				//	int curPOH = curMPS.getPABatIndex(0) + curMPS.getSRatIndex(1) - curMPS.getGRatIndex(1) - max(curMPS.getGRatIndex(0), 0);
				//	curMPS.setPOHatIndex(curPOH, i);
					int curPOH = curMPS.getPAB()[0] + curMPS.getSR()[1] - curMPS.getGR()[1] - max(curMPS.getGR()[0], 0);
					curMPS.setPOH(curPOH, i);
				}
				else
				{
				//	int curPOH = curMPS.getPABatIndex(i-1) + curMPS.getSRatIndex(i) - curMPS.getGRatIndex(i);
				//	curMPS.setPOHatIndex(curPOH, i);
					int curPOH = curMPS.getPAB()[i-1] + curMPS.getSR()[i] - curMPS.getGR()[i];
					curMPS.setPOH(curPOH, i);
				//	System.out.println(s.getNode().getName());
				//	System.out.println(curMPS.getPOH().length);
				}
			//	if (curMPS.getPOHatIndex(i) < curMPS.getSS())
				if (curMPS.getPOH()[i] < curMPS.getSS())
				{
					int curNR = curMPS.getSS() - curMPS.getPOH()[i];
					curMPS.setNR(curNR, i);
					calculatePorcByLSR(curMPS, curMPS.getLSR(), i);
					//int curNR = curMPS.getSS() - curMPS.getPOHatIndex(i);
					//curMPS.setNRatIndex(curNR, i);
					//curMPS.setPORCatIndex(calculatePorcByLSR(curMPS, curMPS.getLSR(), i), i);
				}
				else
				{
					curMPS.setNR(0, i);
					curMPS.setPOR(0, i);
					//curMPS.setNRatIndex(0,i);
					//curMPS.setPORCatIndex(0, i);
				}
				curPAB = curMPS.getPOH()[i] + curMPS.getPORC()[i];
				curMPS.setPAB(curPAB, i);
			 //   curPAB = curMPS.getPOHatIndex(i) + curMPS.getPORCatIndex(i);
			//	curMPS.setPABatIndex(i, curPAB);
				int PORIndex = i - curMPS.getLT();
				if (PORIndex < 0)
				{
					
				}
				else
				{
				//curMPS.setPORatIndex(curMPS.getPORCatIndex(i), PORIndex);
				curMPS.setPOR(curMPS.getPORC()[i], PORIndex);
				}
				s.setMPSNode(curMPS);
			
			}
			System.out.println();
			for (int i=1;i<=T;i++)
				generateNRToSon(s, i);
			s.setIsCalFlag(1);
			//outputMPS(curMPS);
		}
		for (TreeNode s:root.getSon())
		{
			//mpsDecompose(s);
			q.add(s);
		}
		return;
	}
	private int calculatePorcByLSR(MPS curMPS, int lsr, int t) {
		//if (curMPS.getPOHatIndex(t)<0)
		if (curMPS.getPOH()[t]<0)
		{
			if (lsr == 1)
			{
				//curMPS.setPORCatIndex(curMPS.getLS()*curMPS.getNRatIndex(t), t);
				curMPS.setPORC(curMPS.getLS()*curMPS.getNR()[t], t);
			}
			if (lsr == 2)
			{
				int orderNum = curMPS.getLS();
				curMPS.setPORC(curMPS.getLS(), t);
				//curMPS.setPORCatIndex(curMPS.getLS(), t);
			}
			if (lsr == 3)
			{
				int index = curMPS.getLS();
				curMPS.setPOR(curMPS.getNR()[t-index], t);
				//curMPS.setPORCatIndex(curMPS.getNR()[t-index], t);
			}
		}
		else
		{
			//curMPS.setPORCatIndex(0, t);
			curMPS.setPORC(0, t);
		}

		return 0;
	}
	private void outputMPS(MPS curMPS) {
		curMPS.show();
		
	}
	private void generateNRToSon(TreeNode root, int t) {
	//	for (TreeNode w:root.getSon())
		{
		MPS curMPS = root.getMPSNode();
		for (TreeNode s:root.getSon())
		{
			MPS sonMpsNode = s.getMPSNode();
		//	sonMpsNode.addGRatIndex(curMPS.getPORatIndex(t)*s.getQP(), t);
			sonMpsNode.addGR(curMPS.getPOR()[t]*s.getQP().get(root.getNode().getName()), t); 
			s.setMPSNode(sonMpsNode);
		}	
		}
	}
	private int max(int x, int y) {
		
		return  x>y?x:y;
	}
	public void calculateMPS(TreeNode root)
	{

		for (TreeNode s:root.getSon())
		{
			for (TreeNode ts:s.getSon())
			{
				InitGR(s);
			}
			
		}
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		mpsDecompose(root, q);
	/*	for (TreeNode s:root.getSon())
		{
			for (int i=1;i<=T;i++)
			{
				generateNRToSon(s, i);
				
			}
			if (s.getIsCalFlag() == 1)
			mpsDecompose(s, q);
			else
				q.add(s);
		}*/
	//	BlockingQueue<TreeNode> qq = new BlockingQueue<TreeNode>();

		while(!q.isEmpty())
		{
			TreeNode s = q.remove();
			if (s.getIsCalFlag() == 1)
				mpsDecompose(s, q);
			else
				q.add(s);
		}
	}
	private void InitGR(TreeNode root)
	{
		if (root.equals(null))
		{
			return;
		}
		MPS rootMPS = root.getMPSNode();
		
		for (TreeNode i:root.getSon())
		{
			MPS sonMPS = i.getMPSNode();
			sonMPS.addGR(rootMPS.getPORC()[0], 0);
		//	sonMPS.addGRatIndex(rootMPS.getPORCatIndex(0), 0);
			for (TreeNode s:i.getSon())
			{
				InitGR(s);
			}
		}
	}

}
