package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.UsersBean;
import util.PasswordUtil;
import dao.interfaceDao.UsersDao;
import dao.UsersDaoImp;
import dao.GestisceDaoImp;
import dao.interfaceDao.GestisceDao;
import model.UsersBean;
import java.io.IOException;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsersDao usersDao;
	private GestisceDao gestisceDao;
       
	 @Override
    public void init(ServletConfig servletConfig) throws ServletException{
    	super.init(servletConfig);
    	DataSource ds= (DataSource) getServletContext().getAttribute("DataSource");
    	if(ds == null) {
    		throw new ServletException("DataSource non disponibile nel contesto");
    	}
    	usersDao= new UsersDaoImp(ds);
    	gestisceDao= new GestisceDaoImp(ds);
    }
	 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp");
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 try {
			 UsersBean user= checkUser(request);
			 if(user != null) {
				 if(user.getRuolo().equals("ADMIN")) {
					 response.sendRedirect(request.getContextPath() +"/admin/dashboard");
				 }
				 if(user.getRuolo().equals("USER")) {
					 HttpSession session = request.getSession(false);
					 if ("checkout".equals(session.getAttribute("targetURL"))) {
						    response.sendRedirect(request.getContextPath() + "/checkout");
						} else {
						    response.sendRedirect(request.getContextPath() + "/app");
						}
				 }
			 }
			 else {
				    request.setAttribute("errore", "Email o password errati");
				    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
				    dispatcher.forward(request, response);
				}
	    } catch (SQLException e) {
	        throw new ServletException(e);
	    }
	}
	
	private UsersBean checkUser(HttpServletRequest request) throws SQLException{
		String email= request.getParameter("email");
		String password= request.getParameter("password");
		UsersBean user= new UsersBean();
		PasswordUtil encryptpass= new PasswordUtil();
		user.setEmail(email);
		user.setPassword(encryptpass.toDigest(password));
		UsersBean usercheck=usersDao.login(user);
		if(usercheck !=null) {
			request.getSession().setAttribute("role", usercheck.getRuolo());
			request.getSession().setAttribute("userid", usercheck.getId());
			request.getSession().setAttribute("usernome", usercheck.getNome());
			if(usercheck.getRuolo().equals("ADMIN")) {
				int idFarmacia= gestisceDao.getGestisce(usercheck);
				request.getSession().setAttribute("idFarmacia", idFarmacia);
			}
		}
		return usercheck;
	}

}
