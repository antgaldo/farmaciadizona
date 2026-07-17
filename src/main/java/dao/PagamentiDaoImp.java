package dao;

import java.sql.SQLException;

import javax.sql.DataSource;

import model.SpedizioniBean;
import dao.interfaceDao.PagamentiDao;

public class PagamentiDaoImp implements PagamentiDao{
	private DataSource ds;
	
	public PagamentiDaoImp(DataSource ds) {
		this.ds=ds;
	}
	
	public void doSave(SpedizioniBean spedizioni) throws SQLException{
		
	}
}
