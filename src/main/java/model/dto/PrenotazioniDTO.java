package model.dto;

import java.time.LocalDateTime;
import java.math.BigDecimal;
import model.OrdiniDettaglioBean;
import java.util.List;
import java.util.ArrayList;


public class PrenotazioniDTO {
	private int ordine_id;
	private String nomeUtente;
	private LocalDateTime dataAcquisto;
	private BigDecimal totale;
    private List<OrdiniDettaglioBean> lista = new ArrayList<>(); 
	
	public PrenotazioniDTO() {}
	
	public void setDataAcquisto(LocalDateTime dataAcquisto) {
		this.dataAcquisto=dataAcquisto;
	}
	public void setOrdineId(int ordine_id) {
		this.ordine_id=ordine_id;
	}
	public void setTotale(BigDecimal totale) {
		this.totale=totale;
	}
	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente=nomeUtente;
	}
	public void setListaOrdini(List<OrdiniDettaglioBean> lista) {
		this.lista=lista;
	}
	
	public LocalDateTime getDataAcquisto() {
		return dataAcquisto;
	}
	public BigDecimal getTotale() {
		return totale;
	}
	public int getOrdineId() {
		return ordine_id;
	}
	public String getNomeUtente() {
		return nomeUtente;
	}
	
	public List<OrdiniDettaglioBean> getListaOrdini(){
		return lista;
	}
	@Override
	public String toString() {
		return "" + ordine_id+ lista;
	}
}
