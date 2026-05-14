package model;

public class GestisceBean {
	private int user_id;
	private int farmacie_id;
	
	public GestisceBean() {}
	
	public void setUserId(int user_id) {
		this.user_id=user_id;
	}
	
	public void setFarmacieId(int farmacie_id) {
		this.farmacie_id=farmacie_id;
	}
	
	public int getUserId() {
		return user_id;
	}
	
	public int getFarmacieId() {
		return farmacie_id;
	}
}
