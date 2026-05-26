package control.user;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import dao.GestisceDaoImp;
import dao.UsersDaoImp;
import dao.FarmacieDaoImp;
import model.dto.FarmaciaProdottoDTO;

/**
 * Servlet implementation class SearchFarmaco
 */
@WebServlet("/searchfarmaco")
public class SearchFarmaco extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchFarmaco() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<FarmaciaProdottoDTO> lista = (List<FarmaciaProdottoDTO>) request.getAttribute("listaFarmacie");
		request.setAttribute("lista", lista);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/user/searchfarmaco.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
