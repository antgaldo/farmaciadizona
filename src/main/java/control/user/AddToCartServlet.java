package control.user;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;


import model.dto.ElementoCarrelloDTO;
import java.util.List;

import javax.sql.DataSource;
import java.util.ArrayList;

import dao.ImgDaoImp;
import dao.ProdottiDaoImp;
import dao.VendeDaoImp;
import dao.interfaceDao.VendeDao;
import dao.VendeDaoImp;
import dao.interfaceDao.ImgDao;
import util.ConvertCartJson;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/addcartservlet")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VendeDao vendeDao;
	private ImgDao imgDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCartServlet() {
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
    	vendeDao=new VendeDaoImp(ds);
    	imgDao=new ImgDaoImp(ds);
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

	    List<ElementoCarrelloDTO> carrello = (List<ElementoCarrelloDTO>) session.getAttribute("cart");

	    if (carrello == null) {
	        carrello = new ArrayList<>();
	    }

	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(ConvertCartJson.convertCartJson(carrello));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idProdotto = request.getParameter("idProdotto");
	    String nome = request.getParameter("nome");
	    String quantita = request.getParameter("quantita");
	    String prezzo = request.getParameter("prezzo");
	    String idFarmacia = request.getParameter("idFarmacia");
	    
	    HttpSession session = request.getSession();
	    
	    try {
	    	int checkprezzo= vendeDao.getPrezzo(Integer.parseInt(idFarmacia),Integer.parseInt(idProdotto));
	    	 if(checkprezzo==Integer.parseInt(prezzo)) {
	 	    	String pathImage= imgDao.getImageFromIdProdotto(Integer.parseInt(idProdotto));
	 		    List<ElementoCarrelloDTO> carrello = (List<ElementoCarrelloDTO>) request.getSession().getAttribute("cart");
	 		    
	 		    if(carrello == null) {
	 		    	carrello= new ArrayList<>();
	 		    }
	 		  
	 		  boolean prodottoGiaPresente = false;
	 		   for (ElementoCarrelloDTO item : carrello) {
	 			   if(item.getIdProdotto()== Integer.parseInt(idProdotto) && item.getIdFarmacia()== Integer.parseInt(idFarmacia)) {
	 				   prodottoGiaPresente= true;
	 				   item.setQuantita(Integer.parseInt(quantita));
	 				   double prezzoTot= Double.parseDouble(prezzo) * Integer.parseInt(quantita);
	 				   item.setPrezzo(prezzoTot);
	 				   break;
	 			   }
	 		   }
	 		   if(!prodottoGiaPresente) {
	 			   	double prezzoTot1= Double.parseDouble(prezzo) * Integer.parseInt(quantita);
		 		    ElementoCarrelloDTO nuovoProdotto = new ElementoCarrelloDTO(Integer.parseInt(idProdotto),Integer.parseInt(idFarmacia), nome, Integer.parseInt(quantita), prezzoTot1,pathImage);
		 		    carrello.add(nuovoProdotto);
	 		   }
	 		    
	 		    session.setAttribute("cart", carrello);
	 			
	 			response.setContentType("application/json");
	 			response.setCharacterEncoding("UTF-8");
	 			response.getWriter().write(ConvertCartJson.convertCartJson(carrello));
	 	    } 
	    }catch(SQLException e) {
			throw new ServletException(e);
		}
	}

}
