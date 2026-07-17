package model.dto;

import java.io.Serializable;

public class ProdottoDettaglioDTO implements Serializable {

    private static final long serialVersionUID = 1L;
	private int id;
	private String nome;
	private String descrizione;
	private String categoria;
	private String path;
	
	public ProdottoDettaglioDTO() {}
	
	public int getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	
	public String getCategoria() {
		return categoria;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public String getPath() {
		return path;
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
	
	public void setCategoria(String categoria) {
		this.categoria=categoria;
	}
	
	public void setPath(String path) {
		this.path=path;
	}
	
	public String toString(){
		return nome;
	}
}
