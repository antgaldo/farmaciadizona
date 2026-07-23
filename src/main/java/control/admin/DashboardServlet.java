package control.admin;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import dao.interfaceDao.MagazzinoDao;
import dao.ProdottiDaoImp;
import dao.MagazzinoDaoImp;
import dao.interfaceDao.PrenotazioniDao;
import dao.PrenotazioniDaoImp;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/admin/dashboard")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MagazzinoDao magazzinoDao;
	private PrenotazioniDao prenotazioniDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardServlet() {
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
	    	magazzinoDao=new MagazzinoDaoImp(ds);
	    	prenotazioniDao= new PrenotazioniDaoImp(ds);
	    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int idFarmacia= (Integer) request.getSession().getAttribute("idFarmacia");
		if(idFarmacia != 0) {
			try {
				int countProdotti = magazzinoDao.getCountProdotti(idFarmacia);
				int countPrenotazioni = prenotazioniDao.getCountPrenotazioni(idFarmacia);
				request.setAttribute("nprodotti", countProdotti);
				request.setAttribute("nprenotazioni", countPrenotazioni);
			} catch (SQLException e) {
		        throw new ServletException(e);
		    }
		}
		//getCountProdotti
    	request.setAttribute("contentPage","/WEB-INF/views/admin/dashboard.jsp");
	    request.setAttribute("title", "Programmi");
	    request.setAttribute("page", "dashboard");
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
