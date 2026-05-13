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
public class AuthFilter extends HttpFilter{
	private static final long serialVersionUID= 1L;
	
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
		throws IOException, ServletException {
		    String path = request.getRequestURI().substring(request.getContextPath().length());
		    // Se l'URL non è protetto, lascia passare
		    if (!path.startsWith("/admin/") && !path.startsWith("/common/")) {
		    		chain.doFilter(request, response);
		        return; // Per evitare che il codice che segue venga eseguito
		    }
		    // Controllo che il token sia in sessione
		    HttpSession session = request.getSession(false);
		    String role = (session != null) ? (String) session.getAttribute("role") : null;
		    // Controllo autenticazione e autorizzazione
		    boolean autorizzato = false;
		    if (role.equals("ADMIN")) {
		    		if (path.startsWith("/admin/")) {
		            autorizzato = role.equalsIgnoreCase(role.trim());
		        } else if (path.startsWith("/common/")) {
		            autorizzato = role.equals("ADMIN") || role.equals("USER");
		        }
		    }
		    if (autorizzato) {
		        chain.doFilter(request, response);
		    } else {
		        response.sendRedirect(request.getContextPath() + "/index");
		    }
	}
}
