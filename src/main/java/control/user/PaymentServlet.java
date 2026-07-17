package control.user;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.PrenotazioniDaoImp;
import model.dto.PrenotazioniDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import model.PagamentiBean;
import model.SpedizioniBean;


import javax.sql.DataSource;

/**
 * Servlet implementation class PaymentServlet
 */
@WebServlet("/payment")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init(ServletConfig servletConfig) throws ServletException{
    	super.init(servletConfig);
    	DataSource ds= (DataSource) getServletContext().getAttribute("DataSource");
    	if(ds == null) {
    		throw new ServletException("DataSource non disponibile nel contesto");
    	}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int iduser= (int) request.getSession().getAttribute("userid");
		if(iduser!=-1) {
			PagamentiBean pagamento= new PagamentiBean();
			String circuito= request.getParameter("circuito");
			int numero= Integer.parseInt(request.getParameter("numero"));
			String scadenza= request.getParameter("scadenza");
			pagamento.setCircuito(circuito);
			pagamento.setNumero(numero);
			pagamento.setScadenza(scadenza);
			
			SpedizioniBean spedizione= new SpedizioniBean();
			String indirizzo= request.getParameter("indirizzo");
			String nome= request.getParameter("nome");
			String cognome= request.getParameter("cognome");
			int cap= Integer.parseInt(request.getParameter("cap"));
			String citta= request.getParameter("citta");
			spedizione.setCap(cap);
			spedizione.setIndirizzo(indirizzo);
			spedizione.setCitta(citta);
			spedizione.setCognome(cognome);
			spedizione.setNome(nome);
			
		}
		doGet(request, response);
	}

}
