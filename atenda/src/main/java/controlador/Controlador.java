package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Produto;
import service.impl.ProdutoServiceImpl;
import service.ProdutoCriteria;
import service.ProdutoService;

import java.io.IOException;
import java.util.List;

import dao.Results;
import exceptions.DataException;

/**
 * Servlet implementation class Controlador
 */
public class Controlador extends HttpServlet {
	  private static final long serialVersionUID = 1L;
	    private ProdutoService produtoService = new ProdutoServiceImpl();

	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public Controlador() {
	        super();
	    }

	    /**
	     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	     */
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        HttpSession session = request.getSession(false);	        	
	        if (session != null && session.getAttribute("rol") != null) {
	            String rol = (String) session.getAttribute("rol"); 
	            session.setAttribute(rol, "ANON");
	            if ("ADMIN".equals(rol)) {
	                response.sendRedirect("/admin");
	                return;
	            }
	        }
	        int pageSize = 6;
	        int currentPage = 1;
	        String pageParam = request.getParameter("page");
	        if (pageParam != null && !pageParam.isEmpty()) {
	            currentPage = Integer.parseInt(pageParam);
	            if (currentPage < 1) {
	                currentPage = 1;
	            }
	        }
	        try {
	            ProdutoCriteria criteria = new ProdutoCriteria(null, null, null, null, null);
	            Results<Produto> produtosPaginados = produtoService.findBy(criteria, currentPage, pageSize);
	            request.setAttribute("produtosPaginados", produtosPaginados.getPage());
	            request.setAttribute("currentPage", currentPage);
	            request.setAttribute("totalPages", produtosPaginados.getTotal());
	        } catch (NumberFormatException | DataException e) {
	            e.printStackTrace();
	            response.sendRedirect(request.getContextPath() + "/WEB-INF/error.jsp");
	            return;
	        }
	        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
	    }
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doPost(request, response);
	    }
}