package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class ControladorLogin
 */
public class ControladorLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			doPost(request, response);
		}
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			String destino = "/WEB-INF/index.jsp";
			String login = request.getParameter("login");
			String password = request.getParameter("password");

			if (login != null && password != null) {
				if (login.equals("admin") && password.equals("abc123.")) {
					request.getSession().setAttribute("user", "admin");
					request.getSession().setAttribute("rol", "ADMIN");
					destino = "/admin";
				}
				if (login.equals("user") && password.equals("abc123.")) {
					request.getSession().setAttribute("user", "user");
					request.getSession().setAttribute("rol", "BASIC");
					destino = "/atenda";
				}
			}else {
				request.getSession().setAttribute("user", "anonymous");
				request.getSession().setAttribute("rol", "ANON");
				destino = "/atenda";
			}
			request.getRequestDispatcher(destino).forward(request, response);
		}
	}