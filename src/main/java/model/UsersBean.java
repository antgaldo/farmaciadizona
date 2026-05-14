package model;

import java.io.Serializable;

public class UsersBean implements Serializable{
	private static final long serialVersionUID= 1L;
	
	private int id;
	private String nome;
	private String password;
	private String ruolo;
	private String email;
	private int active;
	
	public UsersBean() {}
	
	public int getId(){
		return id;
	}
	
	public void setNome(String nome) {
		this.nome=nome;
	}
	
	public void setPassword(String password) {
		this.password=password;
	}
	
	public void setRuolo(String ruolo) {
		this.ruolo=ruolo;
	}
	
	public void setActive(int active) {
		this.active=active;
	}
	
	public void setEmail(String email) {
		this.email=email;
	}
	
	public String getNome(){
		return nome;
	}
	public String getPassword() {
		return password;
	}
	public String getRuolo() {
		return ruolo;
	}
	public int getActive() {
		return active;
	}
	public String getEmail() {
		return email;
	}
}
