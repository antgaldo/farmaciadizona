package dao.interfaceDao;
import java.sql.SQLException;
import model.ProdottiBean;
import java.util.List;

public interface ProdottiDao {
	public int doSave(ProdottiBean prodotti) throws SQLException;
	public ProdottiBean getProdotto(ProdottiBean prodotti) throws SQLException;
	public List<ProdottiBean> getAll() throws SQLException;
}
