package dao;

import java.sql.Connection;
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
	public FarmaciaProdottoDTO getFarmaciePerProdottoECap(int farmacia_cap,String prodotto_nome) throws SQLException{
		String selectSQL = "SELECT  farmacie.nome AS farmacia_nome, farmacie.cap, vende.prezzo,vende.quantita_disponibile,prodotti.nome AS prodotto_nome FROM farmacie "
				+ "JOIN vende ON farmacie.id=vende.farmacia_id "
				+ "JOIN prodotti ON prodotti.id=vende.prodotto_id "
				+ "WHERE prodotti.nome=? AND farmacie.cap=?";
		try(Connection connection = ds.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)){
			preparedStatement.setString(1, prodotto_nome);
			preparedStatement.setInt(2, farmacia_cap);
			preparedStatement.executeQuery();
			ResultSet rs= preparedStatement.getResultSet();
			if(rs.next()) {
				FarmaciaProdottoDTO result= new FarmaciaProdottoDTO();
				result.setCap(rs.getInt("cap"));
				result.setPrezzo(rs.getInt("prezzo"));
				result.setQuantita(rs.getInt("quantita_disponibile"));
				result.setProdottoNome(rs.getString("prodotto_nome"));
				result.setFarmaciaNome(rs.getString("farmacia_nome"));
				return result;
			}
		}
		return null;
	}
}
