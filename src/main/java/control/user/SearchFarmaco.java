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
import dao.ProdottiDaoImp;
import dao.UsersDaoImp;
import dao.FarmacieDaoImp;
import model.dto.FarmaciaProdottoDTO;
import model.dto.ProdottoDettaglioDTO;

/**
 * Servlet implementation class SearchFarmaco
 */
@WebServlet("/searchfarmaco")
public class SearchFarmaco extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private FarmacieDaoImp farmacieDao;
    private ProdottiDaoImp prodottoDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchFarmaco() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig servletConfig) throws ServletException{
    	super.init(servletConfig);
    	DataSource ds= (DataSource) getServletContext().getAttribute("DataSource");
    	if(ds == null) {
    		throw new ServletException("DataSource non disponibile nel contesto");
    	}
    	farmacieDao= new FarmacieDaoImp(ds);
    	prodottoDao= new ProdottiDaoImp(ds);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String farmaco= request.getParameter("farmaco");
		int cap= Integer.parseInt(request.getParameter("cap"));
		if(farmaco!=null && cap!=0) {
			try {
				List<FarmaciaProdottoDTO> lista= farmacieDao.getFarmaciePerProdottoECap(cap,farmaco);
				ProdottoDettaglioDTO prodotto= prodottoDao.getProdottoDTO(farmaco);
				request.setAttribute("lista", lista);
				request.setAttribute("prodotto", prodotto);
			} catch(SQLException e) {
				System.out.println(e);
			}
		}

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
