package hit.edu.Util;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import hit.edu.Bean.*;
import hit.edu.Entity.TreeNode;

public class CreateTree {
	private Map<String, TreeNode> NodeMap;
	public TreeNode createTree(BOM[] bom, List<Material> material)
	{
		TreeNode root = new TreeNode();
		NodeMap.put("root", root);
		//Collections.sort(material);
		for (Material i:material)
		{
			TreeNode tmp = new TreeNode(i);
			NodeMap.put(i.getName(), tmp);
			if (i.getLLC()==0)
			{
				root.addSon(tmp);
				tmp.setQP(1);
			}
		}
		for (BOM i:bom)
		{
			TreeNode f = NodeMap.get(i.getFather());
			TreeNode s = NodeMap.get(i.getChild());
			f.addSon(s);
			s.setQP(i.getQP());	
		}
		return root;
	}
}
