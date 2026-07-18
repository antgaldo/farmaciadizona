package control.user;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dto.ElementoCarrelloDTO;
import model.dto.PrenotazioniDTO;
import util.SelectorUtil;
import java.util.ArrayList;
import java.util.List;
import java.math.RoundingMode;

import java.util.stream.Collectors;

import dao.OrdiniDaoImp;
import dao.PrenotazioniDaoImp;
import dao.MagazzinoDaoImp;
import model.MagazzinoBean;
import model.OrdiniBean;
import model.OrdiniDettaglioBean;
import model.PagamentiBean;
import model.SpedizioniBean;
import dao.PagamentiDaoImp;
import dao.SpedizioniDaoImp;

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
	private PrenotazioniDaoImp prenotazioniDao;
	private PagamentiDaoImp pagamentiDao;
	private SpedizioniDaoImp spedizioniDao;
       
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
    	prenotazioniDao= new PrenotazioniDaoImp(ds);
    	pagamentiDao= new PagamentiDaoImp(ds);
    	spedizioniDao = new SpedizioniDaoImp(ds);

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int userid= (int)request.getSession().getAttribute("userid");
		try {
			List<PrenotazioniDTO> ordini=  prenotazioniDao.getPrenotazioniByUser(userid);
			request.setAttribute("ordini", ordini);
		} catch(SQLException e) {
			throw new ServletException(e);
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/user/ordini.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String actionpayment = request.getParameter("formSpedizione");

		int id_user = (int) request.getSession().getAttribute("userid");

		List<Integer> listaordini = saveOrdine(request, id_user);

		if ("formSpedizione".equals(actionpayment) && !listaordini.isEmpty()) {
		    for (Integer ordineId : listaordini) {
		        savePayment(request, ordineId);
		        saveSpedizione(request, ordineId);
		    }
		    request.getSession().removeAttribute("cart");
		}

		request.getSession().removeAttribute("cart");
		response.sendRedirect("confermaOrdine.jsp");
	}
	
	private void savePayment(HttpServletRequest request,int ordine_id) throws ServletException, IOException {
		PagamentiBean pagamento= new PagamentiBean();
		String circuito= request.getParameter("circuito");
		String numero= request.getParameter("carta");
		String scadenza= request.getParameter("scadenza");
		pagamento.setCircuito(circuito);
		pagamento.setNumero(numero);
		pagamento.setScadenza(scadenza);
		pagamento.setOrdineId(ordine_id);
		try {
			pagamentiDao.doSave(pagamento);
		} catch(SQLException e) {
			throw new ServletException(e);
		}
		
	}
	private void saveSpedizione(HttpServletRequest request,int ordine_id) throws ServletException, IOException {
		SpedizioniBean spedizione= new SpedizioniBean();
		String indirizzo= request.getParameter("indirizzo");
		String nome= request.getParameter("nome");
		String cognome= request.getParameter("cognome");
		String cap= request.getParameter("cap");
		String citta= request.getParameter("citta");
		spedizione.setOrdineId(ordine_id);
		spedizione.setCap(cap);
		spedizione.setIndirizzo(indirizzo);
		spedizione.setCitta(citta);
		spedizione.setCognome(cognome);
		spedizione.setNome(nome);
		try {
			spedizioniDao.doSave(spedizione);
		} catch(SQLException e) {
			throw new ServletException(e);
		}
	}
	private List<Integer> saveOrdine(HttpServletRequest request, int user_id) throws ServletException, IOException {
		List<ElementoCarrelloDTO> carrello = (List<ElementoCarrelloDTO>) request.getSession().getAttribute("cart");
		//Isoliamo gli id unici delle farmacie nel carrello
		List<Integer> idFarmacieUniche = carrello.stream()
			    .map(ElementoCarrelloDTO::getIdFarmacia)
			    .distinct()
			    .collect(Collectors.toList());
		List<Integer> lista= new ArrayList<>();
		for(Integer idFarmacia: idFarmacieUniche) {
			//filtriamo i prodotti in base agli idFarmacie unici
			List<ElementoCarrelloDTO> prodottiDiQuestaFarmacia = SelectorUtil.filtra(carrello, u -> u.getIdFarmacia() == idFarmacia);
				OrdiniBean ordine = new OrdiniBean();
				ordine.setIdUser(user_id);
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
		               lista.add(ordineDao.doSave(ordine, ordineDettaglioLista));
		            } catch (SQLException e) {
		                throw new ServletException(e);
		            }
		        }
			}
		return lista;
	}

}
