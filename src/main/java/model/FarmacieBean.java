package model;

import java.io.Serializable;

public class FarmacieBean implements Serializable{
	private static final long serialVersionUID= 1L;
	
	private int id;
	private int cap;
	private boolean active;
	private String nome;
	private String indirizzo;
	private String lat;
	private String lon;
	
	public FarmacieBean() {}
	
	public int getId() {
		return id;
	}
	
	public int getCap() {
		return cap;
	}
	
	public boolean getActive() {
		return active;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getIndirizzo() {
		return indirizzo;
	}
	
	public String getLat() {
		return lat;
	}
	
	public String getLon() {
		return lon;
	}
	
	public void setCap(int cap) {
		this.cap=cap;
	}
	
	public void setActive(boolean active) {
		this.active=active;
	}
	
	public void setNome(String nome) {
		this.nome=nome;
	}
	
	public void setIndirizzo(String indirizzo) {
		this.indirizzo=indirizzo;
	}
	
	public void setLat(String lat) {
		this.lat=lat;
	}
	
	public void setLon(String lon) {
		this.lon=lon;
	}
}
