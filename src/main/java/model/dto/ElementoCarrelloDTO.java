package model.dto;

public class ElementoCarrelloDTO {
	private int idProdotto;
	private int idFarmacia;
	private String nome;
	private int quantita;
	private double prezzo;
	
	public ElementoCarrelloDTO(int idProdotto,int idFarmacia, String nome, int quantita,double prezzo) {
		this.idProdotto=idProdotto;
		this.idFarmacia=idFarmacia;
		this.nome=nome;
		this.quantita=quantita;
		this.prezzo=prezzo;
	}
	
	public int getIdProdotto() {
		return idProdotto;
	}
	public int getIdFarmacia() {
		return idFarmacia;
	}
	public int getQuantita() {
		return quantita;
	}
	public String getNome() {
		return nome;
	}
	public void setQuantita(int quantita) {
		this.quantita=quantita;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo=prezzo;
	}
	public double getPrezzo() {
		return prezzo;
	}
}
