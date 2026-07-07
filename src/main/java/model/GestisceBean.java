package model;

public class GestisceBean {
	private int user_id;
	private int farmacia_id;
	
	public GestisceBean() {}
	
	public void setUserId(int user_id) {
		this.user_id=user_id;
	}
	
	public void setFarmaciaId(int farmacia_id) {
		this.farmacia_id=farmacia_id;
	}
	
	public int getUserId() {
		return user_id;
	}
	
	public int getFarmaciaId() {
		return farmacia_id;
	}
}
