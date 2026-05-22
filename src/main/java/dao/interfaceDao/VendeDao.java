package dao.interfaceDao;

import java.sql.SQLException;
import java.util.List;

import model.VendeBean;
import model.FarmacieBean;

public interface VendeDao {
	public void doSave(VendeBean vende) throws SQLException;
	public void delete(int idFarmacia, int idProdotto) throws SQLException;
}
