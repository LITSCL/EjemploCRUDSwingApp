package cl.litscl.ejemplocrudswingapp;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cl.litscl.ejemplocrudswingapp.frame.ActualizarProductoFrame;
import cl.litscl.ejemplocrudswingapp.frame.BorrarProductoFrame;
import cl.litscl.ejemplocrudswingapp.frame.BuscarProductoAlterandoLaFKFrame;
import cl.litscl.ejemplocrudswingapp.frame.BuscarProductoFrame;
import cl.litscl.ejemplocrudswingapp.frame.BuscarProductosAlterandoLaFKFrame;
import cl.litscl.ejemplocrudswingapp.frame.BuscarProductosFrame;
import cl.litscl.ejemplocrudswingapp.frame.CrearCategoriaFrame;
import cl.litscl.ejemplocrudswingapp.frame.CrearProductoFrame;
import cl.litscl.ejemplocrudswingapp.frame.MostrarProductosAleatoriosAlterandoLaFKFrame;
import cl.litscl.ejemplocrudswingapp.frame.MostrarProductosAleatoriosFrame;
import cl.litscl.ejemplocrudswingapp.frame.MostrarProductosAlterandoLaFKFrame;
import cl.litscl.ejemplocrudswingapp.frame.MostrarProductosFrame;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StartFrame extends JFrame {
	private ActualizarProductoFrame actualizarProductoFrame;
	private BorrarProductoFrame borrarProductoFrame;
	private BuscarProductoAlterandoLaFKFrame buscarProductoAlterandoLaFKFrame;
	private BuscarProductoFrame buscarProductoFrame;
	private BuscarProductosAlterandoLaFKFrame buscarProductosAlterandoLaFKFrame;
	private BuscarProductosFrame buscarProductosFrame;
	private CrearCategoriaFrame crearCategoriaFrame;
	private CrearProductoFrame crearProductoFrame;
	private MostrarProductosAleatoriosAlterandoLaFKFrame mostrarProductosAleatoriosAlterandoLaFKFrame;
	private MostrarProductosAleatoriosFrame mostrarProductosAleatoriosFrame;
	private MostrarProductosAlterandoLaFKFrame mostrarProductosAlterandoLaFKFrame;
	private MostrarProductosFrame mostrarProductosFrame;
	private JPanel contentPane;
	private JDesktopPane desktopPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartFrame frame = new StartFrame();
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
	public StartFrame() {
		setTitle("EjemploCRUDSwingApp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 652);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnOpciones = new JMenu("Opciones");
		menuBar.add(mnOpciones);
		
		JMenu mnCategoria = new JMenu("Categoria");
		mnOpciones.add(mnCategoria);
		
		JMenuItem mntmCategoriaSave = new JMenuItem("Save");
		mntmCategoriaSave.addActionListener(e -> mostrarFrameSaveCategoria(e));
		mnCategoria.add(mntmCategoriaSave);
		
		JMenu mnProducto = new JMenu("Producto");
		mnOpciones.add(mnProducto);
		
		JMenuItem mntmProductoSave = new JMenuItem("Save");
		mntmProductoSave.addActionListener(e -> mostrarFrameSaveProducto(e));
		mnProducto.add(mntmProductoSave);
		
		JMenuItem mntmProductoGetAll = new JMenuItem("GetAll");
		mntmProductoGetAll.addActionListener(e -> mostrarFrameGetAllProducto(e));
		mnProducto.add(mntmProductoGetAll);
		
		JMenuItem mntmProductoGetRandom = new JMenuItem("GetRandom");
		mntmProductoGetRandom.addActionListener(e -> mostrarFrameGetRandomProducto(e));
		
		JMenuItem mntmGetAllFKProducto = new JMenuItem("GetAllFK");
		mntmGetAllFKProducto.addActionListener(e -> mostrarFrameGetAllFKProducto(e));
		mnProducto.add(mntmGetAllFKProducto);
		mnProducto.add(mntmProductoGetRandom);
		
		JMenuItem mntmProductoFind = new JMenuItem("Find");
		mntmProductoFind.addActionListener(e -> mostrarFrameFindProducto(e));
		
		JMenuItem mntmGetRandomFKProducto = new JMenuItem("GetRandomFK");
		mntmGetRandomFKProducto.addActionListener(e -> mostrarFrameGetRandomFKProducto(e));
		mnProducto.add(mntmGetRandomFKProducto);
		mnProducto.add(mntmProductoFind);
		
		JMenuItem mntmProductoFindAll = new JMenuItem("FindAll");
		mntmProductoFindAll.addActionListener(e -> mostrarFrameFindAllProducto(e));
		
		JMenuItem mntmFindFKProducto = new JMenuItem("FindFK");
		mntmFindFKProducto.addActionListener(e -> mostrarFrameFindFKProducto(e));
		mnProducto.add(mntmFindFKProducto);
		mnProducto.add(mntmProductoFindAll);
		
		JMenuItem mntmProductoUpdate = new JMenuItem("Update");
		mntmProductoUpdate.addActionListener(e -> mostrarFrameUpdateProducto(e));
		
		JMenuItem mntmFindAllFKProducto = new JMenuItem("FindAllFK");
		mntmFindAllFKProducto.addActionListener(e -> mostrarFrameFindAllFKProducto(e));
		mnProducto.add(mntmFindAllFKProducto);
		mnProducto.add(mntmProductoUpdate);
		
		JMenuItem mntmProductoDelete = new JMenuItem("Delete");
		mntmProductoDelete.addActionListener(e -> mostrarFrameDeleteProducto(e));
		mnProducto.add(mntmProductoDelete);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
	}

	private void mostrarFrameFindAllFKProducto(ActionEvent e) {
		if (this.buscarProductosAlterandoLaFKFrame != null) {
			desktopPane.remove(this.buscarProductosAlterandoLaFKFrame);
		}
		this.buscarProductosAlterandoLaFKFrame = new BuscarProductosAlterandoLaFKFrame();
		desktopPane.add(this.buscarProductosAlterandoLaFKFrame);
		this.buscarProductosAlterandoLaFKFrame.setVisible(true);
	}

	private void mostrarFrameFindFKProducto(ActionEvent e) {
		if (this.buscarProductoAlterandoLaFKFrame != null) {
			desktopPane.remove(this.buscarProductoAlterandoLaFKFrame);
		}
		this.buscarProductoAlterandoLaFKFrame = new BuscarProductoAlterandoLaFKFrame();
		desktopPane.add(this.buscarProductoAlterandoLaFKFrame);
		this.buscarProductoAlterandoLaFKFrame.setVisible(true);
	}
	
	private void mostrarFrameGetRandomFKProducto(ActionEvent e) {
		if (this.mostrarProductosAleatoriosAlterandoLaFKFrame != null) {
			desktopPane.remove(this.mostrarProductosAleatoriosAlterandoLaFKFrame);
		}
		this.mostrarProductosAleatoriosAlterandoLaFKFrame = new MostrarProductosAleatoriosAlterandoLaFKFrame();
		desktopPane.add(this.mostrarProductosAleatoriosAlterandoLaFKFrame);
		this.mostrarProductosAleatoriosAlterandoLaFKFrame.setVisible(true);
	}
	
	private void mostrarFrameGetAllFKProducto(ActionEvent e) {
		if (this.mostrarProductosAlterandoLaFKFrame != null) {
			desktopPane.remove(this.mostrarProductosAlterandoLaFKFrame);
		}
		this.mostrarProductosAlterandoLaFKFrame = new MostrarProductosAlterandoLaFKFrame();
		desktopPane.add(this.mostrarProductosAlterandoLaFKFrame);
		this.mostrarProductosAlterandoLaFKFrame.setVisible(true);
	}

	private void mostrarFrameDeleteProducto(ActionEvent e) {
		if (this.borrarProductoFrame != null) {
			desktopPane.remove(this.borrarProductoFrame);
		}
		this.borrarProductoFrame = new BorrarProductoFrame();
		desktopPane.add(this.borrarProductoFrame);
		this.borrarProductoFrame.setVisible(true);
	}

	private void mostrarFrameUpdateProducto(ActionEvent e) {
		if (this.actualizarProductoFrame != null) {
			desktopPane.remove(this.actualizarProductoFrame);
		}
		this.actualizarProductoFrame = new ActualizarProductoFrame();
		desktopPane.add(this.actualizarProductoFrame);
		this.actualizarProductoFrame.setVisible(true);
	}

	private void mostrarFrameFindAllProducto(ActionEvent e) {
		if (this.buscarProductosFrame != null) {
			desktopPane.remove(this.buscarProductosFrame);
		}
		this.buscarProductosFrame = new BuscarProductosFrame();
		desktopPane.add(this.buscarProductosFrame);
		this.buscarProductosFrame.setVisible(true);
	}

	private void mostrarFrameFindProducto(ActionEvent e) {
		if (this.buscarProductoFrame != null) {
			desktopPane.remove(this.buscarProductoFrame);
		}
		this.buscarProductoFrame = new BuscarProductoFrame();
		desktopPane.add(this.buscarProductoFrame);
		this.buscarProductoFrame.setVisible(true);
	}
	
	private void mostrarFrameGetRandomProducto(ActionEvent e) {
		if (this.mostrarProductosAleatoriosFrame != null) {
			desktopPane.remove(this.mostrarProductosAleatoriosFrame);
		}
		this.mostrarProductosAleatoriosFrame = new MostrarProductosAleatoriosFrame();
		desktopPane.add(this.mostrarProductosAleatoriosFrame);
		this.mostrarProductosAleatoriosFrame.setVisible(true);
	}

	private void mostrarFrameGetAllProducto(ActionEvent e) {
		if (this.mostrarProductosFrame != null) {
			desktopPane.remove(this.mostrarProductosFrame);
		}
		this.mostrarProductosFrame = new MostrarProductosFrame();
		desktopPane.add(this.mostrarProductosFrame);
		this.mostrarProductosFrame.setVisible(true);
	}
	
	private void mostrarFrameSaveProducto(ActionEvent e) {
		if (this.crearProductoFrame != null) {
			desktopPane.remove(this.crearProductoFrame);
		}
		this.crearProductoFrame = new CrearProductoFrame();
		desktopPane.add(this.crearProductoFrame);
		this.crearProductoFrame.setVisible(true);
	}

	private void mostrarFrameSaveCategoria(ActionEvent e) {
		if (this.crearCategoriaFrame != null) {
			desktopPane.remove(this.crearCategoriaFrame);
		}
		this.crearCategoriaFrame = new CrearCategoriaFrame();
		desktopPane.add(this.crearCategoriaFrame);
		this.crearCategoriaFrame.setVisible(true);
	}
}
