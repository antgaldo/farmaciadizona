package model;

import java.io.Serializable;
import java.util.Date;

public class AcquistaBean implements Serializable{
	private static final long serialVersionUID= 1L;
	
	private int id;
	private int users_id;
	private int prodotto_id;
	private double prezzo;
	private Date data_acquisto;
	private int quantita;
	
	public AcquistaBean() {}
}
