package com.delre.webappsession.services;

import com.delre.webappsession.models.Producto;

import java.util.List;

public class ProductoServiceImpl implements ProductoService {
	
	@Override
	public List<Producto> listar() {
		return List.of(
				new Producto("Producto 1", 100),
				new Producto("Producto 2", 200),
				new Producto("Producto 3", 300),
				new Producto("Producto 4", 400),
				new Producto("Producto 5", 500)
		);
	}
}
