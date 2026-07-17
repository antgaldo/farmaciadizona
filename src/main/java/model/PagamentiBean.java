package model;

public class PagamentiBean {
	private int ordine_id;
	private String circuito;
	private int numero;
	private String scadenza;
	
	public PagamentiBean() {}
	
	public int getOrdineId() {
	    return ordine_id;
	}

	public void setOrdineId(int ordine_id) {
	    this.ordine_id = ordine_id;
	}

	public String getCircuito() {
	    return circuito;
	}

	public void setCircuito(String circuito) {
	    this.circuito = circuito;
	}

	public int getNumero() {
	    return numero;
	}

	public void setNumero(int numero) {
	    this.numero = numero;
	}

	public String getScadenza() {
	    return scadenza;
	}

	public void setScadenza(String scadenza) {
	    this.scadenza = scadenza;
	}
}
