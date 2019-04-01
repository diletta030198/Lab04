package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	
public Studente getStudentePerMatricola(int matricola) {
		final String sql = "SELECT cognome, nome, cds FROM studente WHERE matricola LIKE ?";
		String cognome; 
		String nome; 
		String percorsoDiStudi; 
		Studente s; 
		Studente result; 
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);

			ResultSet  rs = st.executeQuery();
			

			if(!rs.next()) {
				
				result = null; 
			}
			else {
			cognome= rs.getString("cognome"); 
			nome= rs.getString("nome"); 
			percorsoDiStudi= rs.getString("CDS");
			s= new Studente(matricola,cognome,nome,percorsoDiStudi); 
			
			result=  s; 
			
			
			}
			//conn.close();
			return result; 
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		
	}
	
	
	public void iscriviStudenteACorso(Corso c,Studente s) {
		final String sql = "INSERT INTO iscrizione VALUES(?,?)";
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, s.getMatricola());
			st.setString(2, c.getCodins());
			
			int rs= st.executeUpdate(); 
			
			//ResultSet rs = st.executeQuery();
			//conn.close();
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		

		
	}

}
