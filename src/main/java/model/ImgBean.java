package model;

import java.io.Serializable;

public class ImgBean implements Serializable{
	private static final long serialVersionUID= 1L;

	private int id;
	private String path;
	private int prodotto_id;
	private String mimetype;
	
	public ImgBean() {}
	
	public int getId() {
		return id;
	}
	
	public String getPath() {
		return path;
	}
	
	public int getProdottoId() {
		return prodotto_id;
	}
	
	public String getMimeType() {
		return mimetype;
	}
	
	public void setPath(String path) {
		this.path=path;
	}
	public void setMimeType(String mimetype) {
		this.mimetype=mimetype;
	}
	public void setProdottoId(int prodotto_id) {
		this.prodotto_id=prodotto_id;
	}
}
