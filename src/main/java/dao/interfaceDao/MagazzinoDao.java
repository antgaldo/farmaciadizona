package dao.interfaceDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import model.dto.MagazzinoDettaglioDTO;
import model.MagazzinoBean;
import model.FarmacieBean;

public interface MagazzinoDao {
	public void doSave(MagazzinoBean magazzino) throws SQLException;
	public void delete(int idFarmacia, int idProdotto) throws SQLException;
	public List<MagazzinoDettaglioDTO> getProdottiFarmacia(int idfarmacia) throws SQLException;
	public int getCountProdotti(int idfarmacia) throws SQLException;
	public double getPrezzo(int idFarmacia, int idProdotto) throws SQLException;
	public MagazzinoBean getProdottoFarmacia(int idFarmacia, int idProdotto) throws SQLException;
	public void editQuantitaProdotto(int idFarmacia,int idProdotto,int quantita,Connection connection) throws SQLException;
}
