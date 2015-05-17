package hit.edu.Util;

import hit.edu.Bean.BOM;
import hit.edu.Bean.Inventory;
import hit.edu.Bean.MPS;
import hit.edu.Bean.Material;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class DBFunc {
	private PreparedStatement pstmt = null;
	private String sql=null;
	private ResultSet rs=null;
	
	Database db = new Database();
	Connection conn = db.DatabaseConn();
	
	public void BOM_Insert(BOM bom)
	{
		sql = "insert into bom values (?,?,?);";
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bom.getFather());
			pstmt.setString(2, bom.getChild());
			pstmt.setInt(3, bom.getQP());
			pstmt.executeUpdate();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public ArrayList<BOM> BOM_Query()
	{
		ArrayList<BOM> bom_list = new ArrayList<BOM>();
		sql = "select * from bom";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				BOM bom = new BOM();
				bom.setFather(rs.getString("Father"));
				bom.setChild(rs.getString("Child"));
				bom.setQP(rs.getInt("QP"));
				
				bom_list.add(bom);
			}
			return bom_list;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public void Material_Insert(Material m)
	{
		sql = "insert into material values(?,?,?,?,?,?,?);";
		try 
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getName());
			pstmt.setInt(2, m.getLT());
			pstmt.setInt(3, m.getST());
			pstmt.setInt(4, m.getSS());
			pstmt.setInt(5, m.getLLC());
			pstmt.setInt(6, m.getLSR());
			pstmt.setInt(7, m.getLS());
			pstmt.executeUpdate();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<Material> Material_Query(){
		int lsr;
		ArrayList<Material> material_list = new ArrayList<Material>();
		sql = "select * from material;";
		try 
		{
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				Material m = new Material();
				m.setName(rs.getString("name"));
				m.setLT(rs.getInt("LT"));
				m.setST(rs.getInt("ST"));
				m.setSS(rs.getInt("SS"));
				m.setLLC(rs.getInt("LLC"));
				m.setLS(rs.getInt("LS"));
				
				lsr = rs.getInt("LSR");
				if(lsr == 1)
					m.setLSR("LFL");
				else if(lsr == 2)
					m.setLSR("FOQ");
				else
					m.setLSR("POQ");
				
				material_list.add(m);
			}
			return material_list;
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void Inventory_Insert(Inventory i)
	{
		int num;
		String str;
		Iterator<Integer> it;
		sql = "insert into inventory values (?,?,?,?);";
		try 
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, i.getName());
			pstmt.setInt(2, i.getOH());
			pstmt.setInt(3, i.getAL());
			
			it = i.getSchedule().iterator();
			str="";
			while(it.hasNext())
			{
				num = it.next();
				str+=num+" ";
			}
			pstmt.setString(4, str);
			
			pstmt.executeUpdate();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public ArrayList<Inventory> Inventory_Query()
	{
		ArrayList<Inventory> inventory_list = new ArrayList<Inventory>();
		ArrayList<Integer> schedule = new ArrayList<Integer>();
		sql = "select * from inventory;";			
		try 
		{
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				Inventory i = new Inventory();
				i.setName(rs.getString("name"));
				i.setOH(rs.getInt("OH"));
				i.setAL(rs.getInt("AL"));
				
				String[] strs = rs.getString("Schedule").split(" ");
				for(int k=0 ; k<strs.length ; k++)
					schedule.add(Integer.parseInt(strs[k]));
				i.setSchedule(schedule);
				
				inventory_list.add(i);
			}
			return inventory_list;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return null;
	}

//	查询有多少个周期，ppt上是12
	public int Period_Query()
	{
		sql = "select * from inventory;";			
		try 
		{
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next())
			{	
				String[] strs = rs.getString("Schedule").split(" ");
				return strs.length;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return 0;
	}

//	p为周期数
	public void MPS_Insert(MPS mps,int p)
	{
		sql = "insert into mps values(?,?,?,?,?,?,?,?,?);";
		try 
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mps.getName());
			for(int i=0 ; i<p ; i++)
			{
				pstmt.setInt(2, i);
				pstmt.setInt(3, mps.getGRatIndex(i));
				pstmt.setInt(4, mps.getSRatIndex(i));
				pstmt.setInt(5, mps.getPOHatIndex(i));
				pstmt.setInt(6, mps.getPABatIndex(i));
				pstmt.setInt(7, mps.getNRatIndex(i));
				pstmt.setInt(8, mps.getPORCatIndex(i));
				pstmt.setInt(9, mps.getPORatIndex(i));
				
				pstmt.executeUpdate();
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

//	product为物品名
	public MPS MPS_Query(String product)
	{
		MPS mps = null;
		sql = "select * from mps where Name = ? order by Period asc;";//按照周期升序排列			
		try 
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				mps = new MPS(rs.getString("Name"));
				mps.pushGR(rs.getInt("GR"));
				mps.pushSR(rs.getInt("SR"));
				mps.pushPOH(rs.getInt("POH"));
				mps.pushPAB(rs.getInt("PAB"));
				mps.pushNR(rs.getInt("NR"));
				mps.pushPORC(rs.getInt("PORC"));
				mps.pushPOR(rs.getInt("POR"));
			}
			return mps;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
//	public static void main(String[] args)
//	{
//		BOM实例测试
/*		BOM bom = new BOM();
		bom.setFather("X");
		bom.setChild("Y");
		bom.setQP(4);
		new DBFunc().BOM_Insert(bom);
		
		ArrayList<BOM> bom_list = new DBFunc().BOM_Query();
		Iterator<BOM> it = bom_list.iterator();
		
		while(it.hasNext())
		{
			bom = it.next();
			System.out.println(bom.getFather()+"\t"+bom.getChild()+"\t"+bom.getQP());
		}*/
		
//		Material实例测试
/*		Material m = new Material();
		m.setName("A");
		m.setLT(1);
		m.setST(0);
		m.setSS(25);
		m.setLLC(0);
		m.setLSR("LFL");
		m.setLS(1);
		
		new DBFunc().Material_Insert(m);
		
		ArrayList<Material> material_list = new DBFunc().Material_Query();
		Iterator<Material> it = material_list.iterator();
		
		while(it.hasNext())
		{
			m = it.next();
			System.out.println(m.getName()+"\t"+m.getLT()+"\t"+m.getST());
		}*/
		
//		Inventory实例测试
/*		Inventory i = new Inventory();
		ArrayList<Integer> schedule = new ArrayList<Integer>();
		i.setName("A");
		i.setOH(20);
		i.setAL(0);
		String[] strs = "0 100 0 0 0 0 0 0 0 0 0 0 0 ".split(" ");
		for(int k=0 ; k<strs.length ; k++)
			schedule.add(Integer.parseInt(strs[k]));
		i.setSchedule(schedule);
		
		new DBFunc().Inventory_Insert(i);
		
		ArrayList<Inventory> inventory_list = new DBFunc().Inventory_Query();
		Iterator<Inventory> it = inventory_list.iterator();
		String str;
		Iterator<Integer> iter;
		
		while(it.hasNext())
		{
			i = it.next();
			schedule = i.getSchedule();
			iter = schedule.iterator();
			System.out.print(i.getName()+"\t"+i.getOH()+"\t"+i.getAL());
			while(iter.hasNext())
			{
				System.out.print(iter.next());
			}
			System.out.println();
		}
		System.out.println(new DBFunc().Period_Query());*/

//		mps实例测试
//		数据太多，不能测
//	}
}
