package dao.interfaceDao.viewInterfaceDao;

import model.viewbean.VendeDettaglioBean;
import java.util.List;
import java.sql.SQLException;

public interface VendeDettaglioDao {
	public List<VendeDettaglioBean> getProdottiFarmacia(int idfarmacia) throws SQLException;
}
