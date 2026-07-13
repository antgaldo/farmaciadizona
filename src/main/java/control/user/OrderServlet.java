package control.user;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dto.ElementoCarrelloDTO;
import util.SelectorUtil;
import java.util.ArrayList;
import java.util.List;
import java.math.RoundingMode;

import java.util.stream.Collectors;

import dao.OrdiniDaoImp;
import dao.MagazzinoDaoImp;
import model.MagazzinoBean;
import model.OrdiniBean;
import model.OrdiniDettaglioBean;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.sql.DataSource;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/order")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MagazzinoDaoImp magazzinoDao;
	private OrdiniDaoImp ordineDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig servletConfig) throws ServletException{
    	super.init(servletConfig);
    	DataSource ds= (DataSource) getServletContext().getAttribute("DataSource");
    	if(ds == null) {
    		throw new ServletException("DataSource non disponibile nel contesto");
    	}
    	magazzinoDao=new MagazzinoDaoImp(ds);
    	ordineDao= new OrdiniDaoImp(ds);
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
		// TODO Auto-generated method stub
		String actionorder= request.getParameter("inFarmacia");
		int id_user= (int) request.getSession().getAttribute("userid");
		List<ElementoCarrelloDTO> carrello = (List<ElementoCarrelloDTO>) request.getSession().getAttribute("cart");
		//Isoliamo gli id unici delle farmacie nel carrello
		List<Integer> idFarmacieUniche = carrello.stream()
			    .map(ElementoCarrelloDTO::getIdFarmacia)
			    .distinct()
			    .collect(Collectors.toList());
		for(Integer idFarmacia: idFarmacieUniche) {
			//filtriamo i prodotti in base agli idFarmacie unici
			List<ElementoCarrelloDTO> prodottiDiQuestaFarmacia = SelectorUtil.filtra(carrello, u -> u.getIdFarmacia() == idFarmacia);
			if(actionorder !=null) {
				OrdiniBean ordine = new OrdiniBean();
				ordine.setIdUser(id_user);
				ordine.setIdFarmacia(idFarmacia);
				ordine.setDataAcquisto(LocalDateTime.now());
				BigDecimal totaleOrdineFarmacia = BigDecimal.ZERO;
				List<OrdiniDettaglioBean> ordineDettaglioLista= new ArrayList<>();
				for(ElementoCarrelloDTO farmacocart: prodottiDiQuestaFarmacia) {
					try {
						//verifichiamo la consistenza dei dati
						MagazzinoBean prodottoMagazzino= magazzinoDao.getProdottoFarmacia(idFarmacia, farmacocart.getIdProdotto());
						BigDecimal prezzoInMagazzino = prodottoMagazzino.getPrezzo();
						//è gia il totale del prodotto
						BigDecimal prezzoNelCarrello = farmacocart.getPrezzo();
						BigDecimal prezzoUnitarioCarrello = prezzoNelCarrello.divide(BigDecimal.valueOf(farmacocart.getQuantita()), 2, RoundingMode.HALF_UP);
						if(farmacocart.getQuantita()<=prodottoMagazzino.getQuantita() 
								&& prezzoInMagazzino.compareTo(prezzoUnitarioCarrello) == 0) {
							BigDecimal quantita = BigDecimal.valueOf(farmacocart.getQuantita());
		                    BigDecimal totalePrezzoFarmaco = prezzoUnitarioCarrello.multiply(quantita);
							totaleOrdineFarmacia = totaleOrdineFarmacia.add(totalePrezzoFarmaco);
							//creiamo un ordine dettaglio per ogni prodotto del carrello
							OrdiniDettaglioBean ordiniDettaglio= new OrdiniDettaglioBean();
							ordiniDettaglio.setNomeProdotto(farmacocart.getNome());
							ordiniDettaglio.setPrezzoProdotto(prodottoMagazzino.getPrezzo());
							ordiniDettaglio.setQuantitaProdotto(farmacocart.getQuantita());
							ordiniDettaglio.setProdottoId(farmacocart.getIdProdotto());
							ordineDettaglioLista.add(ordiniDettaglio);
						}
					} catch(SQLException e) {
						throw new ServletException(e);
					}
				}
				if (!ordineDettaglioLista.isEmpty()) {
		            ordine.setTotale(totaleOrdineFarmacia);
		            try {
		                ordineDao.doSave(ordine, ordineDettaglioLista);
		            } catch (SQLException e) {
		                throw new ServletException(e);
		            }
		        }
			}
		}
	    request.getSession().removeAttribute("cart");
	    response.sendRedirect("confermaOrdine.jsp");
	}

}
