package control.user;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/cartservlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VendeDao vendeDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
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
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
	    
	    try {
	    	int checkprezzo= vendeDao.getPrezzo(Integer.parseInt(idFarmacia),Integer.parseInt(idProdotto));
	    	 if(checkprezzo==Integer.parseInt(prezzo)) {
	 		    
	 		    List<ElementoCarrelloDTO> carrello = (List<ElementoCarrelloDTO>) request.getSession().getAttribute("carrello");
	 		    
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
		 		    ElementoCarrelloDTO nuovoProdotto = new ElementoCarrelloDTO(Integer.parseInt(idProdotto),Integer.parseInt(idFarmacia), nome, Integer.parseInt(quantita), prezzoTot1);
		 		    carrello.add(nuovoProdotto);
	 		   }
	 		    
	 		    request.getSession().setAttribute("carrello", carrello);
	 			
	 			response.setContentType("application/json");
	 			response.setCharacterEncoding("UTF-8");
	 			response.getWriter().write(convertiCarrelloInJson(carrello));
	 	    } 
	    }catch(SQLException e) {
			throw new ServletException(e);
		}
	}
	
	private String convertiCarrelloInJson(List<ElementoCarrelloDTO> carrello) {
	    if (carrello == null || carrello.isEmpty()) {
	        return "[]";
	    }

	    StringBuilder json = new StringBuilder();
	    json.append("[");

	    for (int i = 0; i < carrello.size(); i++) {
	        ElementoCarrelloDTO item = carrello.get(i);
	        String nome = item.getNome().replace("\"", "\\\"");

	        json.append("{");
	        json.append("\"idProdotto\":\"").append(item.getIdProdotto()).append("\",");
	        json.append("\"nome\":\"").append(nome).append("\",");
	        json.append("\"quantita\":").append(item.getQuantita()).append(",");
	        json.append("\"prezzoTot\":").append(item.getPrezzo());
	        json.append("}");

	        if (i < carrello.size() - 1) {
	            json.append(",");
	        }
	    }

	    json.append("]"); 
	    return json.toString();
	}

}
