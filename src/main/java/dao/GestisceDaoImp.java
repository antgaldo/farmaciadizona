package dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.interfaceDao.GestisceDao;
import model.GestisceBean;
import model.UsersBean;

public class GestisceDaoImp implements GestisceDao{
	
	private DataSource ds;
	public GestisceDaoImp(DataSource ds) {
		this.ds=ds;
	}
	
	public void doSave(GestisceBean gestisce) throws SQLException {
		String insertSql= "INSERT INTO gestisce (user_id,farmacie_id) VALUES(?,?)";
		try (Connection connection= ds.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(insertSql)){
			preparedStatement.setInt(1, gestisce.getFarmaciaId());
			preparedStatement.setInt(2, gestisce.getUserId());
			preparedStatement.executeUpdate();
		}
	}
	
	public int getGestisce(UsersBean user) throws SQLException{
		String getSQL= "SELECT farmacie_id FROM gestisce WHERE user_id=?";
		try(Connection connection=ds.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(getSQL)){
			preparedStatement.setInt(1,user.getId());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				int id= rs.getInt("farmacie_id");
				return id;
			}
		};
		return 0;
	}
}
