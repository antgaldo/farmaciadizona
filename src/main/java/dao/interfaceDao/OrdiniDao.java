package dao.interfaceDao;

import model.OrdiniBean;
import model.OrdiniDettaglioBean;
import java.sql.SQLException;
import java.util.List;


public interface OrdiniDao {
	public void doSave(OrdiniBean ordine,List<OrdiniDettaglioBean> ordiniDettaglio) throws SQLException;
}
