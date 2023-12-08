package cl.litscl.ejemplocrudswingapp.frame;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import cl.litscl.ejemplocrudswingappmodel.dao.ProductoDAO;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BorrarProductoFrame extends JInternalFrame {
	private JTextField textFieldId;
	private ProductoDAO daoProducto = new ProductoDAO();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BorrarProductoFrame frame = new BorrarProductoFrame();
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
	public BorrarProductoFrame() {
		this.setTitle("Borrar Producto");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Permite borrar un registro");
		lblNewLabel.setBounds(10, 11, 664, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(10, 58, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		textFieldId = new JTextField();
		textFieldId.setBounds(30, 55, 86, 20);
		getContentPane().add(textFieldId);
		textFieldId.setColumns(10);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(e -> borrarProducto(e));
		btnBorrar.setBounds(585, 381, 89, 23);
		getContentPane().add(btnBorrar);

	}

	private void borrarProducto(ActionEvent e) {
		int id = Integer.parseInt(textFieldId.getText());
		
		daoProducto.delete(id);	
	}
}
