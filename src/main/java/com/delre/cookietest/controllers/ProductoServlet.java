package com.delre.cookietest.controllers;

import com.delre.cookietest.models.Producto;
import com.delre.cookietest.services.ProductoService;
import com.delre.cookietest.services.ProductoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/productos")
public class ProductoServlet extends HttpServlet {

	ProductoService productoService = new ProductoServiceImpl();
	
	List<Producto> productos = productoService.listar();
	
	LoginServlet loginServlet = new LoginServlet();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
	
		this.loginServlet.deleteCookies(req, resp);
		
		if (req.getCookies() != null) {
			for (Cookie cookie : req.getCookies()) {
				if (cookie.getName().equals("username")) {
					resp.setContentType("text/html");
					PrintWriter out = resp.getWriter();
					out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\"\n" +
							"          rel=\"stylesheet\" integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\"\n" +
							"          crossorigin=\"anonymous\">");

					out.println("</head>");
					out.println("<html><body>");
					out.println("<h1 class=\"text-center text-primary\"> TABLA DE PRODUCTOS </h1>");
					out.println("<table class=\"table\">");
					out.println("<tr class=\"table-primary\">");
					out.println("<th scope=\"col\">Nombre</th>");
					out.println("<th scope=\"col\">Precio</th>");
					out.println("</tr>");
					for (Producto producto : productos) {
						out.println("<tr class=\"table-primary\">");
						out.println("<td class=\"table-primary\">" + producto.getNombre() + "</td>");
						out.println("<td class=\"table-primary\">" + producto.getPrecio() + "</td>");
						out.println("</tr>");
					}
					out.println("</table>");
                    out.println("<br>\n" +
                            "    <ul>\n" +
                            "        <a href=\"/cookie-test/\">Regresar</a>\n" +
                            "    </ul>\n" +
                            "    </div>");
					out.println("</body></html>");
					break;
				}
			}
		} else {
			getServletContext().getRequestDispatcher("/index.html").forward(req, resp);
		}
	}
}
