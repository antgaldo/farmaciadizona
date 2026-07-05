package control.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dto.ElementoCarrelloDTO;
import util.ConvertCartJson;

import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class DeleteCartServlet
 */
@WebServlet("/removefromcartservlet")
public class RemoveFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveFromCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		int idProdotto = Integer.parseInt(request.getParameter("idProdotto"));
		List<ElementoCarrelloDTO> carrello = (List<ElementoCarrelloDTO>) session.getAttribute("cart");
		carrello.removeIf(f -> f.getIdProdotto() == idProdotto);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(ConvertCartJson.convertCartJson(carrello));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
