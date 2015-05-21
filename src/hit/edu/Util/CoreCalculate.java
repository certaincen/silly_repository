package hit.edu.Util;

import hit.edu.Bean.*;
import hit.edu.Entity.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;

public class CoreCalculate {
	private Material[] m = null;
	private BOM[] bom_array = null;
	private HashMap<String, Inventory> inventoryMap;
	int T;
	public CoreCalculate(ArrayList<Material> materialList, ArrayList<BOM> bomList, ArrayList<Inventory> inventoryList, int t)
	{
		T = t;
		inventoryMap = new HashMap<String, Inventory>();
		bom_array = new BOM[bomList.size()];
		bomList.toArray(bom_array);
		m = new Material[materialList.size()];
		m = materialList.toArray(m);
		for (Inventory inventory : inventoryList)
		{
			inventoryMap.put(inventory.getName(), inventory);
		}
		
	}
	public ArrayList<MPS> calculate()
	{
		ArrayList<MPS> result = new ArrayList<MPS>();
		HashMap<String, MPS> mpsMap = new HashMap<String, MPS>();
		CreateTree creat = new CreateTree();
		TreeNode root = creat.createTree(bom_array, m, inventoryMap, T);
		DBFunc dbfunc = new DBFunc();
		for (TreeNode node : root.getSon())
		{
			MPS mpsNode = node.getMPSNode();
			mpsNode.copyMPSGR(dbfunc.MPS_Query(mpsNode.getName()));
		}
		CalculateMPS cal = new CalculateMPS(T);
		cal.calculateMPS(root);
		
		root.getAllUniqueValue(mpsMap);
		for (String name : mpsMap.keySet())
		{
			result.add(mpsMap.get(name));
		}
		return result;

	}

}
