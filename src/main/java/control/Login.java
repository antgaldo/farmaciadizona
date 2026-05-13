package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.UsersBean;
import util.PasswordUtil;
import dao.UsersDao;
import dao.UsersDaoImp;
import model.UsersBean;
import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
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
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/Login.jsp");
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 try {
			 if(checkUser(request) != null) {
				 response.sendRedirect(request.getContextPath() +"/admin/dashboard");
			 }
	    } catch (SQLException e) {
	        throw new ServletException(e);
	    }
	}
	
	private UsersBean checkUser(HttpServletRequest request) throws SQLException{
		String nome= request.getParameter("nome");
		String password= request.getParameter("password");
		UsersBean user= new UsersBean();
		PasswordUtil encryptpass= new PasswordUtil();
		user.setNome(nome);
		user.setPassword(encryptpass.toDigest(password));
		UsersBean usercheck=usersDao.login(user);
		if(usercheck !=null) {
			request.getSession().setAttribute("role", usercheck.getRuolo());
		}
		return usercheck;
	}

}
