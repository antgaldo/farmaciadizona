package control.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import dao.FarmacieDaoImp;
import model.dto.FarmaciaProdottoDTO;
import dao.ProdottiDaoImp;
import model.dto.ProdottoDettaglioDTO;

/**
 * Servlet implementation class Home
 */
@WebServlet("/app/*")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private FarmacieDaoImp farmacieDao;
    private ProdottiDaoImp prodottoDao;
    
	 @Override
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
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String farmaco= request.getParameter("farmaco");
		int cap= Integer.parseInt(request.getParameter("cap"));
		try {
			List<FarmaciaProdottoDTO> result= farmacieDao.getFarmaciePerProdottoECap(cap,farmaco);
			ProdottoDettaglioDTO prodotto= prodottoDao.getProdottoDTO(farmaco);
			request.setAttribute("listaFarmacie", result);
			request.setAttribute("farmaco",prodotto);
			request.getRequestDispatcher("/searchfarmaco").forward(request, response);
		} catch (SQLException e) {
	        throw new ServletException(e);
	    }		
	}

}
