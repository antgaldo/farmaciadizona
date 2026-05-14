package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import dao.interfaceDao.FarmacieDao;
import model.FarmacieBean;
import javax.sql.DataSource;
import java.sql.ResultSet;

public class FarmacieDaoImp implements FarmacieDao{
	
	private DataSource ds;
	public FarmacieDaoImp(DataSource ds) {
		this.ds=ds;
	}
	
	@Override
	public int doSave(FarmacieBean farmacie) throws SQLException{
		String insertSql= "INSERT INTO farmacie (cap,active,nome) VALUES(?,?,?)";
		try(Connection connection = ds.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(insertSql,Statement.RETURN_GENERATED_KEYS)){
				preparedStatement.setInt(1, farmacie.getCap());
				preparedStatement.setBoolean(2, farmacie.getActive());
				preparedStatement.setString(3, farmacie.getNome());
				preparedStatement.executeUpdate();
				try (ResultSet rs= preparedStatement.getGeneratedKeys()){
		            if(rs.next()) {
		                return rs.getInt(1);
		            }
		        }
			}
		return 0;
	}
}
