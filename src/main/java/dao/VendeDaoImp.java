package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import dao.interfaceDao.VendeDao;
import model.VendeBean;

public class VendeDaoImp implements VendeDao {
	
	private DataSource ds;
	public VendeDaoImp(DataSource ds) {
		this.ds=ds;
	}
	
	public void doSave(VendeBean vendebean) throws SQLException{
		String insertsql= "INSERT INTO vende (farmacia_id,prodotto_id,prezzo,quantita_disponibile) values(?,?,?,?)";
		try(Connection connection = ds.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(insertsql)){
				preparedStatement.setInt(1, vendebean.getFarmaciaId());
				preparedStatement.setInt(2, vendebean.getProdottoId());
				preparedStatement.setDouble(3, vendebean.getPrezzo());
				preparedStatement.setInt(4, vendebean.getQuantita());
				preparedStatement.executeUpdate();
			}
	}
}
