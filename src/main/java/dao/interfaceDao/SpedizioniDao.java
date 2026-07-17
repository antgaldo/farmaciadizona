package dao.interfaceDao;
import model.SpedizioniBean;
import java.sql.SQLException;

public interface SpedizioniDao {
	public void doSave(SpedizioniBean spedizioni) throws SQLException;
}
