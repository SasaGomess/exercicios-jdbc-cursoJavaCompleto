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
		//CRIAR UM NOVO VENDEDOR - INSERIR DAD0S
		LocalDate date = LocalDate.of(2007, 1, 9);

		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();
			
			/*
			st = conn.prepareStatement("INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			java.sql.Date sqlDate = java.sql.Date.valueOf(date);
			st.setString(1, "Sabrina Gomes");
			st.setString(2, "sabrina@gmail.com");
			st.setDate(3, sqlDate);
			st.setDouble(4, 3000.0);
			st.setInt(5, 4);
			*/
			
			
			st = conn.prepareStatement(
					"insert into department (Name) values ('D1'), ('D2')",
					Statement.RETURN_GENERATED_KEYS);
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				
				ResultSet rs = st.getGeneratedKeys();
				while (rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Done! Id = " + id);
				}
			}
			else {
				System.out.println("No rown affected!");
			}
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
