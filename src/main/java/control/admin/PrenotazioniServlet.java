package control.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dto.PrenotazioniDTO;

import java.io.IOException;
import dao.PrenotazioniDaoImp;
import dao.interfaceDao.PrenotazioniDao;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

/**
 * Servlet implementation class PrenotazioniServlet
 */
@WebServlet("/admin/prenotazioni")
public class PrenotazioniServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrenotazioniDao prenotazioniDao;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrenotazioniServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    public void init(ServletConfig servletConfig) throws ServletException{
    	super.init(servletConfig);
    	DataSource ds= (DataSource) getServletContext().getAttribute("DataSource");
    	if(ds == null) {
    		throw new ServletException("DataSource non disponibile nel contesto");
    	}
    	prenotazioniDao= new PrenotazioniDaoImp(ds);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int idFarmacia= (int) request.getSession().getAttribute("idFarmacia");
		try{
			List<PrenotazioniDTO> lista= prenotazioniDao.getPrenotazioni(idFarmacia);
			request.setAttribute("prenotazioni", lista);
		} catch(SQLException e) {
			throw new ServletException(e);
		}
		request.setAttribute("contentPage","/WEB-INF/views/admin/prenotazioni.jsp");
        request.getRequestDispatcher(
                "/WEB-INF/views/admin/homapanel.jsp"
            ).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
