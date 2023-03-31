package it.polito.tdp.corsi.model;

public class Studente {

	private Integer matricola;
	private String cognome;
	private String nome;
	private String cDS;

	public Studente(Integer matricola, String cognome, String nome, String cDS) {
		this.matricola = matricola;
		this.cognome = cognome;
		this.nome = nome;
		this.cDS = cDS;		
	}

	/**
	 * @return the matricola
	 */
	public Integer getMatricola() {
		return matricola;
	}

	/**
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @return the cDS
	 */
	public String getcDS() {
		return cDS;
	}

	@Override
	public String toString() {
		return "Studente [matricola=" + matricola + ", cognome=" + cognome + ", nome=" + nome + ", cDS=" + cDS + "]";
	}
	
}
