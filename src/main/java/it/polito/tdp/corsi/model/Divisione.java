package it.polito.tdp.corsi.model;

public class Divisione {

	private String cDS;
	private int nStudenti;

	public Divisione(String cDS, int nStudenti) {
		this.cDS = cDS;
		this.nStudenti = nStudenti;		
	}

	public String getcDS() {
		return cDS;
	}

	public int getnStudenti() {
		return nStudenti;
	}

}
