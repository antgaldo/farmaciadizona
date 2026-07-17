package dao.interfaceDao;

import java.sql.SQLException;

import model.SpedizioniBean;

public interface PagamentiDao {
	public void doSave(SpedizioniBean spedizioni) throws SQLException;
}
