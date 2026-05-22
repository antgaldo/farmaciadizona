package dao.interfaceDao.viewDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import dao.interfaceDao.viewInterfaceDao.VendeDettaglioDao;
import model.VendeBean;
import model.viewbean.VendeDettaglioBean;

public class VendeDettaglioDaoImp implements VendeDettaglioDao {
	private DataSource ds;
	public VendeDettaglioDaoImp(DataSource ds) {
		this.ds=ds;
	}
	@Override
	public List<VendeDettaglioBean> getProdottiFarmacia(int idfarmacia) throws SQLException{
		String selectSQL= "SELECT v.farmacia_id,v.prodotto_id,v.prezzo,v.quantita_disponibile,p.nome,p.descrizione FROM vende v JOIN farmacie f ON v.farmacia_id=f.id JOIN prodotti p ON v.prodotto_id = p.id WHERE f.id=?";
		List<VendeDettaglioBean> vende= new ArrayList<VendeDettaglioBean>();
		try(Connection connection = ds.getConnection();
				PreparedStatement preparedStatement= connection.prepareStatement(selectSQL)){
				preparedStatement.setInt(1, idfarmacia);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()) {
					VendeDettaglioBean v= new VendeDettaglioBean();
					v.setIdProdotto(rs.getInt("prodotto_id"));
					v.setNomeProdotto(rs.getString("p.nome"));
					v.setDescrizione(rs.getString("descrizione"));
					v.setPrezzo(rs.getInt("prezzo"));
					v.setQuantita(rs.getInt("quantita_disponibile"));
					vende.add(v);
				};
		}
		return vende;
	}
	
	@Override
	public int getCountProdotti(int idfarmacia) throws SQLException{
		String getSQL="SELECT COUNT(prodotto_id) AS totale FROM vende WHERE farmacia_id=?";
		try(Connection connection = ds.getConnection();PreparedStatement preparedStatement=connection.prepareStatement(getSQL)){
			preparedStatement.setInt(1, idfarmacia);
			ResultSet rs= preparedStatement.executeQuery();
			if(rs.next()) {
				return rs.getInt("total");
			}
		}
		return 0;
	}
}
