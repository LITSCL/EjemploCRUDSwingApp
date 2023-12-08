package cl.litscl.ejemplocrudswingapp.frame;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cl.litscl.ejemplocrudswingappmodel.dao.ProductoDAO;
import cl.litscl.ejemplocrudswingappmodel.dto.Producto;

import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MostrarProductosAleatoriosFrame extends JInternalFrame {
	private JTable tableProductos;
	private DefaultTableModel dtm = new DefaultTableModel();
	private ProductoDAO daoProducto = new ProductoDAO();
	private JTextField textFieldLimite;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MostrarProductosAleatoriosFrame frame = new MostrarProductosAleatoriosFrame();
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
	public MostrarProductosAleatoriosFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
				List<Producto> productos = daoProducto.getRandom(3);
				
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
					fila[6] = p.getCategoriaFK();
					
					dtm.addRow(fila);
				}
				tableProductos.setModel(dtm);			
			}
		});
		this.setTitle("Mostrar Productos Aleatorios");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 83, 664, 321);
		getContentPane().add(scrollPane);
		
		tableProductos = new JTable();
		scrollPane.setViewportView(tableProductos);
		
		JLabel lblNewLabel = new JLabel("<html>\r\nPermite obtener una cantidad determinada de registros y de forma aleatoria\r\n</html>");
		lblNewLabel.setBounds(10, 11, 664, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Limite");
		lblNewLabel_1.setBounds(10, 58, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		textFieldLimite = new JTextField();
		textFieldLimite.setBounds(47, 55, 86, 20);
		getContentPane().add(textFieldLimite);
		textFieldLimite.setColumns(10);
		
		JButton btnNewButton = new JButton("Refrescar");
		btnNewButton.addActionListener(e -> refrescarProductos(e));
		btnNewButton.setBounds(143, 54, 89, 23);
		getContentPane().add(btnNewButton);

	}

	private void refrescarProductos(ActionEvent e) {
		int nuevoLimite = Integer.parseInt(textFieldLimite.getText());
		
		List<Producto> productos = daoProducto.getRandom(nuevoLimite);
		
		dtm.setRowCount(0);
		
		for (Producto p : productos) {
			Object fila[] = new Object[7];
			
			fila[0] = p.getId();
			fila[1] = p.getNombre();
			fila[2] = p.getDescripcion();
			fila[3] = p.getPrecio();
			fila[4] = p.getStock();
			fila[5] = p.getImagen();
			fila[6] = p.getCategoriaFK();
			
			dtm.addRow(fila);
		}
		tableProductos.setModel(dtm);			
	}
}
