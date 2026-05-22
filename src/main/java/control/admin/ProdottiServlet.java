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
import java.util.List;

import dao.UsersDaoImp;
import model.ProdottiBean;
import dao.interfaceDao.ProdottiDao;
import dao.ProdottiDaoImp;
import model.VendeBean;
import dao.interfaceDao.VendeDao;
import dao.VendeDaoImp;
import dao.interfaceDao.viewInterfaceDao.VendeDettaglioDao;
import dao.interfaceDao.viewDao.VendeDettaglioDaoImp;
import model.viewbean.VendeDettaglioBean;

/**
 * Servlet implementation class AdminDashboard
 */
@WebServlet("/admin/prodotti")
public class ProdottiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProdottiDao prodottiDao;
	private VendeDao vendeDao;
	private VendeDettaglioDao vendeDettaglioDao;
	 @Override
	    public void init(ServletConfig servletConfig) throws ServletException{
	    	super.init(servletConfig);
	    	DataSource ds= (DataSource) getServletContext().getAttribute("DataSource");
	    	if(ds == null) {
	    		throw new ServletException("DataSource non disponibile nel contesto");
	    	}
	    	prodottiDao= new ProdottiDaoImp(ds);
	    	vendeDao=new VendeDaoImp(ds);
	    	vendeDettaglioDao= new VendeDettaglioDaoImp(ds);
	    }
	 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProdottiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			List<VendeDettaglioBean> prodotti = vendeDettaglioDao.getProdottiFarmacia((Integer) request.getSession().getAttribute("idFarmacia"));
			request.setAttribute("prodotti", prodotti);
			request.setAttribute("contentPage","/WEB-INF/views/admin/prodotti.jsp");
		    request.setAttribute("title", "Programmi");
	        request.getRequestDispatcher(
	                "/WEB-INF/views/admin/homapanel.jsp"
	            ).forward(request, response);
		} catch(SQLException e) {
			throw new ServletException(e);
		}
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
			 		ProdottiBean prodotto= insertProdotto(request);
			 	    request.getSession().setAttribute("success", "Prodotto inserito");
			 	    response.sendRedirect(request.getContextPath() + "/admin/prodotti");
			 	return;
			 	case "deletevendeprodotto":
			 		deleteVendeProdotto(request,Integer.parseInt(request.getParameter("idProdotto")));
			 		response.sendRedirect(request.getContextPath() + "/admin/prodotti");
			 	return;
			 	/*case "searchprodotto":
			 		request.setAttribute("prodottoTrovato", searchProdotto(request));
			 	break;*/
	            default:
	                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
	                return;
			 }
		     //doGet(request, response);
		    } catch (SQLException e) {
		      throw new ServletException(e);
		    }
	}
	
	private void deleteVendeProdotto(HttpServletRequest request, int id) throws SQLException{
		Integer idProdotto = id;
		Integer idFarmacia= (Integer)request.getSession().getAttribute("idFarmacia");
		if(idProdotto != null && idFarmacia != null) {
			vendeDao.delete(idFarmacia,idProdotto);
		}
	}
	
	private ProdottiBean insertProdotto(HttpServletRequest request) throws SQLException{
		String nome= request.getParameter("nome");
		String descrizione= request.getParameter("descrizione");
		ProdottiBean prodotto= new ProdottiBean();
		VendeBean vende= new VendeBean();
		int prezzo = Integer.parseInt(request.getParameter("prezzo"));
		int quantita = Integer.parseInt(request.getParameter("quantita"));
		
		vende.setFarmaciaId((Integer)request.getSession().getAttribute("idFarmacia"));
		vende.setPrezzo(prezzo);
		vende.setQuantita(quantita);
		prodotto.setNome(nome);
		prodotto.setDescrizione(descrizione);
		//verifica se il prodotto è gia inserito
		ProdottiBean existprodotto= prodottiDao.getProdotto(prodotto);
		if(existprodotto!=null) {
			//se inserito prendi l'id
			vende.setProdottoId(existprodotto.getId());
		} else {
			//altrimenti aggiugilo e prendi l'id
			vende.setProdottoId(prodottiDao.doSave(prodotto));
		}
		vendeDao.doSave(vende);
		return prodotto;
	}
	
	/*private ProdottiBean searchProdotto(HttpServletRequest request) throws SQLException{
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
	}*/

}
