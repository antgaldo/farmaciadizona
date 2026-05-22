package model.viewbean;

public class VendeDettaglioBean {
	int idProdotto;
    String nomeProdotto;
    String descrizione;
    int prezzo;
    int quantita;
    
    public VendeDettaglioBean() {}
    
    public void setIdProdotto(int idProdotto) {
    	this.idProdotto=idProdotto;
    }
    
    public void setNomeProdotto(String nomeProdotto) {
    	this.nomeProdotto=nomeProdotto;
    }
    
    public void setDescrizione(String descrizione) {
    	this.descrizione=descrizione;
    }
    
    public void setPrezzo(int prezzo) {
    	this.prezzo=prezzo;
    }
    
    public void setQuantita(int quantita) {
    	this.quantita=quantita;
    }
    
    public int getIdProdotto() {
    	return idProdotto;
    }
    
    public String getNomeProdotto() {
    	return nomeProdotto;
    }
    
    public String getDescrizione() {
    	return descrizione;
    }
    
    public int getPrezzo() {
    	return prezzo;
    }
    
    public int getQuantita() {
    	return quantita;
    }
    
    public String toString() {
    	return this.getNomeProdotto() + this.getDescrizione();
    }
}
