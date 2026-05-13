package dao;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import model.UsersBean;

public class UsersDaoImp implements UsersDao{

	private DataSource ds;
	public UsersDaoImp(DataSource ds) {
		this.ds=ds;
	}
	@Override
	public void doSave(UsersBean users) throws SQLException {
		String insertSQL= "INSERT INTO " + "users" + " (nome,password,ruolo,active) VALUES (?,?,?,?)";
		try(Connection connection = ds.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)){
			preparedStatement.setString(1, users.getNome());
			preparedStatement.setString(2,users.getPassword());
			preparedStatement.setString(3,users.getRuolo());
			preparedStatement.setInt(4,users.getActive());
			preparedStatement.executeUpdate();
		}
	}
	@Override
	public UsersBean login(UsersBean users) throws SQLException{
		String selectSQL= "SELECT nome,password,ruolo " + "FROM users " + "WHERE " + "nome=?" + "AND password=?";
		try(Connection connection= ds.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)){
			preparedStatement.setString(1, users.getNome());
			preparedStatement.setString(2,users.getPassword());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				 UsersBean u = new UsersBean();
		            u.setNome(rs.getString("nome"));
		            u.setRuolo(rs.getString("ruolo"));
		            return u;
			}
		}
		return null;
	}
}
