package model.dto;

public class FarmaciaProdottoDTO {
	private String prodotto_nome;
	private String farmacia_nome;
	private int cap;
	private int prezzo;
	private int quantita;
	
	public FarmaciaProdottoDTO() {}

	public String getProdottoNome() {
		return prodotto_nome;
	}
	public String getFarmaciaNome() {
		return farmacia_nome;
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
	
	public void setFarmaciaNome(String farmacia_nome) {
		this.farmacia_nome=farmacia_nome;
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
}