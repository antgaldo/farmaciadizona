package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import model.PagamentiBean;
import dao.interfaceDao.PagamentiDao;

public class PagamentiDaoImp implements PagamentiDao{
	private DataSource ds;
	
	public PagamentiDaoImp(DataSource ds) {
		this.ds=ds;
	}
	
	public void doSave(PagamentiBean pagamenti) throws SQLException{
		String insertSql="INSERT INTO pagamento (ordine_id,circuito,numero,scadenza) VALUES(?,?,?,?)";
		try(Connection connection= ds.getConnection();
				PreparedStatement preparedStatement= connection.prepareStatement(insertSql)){
			preparedStatement.setInt(1, pagamenti.getOrdineId());
			preparedStatement.setString(2, pagamenti.getCircuito());
			preparedStatement.setString(3, pagamenti.getNumero());
			preparedStatement.setString(4, pagamenti.getScadenza());
			preparedStatement.executeUpdate();
		}
	}
}
