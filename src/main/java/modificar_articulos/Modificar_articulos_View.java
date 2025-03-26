package modificar_articulos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class Modificar_articulos_View {



	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Modificar_articulos_View window = new Modificar_articulos_View();
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
	public Modificar_articulos_View() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 860, 460);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		panel.setBackground(Color.GRAY);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		btnModificar = new JButton("MODIFICAR ARTICULO");
		btnModificar.setBounds(400, 380, 200, 21);
		panel.add(btnModificar);

		btnRegresar = new JButton("REGRESAR");
		btnRegresar.setBounds(650, 380, 150, 21);
		panel.add(btnRegresar);

		lblNewLabel = new JLabel("LISTA DE ARTICULOS MODIFICABLES");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(40, 50, 280, 37);
		panel.add(lblNewLabel);
		
		lblNewLabel1 = new JLabel("IDENTIFICATE");
		lblNewLabel1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel1.setBounds(400, 150, 280, 37);
		panel.add(lblNewLabel1);
		
		JTFidentificacion = new JTextField();
		JTFidentificacion.setBounds(360, 190, 180, 25);
		panel.add(JTFidentificacion);
		
		btnIdentificarse = new JButton("Comprobar");
		btnIdentificarse.setBounds(360, 230, 180, 25);
		panel.add(btnIdentificarse);

		lblNewLabel2 = new JLabel("MODIFICACIONES");
		lblNewLabel2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel2.setBounds(600, 50, 280, 37);
		panel.add(lblNewLabel2);

		lblNewLabel3 = new JLabel("Palabras Clave");
		lblNewLabel3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel3.setBounds(600, 80, 280, 37);
		panel.add(lblNewLabel3);
		
		JTFpalabras_clave = new JTextField();
		JTFpalabras_clave.setBounds(600, 120, 180, 25);
		JTFpalabras_clave.setEnabled(false);
		panel.add(JTFpalabras_clave);

		lblNewLabel4 = new JLabel("Fichero");
		lblNewLabel4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel4.setBounds(600, 140, 280, 37);
		panel.add(lblNewLabel4);

		JTFfichero = new JTextField();
		JTFfichero.setBounds(600, 180, 180, 25);
		JTFfichero.setEnabled(false);
		panel.add(JTFfichero);
		
		lblNewLabel5 = new JLabel("Resumen");
		lblNewLabel5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel5.setBounds(600, 220, 280, 37);
		panel.add(lblNewLabel5);

		JTAresumen = new JTextArea();
		JTAresumen.setBounds(600, 260, 180, 90);
		JTAresumen.setLineWrap(true);  // Habilita el salto de línea automático
        JTAresumen.setWrapStyleWord(true);  // Asegura que no corte palabras a la mitad
        JTAresumen.setEnabled(false);
		panel.add(JTAresumen);
		
		listArticulos = new JList<>();		
		scrollPane_1 = new JScrollPane(listArticulos);
		scrollPane_1.setBounds(20, 93, 300, 300);
		panel.add(scrollPane_1);


	}

	public JFrame getFrame() {
		return frame;
	}

	public JPanel getPanel() {
		return panel;
	}

	public JButton getBtnModificar() {
		return btnModificar;
	}

	public JButton getBtnRegresar() {
		return btnRegresar;
	}
	public JButton getBtnIdentificarse() {
		return btnIdentificarse;
	}
	public JTextField getJTFidentificacion() {
		return JTFidentificacion;
	}
	public JTextField getJTFpalabras_clave() {
		return JTFpalabras_clave;
	}
	public JTextField getJTFfichero() {
		return JTFfichero;
	}
	public JTextArea getJTAresumen() {
		return JTAresumen;
	}
	public JList<String> getListArticulos() {
		return listArticulos;
	}






	private JPanel panel;
	private JButton btnModificar;
	private JButton btnRegresar;
	private JButton btnIdentificarse;
	
	private JTextField JTFidentificacion;
	private JTextField JTFpalabras_clave;
	private JTextField JTFfichero;
	
	private JTextArea JTAresumen;
	

	private JLabel lblNewLabel;
	private JLabel lblNewLabel1;
	private JLabel lblNewLabel2;
	private JLabel lblNewLabel3;
	private JLabel lblNewLabel4;
	private JLabel lblNewLabel5;

	JList<String> listArticulos;

	private JScrollPane scrollPane_1;



}
