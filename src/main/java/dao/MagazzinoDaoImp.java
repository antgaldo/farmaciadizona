package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import javax.sql.DataSource;

import dao.interfaceDao.MagazzinoDao;
import model.dto.MagazzinoDettaglioDTO;
import model.FarmacieBean;
import model.ProdottiBean;
import model.MagazzinoBean;

public class MagazzinoDaoImp implements MagazzinoDao {
	
	private DataSource ds;
	public MagazzinoDaoImp(DataSource ds) {
		this.ds=ds;
	}
	
	@Override
	public void doSave(MagazzinoBean magazzinobean) throws SQLException{
		String insertsql= "INSERT INTO magazzino (farmacia_id,prodotto_id,prezzo,quantita_disponibile,active) values(?,?,?,?,?)";
		try(Connection connection = ds.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(insertsql)){
				preparedStatement.setInt(1, magazzinobean.getFarmaciaId());
				preparedStatement.setInt(2, magazzinobean.getProdottoId());
				preparedStatement.setBigDecimal(3, magazzinobean.getPrezzo());
				preparedStatement.setInt(4, magazzinobean.getQuantita());
				preparedStatement.setBoolean(5, magazzinobean.getActive());
				preparedStatement.executeUpdate();
			}
	}
	
	@Override
	public void delete(int idFarmacia, int idProdotto) throws SQLException{
		String deletesql= "DELETE FROM magazzino WHERE farmacia_id=? AND prodotto_id=?";
		try(Connection connection = ds.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(deletesql)){
				preparedStatement.setInt(1, idFarmacia);
				preparedStatement.setInt(2, idProdotto);
				preparedStatement.executeUpdate();
		}
	}
	@Override
	public List<MagazzinoDettaglioDTO> getProdottiFarmacia(int idfarmacia) throws SQLException{
		String selectSQL= "SELECT v.farmacia_id,v.prodotto_id,v.prezzo,v.quantita_disponibile,p.nome,p.descrizione,p.categoria,i.path "
				+ "FROM magazzino v "
				+ "JOIN farmacie f ON v.farmacia_id=f.id "
				+ "JOIN prodotti p ON v.prodotto_id = p.id "
				+ "JOIN img i ON v.prodotto_id=i.prodotto_id "
				+ "WHERE f.id=?";
		List<MagazzinoDettaglioDTO> magazzino= new ArrayList<MagazzinoDettaglioDTO>();
		try(Connection connection = ds.getConnection();
				PreparedStatement preparedStatement= connection.prepareStatement(selectSQL)){
				preparedStatement.setInt(1, idfarmacia);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()) {
					MagazzinoDettaglioDTO v= new MagazzinoDettaglioDTO();
					v.setIdProdotto(rs.getInt("prodotto_id"));
					v.setNomeProdotto(rs.getString("p.nome"));
					v.setDescrizione(rs.getString("descrizione"));
					v.setPrezzo(rs.getBigDecimal("prezzo"));
					v.setQuantita(rs.getInt("quantita_disponibile"));
					v.setCategoria(rs.getString("categoria"));
					v.setPathImg(rs.getString("path"));
					magazzino.add(v);
				};
		}
		return magazzino;
	}
	
	@Override
	public MagazzinoBean getProdottoFarmacia(int idFarmacia, int idProdotto) throws SQLException{
		String selectSQL="SELECT * FROM magazzino WHERE farmacia_id=? AND prodotto_id=?";
		try(Connection connection= ds.getConnection();
				PreparedStatement preparedStatement=connection.prepareStatement(selectSQL)){
			preparedStatement.setInt(1, idFarmacia);
			preparedStatement.setInt(2, idProdotto);
			ResultSet rs= preparedStatement.executeQuery();
			while(rs.next()) {
				MagazzinoBean magazzino= new MagazzinoBean();
				magazzino.setPrezzo(rs.getBigDecimal("prezzo"));
				magazzino.setQuantita(rs.getInt("quantita_disponibile"));
				return magazzino;
			}
		}
		return null;
	}
	
	@Override
	public int getCountProdotti(int idfarmacia) throws SQLException{
		String getSQL="SELECT COUNT(prodotto_id) AS totale FROM magazzino WHERE farmacia_id=?";
		try(Connection connection = ds.getConnection();PreparedStatement preparedStatement=connection.prepareStatement(getSQL)){
			preparedStatement.setInt(1, idfarmacia);
			ResultSet rs= preparedStatement.executeQuery();
			if(rs.next()) {
				return rs.getInt("totale");
			}
		}
		return 0;
	}
	@Override
	public double getPrezzo(int idFarmacia, int idProdotto) throws SQLException{
		String getSQL= "SELECT prezzo FROM magazzino WHERE farmacia_id=? AND prodotto_id=?";
		try(Connection connection= ds.getConnection();
				PreparedStatement preparedStatement= connection.prepareStatement(getSQL)){
			preparedStatement.setInt(1, idFarmacia);
			preparedStatement.setInt(2, idProdotto);
			ResultSet rs= preparedStatement.executeQuery();
			if(rs.next()) {
				return rs.getDouble("prezzo");
			}
		}
		return 0;
	}
}
