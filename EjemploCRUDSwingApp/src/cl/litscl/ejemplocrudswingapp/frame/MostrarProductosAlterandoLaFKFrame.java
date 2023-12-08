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

public class MostrarProductosAlterandoLaFKFrame extends JInternalFrame {
	private JTable tableProductos;
	private DefaultTableModel dtm = new DefaultTableModel();
	private ProductoDAO daoProducto = new ProductoDAO();
	private ProductoFK fkProducto = new ProductoFK();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MostrarProductosAlterandoLaFKFrame frame = new MostrarProductosAlterandoLaFKFrame();
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
	public MostrarProductosAlterandoLaFKFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
				List<Producto> productos = daoProducto.getAll();
				
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
		this.setTitle("Mostrar Productos Alterando la FK");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 59, 664, 345);
		getContentPane().add(scrollPane);
		
		tableProductos = new JTable();
		scrollPane.setViewportView(tableProductos);
		
		JLabel lblNewLabel = new JLabel("<html>\r\nPermite obtener todos los registros, pero cambiando la clave foranea (Mostrar otra columna<br/>de la tabla agena, en lugar de su clave primaria), esto se realiza gracias a las clases del paquete FK.\r\n</html>");
		lblNewLabel.setBounds(10, 11, 664, 37);
		getContentPane().add(lblNewLabel);

	}
}