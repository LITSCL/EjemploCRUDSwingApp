package cl.litscl.ejemplocrudswingappmodel.fk;

import cl.litscl.ejemplocrudswingappmodel.dao.CategoriaDAO;
import cl.litscl.ejemplocrudswingappmodel.dto.Categoria;
import cl.litscl.ejemplocrudswingappmodel.dto.Producto;

public class ProductoFK {
	private CategoriaDAO daoCategoria = new CategoriaDAO();
	private Categoria c = new Categoria();
	
	public int getCategoriaId(Producto p) {
		c = daoCategoria.find(p.getCategoriaFK());	
		return c.getId();
	}
	
	public String getCategoriaNombre(Producto p) {
		c = daoCategoria.find(p.getCategoriaFK());	
		return c.getNombre();
	}
}
