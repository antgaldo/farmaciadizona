package control.user;
import java.math.BigDecimal;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dto.ElementoCarrelloDTO;

import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class CheckoutServlet
 */
@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/user/checkout.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//da fare tutti i controlli
		List<ElementoCarrelloDTO> carrello = (List<ElementoCarrelloDTO>) request.getSession().getAttribute("cart");
		BigDecimal totale=BigDecimal.ZERO;
		int quantitaTotale=0;
		for(ElementoCarrelloDTO farmaco : carrello) {
			totale= totale.add(farmaco.getPrezzo());
			quantitaTotale+= farmaco.getQuantita();
		}
		request.getSession().setAttribute("targetURL", "checkout");
		request.getSession().setAttribute("totale", totale);
		request.getSession().setAttribute("quantitaTotale", quantitaTotale);
		doGet(request, response);
	}

}
