package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;


import db.DB;

public class Program {

	public static void main(String[] args) {
		//ATUALIZAR DADOS EXISTENTES
		Connection conn = null;
		
		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();
			
			st = conn.prepareStatement("UPDATE seller SET BaseSalary = BaseSalary + ? WHERE (DepartmentId = ?)");
			
			st.setDouble(1, 200.00);
			st.setInt(2, 2);
			
			int rowAffected = st.executeUpdate();
			
			System.out.println("Done! Rows afffected: " + rowAffected);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}

}
