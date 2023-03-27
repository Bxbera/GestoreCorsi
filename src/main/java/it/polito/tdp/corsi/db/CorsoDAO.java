package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.model.Corso;

public class CorsoDAO {
	
	public List<Corso> getCorsiByPeriodo(int periodo) {
		
		String sql = "SELECT * FROM corso WHERE pd = ?" ;
		
		List<Corso> resultCorso = new ArrayList<>() ;
		
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, periodo);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Corso c = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd")) ;
				resultCorso.add(c);
			}
			rs.close();
			conn.close();
			
			return resultCorso;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Errore in CorsoDAO") ;
			e.printStackTrace(); 
			return null;
		}
	}

	public Map<Corso, Integer> getCorsiIscritti(int periodo) {
		
		String sql = "SELECT c.codins, c.crediti, c.nome, c.pd, COUNT(*) AS n "
				+ "FROM corso c, iscrizione i "
				+ "WHERE pd = ? AND c.codins = i.codins "
				+ "GROUP BY c.codins, c.crediti, c.nome, c.pd" ;
		
		Map<Corso, Integer> resultMap = new HashMap<>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, periodo);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Corso c = new Corso(rs.getString("codins"), rs.getInt("crediti"), 
						rs.getString("nome"), rs.getInt("pd")) ;
				resultMap.put(c, rs.getInt("n"));
			}
			
			
			st.close();
			conn.close();
			return resultMap;
			
		} catch(SQLException e) {
			System.out.println("Error in Corso DAO");
			e.printStackTrace();
			return null;
		}
	}
}