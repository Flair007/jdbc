package com.jlcindia.jdbc;

import java.sql.*;

import com.jlcindia.jdbc.util.JDBCUtil;

public class Lab10{

	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
		//1.Load the Driver Class &2.Establish the connection 	
			con=JDBCUtil.getMySQLConnection();
		//3.Prepare the Sql Statement
			String sql=null;
			int sid =97;
			sql="update jlcstudents set phone=90909090 where sid=?";
		//sql="insert into jlcstudents values(96,'Dande','dande@jlc',96969696)";
			//4.create the jdbc connection
			ps=con.prepareStatement(sql);
		//5.submit the sql statement to database using jdbc statement.
			ps.setInt(1, sid);
			boolean b1=ps.execute();
		//6.Process the result.
			if(b1) {
				rs=ps.getResultSet();
				if(rs.next()) {
			 do{
				int id=rs.getInt(1);
				String sn=rs.getString(2);
				String em=rs.getString(3);
				long ph=rs.getLong(4);
				System.out.println(id+"\t"+sn+"\t"+em+"\t"+ph);
			}while(rs.next());
		}
				}else {
				int x=ps.getUpdateCount();
				System.out.println("Result: "+x);
			}
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			JDBCUtil.cleanup(rs, ps, con);
		}
	}
}
