package control.admin;
import java.math.BigDecimal;

import jakarta.servlet.RequestDispatcher;
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
import jakarta.servlet.http.Part;
import java.io.File;
import jakarta.servlet.annotation.MultipartConfig;

import dao.UsersDaoImp;
import model.ProdottiBean;
import dao.interfaceDao.ProdottiDao;
import dao.ProdottiDaoImp;
import model.MagazzinoBean;
import dao.interfaceDao.MagazzinoDao;
import dao.MagazzinoDaoImp;
import model.dto.MagazzinoDettaglioDTO;
import dao.interfaceDao.ImgDao;
import dao.ImgDaoImp;
import model.ImgBean;
import util.UploadPath;

/**
 * Servlet implementation class AdminDashboard
 */
@WebServlet("/admin/prodotti")
@MultipartConfig(maxFileSize = 5 * 1024 * 1024, // max 5 MB
	maxRequestSize = 10 * 1024 * 1024, // max 10 MB
	fileSizeThreshold = 2* 1024 * 1024) // 2 MB
public class ProdottiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProdottiDao prodottiDao;
	private MagazzinoDao magazzinoDao;
	private ImgDao imgDao;
	 @Override
	    public void init(ServletConfig servletConfig) throws ServletException{
	    	super.init(servletConfig);
	    	DataSource ds= (DataSource) getServletContext().getAttribute("DataSource");
	    	if(ds == null) {
	    		throw new ServletException("DataSource non disponibile nel contesto");
	    	}
	    	prodottiDao= new ProdottiDaoImp(ds);
	    	magazzinoDao=new MagazzinoDaoImp(ds);
	    	imgDao= new ImgDaoImp(ds);
			// Crea la cartella uploads
			String uploadPath = System.getenv("UPLOAD_PATH");
			//System.out.println(uploadPath);
			if (uploadPath == null || uploadPath.isBlank()) {
			    throw new ServletException("Variabile ambiente UPLOAD_PATH non configurata");
			}
	    }
	 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProdottiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			List<MagazzinoDettaglioDTO> prodotti = magazzinoDao.getProdottiFarmacia((Integer) request.getSession().getAttribute("idFarmacia"));
			request.setAttribute("prodotti", prodotti);
			request.setAttribute("contentPage","/WEB-INF/views/admin/prodotti.jsp");
		    request.setAttribute("title", "Programmi");
	        request.getRequestDispatcher(
	                "/WEB-INF/views/admin/homapanel.jsp"
	            ).forward(request, response);
		} catch(SQLException e) {
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action= request.getParameter("action");
		 try {
			 switch(action) {
			 	case "addprodotto":
			 		insertProdotto(request);
			 	    request.getSession().setAttribute("success", "Prodotto inserito");
			 	    response.sendRedirect(request.getContextPath() + "/admin/prodotti");
			 	    return;
			 	case "editprodotto":
			 		editProdotto(request);
			 	    request.getSession().setAttribute("success", "Prodotto modificato");
			 	    response.sendRedirect(request.getContextPath() + "/admin/prodotti");
			 	    return;
			 	case "deletevendeprodotto":
			 		deleteVendeProdotto(request,Integer.parseInt(request.getParameter("idProdotto")));
			 		response.sendRedirect(request.getContextPath() + "/admin/prodotti");
			 	return;
			 	/*case "searchprodotto":
			 		request.setAttribute("prodottoTrovato", searchProdotto(request));
			 	break;*/
	            default:
	                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
	                return;
			 }
		     //doGet(request, response);
		    } catch (SQLException e) {
		      throw new ServletException(e);
		    }
	}
	
	private void deleteVendeProdotto(HttpServletRequest request, int id) throws SQLException{
		Integer idProdotto = id;
		Integer idFarmacia= (Integer)request.getSession().getAttribute("idFarmacia");
		if(idProdotto != null && idFarmacia != null) {
			magazzinoDao.delete(idFarmacia,idProdotto);
		}
	}
	
	private void insertProdotto(HttpServletRequest request) throws SQLException{
		ProdottiBean prodotto= new ProdottiBean();
		String nome= request.getParameter("nome");
		String descrizione= request.getParameter("descrizione");
		String categoria= request.getParameter("categoria");
		prodotto.setNome(nome);
		prodotto.setDescrizione(descrizione);
		prodotto.setCategoria(categoria);
		//verifica se il prodotto è gia inserito
		ProdottiBean existprodotto= prodottiDao.getProdotto(prodotto.getNome());
		int prodotto_id;
		if(existprodotto!=null) {
			//se inserito prendi l'id
			prodotto_id= existprodotto.getId();
		} else {
			//altrimenti aggiugilo e prendi l'id
			prodotto_id = prodottiDao.doSave(prodotto);
		}
		//crea e aggiungi magazzino
		MagazzinoBean magazzino= insertMagazzino(request,prodotto_id);
		magazzinoDao.doSave(magazzino);
		//crea e aggiungi img
		try {
			ImgBean img = insertImg(request,prodotto_id);
			imgDao.doSave(img);
		} catch(ServletException s) {
			System.err.println("Error:" + s.getMessage());
		} catch(IOException i) {
			System.err.println("Error:" + i.getMessage());
		}
		
	}
	private void editProdotto(HttpServletRequest request) throws SQLException{
		ProdottiBean prodotto= new ProdottiBean();
		String descrizione= request.getParameter("descrizione");
		int prodotto_id=  Integer.parseInt(request.getParameter("id"));
		prodotto.setDescrizione(descrizione);
		prodotto.setId(prodotto_id);
		prodottiDao.doEdit(prodotto);
		MagazzinoBean magazzino= editMagazzino(request,prodotto_id);
		magazzinoDao.doEdit(magazzino);
	}
	private MagazzinoBean insertMagazzino(HttpServletRequest request,int prodotto_id){
		MagazzinoBean magazzino= new MagazzinoBean();
		BigDecimal prezzo = new BigDecimal(request.getParameter("prezzo"));
		int quantita = Integer.parseInt(request.getParameter("quantita"));
		magazzino.setFarmaciaId((Integer)request.getSession().getAttribute("idFarmacia"));
		magazzino.setPrezzo(prezzo);
		magazzino.setQuantita(quantita);
		magazzino.setActive(true);
		magazzino.setProdottoId(prodotto_id);
		return magazzino;
	}
	private MagazzinoBean editMagazzino(HttpServletRequest request,int prodotto_id){
		MagazzinoBean magazzino= new MagazzinoBean();
		BigDecimal prezzo = new BigDecimal(request.getParameter("prezzo"));
		int quantita = Integer.parseInt(request.getParameter("quantita"));
		magazzino.setFarmaciaId((Integer)request.getSession().getAttribute("idFarmacia"));
		magazzino.setPrezzo(prezzo);
		magazzino.setQuantita(quantita);
		magazzino.setProdottoId(prodotto_id);
		return magazzino;
	}
	private ImgBean insertImg(HttpServletRequest request,int prodotto_id) throws SQLException, ServletException, IOException{
		ImgBean img= new ImgBean();
		Part part = request.getPart("img");
		UploadPath uploadPathService= new UploadPath();
		if(part !=null) {
			String originalFileName = part.getSubmittedFileName();
			if (originalFileName != null && !originalFileName.isEmpty() && part.getSize() > 0) {
				String mimeType = part.getContentType();
				String uniqueFileName = uploadPathService.buildUniqueFileName(part);
				String uploadDir = System.getenv("UPLOAD_PATH");
				String uploadPath = uploadDir + File.separator + uniqueFileName;
				img.setMimeType(mimeType);
				img.setPath(uniqueFileName);
				img.setProdottoId(prodotto_id);
				part.write(uploadPath);
			}
		}
		return img;
	}
	
	/*private ProdottiBean searchProdotto(HttpServletRequest request) throws SQLException{
		String nome= request.getParameter("nome");
		ProdottiBean prodotto= new ProdottiBean();
		prodotto.setNome(nome);
		if(isExistProdotto(prodotto)) {
			ProdottiBean p= prodottiDao.getProdotto(prodotto);
			return p;
		} else {
			request.setAttribute("error","Prodotto non trovato");
		}
		return null;
	}*/

}
