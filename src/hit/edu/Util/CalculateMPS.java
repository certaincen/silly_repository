package hit.edu.Util;

import hit.edu.Bean.MPS;
import hit.edu.Bean.Material;
import hit.edu.Entity.TreeNode;

public class CalculateMPS {
	public final static int T = 12;
	public void mpsDecompose(TreeNode root)
	{
		for (TreeNode s:root.getSon())
		{
			MPS curMPS = s.getMPSNode();
			int curPAB = curMPS.getOH() - curMPS.getAL() + max(curMPS.getSRatIndex(0), 0);
			for (int i=1;i<=T;i++)
			{
				if (i==1)
				{
					int curPOH = curMPS.getPABatIndex(0) + curMPS.getSRatIndex(1) - curMPS.getGRatIndex(1) - max(curMPS.getGRatIndex(0), 0);
					curMPS.setPOHatIndex(curPOH, i);
				}
				else
				{
					int curPOH = curMPS.getPABatIndex(i-1) + curMPS.getSRatIndex(i) - curMPS.getGRatIndex(i);
					curMPS.setPOHatIndex(curPOH, i);
				}
				if (curMPS.getPOHatIndex(i) < curMPS.getSS())
				{
					int curNR = curMPS.getSS() - curMPS.getPOHatIndex(i);
					curMPS.setNRatIndex(curNR, i);
					curMPS.setPORCatIndex(calculatePorcByLSR(curMPS, curMPS.getLSR(), i), i);
				}
				else
				{
					curMPS.setNRatIndex(0,i);
					curMPS.setPORCatIndex(0, i);
				}
			    curPAB = curMPS.getPOHatIndex(i) + curMPS.getPORCatIndex(i);
				curMPS.setPABatIndex(i, curPAB);
				int PORIndex = i - curMPS.getLT();
				curMPS.setPORatIndex(curMPS.getPORCatIndex(i), PORIndex);
				s.setMPSNode(curMPS);
				generateNRToSon(s, i);
			}
			outputMPS(curMPS);
		}
		for (TreeNode s:root.getSon())
		{
			mpsDecompose(s);
		}
		return;
	}
	private int calculatePorcByLSR(MPS curMPS, int lsr, int t) {
		if (curMPS.getPOHatIndex(t)<0)
		{
			if (lsr == 1)
			{
				curMPS.setPORCatIndex(curMPS.getLS()*curMPS.getNRatIndex(t), t);
			}
			if (lsr == 2)
			{
				curMPS.setPORCatIndex(curMPS.getLS(), t);
			}
			if (lsr == 3)
			{
				int index = curMPS.getLS();
				curMPS.setPORCatIndex(curMPS.getNRatIndex(t-index), t);
			}
		}
		else
		{
			curMPS.setPORCatIndex(0, t);
		}

		return 0;
	}
	private void outputMPS(MPS curMPS) {
		curMPS.show();
		
	}
	private void generateNRToSon(TreeNode root, int t) {
		MPS curMPS = root.getMPSNode();
		for (TreeNode s:root.getSon())
		{
			MPS sonMpsNode = s.getMPSNode();
			sonMpsNode.addGRatIndex(curMPS.getPORatIndex(t)*s.getQP(), t);
			s.setMPSNode(sonMpsNode);
		}		
	}
	private int max(int x, int y) {
		
		return  x>y?x:y;
	}
	public void calculateMPS(TreeNode root)
	{
		for (TreeNode s:root.getSon())
		{
			InitGR(s);
		}
			mpsDecompose(root);
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
			sonMPS.addGRatIndex(rootMPS.getPORCatIndex(0), 0);
			for (TreeNode s:i.getSon())
			{
				InitGR(s);
			}
		}
	}

}
