package cl.litscl.ejemplocrudswingappmodel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cl.litscl.ejemplocrudswingappmodel.dto.Producto;
import cl.litscl.ejemplocrudswingappmodel.util.BDUtil;

public class ProductoDAO {
	private BDUtil bdUtil = new BDUtil();
	
	public boolean save(Producto p) {
		boolean resultado;
		try {
			//1. Conectarse a la base de datos.
			bdUtil.conectar();
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			//2. Definir la sentencia SQL (INSERT).
			String sql = "INSERT INTO producto(nombre, descripcion, precio, stock, imagen, categoria_id) VALUES(?, ?, ?, ?, ?, ?)"; //Los ID Autoincrementales no van aca, ya que el dbms asigna su valor.
			//3. Preparar la sentencia SQL.
			Connection conexion = bdUtil.getConexion();
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setString(1, p.getNombre());
			ps.setString(2, p.getDescripcion());
			ps.setDouble(3, p.getPrecio());
			ps.setInt(4, p.getStock());
			ps.setString(5, p.getImagen());
			ps.setInt(6, p.getCategoriaFK());
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
	
	public List<Producto> getAll() {
		boolean resultado;
		List<Producto> productos = new ArrayList<Producto>(); //La lista ya no debe estar arriba estatica ya que el contenido va a variar.
		try {
			//1. Conectarse a la base de datos.
			bdUtil.conectar();
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			//2. Definir la sentencia SQL (SELECT).
			String sql = "SELECT * FROM producto";
			//3. Preparar la sentencia SQL.
			Connection conexion = bdUtil.getConexion();
			PreparedStatement ps = conexion.prepareStatement(sql);
			//4. Ejecutar la sentencia SQL.
			ResultSet rs = ps.executeQuery();
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);
			while (rs.next()) { //Se repite mientras avance el puntero.
				Producto p = new Producto();
				p.setId(rs.getInt(1));
				p.setNombre(rs.getString(2));
				p.setDescripcion(rs.getString(3));
				p.setPrecio(rs.getDouble(4));
				p.setStock(rs.getInt(5));
				p.setImagen(rs.getString(6));
				p.setCategoriaFK(rs.getInt(7));
		
				productos.add(p); //Añade el producto a la lista.
			}
			rs.close(); //Se cierra el puntero.
		} catch (Exception ex) {
			productos = null;
			//5. Desconectarse.
		} finally { 
			bdUtil.desconectar(); //Envía la petición de desconexión al dbms.
		}
		return productos;
	}
	
	public List<Producto> getRandom(int limite) {
		boolean resultado;
		List<Producto> productos = new ArrayList<Producto>(); //La lista ya no debe estar arriba estatica ya que el contenido va a variar.
		try {
			//1. Conectarse a la base de datos.
			bdUtil.conectar();
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			//2. Definir la sentencia SQL (SELECT).
			String sql = "SELECT * FROM producto ORDER BY RAND() LIMIT ?";
			//3. Preparar la sentencia SQL.
			Connection conexion = bdUtil.getConexion();
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setInt(1, limite);
			//4. Ejecutar la sentencia SQL.
			ResultSet rs = ps.executeQuery();
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);
			while (rs.next()) { //Se repite mientras avance el puntero.
				Producto p = new Producto();
				p.setId(rs.getInt(1));
				p.setNombre(rs.getString(2));
				p.setDescripcion(rs.getString(3));
				p.setPrecio(rs.getDouble(4));
				p.setStock(rs.getInt(5));
				p.setImagen(rs.getString(6));
				p.setCategoriaFK(rs.getInt(7));
		
				productos.add(p); //Añaade el producto a la lista.
			}
			rs.close(); //Se cierra el puntero.
		} catch (Exception ex) {
			productos = null;
			//5. Desconectarse.
		} finally { 
			bdUtil.desconectar(); //Envía la petición de desconexión al dbms.
		}
		return productos;
	}
	
	public Producto find(int id) {
		boolean resultado;
		Producto p = new Producto();
		try {
			//1. Conectarse a la base de datos.
			bdUtil.conectar();
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			//2. Definir la sentencia SQL (SELECT).
			String sql = "SELECT * FROM producto WHERE id = ?";
			//3. Preparar la sentencia SQL.
			Connection conexion = bdUtil.getConexion();
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setInt(1, id);
			//4. Ejecutar la sentencia SQL.
			ResultSet rs = ps.executeQuery();
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);
			while (rs.next()) { //Se repite mientras avance el puntero.
				p.setId(rs.getInt(1));
				p.setNombre(rs.getString(2));
				p.setDescripcion(rs.getString(3));
				p.setPrecio(rs.getDouble(4));
				p.setStock(rs.getInt(5));
				p.setImagen(rs.getString(6));
				p.setCategoriaFK(rs.getInt(7));
			}
			rs.close(); //Se cierra el puntero.
		} catch (Exception ex) {
			return null;
			//5. Desconectarse.
		} finally { 
			bdUtil.desconectar(); //Envía la petición de desconexión al dbms.
		}
		return p;
	}
	
	public List<Producto> findAll(String columna, String valor) {
		boolean resultado;
		List<Producto> productos = new ArrayList<Producto>(); //La lista ya no debe estar arriba estatica ya que el contenido va a variar.
		try {
			//1. Conectarse a la base de datos.
			bdUtil.conectar();
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			//2. Definir la sentencia SQL (SELECT).
			String sql = "SELECT * FROM producto WHERE " + columna + " = " + "?"; //El Statement solo puede entregar valores (El nombre de la columna simplemente se debe concatenar).
			//3. Preparar la sentencia SQL.
			Connection conexion = bdUtil.getConexion();
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setString(1, valor);
			//4. Ejecutar la sentencia SQL.
			ResultSet rs = ps.executeQuery();
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);
			while (rs.next()) { //Se repite mientras avance el puntero.
				Producto p = new Producto();
				p.setId(rs.getInt(1));
				p.setNombre(rs.getString(2));
				p.setDescripcion(rs.getString(3));
				p.setPrecio(rs.getDouble(4));
				p.setStock(rs.getInt(5));
				p.setImagen(rs.getString(6));
				p.setCategoriaFK(rs.getInt(7));
		
				productos.add(p); //Añade el producto a la lista.
			}
			rs.close(); //Se cierra el puntero.
		} catch (Exception ex) {
			productos = null;
			//5. Desconectarse.
		} finally { 
			bdUtil.desconectar(); //Envía la petición de desconexión al dbms.
		}
		return productos;
	}
	
	public boolean update(Producto p) {
		boolean resultado;
		try {
			//1. Conectarse a la base de datos.
			bdUtil.conectar();
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			//2. Definir la sentencia SQL (UPDATE).
			String sql = "UPDATE producto SET nombre = ?, descripcion = ?, precio = ?, stock = ?, imagen = ?, categoria_id = ? WHERE id = ?";
			//3. Preparar la sentencia SQL.
			Connection conexion = bdUtil.getConexion();
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setString(1, p.getNombre());
			ps.setString(2, p.getDescripcion());
			ps.setDouble(3, p.getPrecio());
			ps.setInt(4, p.getStock());
			ps.setString(5, p.getImagen());
			ps.setInt(6, p.getCategoriaFK());
			ps.setInt(7, p.getId());
			//4. Ejecutar la sentencia SQL.
			ps.executeUpdate();
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);		
		} catch (Exception ex) {
			resultado = false;
			//5. Desconectarse.
		} finally { 
			bdUtil.desconectar(); //Envía la petición de desconexión al dbms.
		}
		return resultado;
	}
	
	public boolean delete(int id) {
		boolean resultado;
		try {
			//1. Conectarse a la base de datos.
			bdUtil.conectar();
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			//2. Definir la sentencia SQL (UPDATE).
			String sql = "DELETE FROM producto WHERE id = ?";
			//3. Preparar la sentencia SQL.
			Connection conexion = bdUtil.getConexion();
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setInt(1, id);
			//4. Ejecutar la sentencia SQL.
			ps.executeUpdate();
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);		
		} catch (Exception ex) {
			resultado = false;
			//5. Desconectarse.
		} finally { 
			bdUtil.desconectar(); //Envía la petición de desconexión al dbms.
		}
		return resultado;
	}
}
