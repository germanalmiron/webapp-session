package com.delre.webappsession.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public class LoginServiceImpl implements LoginService {

	@Override
	public Optional<String> getUsername (HttpServletRequest req){

			HttpSession session = req.getSession();
			String username = (String) session.getAttribute("username");

			if(username != null){
				return Optional.of(username); // convertimos a un Optional y lo devolvemos
			}
			return Optional.empty(); //devolvemos un Optional Vacio
	}
}

