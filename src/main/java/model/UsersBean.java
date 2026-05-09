package model;

import java.io.Serializable;

public class UsersBean implements Serializable{
	private static final long serialVersionUID= 1L;
	
	private int id;
	private String nome;
	private String password;
	private String ruolo;
	
	public UsersBean() {}
	
	public int getId(){
		return id;
	}
	
	public String getNome(){
		return nome;
	}
	
}
