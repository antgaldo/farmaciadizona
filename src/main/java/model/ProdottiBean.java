package model;

import java.io.Serializable;

public class ProdottiBean implements Serializable{
	private static final long serialVersionUID= 1L;

	private int id;
	private String nome;
	private String descrizione;
	
	public ProdottiBean() {}
	
	public int getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public void setId(int id) {
		this.id=id;
	}
	public void setNome(String nome) {
		this.nome=nome;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione=descrizione;
	}
}
