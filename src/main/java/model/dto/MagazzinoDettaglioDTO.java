package model.dto;

public class MagazzinoDettaglioDTO {
	int idProdotto;
    String nomeProdotto;
    String descrizione;
    double prezzo;
    int quantita;
    String categoria;
    String pathimg;
    
    public MagazzinoDettaglioDTO() {}
    
    public void setIdProdotto(int idProdotto) {
    	this.idProdotto=idProdotto;
    }
    
    public void setCategoria(String categoria) {
    	this.categoria=categoria;
    }
    
    public void setNomeProdotto(String nomeProdotto) {
    	this.nomeProdotto=nomeProdotto;
    }
    
    public void setDescrizione(String descrizione) {
    	this.descrizione=descrizione;
    }
    
    public void setPrezzo(double prezzo) {
    	this.prezzo=prezzo;
    }
    
    public void setQuantita(int quantita) {
    	this.quantita=quantita;
    }
    public void setPathImg(String pathimg) {
    	this.pathimg=pathimg;
    }
    public String getPathImg() {
    	return pathimg;
    }
    
    public int getIdProdotto() {
    	return idProdotto;
    }
    
    public String getNomeProdotto() {
    	return nomeProdotto;
    }
    
    public String getCategoria() {
    	return categoria;
    }
    
    public String getDescrizione() {
    	return descrizione;
    }
    
    public double getPrezzo() {
    	return prezzo;
    }
    
    public int getQuantita() {
    	return quantita;
    }
    
    public String toString() {
    	return this.getNomeProdotto() + this.getDescrizione();
    }
}
