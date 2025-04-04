package nueva_version;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JTextArea;

public class NuevaVersion_View {

	private JFrame frame;
	private JTextField TF_IdAutor;
	private JTextField TF_PalabrasClave;
	private JTextField TF_Fichero;
	private JTextField TF_IdAutorEnvio;
	private JButton btnInicioSesion;
	private JList<String> listArticulos;
	private JTextArea TA_Resumen;
	private JButton btnOK;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuevaVersion_View window = new NuevaVersion_View();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NuevaVersion_View() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		TF_IdAutor = new JTextField();
		TF_IdAutor.setBounds(10, 34, 105, 30);
		frame.getContentPane().add(TF_IdAutor);
		TF_IdAutor.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Id Autor");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 105, 14);
		frame.getContentPane().add(lblNewLabel);
		
		btnInicioSesion = new JButton("OK");
		btnInicioSesion.setBounds(7, 75, 108, 23);
		frame.getContentPane().add(btnInicioSesion);
		
		JLabel lblNewLabel_1 = new JLabel("Articulos aceptados");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(181, 11, 105, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(181, 42, 105, 102);
		frame.getContentPane().add(scrollPane);
		
		listArticulos = new JList();
		scrollPane.setViewportView(listArticulos);
		
		JLabel lblNewLabel_2 = new JLabel("Nueva versión");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(425, 11, 105, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Resumen:");
		lblNewLabel_3.setBounds(343, 42, 63, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		TA_Resumen = new JTextArea();
		TA_Resumen.setBounds(416, 37, 136, 61);
		frame.getContentPane().add(TA_Resumen);
		
		JLabel lblNewLabel_4 = new JLabel("Pal. clave:");
		lblNewLabel_4.setBounds(343, 127, 63, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		TF_PalabrasClave = new JTextField();
		TF_PalabrasClave.setBounds(416, 124, 136, 20);
		frame.getContentPane().add(TF_PalabrasClave);
		TF_PalabrasClave.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Fichero:");
		lblNewLabel_5.setBounds(360, 166, 46, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		TF_Fichero = new JTextField();
		TF_Fichero.setBounds(416, 163, 136, 20);
		frame.getContentPane().add(TF_Fichero);
		TF_Fichero.setColumns(10);
		
		btnOK = new JButton("OK");
		btnOK.setEnabled(false);
		btnOK.setBounds(416, 202, 136, 23);
		frame.getContentPane().add(btnOK);
		
		TF_IdAutorEnvio = new JTextField();
		TF_IdAutorEnvio.setEditable(false);
		TF_IdAutorEnvio.setBounds(181, 203, 105, 20);
		frame.getContentPane().add(TF_IdAutorEnvio);
		TF_IdAutorEnvio.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("IdAutor Envio");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(181, 169, 105, 14);
		frame.getContentPane().add(lblNewLabel_6);
	}
	public JButton getBotonInicio() {return this.btnInicioSesion;}
	public JTextField getTF_IdAutor() {return this.TF_IdAutor;}
	public JFrame getFrame() {return this.frame;}
	public JList<String> getListaArticulos() { return this.listArticulos;}
	public JTextField getTF_IdAutorEnvio() {return this.TF_IdAutorEnvio;}
	public JTextArea getTA_Resumen() {return this.TA_Resumen;}
	public JTextField getTF_PalabrasClave() {return this.TF_PalabrasClave;}
	public JTextField getTF_Fichero() {return this.TF_Fichero;}
	public JButton getBotonOK() {return this.btnOK;}
}
