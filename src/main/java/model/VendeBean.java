package model;

import java.io.Serializable;

public class VendeBean implements Serializable{
	private static final long serialVersionUID= 1L;
	
	private int farmacia_id;
	private int prodotto_id;
	private double prezzo;
	private int quantita;
	private boolean active;
	
	public VendeBean() {}
	
	public void setFarmaciaId(int farmacia_id) {
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
	
	public void setActive(boolean active) {
		this.active=active;
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
	
	public boolean getActive() {
		return active;
	}
	
	public String toString() {
		return "VendeBean{" +"farmaciaid=" + farmacia_id + ", prodottoid='" + prodotto_id + '\'' +'}';
	}
}
