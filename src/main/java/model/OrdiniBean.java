package model;
import java.io.Serializable;
import java.math.BigDecimal;

import java.time.LocalDateTime;


public class OrdiniBean implements Serializable {

    private static final long serialVersionUID = 1L;
	private int id_user;
	private int id_farmacia;
	private int id;
	private LocalDateTime data_acquisto;
	private BigDecimal totale;
	
	public OrdiniBean() {}
	
	public void setId(int id) {
		this.id=id;
	}
	public void setIdFarmacia(int id_farmacia) {
		this.id_farmacia=id_farmacia;
	}
	public void setIdUser(int id_user) {
		this.id_user=id_user;
	}
	
	public void setDataAcquisto(LocalDateTime data_acquisto) {
		this.data_acquisto=data_acquisto;
	}
	public void setTotale(BigDecimal totale) {
		this.totale=totale;
	}
	public int getId() {
		return id;
	}
	public int getIdFarmacia() {
		return id_farmacia;
	}
	public int getIdUser() {
		return id_user;
	}
	public LocalDateTime getDataAcquisto() {
		return data_acquisto;
	}
	
	public BigDecimal getTotale() {
		return totale;
	}
}
