package cl.litscl.ejemplocrudswingapp.frame;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import cl.litscl.ejemplocrudswingappmodel.dao.CategoriaDAO;
import cl.litscl.ejemplocrudswingappmodel.dao.ProductoDAO;
import cl.litscl.ejemplocrudswingappmodel.dto.Categoria;
import cl.litscl.ejemplocrudswingappmodel.dto.Producto;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class CrearProductoFrame extends JInternalFrame {
	private JTextField textFieldNombre;
	private JTextField textFieldDescripcion;
	private JTextField textFieldPrecio;
	private JTextField textFieldStock;
	private JTextField textFieldImagen;
	private JComboBox<Categoria> comboBoxCategoria;
	private ProductoDAO daoProducto = new ProductoDAO();
	private CategoriaDAO daoCategoria = new CategoriaDAO();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearProductoFrame frame = new CrearProductoFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CrearProductoFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
				List<Categoria> categorias = daoCategoria.getAll();
				
				for (Categoria c : categorias) {
					comboBoxCategoria.addItem(c);
				}
			}
		});
		this.setTitle("Crear Producto");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(10, 58, 46, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Descripci\u00F3n");
		lblNewLabel_1.setBounds(10, 83, 75, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Precio");
		lblNewLabel_2.setBounds(10, 108, 46, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Stock");
		lblNewLabel_3.setBounds(10, 133, 46, 14);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Imagen");
		lblNewLabel_4.setBounds(10, 158, 60, 14);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Categoria");
		lblNewLabel_5.setBounds(10, 183, 75, 14);
		getContentPane().add(lblNewLabel_5);
		
		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setBounds(83, 80, 86, 20);
		getContentPane().add(textFieldDescripcion);
		textFieldDescripcion.setColumns(10);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(83, 55, 86, 20);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldPrecio = new JTextField();
		textFieldPrecio.setBounds(83, 105, 86, 20);
		getContentPane().add(textFieldPrecio);
		textFieldPrecio.setColumns(10);
		
		textFieldStock = new JTextField();
		textFieldStock.setBounds(83, 130, 86, 20);
		getContentPane().add(textFieldStock);
		textFieldStock.setColumns(10);
		
		textFieldImagen = new JTextField();
		textFieldImagen.setBounds(83, 155, 86, 20);
		getContentPane().add(textFieldImagen);
		textFieldImagen.setColumns(10);
		
		comboBoxCategoria = new JComboBox<Categoria>();
		comboBoxCategoria.setBounds(83, 180, 86, 20);
		getContentPane().add(comboBoxCategoria);
		
		JButton btnCrearProducto = new JButton("Crear");
		btnCrearProducto.addActionListener(e -> crearProducto(e));
		btnCrearProducto.setBounds(585, 381, 89, 23);
		getContentPane().add(btnCrearProducto);
		
		JLabel lblNewLabel_6 = new JLabel("Permite guardar un registro");
		lblNewLabel_6.setBounds(10, 11, 664, 14);
		getContentPane().add(lblNewLabel_6);

	}

	private void crearProducto(ActionEvent e) {
		String nombre = textFieldNombre.getText();
		String descripcion = textFieldDescripcion.getText();
		double precio = Double.parseDouble(textFieldPrecio.getText());
		int stock = Integer.parseInt(textFieldStock.getText());
		String imagen = textFieldImagen.getText();
		Categoria c = (Categoria)comboBoxCategoria.getSelectedItem();
		int categoriaId = c.getId();
		
		Producto p = new Producto();
		p.setNombre(nombre);
		p.setDescripcion(descripcion);
		p.setPrecio(precio);
		p.setStock(stock);
		p.setImagen(imagen);
		p.setCategoriaFK(categoriaId);
		
		daoProducto.save(p);
	}
}
