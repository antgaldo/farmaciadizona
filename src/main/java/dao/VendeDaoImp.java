package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import javax.sql.DataSource;

import dao.interfaceDao.VendeDao;
import model.dto.VendeDettaglioDTO;
import model.FarmacieBean;
import model.ProdottiBean;
import model.VendeBean;
import model.dto.VendeDettaglioDTO;

public class VendeDaoImp implements VendeDao {
	
	private DataSource ds;
	public VendeDaoImp(DataSource ds) {
		this.ds=ds;
	}
	
	@Override
	public void doSave(VendeBean vendebean) throws SQLException{
		String insertsql= "INSERT INTO vende (farmacia_id,prodotto_id,prezzo,quantita_disponibile,active) values(?,?,?,?,?)";
		try(Connection connection = ds.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(insertsql)){
				preparedStatement.setInt(1, vendebean.getFarmaciaId());
				preparedStatement.setInt(2, vendebean.getProdottoId());
				preparedStatement.setDouble(3, vendebean.getPrezzo());
				preparedStatement.setInt(4, vendebean.getQuantita());
				preparedStatement.setBoolean(5, vendebean.getActive());
				preparedStatement.executeUpdate();
			}
	}
	
	@Override
	public void delete(int idFarmacia, int idProdotto) throws SQLException{
		String deletesql= "DELETE FROM vende WHERE farmacia_id=? AND prodotto_id=?";
		try(Connection connection = ds.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(deletesql)){
				preparedStatement.setInt(1, idFarmacia);
				preparedStatement.setInt(2, idProdotto);
				preparedStatement.executeUpdate();
		}
	}
	@Override
	public List<VendeDettaglioDTO> getProdottiFarmacia(int idfarmacia) throws SQLException{
		String selectSQL= "SELECT v.farmacia_id,v.prodotto_id,v.prezzo,v.quantita_disponibile,p.nome,p.descrizione,p.categoria,i.path "
				+ "FROM vende v "
				+ "JOIN farmacie f ON v.farmacia_id=f.id "
				+ "JOIN prodotti p ON v.prodotto_id = p.id "
				+ "JOIN img i ON v.prodotto_id=i.prodotto_id "
				+ "WHERE f.id=?";
		List<VendeDettaglioDTO> vende= new ArrayList<VendeDettaglioDTO>();
		try(Connection connection = ds.getConnection();
				PreparedStatement preparedStatement= connection.prepareStatement(selectSQL)){
				preparedStatement.setInt(1, idfarmacia);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()) {
					VendeDettaglioDTO v= new VendeDettaglioDTO();
					v.setIdProdotto(rs.getInt("prodotto_id"));
					v.setNomeProdotto(rs.getString("p.nome"));
					v.setDescrizione(rs.getString("descrizione"));
					v.setPrezzo(rs.getInt("prezzo"));
					v.setQuantita(rs.getInt("quantita_disponibile"));
					v.setCategoria(rs.getString("categoria"));
					v.setPathImg(rs.getString("path"));
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
				return rs.getInt("totale");
			}
		}
		return 0;
	}
}
