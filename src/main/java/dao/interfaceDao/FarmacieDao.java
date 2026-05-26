package dao.interfaceDao;

import java.sql.SQLException;
import java.util.List;

import model.dto.FarmaciaProdottoDTO;
import model.FarmacieBean;

public interface FarmacieDao {
	public int doSave(FarmacieBean farmacie) throws SQLException;
	public List<FarmaciaProdottoDTO> getFarmaciePerProdottoECap(int farmacia_cap,String prodotto_nome) throws SQLException;
}
