package dao;

import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import dao.interfaceDao.FarmacieDao;
import model.FarmacieBean;
import model.dto.FarmaciaProdottoDTO;

import javax.sql.DataSource;
import java.sql.ResultSet;

public class FarmacieDaoImp implements FarmacieDao{
	
	private DataSource ds;
	public FarmacieDaoImp(DataSource ds) {
		this.ds=ds;
	}
	
	@Override
	public int doSave(FarmacieBean farmacie) throws SQLException{
		String insertSql= "INSERT INTO farmacie (cap,active,nome,indirizzo,lat,lon) VALUES(?,?,?,?,?,?)";
		try(Connection connection = ds.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(insertSql,Statement.RETURN_GENERATED_KEYS)){
				preparedStatement.setInt(1, farmacie.getCap());
				preparedStatement.setBoolean(2, farmacie.getActive());
				preparedStatement.setString(3, farmacie.getNome());
				preparedStatement.setString(4, farmacie.getIndirizzo());
				preparedStatement.setString(5, farmacie.getLat());
				preparedStatement.setString(6, farmacie.getLon());
				preparedStatement.executeUpdate();
				try (ResultSet rs= preparedStatement.getGeneratedKeys()){
		            if(rs.next()) {
		                return rs.getInt(1);
		            }
		        }
			}
		return 0;
	}
	
	@Override
	public List<FarmaciaProdottoDTO> getFarmaciePerProdottoECap(int farmacia_cap,String prodotto_nome) throws SQLException{
		String selectSQL = "SELECT farmacie.nome AS farmacia_nome,farmacie.indirizzo,farmacie.lat,"
				+ "farmacie.lon, farmacie.cap,magazzino.farmacia_id, magazzino.prezzo,magazzino.quantita_disponibile,"
				+ "prodotti.nome AS prodotto_nome "
				+ "FROM farmacie "
				+ "JOIN magazzino ON farmacie.id=magazzino.farmacia_id "
				+ "JOIN prodotti ON prodotti.id=magazzino.prodotto_id "
				+ "WHERE prodotti.nome=? AND farmacie.cap=?";
		List<FarmaciaProdottoDTO> resultlist= new ArrayList<>();
		try(Connection connection = ds.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)){
			preparedStatement.setString(1, prodotto_nome);
			preparedStatement.setInt(2, farmacia_cap);
			preparedStatement.executeQuery();
			ResultSet rs= preparedStatement.getResultSet();
			while(rs.next()) {
				FarmaciaProdottoDTO result= new FarmaciaProdottoDTO();
				result.setCap(rs.getInt("cap"));
				result.setPrezzo(rs.getBigDecimal("prezzo"));
				result.setQuantita(rs.getInt("quantita_disponibile"));
				result.setIdFarmacia(rs.getInt("farmacia_id"));
				result.setProdottoNome(rs.getString("prodotto_nome"));
				result.setFarmaciaNome(rs.getString("farmacia_nome"));
				result.setIndirizzo(rs.getString("indirizzo"));
				result.setLat(rs.getString("lat"));
				result.setLon(rs.getString("lon"));
				resultlist.add(result);
			}
		}
		return resultlist;
	}
}
