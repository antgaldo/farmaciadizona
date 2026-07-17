package dao;

import java.sql.SQLException;
import javax.sql.DataSource;
import dao.interfaceDao.SpedizioniDao;
import model.SpedizioniBean;

public class SpedizioniDaoImp implements SpedizioniDao{
	private DataSource ds;
	
	public SpedizioniDaoImp(DataSource ds) {
		this.ds=ds;
	}
	
	public void doSave(SpedizioniBean spedizioni) throws SQLException{
		
	}
}
