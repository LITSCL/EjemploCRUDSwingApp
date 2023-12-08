package cl.litscl.ejemplocrudswingapp.frame;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import cl.litscl.ejemplocrudswingappmodel.dao.CategoriaDAO;
import cl.litscl.ejemplocrudswingappmodel.dao.ProductoDAO;
import cl.litscl.ejemplocrudswingappmodel.dto.Categoria;
import cl.litscl.ejemplocrudswingappmodel.dto.Producto;
import java.awt.event.ActionListener;

public class ActualizarProductoFrame extends JInternalFrame {
	private JTextField textFieldId;
	private JTextField textFieldNombre;
	private JTextField textFieldDescripcion;
	private JTextField textFieldPrecio;
	private JTextField textFieldStock;
	private JTextField textFieldImagen;
	private JTextField textFieldBuscar;
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
					ActualizarProductoFrame frame = new ActualizarProductoFrame();
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
	public ActualizarProductoFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
				Producto p = daoProducto.find(6);
				
				textFieldId.setText(Integer.toString(p.getId()));
				textFieldNombre.setText(p.getNombre());
				textFieldDescripcion.setText(p.getDescripcion());
				textFieldPrecio.setText(Double.toString(p.getPrecio()));
				textFieldStock.setText(Integer.toString(p.getStock()));
				textFieldImagen.setText(p.getImagen());
				
				List<Categoria> categorias = daoCategoria.getAll();
				
				for (Categoria c : categorias) {
					comboBoxCategoria.addItem(c);					
					if (c.getId() == p.getCategoriaFK()) {
						comboBoxCategoria.setSelectedItem(c);
					}
				}
				textFieldId.setEnabled(false);
			}
		});
		this.setTitle("Actualizar Producto");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(10, 83, 46, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Descripci\u00F3n");
		lblNewLabel_1.setBounds(10, 108, 75, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Precio");
		lblNewLabel_2.setBounds(10, 133, 46, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Stock");
		lblNewLabel_3.setBounds(10, 158, 46, 14);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Imagen");
		lblNewLabel_4.setBounds(10, 183, 60, 14);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Categoria");
		lblNewLabel_5.setBounds(10, 208, 75, 14);
		getContentPane().add(lblNewLabel_5);
		
		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setBounds(83, 105, 86, 20);
		getContentPane().add(textFieldDescripcion);
		textFieldDescripcion.setColumns(10);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(83, 80, 86, 20);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldPrecio = new JTextField();
		textFieldPrecio.setBounds(83, 133, 86, 20);
		getContentPane().add(textFieldPrecio);
		textFieldPrecio.setColumns(10);
		
		textFieldStock = new JTextField();
		textFieldStock.setBounds(83, 155, 86, 20);
		getContentPane().add(textFieldStock);
		textFieldStock.setColumns(10);
		
		textFieldImagen = new JTextField();
		textFieldImagen.setBounds(83, 180, 86, 20);
		getContentPane().add(textFieldImagen);
		textFieldImagen.setColumns(10);
		
		comboBoxCategoria = new JComboBox<Categoria>();
		comboBoxCategoria.setBounds(83, 205, 86, 20);
		getContentPane().add(comboBoxCategoria);
		
		JButton btnActualizarProducto = new JButton("Actualizar");
		btnActualizarProducto.addActionListener(e -> actualizarProducto(e));
		btnActualizarProducto.setBounds(568, 381, 106, 23);
		getContentPane().add(btnActualizarProducto);
		
		JLabel lblNewLabel_6 = new JLabel("Permite actualizar un registro");
		lblNewLabel_6.setBounds(10, 11, 664, 14);
		getContentPane().add(lblNewLabel_6);
		
		textFieldBuscar = new JTextField();
		textFieldBuscar.setColumns(10);
		textFieldBuscar.setBounds(489, 55, 86, 20);
		getContentPane().add(textFieldBuscar);
		
		JLabel lblNewLabel_7 = new JLabel("ID ");
		lblNewLabel_7.setBounds(469, 58, 46, 14);
		getContentPane().add(lblNewLabel_7);
		
		JButton btnBuscarProducto = new JButton("Buscar");
		btnBuscarProducto.addActionListener(e -> buscarProducto(e));
		btnBuscarProducto.setBounds(585, 54, 89, 23);
		getContentPane().add(btnBuscarProducto);
		
		JLabel lblNewLabel_8 = new JLabel("ID");
		lblNewLabel_8.setBounds(10, 58, 46, 14);
		getContentPane().add(lblNewLabel_8);
		
		textFieldId = new JTextField();
		textFieldId.setColumns(10);
		textFieldId.setBounds(83, 55, 86, 20);
		getContentPane().add(textFieldId);

	}

	private void buscarProducto(ActionEvent e) {
		int id = Integer.parseInt(textFieldBuscar.getText());
		
		Producto p = daoProducto.find(id);
		
		textFieldId.setText(Integer.toString(p.getId()));
		textFieldNombre.setText(p.getNombre());
		textFieldDescripcion.setText(p.getDescripcion());
		textFieldPrecio.setText(Double.toString(p.getPrecio()));
		textFieldStock.setText(Integer.toString(p.getStock()));
		textFieldImagen.setText(p.getImagen());
		
		List<Categoria> categorias = daoCategoria.getAll();
		
		for (Categoria c : categorias) {
			comboBoxCategoria.addItem(c);					
			if (c.getId() == p.getCategoriaFK()) {
				comboBoxCategoria.setSelectedItem(c);
			}
		}
	}

	private void actualizarProducto(ActionEvent e) {
		int id = Integer.parseInt(textFieldId.getText());
		String nombre = textFieldNombre.getText();
		String descripcion = textFieldDescripcion.getText();
		double precio = Double.parseDouble(textFieldPrecio.getText());
		int stock = Integer.parseInt(textFieldStock.getText());
		String imagen = textFieldImagen.getText();
		Categoria c = (Categoria)comboBoxCategoria.getSelectedItem();
		int categoriaId = c.getId();
		
		Producto p = new Producto();
		p.setId(id);
		p.setNombre(nombre);
		p.setDescripcion(descripcion);
		p.setPrecio(precio);
		p.setStock(stock);
		p.setImagen(imagen);
		p.setCategoriaFK(categoriaId);
		
		daoProducto.update(p);
	}
}
