package cl.litscl.ejemplocrudswingappmodel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cl.litscl.ejemplocrudswingappmodel.dto.Categoria;
import cl.litscl.ejemplocrudswingappmodel.util.BDUtil;

public class CategoriaDAO {
	private BDUtil bdUtil = new BDUtil();
	
	public boolean save(Categoria c) {
		boolean resultado;
		try {
			//1. Conectarse a la base de datos.
			bdUtil.conectar();
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			//2. Definir la sentencia SQL (INSERT).
			String sql = "INSERT INTO categoria(nombre) VALUES(?)"; //Los ID Autoincrementales no van aca, ya que el dbms asigna su valor.
			//3. Preparar la sentencia SQL.
			Connection conexion = bdUtil.getConexion();
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setString(1, c.getNombre());
			//4. Ejecutar la sentencia SQL.
			ps.executeUpdate();
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecución del SQL: " + resultado);
			//5. Desconectarse.
		} finally { 
			bdUtil.desconectar(); //Envía la petición de desconexión al dbms.
		}
		return resultado;
	}

	public List<Categoria> getAll() {
		boolean resultado;
		List<Categoria> categorias = new ArrayList<Categoria>(); //La lista ya no debe estar arriba estatica ya que el contenido va a variar.
		try {
			//1. Conectarse a la base de datos.
			bdUtil.conectar();
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			//2. Definir la sentencia SQL (SELECT).
			String sql = "SELECT * FROM categoria";
			//3. Preparar la sentencia SQL.
			Connection conexion = bdUtil.getConexion();
			PreparedStatement ps = conexion.prepareStatement(sql);
			//4. Ejecutar la sentencia SQL.
			ResultSet rs = ps.executeQuery();
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);
			while (rs.next()) { //Se repite mientras avance el puntero.
				Categoria c = new Categoria();
				c.setId(rs.getInt(1));
				c.setNombre(rs.getString(2));
		
				categorias.add(c); //Añade la categoria a la lista.
			}
			rs.close(); //Se cierra el puntero.
		} catch (Exception ex) {
			categorias = null;
			//5. Desconectarse.
		} finally { 
			bdUtil.desconectar(); //Envía la petición de desconexión al dbms.
		}
		return categorias;
	}
	
	public Categoria find(int id) {
		boolean resultado;
		Categoria c = new Categoria();
		try {
			//1. Conectarse a la base de datos.
			bdUtil.conectar();
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			//2. Definir la sentencia SQL (SELECT).
			String sql = "SELECT * FROM categoria WHERE id = ?";
			//3. Preparar la sentencia SQL.
			Connection conexion = bdUtil.getConexion();
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setInt(1, id);
			//4. Ejecutar la sentencia SQL.
			ResultSet rs = ps.executeQuery();
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);
			while (rs.next()) { //Se repite mientras avance el puntero.
				c.setId(rs.getInt(1));
				c.setNombre(rs.getString(2));
			}
			rs.close(); //Se cierra el puntero.
		} catch (Exception ex) {
			return null;
			//5. Desconectarse.
		} finally { 
			bdUtil.desconectar(); //Envía la petición de desconexión al dbms.
		}
		return c;
	}
}
