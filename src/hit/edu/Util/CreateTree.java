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
	public TreeNode createTree(BOM[] bom, List<Material> material, HashMap<String, Inventory> inventoryMap)
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
				tmp.setQP(1);
				tmp.setFather(root);
			}
		}
		for (BOM i:bom)
		{
			TreeNode f = NodeMap.get(i.getFather());
			TreeNode s = NodeMap.get(i.getChild());
			f.addSon(s);
			s.setQP(i.getQP());	
			s.setFather(f);
		}
		return root;
	}
}
