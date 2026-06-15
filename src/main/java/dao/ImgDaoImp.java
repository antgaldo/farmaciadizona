package dao;
import java.sql.Connection;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.interfaceDao.ImgDao;
import model.ImgBean;

public class ImgDaoImp implements ImgDao{
	private DataSource ds;
	
	public ImgDaoImp(DataSource ds) {
		this.ds=ds;
	}
	
	public void doSave(ImgBean img) throws SQLException {
		String insertSql="INSERT INTO img (path,prodotto_id,mimetype) VALUES(?,?,?)";
		try(Connection connection=ds.getConnection();
				PreparedStatement preparedStatement= connection.prepareStatement(insertSql)){
			preparedStatement.setString(1, img.getPath());
			preparedStatement.setInt(2, img.getProdottoId());
			preparedStatement.setString(3, img.getMimeType());
			preparedStatement.executeUpdate();
		}
	}
}
