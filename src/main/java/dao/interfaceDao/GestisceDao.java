package dao.interfaceDao;
import model.GestisceBean;
import model.UsersBean;

import java.sql.SQLException;

public interface GestisceDao {
	public void doSave(GestisceBean gestisce) throws SQLException;
	public int getGestisce(UsersBean user) throws SQLException;
}
