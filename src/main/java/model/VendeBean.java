package model;

import java.io.Serializable;

public class VendeBean implements Serializable{
	private static final long serialVersionUID= 1L;
	
	private int farmacia_id;
	private int prodotto_id;
	private double prezzo;
	private int quantita;
	
	public VendeBean() {}
	
	public void setFarmadiaId(int farmacia_id) {
		this.farmacia_id=farmacia_id;
	}
	public void setProdottoId(int prodotto_id) {
		this.prodotto_id=prodotto_id;
	}
	
	public void setPrezzo(double prezzo) {
		this.prezzo=prezzo;
	}
	
	public void setQuantita(int quantita) {
		this.quantita=quantita;
	}
	
	public int getFarmaciaId() {
		return farmacia_id;
	}
	public int getProdottoId() {
		return prodotto_id;
	}
	
	public double getPrezzo() {
		return prezzo;
	}
	public int getQuantita() {
		return quantita;
	}
}
