package dao.interfaceDao;
import model.GestisceBean;
import java.sql.SQLException;

public interface GestisceDao {
	public void doSave(GestisceBean gestisce) throws SQLException;
}
