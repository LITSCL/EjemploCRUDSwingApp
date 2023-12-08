package cl.litscl.ejemplocrudswingapp.frame;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cl.litscl.ejemplocrudswingappmodel.dao.ProductoDAO;
import cl.litscl.ejemplocrudswingappmodel.dto.Producto;
import cl.litscl.ejemplocrudswingappmodel.fk.ProductoFK;

import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class BuscarProductosAlterandoLaFKFrame extends JInternalFrame {
	private JTable tableProductos;
	private DefaultTableModel dtm = new DefaultTableModel();
	private ProductoDAO daoProducto = new ProductoDAO();
	private ProductoFK fkProducto = new ProductoFK();
	private JComboBox<String> comboBoxColumnas;
	private JTextField textFieldValor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscarProductosAlterandoLaFKFrame frame = new BuscarProductosAlterandoLaFKFrame();
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
	public BuscarProductosAlterandoLaFKFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
				List<Producto> productos = daoProducto.findAll("categoria_id", "2");
				
				comboBoxColumnas.addItem("ID");
				comboBoxColumnas.addItem("Nombre");
				comboBoxColumnas.addItem("Descripcion");
				comboBoxColumnas.addItem("Precio");	
				comboBoxColumnas.addItem("Stock");
				comboBoxColumnas.addItem("Imagen");
				comboBoxColumnas.addItem("Categoria");
				
				dtm.addColumn("ID");
				dtm.addColumn("Nombre");
				dtm.addColumn("Descripcion");
				dtm.addColumn("Precio");
				dtm.addColumn("Stock");
				dtm.addColumn("Imagen");
				dtm.addColumn("Categoria");
				
				for (Producto p : productos) {
					Object fila[] = new Object[7];
					
					fila[0] = p.getId();
					fila[1] = p.getNombre();
					fila[2] = p.getDescripcion();
					fila[3] = p.getPrecio();
					fila[4] = p.getStock();
					fila[5] = p.getImagen();
					fila[6] = fkProducto.getCategoriaNombre(p);
					
					dtm.addRow(fila);
				}
				tableProductos.setModel(dtm);			
			}
		});
		this.setTitle("Buscar Productos Alterando la FK");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 83, 664, 321);
		getContentPane().add(scrollPane);
		
		tableProductos = new JTable();
		scrollPane.setViewportView(tableProductos);
		
		JLabel lblNewLabel = new JLabel("<html>Permite buscar uno o varios registros en base a una condici\u00F3n, pero cambiando la clave foranea (Mostrar otra columna de la tabla agena, en lugar de su clave primaria), esto se realiza gracias a las clases del paquete FK</html>");
		lblNewLabel.setBounds(10, 11, 664, 36);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Columna");
		lblNewLabel_1.setBounds(10, 58, 70, 14);
		getContentPane().add(lblNewLabel_1);
		
		comboBoxColumnas = new JComboBox<String>();
		comboBoxColumnas.setBounds(64, 55, 86, 20);
		getContentPane().add(comboBoxColumnas);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(e -> buscarProductos(e));
		btnBuscar.setBounds(256, 54, 89, 23);
		getContentPane().add(btnBuscar);
		
		textFieldValor = new JTextField();
		textFieldValor.setBounds(160, 55, 86, 20);
		getContentPane().add(textFieldValor);
		textFieldValor.setColumns(10);

	}

	private void buscarProductos(ActionEvent e) {
		String columnaSeleccionada = comboBoxColumnas.getSelectedItem().toString().toLowerCase();
		
		if (columnaSeleccionada.equals("categoria")) {
			columnaSeleccionada = "categoria_id";
		}
		
		String valorIntroducido = textFieldValor.getText();
		
		List<Producto> productos = daoProducto.findAll(columnaSeleccionada, valorIntroducido);
		
		dtm.setRowCount(0);
		
		for (Producto p : productos) {
			Object fila[] = new Object[7];
			
			fila[0] = p.getId();
			fila[1] = p.getNombre();
			fila[2] = p.getDescripcion();
			fila[3] = p.getPrecio();
			fila[4] = p.getStock();
			fila[5] = p.getImagen();
			fila[6] = fkProducto.getCategoriaNombre(p);
			
			dtm.addRow(fila);
		}
		tableProductos.setModel(dtm);	
	}
}
