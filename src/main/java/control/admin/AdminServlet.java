package control.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import dao.UsersDaoImp;
import model.ProdottiBean;
import dao.interfaceDao.ProdottiDao;
import dao.ProdottiDaoImp;
import model.VendeBean;
import dao.interfaceDao.VendeDao;
import dao.VendeDaoImp;

/**
 * Servlet implementation class AdminDashboard
 */
@WebServlet("/admin/*")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProdottiDao prodottiDao;
	private VendeDao vendeDao;
	 @Override
	    public void init(ServletConfig servletConfig) throws ServletException{
	    	super.init(servletConfig);
	    	DataSource ds= (DataSource) getServletContext().getAttribute("DataSource");
	    	if(ds == null) {
	    		throw new ServletException("DataSource non disponibile nel contesto");
	    	}
	    	prodottiDao= new ProdottiDaoImp(ds);
	    	vendeDao=new VendeDaoImp(ds);
	    }
	 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/admin/Admin.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action= request.getParameter("action");
		 try {
			 switch(action) {
			 	case "addprodotto":
			 		int idProdotto=insertProdotto(request);
			 		insertVende(request,idProdotto);
			 	break;
			 	case "searchprodotto":
			 		request.setAttribute("prodottoTrovato", searchProdotto(request));
			 	break;
	            default:
	                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			 }
		      doGet(request, response);
		    } catch (SQLException e) {
		      throw new ServletException(e);
		    }
	}
	
	private int insertProdotto(HttpServletRequest request) throws SQLException{
		String nome= request.getParameter("nome");
		String descrizione= request.getParameter("descrizione");
		ProdottiBean prodotto= new ProdottiBean();
		prodotto.setNome(nome);
		prodotto.setDescrizione(descrizione);
		if(isExistProdotto(prodotto)) {
			request.setAttribute("error","Prodotto gia inserito");
		} else {
			int id=prodottiDao.doSave(prodotto);
			return id;
		}
		return 0;
	}
	
	private void insertVende(HttpServletRequest request,int idProdotto) throws SQLException{
		int idFarmacia= (int)request.getSession().getAttribute("userid");
		int prezzo=  Integer. parseInt(request.getParameter("prezzo"));
		int quantita=  Integer. parseInt(request.getParameter("quantita"));
		VendeBean vende= new VendeBean();
		vende.setFarmadiaId(idFarmacia);
		vende.setProdottoId(idProdotto);
		vende.setPrezzo(prezzo);
		vende.setQuantita(quantita);
		vendeDao.doSave(vende);
	}
	
	private ProdottiBean searchProdotto(HttpServletRequest request) throws SQLException{
		String nome= request.getParameter("nome");
		ProdottiBean prodotto= new ProdottiBean();
		prodotto.setNome(nome);
		if(isExistProdotto(prodotto)) {
			ProdottiBean p= prodottiDao.getProdotto(prodotto);
			return p;
		} else {
			request.setAttribute("error","Prodotto non trovato");
		}
		return null;
	}
	
	private boolean isExistProdotto(ProdottiBean prodotto) throws SQLException{
		boolean check= prodottiDao.checkProdotto(prodotto);
		return check;
	}

}
