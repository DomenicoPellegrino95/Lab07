package it.polito.tdp.dizionario.model;

import java.util.*;

import org.jgrapht.Graphs;
import org.jgrapht.graph.*;

import it.polito.tdp.*;
import it.polito.tdp.dizionario.db.WordDAO;


public class Model {
    
	WordDAO wordDAO = new WordDAO();
	List<String> parole;
	Multigraph<String, DefaultEdge> grafo= new Multigraph<String, DefaultEdge>(DefaultEdge.class);
	
	
	public List<String> createGraph(int numeroLettere) {
		parole= new ArrayList<String>(wordDAO.getAllWordsFixedLength(numeroLettere));
		for (String s:parole){
			grafo.addVertex(s);
		}
		
		for(String s:parole){
			for(String s2:parole){
				if(this.differenzaLettera(s, s2))
					grafo.addEdge(s, s2);
			}
		}
		return new ArrayList<String>();
	}

	public List<String> displayNeighbours(String parolaInserita) {
		return Graphs.neighborListOf(grafo, parolaInserita);
	}

	public String findMaxDegree() {
		int max=0;
		String risultato="";
		String massimiVicini="";
		for(String s:grafo.vertexSet()){
			if(grafo.degreeOf(s)>=max){
				massimiVicini=s;
				max=grafo.degreeOf(massimiVicini);
			}
		}
		risultato+="La parola con più vicini è: "+massimiVicini+", e ha "+grafo.degreeOf(massimiVicini)+" parole simili, che sono: ";                                   
		for (String s:this.displayNeighbours(massimiVicini)){
			risultato+=s+", ";
		}
		return risultato;
	}
	
	public boolean differenzaLettera(String s1, String s2){
		int lettereUguali=0;
		
	    for (int i=0; i < s1.length(); i++)
	    {
	        if (s1.charAt(i) == s2.charAt(i))
	        {
	             lettereUguali++;
	        }
	    }
	    if (lettereUguali==s1.length()-1)
	    	return true;
	    else
	    	return false;
		
	}
}
