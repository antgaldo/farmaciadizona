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
		String insertSql= "INSERT INTO gestisce (user_id,farmacia_id) VALUES(?,?)";
		try (Connection connection= ds.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(insertSql)){
			preparedStatement.setInt(1, gestisce.getFarmaciaId());
			preparedStatement.setInt(2, gestisce.getUserId());
			preparedStatement.executeUpdate();
		}
	}
	
	public Object[] getGestisce(UsersBean user) throws SQLException{
		String getSQL= "SELECT farmacia_id,farmacie.nome "
				+ " FROM gestisce"
				+ " JOIN farmacie ON farmacie.id=farmacia_id "
				+ " WHERE user_id=?";
		Object[] lista= new Object[2];
		try(Connection connection=ds.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(getSQL)){
			preparedStatement.setInt(1,user.getId());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				int id= rs.getInt("farmacia_id");
				String nome= rs.getString("nome");
				lista[0]=id;
				lista[1]=nome;
				return lista;
				
			}
		};
		return null;
	}
}
