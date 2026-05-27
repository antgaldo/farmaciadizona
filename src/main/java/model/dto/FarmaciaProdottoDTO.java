package model.dto;

public class FarmaciaProdottoDTO {
	private String prodotto_nome;
	private String farmacia_nome;
	private int cap;
	private int prezzo;
	private int quantita;
	private String indirizzo;
	private String lat;
	private String lon;
	
	public FarmaciaProdottoDTO() {}

	public String getProdottoNome() {
		return prodotto_nome;
	}
	
	public String getLat() {
		return lat;
	}
	public String getLon() {
		return lon;
	}
	public String getFarmaciaNome() {
		return farmacia_nome;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public int getCap() {
		return cap;
	}
	
	public int getPrezzo() {
		return prezzo;
	}
	
	public int getQuantita() {
		return quantita;
	}
	
	public void setProdottoNome(String prodotto_nome) {
		this.prodotto_nome=prodotto_nome;
	}
	public void setLat(String lat) {
		this.lat=lat;
	}
	public void setLon(String lon) {
		this.lon=lon;
	}
	public void setFarmaciaNome(String farmacia_nome) {
		this.farmacia_nome=farmacia_nome;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo=indirizzo;
	}
	
	public void setCap(int cap) {
		this.cap=cap;
	}
	public void setPrezzo(int prezzo) {
		this.prezzo=prezzo;
	}
	public void setQuantita(int quantita) {
		this.quantita=quantita;
	}
	
	public String toString() {
		return prodotto_nome + farmacia_nome;
	}
}