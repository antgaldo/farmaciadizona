package model.dto;

public class ElementoCarrelloDTO {
	private int idProdotto;
	private int idFarmacia;
	private String nome;
	private String nomeFarmacia;
	private int quantita;
	private double prezzo;
	private String path;
	
	public ElementoCarrelloDTO(int idProdotto,int idFarmacia,String nomeFarmacia, String nome, int quantita,double prezzo,String path) {
		this.idProdotto=idProdotto;
		this.idFarmacia=idFarmacia;
		this.nome=nome;
		this.quantita=quantita;
		this.prezzo=prezzo;
		this.path=path;
		this.nomeFarmacia=nomeFarmacia;
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
	public String getNomeFarmacia() {
		return nomeFarmacia;
	}
	public void setQuantita(int quantita) {
		this.quantita=quantita;
	}
	public void setPath(String path) {
		this.path=path;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo=prezzo;
	}
	public double getPrezzo() {
		return prezzo;
	}
	public void setNomeFarmacia(String nomeFarmacia) {
		this.nomeFarmacia=nomeFarmacia;
	}
	public String getPath() {
		return path;
	}
	
}
