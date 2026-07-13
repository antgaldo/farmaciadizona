package dao.interfaceDao;
import model.OrdiniDettaglioBean;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OrdiniDettaglioDao {
	public void doSave(int ordine_id,List<OrdiniDettaglioBean> ordiniDettaglioBean,Connection connection) throws SQLException;
}
