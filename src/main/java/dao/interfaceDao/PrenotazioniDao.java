package dao.interfaceDao;

import model.dto.PrenotazioniDTO;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public interface PrenotazioniDao {
	public List<PrenotazioniDTO> getPrenotazioni(int farmacia_id) throws SQLException;
	public List<PrenotazioniDTO> getPrenotazioniByUser(int user_id) throws SQLException;
	public int getCountPrenotazioni(int farmacia_id) throws SQLException;
}
