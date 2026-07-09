package control.json;

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
import dao.FarmacieDaoImp;
import dao.ProdottiDaoImp;
import dao.interfaceDao.ProdottiDao;


/**
 * Servlet implementation class CercaFarmacoJson
 */
@WebServlet("/CercaFarmacoJson")
public class CercaFarmacoJson extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ProdottiDao prodottiDao;

	@Override
    public void init(ServletConfig servletConfig) throws ServletException{
    	super.init(servletConfig);
    	DataSource ds= (DataSource) getServletContext().getAttribute("DataSource");
    	if(ds == null) {
    		throw new ServletException("DataSource non disponibile nel contesto");
    	}
    	prodottiDao= new ProdottiDaoImp(ds);
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CercaFarmacoJson() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nomeprodotto = request.getParameter("q");
    	if(nomeprodotto!=null) {
    		try {
    			List<String> result= prodottiDao.getNameProdotto(nomeprodotto);
    			response.setContentType("application/json");
    			response.setCharacterEncoding("UTF-8");
    			String json = "[\"" + String.join("\",\"", result) + "\"]";
    			response.getWriter().write(json); 
    		} catch (SQLException e) {
    	        throw new ServletException(e);
    	    }	
    	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
