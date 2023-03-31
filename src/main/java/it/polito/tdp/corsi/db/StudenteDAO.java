package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.corsi.model.Divisione;
import it.polito.tdp.corsi.model.Studente;

public class StudenteDAO {
	
	public List<Studente> getIscrittiCorso (String codIns) {
		String sql = "SELECT s.matricola, s.cognome, s.nome, s.CDS "
				+ "FROM studente s, iscrizione i "
				+ "WHERE s.matricola = i.matricola AND i.codins = ?";
		
		List<Studente> risultato = new ArrayList<>();
		
		Connection conn = ConnectDB.getConnection();
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, codIns);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				risultato.add(new Studente(rs.getInt("matricola"), rs.getString("cognome"), 
						rs.getString("nome"), rs.getString("CDS") ) );
			}
			
			rs.close();
			st.close();
			conn.close();
			return risultato;
			
		} catch (SQLException e) {
			System.err.println("Errore connessione al db");
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Divisione> getDivisioneStudentiCorso (String codins) {
		String sql = "SELECT s.CDS, COUNT(*) AS n "
				+ "FROM studente s, iscrizione i "
				+ "WHERE s.matricola = i.matricola AND i.codins = ? AND s.CDS <> \"\" "
				+ "GROUP BY s.CDS" ;
		
		List<Divisione> risultato = new ArrayList<>();
		
		Connection conn = ConnectDB.getConnection();
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, codins);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				risultato.add(new Divisione(rs.getString("CDS"), rs.getInt("n") ) );
			}
			
			rs.close();
			st.close();
			conn.close();
			return risultato;
			
		} catch (SQLException e) {
			System.err.println("Errore connessione al db");
			e.printStackTrace();
			return null;
		}
		
	}
}
