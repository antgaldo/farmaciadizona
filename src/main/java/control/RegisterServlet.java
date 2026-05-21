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
import dao.interfaceDao.UsersDao;
import dao.UsersDaoImp;
import model.ProdottiBean;
import model.UsersBean;
import util.PasswordUtil;
import model.FarmacieBean;
import model.GestisceBean;
import dao.interfaceDao.GestisceDao;
import dao.interfaceDao.FarmacieDao;
import dao.FarmacieDaoImp;
import dao.GestisceDaoImp;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UsersDao usersDao;
    private FarmacieDao farmacieDao;
    private GestisceDao gestisceDao;
    
    @Override
    public void init(ServletConfig servletConfig) throws ServletException{
    	super.init(servletConfig);
    	DataSource ds= (DataSource) getServletContext().getAttribute("DataSource");
    	if(ds == null) {
    		throw new ServletException("DataSource non disponibile nel contesto");
    	}
    	usersDao= new UsersDaoImp(ds);
    	farmacieDao= new FarmacieDaoImp(ds);
    	gestisceDao= new GestisceDaoImp(ds);
    }
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/register.jsp");
		dispatcher.forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action= request.getParameter("action");
		 try {
			 switch(action) {
			 case "registerUser":
				 insertUser(request);
				 break;
			 case "registerAdmin":
				 insertAdmin(request);
				 break;
			 }
			 doGet(request, response);
		    } catch (SQLException e) {
		        throw new ServletException(e);
		    }
	}
	
	private void insertUser(HttpServletRequest request) throws SQLException{
		String nome= request.getParameter("nome");
		String password= request.getParameter("password");
		String email= request.getParameter("email");
		String ruolo= "USER";
		UsersBean user= new UsersBean();
		PasswordUtil encryptpass= new PasswordUtil();
		user.setNome(nome);
		user.setPassword(encryptpass.toDigest(password));
		user.setRuolo(ruolo);
		user.setEmail(email);
		user.setActive(user.getRuolo().equals("USER") ? 1:0);
		if(usersDao.getUser(user) ==null) {
			usersDao.doSave(user);
		} else {
			request.setAttribute("error","Utente gia registrato");
		}
	}
	
	private void insertAdmin(HttpServletRequest request) throws SQLException{
		String nome= request.getParameter("nome");
		String password= request.getParameter("password");
		String email= request.getParameter("email");
		String ruolo= "ADMIN";
		UsersBean user= new UsersBean();
		PasswordUtil encryptpass= new PasswordUtil();
		int cap= Integer.parseInt(request.getParameter("cap"));
		String nomeFarmacia= request.getParameter("nomeFarmacia");
		FarmacieBean farmacia= new FarmacieBean();
		GestisceBean gestisce= new GestisceBean();
		user.setNome(nome);
		user.setPassword(encryptpass.toDigest(password));
		user.setRuolo(ruolo);
		user.setEmail(email);
		user.setActive(0);
		farmacia.setCap(cap);
		farmacia.setActive(false);
		farmacia.setNome(nomeFarmacia);
		if(usersDao.getUser(user)==null) {
			int idFarmacia= farmacieDao.doSave(farmacia);
			if(idFarmacia != 0) {
				int idUser= usersDao.doSave(user);
				gestisce.setFarmacieId(idFarmacia);
				gestisce.setUserId(idUser);
				gestisceDao.doSave(gestisce);
			}
		} else {
			request.setAttribute("error","Utente gia registrato");
		}
	}
}
