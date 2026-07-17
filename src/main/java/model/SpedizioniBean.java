package model;

public class SpedizioniBean {
	private int ordine_id;
	private String indirizzo;
	private String nome;
	private String cognome;
	private String cap;
	private String citta;
	
	public SpedizioniBean() {}
	
	public int getOrdineId() {
	    return ordine_id;
	}

	public void setOrdineId(int ordine_id) {
	    this.ordine_id = ordine_id;
	}

	public String getIndirizzo() {
	    return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
	    this.indirizzo = indirizzo;
	}

	public String getNome() {
	    return nome;
	}

	public void setNome(String nome) {
	    this.nome = nome;
	}

	public String getCognome() {
	    return cognome;
	}

	public void setCognome(String cognome) {
	    this.cognome = cognome;
	}

	public String getCap() {
	    return cap;
	}

	public void setCap(String cap) {
	    this.cap = cap;
	}

	public String getCitta() {
	    return citta;
	}

	public void setCitta(String citta) {
	    this.citta = citta;
	}
}
