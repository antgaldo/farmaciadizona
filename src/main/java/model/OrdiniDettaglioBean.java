package model;
import java.math.BigDecimal;

public class OrdiniDettaglioBean {
	private int id;
	private int id_ordine;
	private int prodotto_id;
	private String nome_prodotto;
	private BigDecimal prezzo_prodotto;
	private int quantita_prodotto;
	
	public OrdiniDettaglioBean() {}
	
	public void setId(int id) {
		this.id=id;
	}
	public void setProdottoId(int prodotto_id) {
		this.prodotto_id=prodotto_id;
	}
	public void setIdOrdine(int id_ordine) {
		this.id_ordine=id_ordine;
	}
	
	public void setNomeProdotto(String nome_prodotto) {
		this.nome_prodotto=nome_prodotto;
	}
	public void setPrezzoProdotto(BigDecimal prezzo_prodotto) {
		this.prezzo_prodotto=prezzo_prodotto;
	}
	public void setQuantitaProdotto(int quantita_prodotto) {
		this.quantita_prodotto=quantita_prodotto;
	}
	public int getId() {
		return id;
	}
	public int getIdOrdine() {
		return id_ordine;
	}
	public String getNomeProdotto() {
		return nome_prodotto;
	}
	public BigDecimal getPrezzoProdotto() {
		return prezzo_prodotto;
	}
	public int getQuantitaProdotto() {
		return quantita_prodotto;
	}
	public int getProdottoId() {
		return prodotto_id;
	}
}
