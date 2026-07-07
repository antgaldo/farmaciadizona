package dao.interfaceDao;

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
	public int getPrezzo(int idFarmacia, int idProdotto) throws SQLException;
}
