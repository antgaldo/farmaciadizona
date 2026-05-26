package dao.interfaceDao;

import java.sql.SQLException;
import model.dto.FarmaciaProdottoDTO;
import model.FarmacieBean;

public interface FarmacieDao {
	public int doSave(FarmacieBean farmacie) throws SQLException;
	public FarmaciaProdottoDTO getFarmaciePerProdottoECap(int farmacia_cap,String prodotto_nome) throws SQLException;
}
