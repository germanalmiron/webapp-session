package com.delre.webappsession.controllers;

import com.delre.webappsession.services.LoginService;
import com.delre.webappsession.services.LoginServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        LoginService auth = new LoginServiceImpl();
        Optional<String> username = auth.getUsername(req);

        if (username.isPresent()){
            HttpSession session = req.getSession();
            session.invalidate(); //elimina la session

        }
        resp.sendRedirect(req.getContextPath()+  "/login.jsp");
    }
}
