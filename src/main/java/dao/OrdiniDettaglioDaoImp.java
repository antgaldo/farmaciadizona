package dao;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import javax.sql.DataSource;
import java.sql.PreparedStatement;


import dao.interfaceDao.OrdiniDettaglioDao;
import model.OrdiniDettaglioBean;

public class OrdiniDettaglioDaoImp implements OrdiniDettaglioDao {
	private DataSource ds;
	public OrdiniDettaglioDaoImp(DataSource ds) {
		this.ds=ds;
	}
	public void doSave(int ordine_id,List<OrdiniDettaglioBean> ordiniDettaglioBean,Connection connection) throws SQLException {
		String insertsql= "INSERT INTO ordini_dettaglio (ordini_id,prodotto_id,quantita_prodotto,nome_prodotto,prezzo_prodotto) values(?,?,?,?,?)";
		try(PreparedStatement preparedStatement= connection.prepareStatement(insertsql)){
        	for(OrdiniDettaglioBean ordineDettaglio: ordiniDettaglioBean) {
        		ordineDettaglio.setIdOrdine(ordine_id);
        		//ordineDettaglio.addBatch();
    			preparedStatement.setInt(1, ordineDettaglio.getIdOrdine());
    			preparedStatement.setInt(2, ordineDettaglio.getProdottoId());
    			preparedStatement.setInt(3, ordineDettaglio.getQuantitaProdotto());
    			preparedStatement.setString(4, ordineDettaglio.getNomeProdotto());
    			preparedStatement.setBigDecimal(5, ordineDettaglio.getPrezzoProdotto());
    			preparedStatement.addBatch();
        	}
        	preparedStatement.executeBatch();
		}
	}

}
