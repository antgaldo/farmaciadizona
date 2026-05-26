package control.json;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Servlet implementation class CercaIndirizzoJson
 */
@WebServlet("/CercaIndirizzoJson")
public class CercaIndirizzoJson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CercaIndirizzoJson() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	String q = request.getParameter("q");
    	if(q != null) {
    		String urlStr = "https://nominatim.openstreetmap.org/search?q="+ URLEncoder.encode(q, "UTF-8") + "&format=json&limit=5&addressdetails=1";
    		HttpURLConnection conn = (HttpURLConnection) new URL(urlStr).openConnection();
    		conn.setRequestMethod("GET");
    		conn.setRequestProperty("User-Agent", "farmaciadizona");
    		conn.setRequestProperty("From", "antonio@awwviaggi.it");
    		String res = new String(conn.getInputStream().readAllBytes());
    		response.setContentType("application/json");
    		response.setCharacterEncoding("UTF-8");
    		response.getWriter().write(res);    	
    	}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
