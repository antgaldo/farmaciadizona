package dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.interfaceDao.GestisceDao;
import model.GestisceBean;

public class GestisceDaoImp implements GestisceDao{
	
	private DataSource ds;
	public GestisceDaoImp(DataSource ds) {
		this.ds=ds;
	}
	
	public void doSave(GestisceBean gestisce) throws SQLException {
		String insertSql= "INSERT INTO gestisce (user_id,farmacie_id) VALUES(?,?)";
		try (Connection connection= ds.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(insertSql)){
			preparedStatement.setInt(1, gestisce.getFarmacieId());
			preparedStatement.setInt(2, gestisce.getUserId());
			preparedStatement.executeUpdate();
		}
	}
}
