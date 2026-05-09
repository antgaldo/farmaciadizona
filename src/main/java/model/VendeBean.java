package model;

import java.io.Serializable;

public class VendeBean implements Serializable{
	private static final long serialVersionUID= 1L;
	
	private int farmacia_id;
	private int prodotto_id;
	private double prezzo;
	private int quantità;
	
	public VendeBean() {}
}
