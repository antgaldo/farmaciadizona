package dao;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

import dao.interfaceDao.ProdottiDao;
import model.ProdottiBean;
import model.UsersBean;


public class ProdottiDaoImp implements ProdottiDao{
	
	private DataSource ds;
	public ProdottiDaoImp(DataSource ds) {
		this.ds=ds;
	}
	@Override
	public int doSave(ProdottiBean prodotti) throws SQLException{
		String insertSQL= "INSERT INTO " + "prodotti " + "(nome,descrizione) " + "VALUES(?,?)";
		try(Connection connection = ds.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(insertSQL,Statement.RETURN_GENERATED_KEYS)){
			preparedStatement.setString(1, prodotti.getNome());
			preparedStatement.setString(2, prodotti.getDescrizione());
			preparedStatement.executeUpdate();
			try(ResultSet rs = preparedStatement.getGeneratedKeys()) {
	            if(rs.next()) {
	                return rs.getInt(1);
	            }
	        }
		}
		return 0;
	}
	
	@Override
	public boolean checkProdotto(ProdottiBean prodotto) throws SQLException{
		String selectSQL= "SELECT * FROM prodotti WHERE nome= ?" ;
		try(Connection connection = ds.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)){
			preparedStatement.setString(1, prodotto.getNome());
			ResultSet rs = preparedStatement.executeQuery();
			return rs.next();
		}
	}
	
	@Override
	public ProdottiBean getProdotto(ProdottiBean prodotto) throws SQLException{
		String selectSQL= "SELECT * FROM prodotti WHERE nome=?" ;
		try(Connection connection = ds.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)){
			preparedStatement.setString(1, prodotto.getNome());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				ProdottiBean p= new ProdottiBean();
				p.setNome(rs.getString("nome"));
				p.setDescrizione(rs.getString("descrizione"));
				return p;
			};
		}
		return null;
	}
}
