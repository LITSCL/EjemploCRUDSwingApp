package cl.litscl.ejemplocrudswingapp.frame;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import cl.litscl.ejemplocrudswingappmodel.dao.ProductoDAO;
import cl.litscl.ejemplocrudswingappmodel.dto.Categoria;
import cl.litscl.ejemplocrudswingappmodel.dto.Producto;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class BuscarProductoFrame extends JInternalFrame {
	private JTextField textFieldBuscar;
	private JTable tableProducto;
	private DefaultTableModel dtm = new DefaultTableModel();
	private ProductoDAO daoProducto = new ProductoDAO();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscarProductoFrame frame = new BuscarProductoFrame();
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
	public BuscarProductoFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
				Producto p = daoProducto.find(1);
				
				dtm.addColumn("ID");
				dtm.addColumn("Nombre");
				dtm.addColumn("Descripcion");
				dtm.addColumn("Precio");
				dtm.addColumn("Stock");
				dtm.addColumn("Imagen");
				dtm.addColumn("Categoria");
				
				Object fila[] = new Object[7];
				
				fila[0] = p.getId();
				fila[1] = p.getNombre();
				fila[2] = p.getDescripcion();
				fila[3] = p.getPrecio();
				fila[4] = p.getStock();
				fila[5] = p.getImagen();
				fila[6] = p.getCategoriaFK();
				
				dtm.addRow(fila);
				
				tableProducto.setModel(dtm);			
			}
		});
		this.setTitle("Buscar Producto");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Permite buscar un registro en base a su clave primaria");
		lblNewLabel.setBounds(10, 11, 664, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ID del Producto");
		lblNewLabel_1.setBounds(10, 58, 108, 14);
		getContentPane().add(lblNewLabel_1);
		
		textFieldBuscar = new JTextField();
		textFieldBuscar.setBounds(107, 55, 86, 20);
		getContentPane().add(textFieldBuscar);
		textFieldBuscar.setColumns(10);
		
		JButton btnBuscarProducto = new JButton("Buscar");
		btnBuscarProducto.addActionListener(e -> buscarProducto(e));
		btnBuscarProducto.setBounds(203, 54, 89, 23);
		getContentPane().add(btnBuscarProducto);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 83, 664, 321);
		getContentPane().add(scrollPane);
		
		tableProducto = new JTable();
		scrollPane.setViewportView(tableProducto);

	}
	
	private void buscarProducto(ActionEvent e) {
		int id = Integer.parseInt(textFieldBuscar.getText());
		
		Producto p = daoProducto.find(id);
		
		dtm.setRowCount(0);
		
		Object fila[] = new Object[7];
		
		fila[0] = p.getId();
		fila[1] = p.getNombre();
		fila[2] = p.getDescripcion();
		fila[3] = p.getPrecio();
		fila[4] = p.getStock();
		fila[5] = p.getImagen();
		fila[6] = p.getCategoriaFK();
		
		dtm.addRow(fila);
		
		tableProducto.setModel(dtm);		
	}
}
