package model;

import java.sql.Date;

public class OrdiniBean {
	private int id_user;
	private int id_farmacia;
	private int id;
	private Date data_acquisto;
	private double totale;
	
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
	
	public void setDataAcquisto(Date data_acquisto) {
		this.data_acquisto=data_acquisto;
	}
	public void setTotale(int totale) {
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
	public Date getDataAcquisto() {
		return data_acquisto;
	}
	
	public double getTotale() {
		return totale;
	}
}
