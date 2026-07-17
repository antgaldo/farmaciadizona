package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.sql.DataSource;
import dao.interfaceDao.OrdiniDettaglioDao;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

import dao.interfaceDao.OrdiniDao;
import model.OrdiniBean;
import model.OrdiniDettaglioBean;

public class OrdiniDaoImp implements OrdiniDao{
	
	private DataSource ds;
	private OrdiniDettaglioDao ordiniDettaglioDao;
	public OrdiniDaoImp(DataSource ds) {
		this.ds=ds;
		ordiniDettaglioDao =new OrdiniDettaglioDaoImp(ds);
	}
	
	public int doSave(OrdiniBean ordine,List<OrdiniDettaglioBean> ordiniDettaglio) throws SQLException{
		String insertsql= "INSERT INTO ordini (user_id,farmacia_id,data_acquisto,totale) values(?,?,?,?)";
		Connection connection = ds.getConnection();
		connection.setAutoCommit(false);
		int ordine_id;
		try(PreparedStatement preparedStatement = connection.prepareStatement(insertsql,Statement.RETURN_GENERATED_KEYS)){
				preparedStatement.setInt(1, ordine.getIdUser());
				preparedStatement.setInt(2, ordine.getIdFarmacia());
				preparedStatement.setObject(3, ordine.getDataAcquisto());
				preparedStatement.setBigDecimal(4, ordine.getTotale());
				preparedStatement.executeUpdate();
				ResultSet rs= preparedStatement.getGeneratedKeys();
	            if (rs.next()) {
	            	ordine_id= rs.getInt(1);
            		ordiniDettaglioDao.doSave(ordine_id,ordiniDettaglio,connection);
	            	//ordineDettaglio.executeBatch();
	            } else {
	                throw new SQLException("Impossibile generare l'ID dell'ordine.");
	            }
	            connection.commit();
	            return ordine_id;
			} catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            } throw e; 
        } finally {
            if (connection != null) {
                try {
                	// Ripristina lo stato iniziale del database
                    connection.setAutoCommit(true); 
                    // Rilascia la connessione e la restituisce al pool
                    connection.close();             
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
	}
}
