package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {

	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				// Crea un nuovo JAVA Bean Corso
				Corso c= new Corso(codins,numeroCrediti,nome,periodoDidattico); 
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				corsi.add(c); 
			}
			
			
          //conn.close();
			return corsi;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		
		
	}

	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	
	public Corso getCorso(String codice) {
		
		
		
	List<Corso> temp= this.getTuttiICorsi(); 
	Corso res = new Corso("",0,"",0); 

	for (Corso c: temp) {
		if(c.getCodins().equals(codice)) {
		res.setCodins(c.getCodins());
		res.setNome(c.getNome());
		res.setPd(c.getPd());
		res.setCrediti(c.getCrediti());
			break; 
		}
	}
	
	if(res.getCodins().equals("")) {
		
		return null; 
	}
	else 
		return  res; 
	
	
	
	
	}
	

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		final String sql = "SELECT matricola FROM iscrizione WHERE codins LIKE ?";
		List<Studente> studenti = new LinkedList<Studente>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodins());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				StudenteDAO s= new StudenteDAO(); 
				int matr= rs.getInt("matricola"); 
				Studente stu= s.getStudentePerMatricola(matr); 
				studenti.add(stu); 
				
				}
			//conn.close();
			}
		catch (SQLException e) {
				// e.printStackTrace();
				throw new RuntimeException("Errore Db");
			}
		return studenti; 
			
		}
	
	
	public List<Corso> getCorsiSeguitiDaUnoStudente(Studente s) {
		final String sql = "SELECT codins FROM iscrizione WHERE matricola LIKE ?";
		List<Corso> corsi = new LinkedList<Corso>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, s.getMatricola());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				String cod= rs.getString("codins");
				corsi.add(this.getCorso(cod)); 
				
				}
			
			//conn.close();
			}
		catch (SQLException e) {
				// e.printStackTrace();
				throw new RuntimeException("Errore Db");
			}
	
		return corsi; 
			
		}
		
	

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		// TODO
		// ritorna true se l'iscrizione e' avvenuta con successo
		return false;
	}
}
