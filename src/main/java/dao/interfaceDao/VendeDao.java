package dao.interfaceDao;

import java.sql.SQLException;
import model.VendeBean;

public interface VendeDao {
	public void doSave(VendeBean vende) throws SQLException;
}
