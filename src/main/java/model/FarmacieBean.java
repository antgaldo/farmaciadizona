package model;

import java.io.Serializable;

public class FarmacieBean implements Serializable{
	private static final long serialVersionUID= 1L;
	
	private int id;
	private int cap;
	private boolean active;
	private String nome;
	
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
	
	public void setCap(int cap) {
		this.cap=cap;
	}
	
	public void setActive(boolean active) {
		this.active=active;
	}
	
	public void setNome(String nome) {
		this.nome=nome;
	}
}
