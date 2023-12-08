package cl.litscl.ejemplocrudswingapp.frame;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import cl.litscl.ejemplocrudswingappmodel.dao.CategoriaDAO;
import cl.litscl.ejemplocrudswingappmodel.dto.Categoria;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CrearCategoriaFrame extends JInternalFrame {
	private JTextField textFieldNombre;
	private CategoriaDAO daoCategoria = new CategoriaDAO();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearCategoriaFrame frame = new CrearCategoriaFrame();
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
	public CrearCategoriaFrame() {
		this.setTitle("Crear Categoria");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(10, 58, 58, 14);
		getContentPane().add(lblNewLabel);
		
		JButton btnCrearCategoria = new JButton("Crear");
		btnCrearCategoria.addActionListener(e -> crearCategoria(e));
		btnCrearCategoria.setBounds(585, 381, 89, 23);
		getContentPane().add(btnCrearCategoria);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(59, 55, 86, 20);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);

	}

	private void crearCategoria(ActionEvent e) {
		String nombre = textFieldNombre.getText();

		Categoria c = new Categoria();
		c.setNombre(nombre);
		
		daoCategoria.save(c);
	}
}
