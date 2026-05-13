package dao;

import java.sql.SQLException;
import model.UsersBean;

public interface UsersDao {
	public void doSave(UsersBean users) throws SQLException;
	public UsersBean login(UsersBean users) throws SQLException;
}
