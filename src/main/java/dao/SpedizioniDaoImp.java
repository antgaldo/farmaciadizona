package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;
import dao.interfaceDao.SpedizioniDao;
import model.SpedizioniBean;

public class SpedizioniDaoImp implements SpedizioniDao{
	private DataSource ds;
	
	public SpedizioniDaoImp(DataSource ds) {
		this.ds=ds;
	}
	
	public void doSave(SpedizioniBean spedizioni) throws SQLException{
		String insertSql="INSERT INTO spedizioni (ordine_id,nome,cognome,indirizzo,cap,citta) VALUES(?,?,?,?,?,?)";
		try(Connection connection= ds.getConnection();
				PreparedStatement preparedStatement= connection.prepareStatement(insertSql)){
			preparedStatement.setInt(1, spedizioni.getOrdineId());
			preparedStatement.setString(2, spedizioni.getNome());
			preparedStatement.setString(3, spedizioni.getCognome());
			preparedStatement.setString(4, spedizioni.getIndirizzo());
			preparedStatement.setString(5, spedizioni.getCap());
			preparedStatement.setString(6, spedizioni.getCitta());
			preparedStatement.executeUpdate();
		}
	}
}
