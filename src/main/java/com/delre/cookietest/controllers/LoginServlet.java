package com.delre.cookietest.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final String USERNAME = "admin";
	private static final String PASSWORD = "123456";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		//get cookies
		this.deleteCookies(req, resp);
		try {
			Cookie[] cookies = req.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("username")) {
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
						out.println("<h1>Hola " + cookie.getValue() + ", ya has iniciado sesión</h1>");
						out.println(" <form action=\"/cookie-test/\"><button type=\"submit\" class=\"btn btn-primary\">Ir a WEB-APP</button></form>");
						out.println("</body></html>");
						break;
					}
				}
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
			Cookie cookie = new Cookie("username", username);
			cookie.setMaxAge(60 * 60 * 24);
			resp.setContentType("text/html");
			resp.addCookie(cookie);
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
			/*resp.sendRedirect("login");*/
			getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
		}
	}

	public void deleteCookies(HttpServletRequest req, HttpServletResponse resp) {
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if(!cookie.getName().equals("username")) {
					cookie.setMaxAge(0);
					resp.addCookie(cookie);
				}
			}
		}
	}
}
