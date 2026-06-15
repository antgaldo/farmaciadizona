package dao.interfaceDao;

import java.sql.SQLException;
import model.ImgBean;

public interface ImgDao {
	public void doSave(ImgBean img) throws SQLException;
}
