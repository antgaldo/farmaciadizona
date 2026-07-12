package dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.sql.DataSource;
import dao.interfaceDao.UsersDao;
import model.UsersBean;

public class UsersDaoImp implements UsersDao{

	private DataSource ds;
	public UsersDaoImp(DataSource ds) {
		this.ds=ds;
	}
	@Override
	public int doSave(UsersBean users) throws SQLException {
		String insertSQL= "INSERT INTO users (nome,password,ruolo,active,email) VALUES (?,?,?,?,?)";
		try(Connection connection = ds.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(insertSQL,Statement.RETURN_GENERATED_KEYS)){
			preparedStatement.setString(1, users.getNome());
			preparedStatement.setString(2,users.getPassword());
			preparedStatement.setString(3,users.getRuolo());
			preparedStatement.setInt(4,users.getActive());
			preparedStatement.setString(5, users.getEmail());
			preparedStatement.executeUpdate();
			try(ResultSet rs= preparedStatement.getGeneratedKeys()){
				if(rs.next()) {
					return rs.getInt(1);
				}
			}
		}
		throw new SQLException("Errore creazione utente");
	}
	
	@Override
	public UsersBean login(UsersBean users) throws SQLException{
		String selectSQL= "SELECT * FROM users WHERE email=?";
		try(Connection connection= ds.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)){
			preparedStatement.setString(1, users.getEmail());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				if(rs.getString("password").equals(users.getPassword())) {
					 UsersBean u = new UsersBean();
			            u.setEmail(rs.getString("email"));
			            u.setRuolo(rs.getString("ruolo"));
			            u.setNome(rs.getString("nome"));
			            u.setId(rs.getInt("id"));
			            return u;
				}
			}
		}
		return null;
	}
	
	@Override
	public UsersBean getUser(UsersBean user) throws SQLException{
		String selectSQL= "SELECT nome,email FROM users WHERE nome=? AND email=?";
		try(Connection connection=ds.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)){
			preparedStatement.setString(1, user.getNome());
			preparedStatement.setString(2, user.getEmail());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				UsersBean u= new UsersBean();
					u.setEmail(rs.getString("email"));
					u.setNome(rs.getString("nome"));
					return u;
			}
		}
		return null;
	}
}
