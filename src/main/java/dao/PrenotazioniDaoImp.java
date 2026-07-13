package dao;

import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;
import java.sql.SQLException;

import dao.interfaceDao.PrenotazioniDao;
import model.dto.PrenotazioniDTO;
import model.OrdiniDettaglioBean;

public class PrenotazioniDaoImp implements PrenotazioniDao{
	private DataSource ds;
	public PrenotazioniDaoImp(DataSource ds) {
		this.ds=ds;
	}
	
	public List<PrenotazioniDTO> getPrenotazioni(int farmacia_id) throws SQLException{
		String selectSQL="SELECT "
				+ " ordini.id AS ordine_id,"
				+ " ordini.totale,"
				+ " ordini.data_acquisto,"
				+ " u.nome AS nome_utente,"
				+ " o.nome_prodotto,"
				+ " o.quantita_prodotto,"
				+ " o.prezzo_prodotto"
				+ " FROM ordini"
				+ " JOIN ordini_dettaglio o ON ordini.id=o.ordini_id"
				+ " JOIN users u ON ordini.user_id=u.id"
				+ " WHERE farmacia_id =?";
		List<PrenotazioniDTO> lista= new ArrayList();
		try(Connection connection= ds.getConnection();
				PreparedStatement preparedStatement= connection.prepareStatement(selectSQL)){
			preparedStatement.setInt(1, farmacia_id);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				int idOrdine = rs.getInt("ordine_id");
				PrenotazioniDTO ordineEsistente = null;
	            for (PrenotazioniDTO o : lista) {
	                if (o.getOrdineId() == idOrdine) {
	                    ordineEsistente = o;
	                    break;
	                }
	            }
	            if(ordineEsistente==null) {
	            	ordineEsistente = new PrenotazioniDTO();
	            	ordineEsistente.setDataAcquisto(rs.getTimestamp("data_acquisto").toLocalDateTime());
	            	ordineEsistente.setOrdineId(idOrdine);
	            	ordineEsistente.setNomeUtente(rs.getString("nome_utente"));
	            	ordineEsistente.setTotale(rs.getBigDecimal("totale"));
	            	lista.add(ordineEsistente);
	            }
	            OrdiniDettaglioBean ordineDettaglio = new OrdiniDettaglioBean();
	            ordineDettaglio.setNomeProdotto(rs.getString("nome_prodotto"));
	            ordineDettaglio.setQuantitaProdotto(rs.getInt("quantita_prodotto"));
	            ordineDettaglio.setPrezzoProdotto(rs.getBigDecimal("prezzo_prodotto"));
	            ordineEsistente.getListaOrdini().add(ordineDettaglio);
			}
		}
		return lista;
	}
}
