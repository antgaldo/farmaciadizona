package dao.interfaceDao;
import java.sql.SQLException;
import model.ProdottiBean;

public interface ProdottiDao {
	public int doSave(ProdottiBean prodotti) throws SQLException;
	public boolean checkProdotto(ProdottiBean prodotti) throws SQLException;
	public ProdottiBean getProdotto(ProdottiBean prodotti) throws SQLException;
}
