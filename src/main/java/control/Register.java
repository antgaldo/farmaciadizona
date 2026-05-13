package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;
import javax.sql.DataSource;
import dao.UsersDao;
import dao.UsersDaoImp;
import model.UsersBean;
import util.PasswordUtil;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UsersDao usersDao;
    
    @Override
    public void init(ServletConfig servletConfig) throws ServletException{
    	super.init(servletConfig);
    	DataSource ds= (DataSource) getServletContext().getAttribute("DataSource");
    	if(ds == null) {
    		throw new ServletException("DataSource non disponibile nel contesto");
    	}
    	usersDao= new UsersDaoImp(ds);
    }
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/Register.jsp");
		dispatcher.forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 try {
		      insertUser(request);
		    } catch (SQLException e) {
		        throw new ServletException(e);
		    }
	}
	
	private void insertUser(HttpServletRequest request) throws SQLException{
		String nome= request.getParameter("nome");
		String password= request.getParameter("password");
		String ruolo= request.getParameter("ruolo");
		UsersBean user= new UsersBean();
		PasswordUtil encryptpass= new PasswordUtil();
		user.setNome(nome);
		user.setPassword(encryptpass.toDigest(password));
		user.setRuolo(ruolo);
		user.setActive(user.getRuolo().equals("USER") ? 1:0);
		usersDao.doSave(user);
	}

}
