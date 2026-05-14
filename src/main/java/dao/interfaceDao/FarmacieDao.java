package dao.interfaceDao;

import java.sql.SQLException;
import model.FarmacieBean;

public interface FarmacieDao {
	public int doSave(FarmacieBean farmacie) throws SQLException;
}
