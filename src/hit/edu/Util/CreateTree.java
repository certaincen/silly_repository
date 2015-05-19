package hit.edu.Util;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hit.edu.Bean.*;
import hit.edu.Entity.TreeNode;

public class CreateTree {
	private HashMap<String, TreeNode> NodeMap;
	public CreateTree()
	{
		NodeMap = new HashMap<String, TreeNode>();
	}
	HashMap<String, TreeNode> GetMap()
	{
		return NodeMap;
	}
	public TreeNode createTree(BOM[] bom, Material[] material, HashMap<String, Inventory> inventoryMap)
	{
		TreeNode root = new TreeNode();
		NodeMap.put("root", root);
		//Collections.sort(material);
		for (Material i:material)
		{
			TreeNode tmp = new TreeNode(i,new MPS(i.getName()));
			MPS tmpMps = tmp.getMPSNode();
			tmpMps.copyFromMaterialAndInventory(i, inventoryMap.get(i.getName()));
			NodeMap.put(i.getName(), tmp);
			if (i.getLLC()==0)
			{
				root.addSon(tmp);
				tmp.addQP("root", 1);
				tmp.setFather(root);
			}
		}
		for (BOM i:bom)
		{
			TreeNode f = NodeMap.get(i.getFather());
			TreeNode s = NodeMap.get(i.getChild());
			f.addSon(s);
			s.addQP(i.getFather(), i.getQP());
			s.setInDegree(s.getInDegree()+1);
			s.setFather(f);
		}
		TreeNode node = NodeMap.get("A");
		MPS mpsNode = node.getMPSNode();
	//	node.setIsCalFlag(1);
		int[] gr = {0, 80, 50, 100, 60, 100, 70, 100, 60, 100, 50, 100, 50};
		int[] sr = {0, 100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		int[] poh = {0, 40, -10, -75, -35, -75, -45, -75, -35, -75, -25, -75, -25};
		int[] pab = {0, 40, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25};
		int[] nr = {0, 0, 35, 100, 60, 100, 70, 100, 60, 100, 50, 100, 50};
		int[] porc = {0, 0, 35, 100, 60, 100, 70, 100, 60, 100, 50, 100, 50};
		int[] por = {0, 35, 100, 60, 100, 70, 100, 60, 100, 50, 100, 50, 0};
		mpsNode.setGR(gr);
		mpsNode.setSR(sr);
	//	mpsNode.setPOH(poh);
	//	mpsNode.setPAB(pab);
	//	mpsNode.setNR(nr);
	//	mpsNode.setPORC(porc);
	//	mpsNode.setPOR(por);
		 node = NodeMap.get("B");
		 mpsNode = node.getMPSNode();
	//	 node.setIsCalFlag(1);
		int[] gr1 = {0, 70, 100, 50, 90, 60, 110, 60, 100, 50, 100, 50, 100};
		int[] sr1 = {0, 50, 100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		int[] poh1 = {0, 20, 20, -30, -70, -40, -90, -40, -80, -30, -80, -30, -80};
		int[] pab1 = {0, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};
		int[] nr1 = {0, 0, 0, 50, 90, 60, 110, 60, 100, 50, 100, 50, 100};
		int[] porc1 = {0, 0, 0, 50, 90, 60, 110, 60, 100, 50, 100, 50, 100};
		int[] por1 = {0, 0, 50, 90, 60, 110, 60, 100, 50, 100, 50, 100, 0};
		mpsNode.setGR(gr1);
		//mpsNode.setSR(sr1);
	//	mpsNode.setPOH(poh1);
	//	mpsNode.setPAB(pab1);
	//	mpsNode.setNR(nr1);
	//	mpsNode.setPORC(porc1);
	//	mpsNode.setPOR(por1);
		return root;
	
	}
}
