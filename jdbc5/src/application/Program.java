package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;


import db.DB;
import db.DBIntegrityException;

public class Program {
	//DELEÇÃO PELO ID, SÓ POSSO DELETAR QUE NÃO FOR PAI DE OUTRA TABELA
	public static void main(String[] args) {
		
		Connection conn = null;
		
		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();
			
			st = conn.prepareStatement("DELETE FROM department WHERE Id = ?");
			
			st.setInt(1, 2);
			
			
			int rowAffected = st.executeUpdate();
			
			System.out.println("Done! Rows afffected: " + rowAffected);
		}
		catch(SQLException e) {
			throw new DBIntegrityException(e.getMessage());
		}
		finally {
			
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}

}
