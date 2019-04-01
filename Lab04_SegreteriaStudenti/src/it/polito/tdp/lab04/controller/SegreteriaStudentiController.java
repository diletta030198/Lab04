package it.polito.tdp.lab04.controller;

import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;

public class SegreteriaStudentiController {

	private Model model; 
	private List<Corso> corsi= new LinkedList<Corso>(); 
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private ComboBox<Corso> listaCorsi;

    @FXML
    private Button btnCercaIscritti;

    @FXML
    private TextField txtInserisciMatricola;

    @FXML
    private Button btnRestituisciStudente;

    @FXML
    private TextField txtRestituisciNome;

    @FXML
    private TextField txtRestituisciCognome;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtRestituisciCorsi;

    @FXML
    private Button btnReset;

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	this.txtRestituisciCorsi.clear();
    	if(this.txtInserisciMatricola.getText()==null) {
    		this.txtRestituisciCorsi.setText("Inserisci una matricola!");
    		return; 
    	}
    	//metto solo una matricola e cerco i corsi seguiti dalla matricola
    	if(this.listaCorsi.getValue()==null || this.listaCorsi.getValue().getNome().equals("")) {
    	
    	if(this.txtInserisciMatricola.getText()==null) {
    		this.txtRestituisciCorsi.setText("Inserisci una matricola!");
    	}
    	else {
    		 String matricola= txtInserisciMatricola.getText();
    	     
    	     int numeroMatricola; 
    	     try {
    	    	numeroMatricola=  Integer.parseInt(matricola); 
    	    	Studente s=  this.model.getStudentePerMatricola(numeroMatricola);
    	    	if(s==null) {
    	    		this.txtRestituisciCorsi.setText("La matricola non esiste!\n");
    	    		}
    	    	
    	    	else
    	    	{
    	    		List<Corso> temp=  this.model.restituisciCorsiPerMatricola(s);
    	    		if(temp.size()==0) {
    	    			this.txtRestituisciCorsi.setText("Lo studente non è iscritto ad alcun corso/n");
    	    			
    	    		}
    	    		else {
    	    		for (Corso c: temp) {
    	    			this.txtRestituisciCorsi.appendText(c.toString2()+"\n");
    	    			
    	    		}
    	    		}
    	    		
    	    		}
    	    	
    	     }
    	     catch(NumberFormatException e) {
    	    	 txtRestituisciCorsi.appendText("La matricola deve essere un numero!\n");
    	    	
    	     }
    		
    	}}
    	else  //inserisce sia matricola, sia il corso
    	{
       String matricola= txtInserisciMatricola.getText();
    	     
    	     int numeroMatricola; 
    	     try {
    	    	numeroMatricola=  Integer.parseInt(matricola); 
    	    	Studente s=  this.model.getStudentePerMatricola(numeroMatricola);
    	    	if(s==null) {
    	    		this.txtRestituisciCorsi.setText("La matricola non esiste!\n");}
    	    	
    	    	else
    	    	{
    	    		Corso c= this.listaCorsi.getValue(); 
    	    		boolean result; 
    	    	result= this.model.studenteSegueCorso(s, c); 
    	    			if(result==true) {
    	    				this.txtRestituisciCorsi.setText("Lo studente segue il corso");
    	    				
    	    			}
    	    			else 
    	    				this.txtRestituisciCorsi.setText("Lo studente non segue il corso");
    	    		}
    	    		
    	    		
    	
    	    	} catch(NumberFormatException e) {
       	    	 txtRestituisciCorsi.appendText("La matricola deve essere un numero!\n");
     	    	
       	     }
    	}
    }

    @FXML
    void doCercaIscrittiCorso(ActionEvent event) {
    	this.txtRestituisciCorsi.clear();
    	if(this.listaCorsi.getValue()==null || this.listaCorsi.getValue().getNome().equals("")) {
    		this.txtRestituisciCorsi.setText("Seleziona un corso!\n");
    	}
    	else {
     Corso c= this.listaCorsi.getValue(); 
     
     List <Studente> studenti= this.model.restituisciStudentiPerCorso(c);
     if(studenti.size()==0) {
    	 this.txtRestituisciCorsi.setText("per questo corso non ci sono iscritti\n");
    	 
     }
     else {
   for (Studente s: studenti) {
	   
	  this.txtRestituisciCorsi.appendText(s.toString()+"\n");}
   }}
    	
   
    
    
    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	
    	this.txtRestituisciCorsi.clear();
    	
    	if(this.listaCorsi.getValue()==null || this.listaCorsi.getValue().getNome().equals("")) {
    		this.txtRestituisciCorsi.setText("Scegli un corso");
    		return; 
    	}
    	
    	if(this.txtInserisciMatricola.getText()==null) {
    		this.txtRestituisciCorsi.setText("Inserisci una matricola!");
    		return; 
    	}
    	
    	
    	 String matricola= txtInserisciMatricola.getText();
	     
	     int numeroMatricola; 
	     try {
	    	numeroMatricola=  Integer.parseInt(matricola); 
	    	Studente s=  this.model.getStudentePerMatricola(numeroMatricola);
	    	if(s==null) {
	    		this.txtRestituisciCorsi.setText("La matricola non esiste!\n");}
	    	
	    	else
	    	{
	    		Corso c= this.listaCorsi.getValue(); 
	    		boolean result; 
	    	result= this.model.studenteSegueCorso(s, c); 
	    			if(result==true) {
	    				this.txtRestituisciCorsi.setText("Lo studente e' gia' iscritto al corso");
	    				
	    			}
	    			else 
	    			{
	    				this.model.iscriviStudenteACorso(s, c);
	    				this.txtRestituisciCorsi.setText("studente iscritto al corso");}
	    			
	    		}
	    		
	    		
	
	    	} catch(NumberFormatException e) {
   	    	 txtRestituisciCorsi.appendText("La matricola deve essere un numero!\n");
 	    	
   	     }
    }

    @FXML
    void doReset(ActionEvent event) {
 this.txtInserisciMatricola.clear();
 this.txtRestituisciCognome.clear();
 this.txtRestituisciCorsi.clear();
 this.txtRestituisciNome.clear();
    }

    @FXML
    void giveStudent(ActionEvent event) {
    	if(txtInserisciMatricola.getText()==null) {
    		this.txtRestituisciCorsi.setText("Inserisci una matricola!/n");
    		return;
    	}
     String matricola= txtInserisciMatricola.getText();
     
     int numeroMatricola; 
     try {
    	numeroMatricola=  Integer.parseInt(matricola); 
    	Studente s=  this.model.getStudentePerMatricola(numeroMatricola);
    	if(s!=null) {
    	this.txtRestituisciCognome.setText(s.getCognome());
    	this.txtRestituisciNome.setText(s.getNome()); }
    	else
    	{this.txtRestituisciCorsi.setText("La matricola non esiste!\n");}
    	
     }
     catch(NumberFormatException e) {
    	 txtRestituisciCorsi.appendText("La matricola deve essere un numero!\n");
    	
     }
     
    
    }
    
    public void setModel (Model model) {
    	this.model=model; 
    	corsi.addAll(model.getAllCorsi());
    	Collections.sort(corsi);
    	Corso c= new Corso("",2,"",2);
    	this.listaCorsi.getItems().add(c);
    	this.listaCorsi.getItems().addAll(corsi); 
    	
    	
    	
    }

    @FXML
    void initialize() {
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtInserisciMatricola != null : "fx:id=\"txtInserisciMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnRestituisciStudente != null : "fx:id=\"btnRestituisciStudente\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtRestituisciNome != null : "fx:id=\"txtRestituisciNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtRestituisciCognome != null : "fx:id=\"txtRestituisciCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtRestituisciCorsi != null : "fx:id=\"txtRestituisciCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert listaCorsi != null : "fx:id=\"listaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
    }
}