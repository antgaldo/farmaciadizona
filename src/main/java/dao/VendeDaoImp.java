package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import javax.sql.DataSource;

import dao.interfaceDao.VendeDao;
import model.FarmacieBean;
import model.ProdottiBean;
import model.VendeBean;

public class VendeDaoImp implements VendeDao {
	
	private DataSource ds;
	public VendeDaoImp(DataSource ds) {
		this.ds=ds;
	}
	
	@Override
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
	
	@Override
	public void delete(int idFarmacia, int idProdotto) throws SQLException{
		String deletesql= "DELETE FROM vende WHERE farmacia_id=? AND prodotto_id=?";
		try(Connection connection = ds.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(deletesql)){
				preparedStatement.setInt(1, idFarmacia);
				preparedStatement.setInt(2, idProdotto);
				preparedStatement.executeUpdate();
		}
	}
}
