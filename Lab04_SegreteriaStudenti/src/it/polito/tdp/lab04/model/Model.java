package it.polito.tdp.lab04.model;


import java.util.LinkedList;
import java.util.List;


import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {  //dentro il model io faccio una funzione che colleghi il dao al model 
	
	public List<Corso> corsi= new LinkedList<Corso>(); 
	
	

	public List<Corso> getAllCorsi(){
		
		CorsoDAO dao= new CorsoDAO();
		corsi.addAll(dao.getTuttiICorsi()); 
		
		
		return corsi; 
	}
	
	public Studente getStudentePerMatricola(int matricola) {
		 StudenteDAO dao= new StudenteDAO();
		 
		 return dao.getStudentePerMatricola(matricola); 
		}
	
	public List<Studente> restituisciStudentiPerCorso(Corso corso){
		CorsoDAO dao= new CorsoDAO(); 
		return dao.getStudentiIscrittiAlCorso(corso); 
	}
	
	public List<Corso> restituisciCorsiPerMatricola(Studente s){
		CorsoDAO dao = new CorsoDAO(); 
		return dao.getCorsiSeguitiDaUnoStudente(s); 
	}
	
	
	public Corso getCorso(String codice) {
		
		List<Corso> corsi= new LinkedList <Corso>();
		corsi.addAll(this.getAllCorsi()); 
		
		for(Corso c: corsi) {
			if(c.getCodins().equals(codice)) {
			/*	flag=true;
				break;*/
				return c;
			}
		}
		return null;
	} 
	
	public boolean studenteSegueCorso(Studente s, Corso c) {
		if(this.restituisciStudentiPerCorso(c).contains(s))
			return true; 
		else 
			return false; 
	}
	
	public void iscriviStudenteACorso(Studente s, Corso c) {
		StudenteDAO dao= new StudenteDAO(); 
		dao.iscriviStudenteACorso(c, s);
	}
	
	
	

}
