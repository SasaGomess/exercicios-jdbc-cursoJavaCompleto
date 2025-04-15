package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;


import db.DB;
import db.DBIntegrityException;
import db.DbException;

public class Program {
	//TRANSAÇÃO - rollback para voltar e quando der exceção e não atualizar ninguém.
	public static void main(String[] args) {
		
		Connection conn = null;
		
		Statement st = null;
		
		try {
			conn = DB.getConnection();
			
			conn.setAutoCommit(false);//dependente da confirmação explicita do programador
			
			st = conn.createStatement();
			
			int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");
			
			/*int x = 1;
			if(x < 2) {
				
				throw new SQLException("Fake Error");
			}*/
			
			int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");
			
			conn.commit();
			
			System.out.println(rows1 + ", " + rows2);
			
		

		}
		catch(SQLException e) {
			try {
				conn.rollback();
				
				throw new DbException("Transaction rolled back! Caused by: " + e.getMessage());
				
			} catch (SQLException e1) {
				
				throw new DbException("Error try to rollback! Caused by: " + e1.getMessage());
				
			}
		}
		finally {
			
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}

}
