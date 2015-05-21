package hit.edu.Util;

import hit.edu.Bean.*;

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
	
	public boolean BOM_Exist(BOM bom)
	{
		sql = "select * from bom where Father = ? and Child = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bom.getFather());
			pstmt.setString(2, bom.getChild());
			rs = pstmt.executeQuery();
			if(rs.next())
				return true;
			else
				return false;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public void BOM_Update(BOM bom)
	{
		sql = "update bom set QP=? where Father=? and Child=?;";
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bom.getQP());
			pstmt.setString(2, bom.getFather());
			pstmt.setString(3, bom.getChild());
			pstmt.executeUpdate();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
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
	
	//按照产品名查询
	public Material Material_Query(String product){
		int lsr;
		Material m = new Material();
		sql = "select * from material where Name = ?;";
		try 
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
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
			}
			return m;
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//查询material表所有
	public ArrayList<Material> Material_QueryAll(){
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
	
	public boolean Material_Exist(String product)
	{
		sql = "select * from material where Name = ?;";
		try 
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product);
			rs = pstmt.executeQuery();
			if(rs.next())
				return true;
			else
				return false;
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void Material_Update(Material m)
	{
		sql = "update material set LT=?,ST=?,SS=?,LLC=?,LSR=?,LS=? where Name=?;";
		try 
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, m.getLT());
			pstmt.setInt(2, m.getST());
			pstmt.setInt(3, m.getSS());
			pstmt.setInt(4, m.getLLC());
			pstmt.setInt(5, m.getLSR());
			pstmt.setInt(6, m.getLS());
			pstmt.setString(7, m.getName());
			pstmt.executeUpdate();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
        
        public int Material_Num()
        {
            int count = 0;
            sql = "select * from material;";
            try 
            {
                    pstmt = conn.prepareStatement(sql);
                    rs = pstmt.executeQuery();
                    while(rs.next())
                    {
                        count++;
                    }
                    return count;
            } 
            catch (SQLException e) {
                    e.printStackTrace();
            }
            return count;
        }
	
	public void Inventory_Insert(Inventory i)
	{
		int[] num;
		String str;
		//Iterator<Integer> it;
		sql = "insert into inventory values (?,?,?,?);";
		try 
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, i.getName());
			pstmt.setInt(2, i.getOH());
			pstmt.setInt(3, i.getAL());
			
		//	it = i.getSchedule().iterator();
			str="";
			num = i.getSchedule();
			for (int j=0;j<num.length;j++)
				str += (num[j]+" ");
			
		/*  while(it.hasNext())
			{
				num = it.next();
				str+=num+" ";
			}
			*/
			pstmt.setString(4, str);
			
			pstmt.executeUpdate();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public Inventory Inventory_Query(String product)
	{
		Inventory i = new Inventory();
		//ArrayList<Integer> schedule = new ArrayList<Integer>();
		int[] schedule = new int[13];
		sql = "select * from inventory where Name = ?;";			
		try 
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				i.setName(rs.getString("Name"));
				i.setOH(rs.getInt("OH"));
				i.setAL(rs.getInt("AL"));
				
				String[] strs = rs.getString("Schedule").split(" ");
				for(int k=0 ; k<strs.length ; k++)
					schedule[k] = Integer.parseInt(strs[k]);
					//schedule.add(Integer.parseInt(strs[k]));
				i.setSchedule(schedule);
			}
			return i;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Inventory> Inventory_QueryAll()
	{
		ArrayList<Inventory> inventory_list = new ArrayList<Inventory>();
		//ArrayList<Integer> schedule = new ArrayList<Integer>();
		int[] schedule;
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
				schedule = new int[strs.length];
				for(int k=0 ; k<strs.length ; k++)
					schedule[k] = Integer.parseInt(strs[k]);
					//schedule.add(Integer.parseInt(strs[k]));
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
	
	public boolean Inventory_Exist(String product)
	{
		sql = "select * from inventory where Name = ?;";			
		try 
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product);
			rs = pstmt.executeQuery();
			if(rs.next())
				return true;
			else
				return false;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public void Inventory_Update(Inventory i)
	{
		int[] num;
		String str;
		//Iterator<Integer> it;
		sql = "update inventory set OH=?,AL=?,Schedule=? where Name = ?;";
		try 
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, i.getOH());
			pstmt.setInt(2, i.getAL());
			
		//	it = i.getSchedule().iterator();
			str="";
			num = i.getSchedule();
			for (int j=0;j<num.length;j++)
				str += (num[j]+" ");
			
		/*  while(it.hasNext())
			{
				num = it.next();
				str+=num+" ";
			}
			*/
			pstmt.setString(3, str);
			pstmt.setString(4, i.getName());
			
			pstmt.executeUpdate();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

//	周期查询，ppt上13
//	若要再建一个项目，则要对项目编写id
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
	public int MPS_Insert(MPS mps,int p)
	{
		sql = "insert into mps values(?,?,?,?,?,?,?,?,?);";
		try 
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mps.getName());
			for(int i=0 ; i<p ; i++)
			{
				pstmt.setInt(2, i);
				pstmt.setInt(3, mps.getGR()[i]);
				pstmt.setInt(4, mps.getSR()[i]);
				pstmt.setInt(5, mps.getPOH()[i]);
				pstmt.setInt(6, mps.getPAB()[i]);
				pstmt.setInt(7, mps.getNR()[i]);
				pstmt.setInt(8, mps.getPORC()[i]);
				pstmt.setInt(9, mps.getPOR()[i]);
				try
				{
				pstmt.executeUpdate();
				}
				catch (SQLException e)
				{
					return -1;
					//e.printStackTrace();
				}
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return 1;
	}

//	product为产品名
	public MPS MPS_Query(String product)
	{
		MPS mps = new MPS();
		sql = "select * from mps where Name = ? order by Period asc;";//按照周期period升序排列
		try 
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product);
			rs = pstmt.executeQuery();
			int index = 0;
			while(rs.next())
			{
				mps.setName(rs.getString("Name"));
				mps.setGR(rs.getInt("GR"), index);
				mps.setSR(rs.getInt("SR"), index);
				mps.setPOH(rs.getInt("POH"), index);
				mps.setPAB(rs.getInt("PAB"), index);
				mps.setNR(rs.getInt("NR"), index);
				mps.setPORC(rs.getInt("PORC"), index);
				mps.setPOR(rs.getInt("POR"), index);
				index++;
			}
			
			sql = "select * from material where Name = ?;";//LT,ST,SS,LSR,LS
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				mps.setLT(rs.getInt("LT"));
				mps.setST(rs.getInt("ST"));
				mps.setSS(rs.getInt("SS"));
				mps.setLSR(rs.getInt("LSR"));
				mps.setLS(rs.getInt("LS"));
			}
			
			sql = "select * from inventory where Name = ?;";//OH,AL
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				mps.setOH(rs.getInt("OH"));
				mps.setAL(rs.getInt("AL"));
			}
			
			return mps;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
//	p为周期
	public void MPS_Update(MPS mps,int p)
	{
		sql = "update mps set GR=?,SR=?,POH=?,PAB=?,NR=?,PORC=?,POR=? where Name = ? and Period =?;";
		try 
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(8, mps.getName());//设置名字
			for(int i=0 ; i<p ; i++)
			{
				pstmt.setInt(9, i);//设置周期
				pstmt.setInt(1, mps.getGR()[i]);
				pstmt.setInt(2, mps.getSR()[i]);
				pstmt.setInt(3, mps.getPOH()[i]);
				pstmt.setInt(4, mps.getPAB()[i]);
				pstmt.setInt(5, mps.getNR()[i]);
				pstmt.setInt(6, mps.getPORC()[i]);
				pstmt.setInt(7, mps.getPOR()[i]);
				
				pstmt.executeUpdate();
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
/*	public static void main(String[] args)
	{
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
		
		m = new DBFunc().Material_Query("A");
		
		System.out.println(m.getName()+"\t"+m.getLT()+"\t"+m.getST());*/
		
//		Inventory实例测试
/*		Inventory i = new Inventory();
		int[] schedule = new int[13];
		//ArrayList<Integer> schedule = new ArrayList<Integer>();
		i.setName("A");
		i.setOH(20);
		i.setAL(0);
		String[] strs = "0 100 0 0 0 0 0 0 0 0 0 0 0 ".split(" ");
		for(int k=0 ; k<strs.length ; k++)
			schedule[k] = Integer.parseInt(strs[k]);
			//schedule.add(Integer.parseInt(strs[k]));
		
		i.setSchedule(schedule);
		
		new DBFunc().Inventory_Insert(i);
		
		i = new DBFunc().Inventory_Query("A");
	
		schedule = i.getSchedule();
		System.out.println(i.getName()+"\t"+i.getOH()+"\t"+i.getAL()+"\t"+schedule[0]+"\t"+schedule[1]);
		System.out.println(new DBFunc().Period_Query());*/

//		mps实例测试
//		数据多，不好测，只测了更新update函数，其他有关mps函数调用见Test
/*		MPS mps = new MPS("A");
		int[] num = new int[13];
		for(int i= 0;i<13;i++)
			num[i] = i;
		mps.setGR(num);
		mps.setSR(num);
		mps.setPOH(num);
		mps.setPAB(num);
		mps.setNR(num);
		mps.setPORC(num);
		mps.setPOR(num);
		
		new DBFunc().MPS_Update(mps,13);*/
		
	}
	

