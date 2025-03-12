package aceptar_articulos;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class HU29225_29226_View {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HU29225_29226_View window = new HU29225_29226_View();
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
	public HU29225_29226_View() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setBackground(Color.GRAY);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		btnAceptar = new JButton("ACEPTAR ");
		btnAceptar.setBounds(534, 385, 98, 21);
		panel.add(btnAceptar);
		
		btnDenegar = new JButton("DENEGAR ");
		btnDenegar.setBounds(655, 385, 98, 21);
		panel.add(btnDenegar);
		
		lblNewLabel = new JLabel("Lista de Articulos");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(26, 46, 129, 37);
		panel.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Lista de Revisores");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(220, 46, 129, 37);
		panel.add(lblNewLabel_1);
		
		lblNewLabel_1_1 = new JLabel("Información");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(433, 46, 129, 37);
		panel.add(lblNewLabel_1_1);
		
		
		listArticulos = new JList<>();		
		scrollPane_1 = new JScrollPane(listArticulos);
		scrollPane_1.setBounds(20, 93, 115, 220);
		panel.add(scrollPane_1);
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(220, 93, 115, 220);
		panel.add(scrollPane_2);
		
		listRevisores = new JList<>();
		scrollPane_2.setViewportView(listRevisores);
		
		lblNombre_Articulo = new JLabel("Selecciona un Articulo ");
		lblNombre_Articulo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombre_Articulo.setBounds(433, 94, 129, 13);
		panel.add(lblNombre_Articulo);
		
		lblLabel1 = new JLabel("Nivel de experto: ");
		lblLabel1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLabel1.setBounds(433, 130, 129, 13);
		panel.add(lblLabel1);
		
		lblLabel2 = new JLabel("Comentario Autor\r\n");
		lblLabel2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLabel2.setBounds(433, 167, 129, 13);
		panel.add(lblLabel2);
		
		lblLabel3 = new JLabel("Comentario Coordinador");
		lblLabel3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLabel3.setBounds(612, 167, 143, 13);
		panel.add(lblLabel3);
		
		lblLabel4 = new JLabel("Valoracion final:");
		lblLabel4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLabel4.setBounds(433, 342, 89, 13);
		panel.add(lblLabel4);
		
		textAreaComentAutor = new JTextArea();
		textAreaComentAutor.setBounds(433, 212, 130, 90);
		panel.add(textAreaComentAutor);
		
		textAreaComentCoord = new JTextArea();
		textAreaComentCoord.setBounds(612, 212, 130, 90);
		panel.add(textAreaComentCoord);
		
		lblNivelExperto = new JLabel(" ");
		lblNivelExperto.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNivelExperto.setBounds(533, 131, 129, 13);
		panel.add(lblNivelExperto);
		
		lblValoracionFinal = new JLabel("");
		lblValoracionFinal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblValoracionFinal.setBounds(532, 342, 129, 13);
		panel.add(lblValoracionFinal);
		
		btnListaCompleta = new JButton("Lista Completa");
		btnListaCompleta.setBounds(20, 385, 104, 21);
		panel.add(btnListaCompleta);
		
		btnListaAutomatica = new JButton("Lista Automatica");
		btnListaAutomatica.setBounds(151, 385, 115, 21);
		panel.add(btnListaAutomatica);
		
		btnAceptaTodos = new JButton("Aceptar todos");
		btnAceptaTodos.setEnabled(false);
		btnAceptaTodos.setBounds(307, 385, 115, 21);
		panel.add(btnAceptaTodos);
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public JPanel getPanel() {
		return panel;
	}

	public JButton getBtnAceptar() {
		return btnAceptar;
	}

	public JButton getBtnDenegar() {
		return btnDenegar;
	}
	public JButton getBtnListaCompleta() {
		return btnListaCompleta;
	}

	public JButton getBtnListaAutomatica() {
		return btnListaAutomatica;
	}
	public JButton getBtnAceptaTodos() {
		return btnAceptaTodos;
	}
	public JList<String> getListArticulos() {
		return listArticulos;
	}

	public JList<String> getListRevisores() {
		return listRevisores;
	}

	public JLabel getLblNombre_Articulo() {
		return lblNombre_Articulo;
	}

	public JTextArea getTextAreaComentAutor() {
		return textAreaComentAutor;
	}

	public JTextArea getTextAreaComentCoord() {
		return textAreaComentCoord;
	}

	public JLabel getLblNivelExperto() {
		return lblNivelExperto;
	}

	public JLabel getLblValoracionFinal() {
		return lblValoracionFinal;
	}


	private JPanel panel;
	private JButton btnAceptar;
	private JButton btnDenegar;
	private JButton btnListaCompleta;
	private JButton btnListaAutomatica;
	private JButton btnAceptaTodos;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_1_1;
	JList<String> listArticulos;
	JList<String> listRevisores;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_1;
	private JLabel lblNombre_Articulo;
	private JLabel lblLabel1;
	private JLabel lblLabel2;
	private JLabel lblLabel3;
	private JLabel lblLabel4;
	private JTextArea textAreaComentAutor;
	private JTextArea textAreaComentCoord;
	private JLabel lblNivelExperto;
	private JLabel lblValoracionFinal;	
}
