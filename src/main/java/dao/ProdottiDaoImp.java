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
import java.util.List;
import java.util.ArrayList;

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
				p.setId(rs.getInt("id"));
				return p;
			};
		}
		return null;
	}
	
	@Override
	public List<ProdottiBean> getAll() throws SQLException{
		List<ProdottiBean> lista= new ArrayList<ProdottiBean>();
		String selectSQL= "SELECT * FROM prodotti";
		try(Connection connection = ds.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)){
			ResultSet rs = preparedStatement.executeQuery();
			 while(rs.next()) {
	            ProdottiBean prodotto = new ProdottiBean();
	            prodotto.setId(rs.getInt("id"));
	            prodotto.setNome(rs.getString("nome"));
	            lista.add(prodotto);
		    }
		}
		return lista;
	}
	
}
