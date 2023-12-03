package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class ControladorLogout
 */
public class ControladorLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doPost(request, response);
		}
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			String destino = "/atenda";
			System.out.println("Logout controller::");
			HttpSession session = request.getSession();
			if (session != null) {
				session.removeAttribute("user");
				session.removeAttribute("rol");
			}
			request.getRequestDispatcher(destino).forward(request, response);
		}
	}
