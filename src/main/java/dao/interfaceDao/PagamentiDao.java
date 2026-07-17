package dao.interfaceDao;

import java.sql.SQLException;

import model.PagamentiBean;

public interface PagamentiDao {
	public void doSave(PagamentiBean pagamento) throws SQLException;
}
