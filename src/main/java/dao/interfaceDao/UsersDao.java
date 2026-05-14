package dao.interfaceDao;

import java.sql.SQLException;
import model.UsersBean;

public interface UsersDao {
	public int doSave(UsersBean users) throws SQLException;
	public UsersBean login(UsersBean users) throws SQLException;
	public UsersBean getUser(UsersBean users) throws SQLException;
}
