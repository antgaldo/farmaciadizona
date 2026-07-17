package filter;
import java.io.IOException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpSession;
@WebFilter("/*")
public class AuthFilter extends HttpFilter {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

			String path = request.getRequestURI().substring(request.getContextPath().length());
		    
			boolean isStaticResource = path.startsWith("/css/") 
					|| path.startsWith("/js/") 
					|| path.startsWith("/img/");

		    //redirect alla dashboard
		    if (path.equals("/admin")) {
		    	response.sendRedirect(request.getContextPath() + "/admin/dashboard");
		    	return;
		    }

		    // lascia passare se non protetto
		    if ((!path.startsWith("/admin") && !path.startsWith("/common/")) || isStaticResource) {
		    	chain.doFilter(request, response);
		        return; 
		    }

		    HttpSession session = request.getSession(false);

		    String role = (session != null) ? (String) session.getAttribute("role") : null;
		    Integer userid = (session != null) ? (Integer) session.getAttribute("userid") : null;
		    
		    boolean autorizzato = false;

		    if (role != null && userid != null) {

		        if (path.startsWith("/admin")) {
		            if ("ADMIN".equals(role)) {
		                autorizzato = true;
		            }

		        } else if (path.startsWith("/common/")) {
		            if ("ADMIN".equals(role) || "USER".equals(role)) {
		                autorizzato = true;
		            }
		        }
		    }

		    if (autorizzato) {
		        chain.doFilter(request, response);
		    } else {
		        response.sendRedirect(request.getContextPath() + "/app");
		    }
	}
}