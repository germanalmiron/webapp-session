package com.delre.webappsession.controllers;

import com.delre.webappsession.services.LoginService;
import com.delre.webappsession.services.LoginServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final String USERNAME = "admin";
	private static final String PASSWORD = "123456";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		LoginService autentSession = new LoginServiceImpl();
		
		Optional<String> usernameOptional = autentSession.getUsername(req);
		
		try {
			if (usernameOptional.isPresent()) {
						resp.setContentType("text/html");
						PrintWriter out = resp.getWriter();
						out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\"\n" +
								"          rel=\"stylesheet\" integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\"\n" +
								"          crossorigin=\"anonymous\">");
						out.println("<html><body>");
						out.println("<div> <nav class=\"navbar navbar-dark bg-dark\">\n" +
								"        <div class=\"container-fluid\">\n" +
								"    <span class=\"navbar-text\" style=\"color: white\">\n" +
								"      BIENVENIDOS AL SISTEMA\n" +
								"    </span>\n" +
								"        </div>\n" +
								"    </nav></div>");
                        out.println("<br>");
						out.println("<h1>Hola "+usernameOptional.get()+", ya has iniciado sesión</h1>");
						out.println("<br>");
						out.println(" <form action=\"/webapp-session/\"><button type=\"submit\" class=\"btn btn-primary\">Ir a WEB-APP SESSION</button></form>");
						out.println("</body></html>");
			} else {
				getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		List<String> errores = new ArrayList<>();

		if (username.equals(USERNAME) && password.equals(PASSWORD)) {
			HttpSession session = req.getSession();
			session.setAttribute("username", username);
			
			
			resp.setContentType("text/html");
			resp.sendRedirect("productos");
		} else {
			if (!username.equals(USERNAME)) {
				errores.add("<div class=\"alert alert-danger\" role=\"alert\">\n" +
						"                    Usuario incorrecto\n" +
						"    </div>");
				}
			if(!password.equals(PASSWORD)){
				errores.add("<div class=\"alert alert-danger\" role=\"alert\">\n" +
						"                    Password incorrecto\n" +
						"    </div>");
			}
			req.setAttribute("errores",errores);
			//resp.sendRedirect("login");
			getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
		}
	}
}
