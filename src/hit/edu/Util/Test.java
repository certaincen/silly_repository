package hit.edu.Util;

import java.util.HashMap;

import hit.edu.Bean.BOM;
import hit.edu.Bean.Inventory;
import hit.edu.Bean.MPS;
import hit.edu.Bean.Material;
import hit.edu.Entity.TreeNode;

public class Test {

	public static void main(String[] args) {
                DBFunc db_client = new DBFunc();
		Material m[] = new Material[6];
		BOM bom_array[] = new BOM[8];
		Inventory a,b,c,d,e,f;
		MPS mA, mB, mC, mD, mE, mF;
		m[0] = new Material("A", 1, 0, 25, 0, "LFL", 1);
		m[1] = new Material("B", 1, 0, 20, 0, "LFL", 1);
		m[2] = new Material("C", 1, 0, 5, 2, "FOQ", 500);
		m[3] = new Material("D", 1, 0, 5, 1, "FOQ", 200);
		m[4] = new Material("E", 2, 0, 50, 3, "FOQ", 200);
		m[5] = new Material("F", 2, 1, 105, 3, "FOQ", 200);
                for(int i = 0; i < 6 ;i++){
                    db_client.Material_Insert(m[i]);
                }
		bom_array[0] = new BOM("A", "C", 2);
		bom_array[1] = new BOM("A", "D", 1);
		bom_array[2] = new BOM("D", "E", 2);
		bom_array[3] = new BOM("D", "C", 1);
		bom_array[4] = new BOM("C", "E", 1);
		bom_array[5] = new BOM("C", "F", 1);
		bom_array[6] = new BOM("B", "E", 1);
		bom_array[7] = new BOM("B", "C", 1);
		for(int i = 0 ; i< 8 ; i++){
                    db_client.BOM_Insert(bom_array[i]);
                }
		//ArrayList<Integer> tmp = new ArrayList<Integer>(25);
		int[] tmpA = new int[13];
		int[] tmpB = new int[13];
		int[] tmpC = new int[13];
		int[] tmpD = new int[13];
		int[] tmpE = new int[13];
		int[] tmpF = new int[13];
		
		//tmp.add(1, 100);
		tmpA[1] = 100;
		a = new Inventory("A", 20, 0, tmpA);
		//tmp.clear();
	//	tmp.add(1, 50);
	//	tmp.add(2, 100);
		tmpB[1] = 50;
		tmpB[2] = 100;
		b = new Inventory("B", 40, 0, tmpB);
		//tmp.clear();
	//	tmp.add(1, 200);
	//	tmp.add(2, 150);
		tmpC[1] = 200;
		tmpC[2] = 150;
		c = new Inventory("C", 60, 0, tmpC);
		//tmp.clear();

		d = new Inventory("D", 60, 20, tmpD);
		//tmp.clear();
	//	tmp.add(1, 1500);
		tmpE[1] = 1500;
		e = new Inventory("E", 100, 0, tmpE);
	//	tmp.clear();
	//	tmp.add(1, 1000);
	//	tmp.add(2, 0);
	//	tmp.add(3, 0);
		tmpF[1] = 1000;
		f = new Inventory("F", 100, 0, tmpF);
		HashMap<String, Inventory> inventoryMap = new HashMap<String, Inventory>();
		inventoryMap.put("A", a);
		inventoryMap.put("B", b);
		inventoryMap.put("C", c);
		inventoryMap.put("D", d);
		inventoryMap.put("E", e);
		inventoryMap.put("F", f);
		
		CreateTree creat = new CreateTree();
		TreeNode root = creat.createTree(bom_array, m, inventoryMap);
		
		CalculateMPS cal = new CalculateMPS();
		cal.calculateMPS(root);
		root.writetoDB();
		DBFunc dbfunc = new DBFunc();
		mA = dbfunc.MPS_Query("A");
		mB = dbfunc.MPS_Query("B");
		mC = dbfunc.MPS_Query("C");
		mD = dbfunc.MPS_Query("D");
		mE = dbfunc.MPS_Query("E");
		mF = dbfunc.MPS_Query("F");
		mA.show();
		mB.show();
		mC.show();
		mD.show();
		mE.show();
		mF.show();
		

	}

}
