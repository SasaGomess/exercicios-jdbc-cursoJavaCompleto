package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Program {

	public static void main(String[] args) {
		
		//CRIANDO CONSULTA DOS DADOS DA TABELA - RECUPERANDO DADOS
		Connection conn = null;
		//consulta sql pelo statment
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DB.getConnection();
			
			st = conn.createStatement();
			//resultado da consulta atribuido ao resultset
			rs = st.executeQuery("select * from department");
			
			while(rs.next()) {
				
				System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally{
			DB.closeResultSet(rs);;
			DB.closeStatement(st);;
			DB.closeConnection();
		}
		
	}

}
