package HU_29224;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;

public class H29224_View {

	private JFrame frame;
	private JTextField tFComentarioAutor;
	private JTextField tFComentarioCoordinador;
	private JScrollPane sPListadetitulos;
	private JButton btnEnviar_coordinador_1;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	
	private JList<String> listaTitulos;
	private H29224_Model model = new H29224_Model();
	private JLabel lblNewLabel;
	private JLabel lblComentarioCoordinador;
	private JLabel lblListaDeTitulos;
	private JTextField tFidAutor;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					H29224_View window = new H29224_View();
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
	public H29224_View() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	    frame = new JFrame();
	    frame.setBounds(100, 100, 644, 505);
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    frame.getContentPane().setLayout(null);

	    tFComentarioAutor = new JTextField();
	    tFComentarioAutor.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    tFComentarioAutor.setBounds(254, 56, 231, 150);
	    frame.getContentPane().add(tFComentarioAutor);
	    tFComentarioAutor.setColumns(10);

	    tFComentarioCoordinador = new JTextField();
	    tFComentarioCoordinador.setColumns(10);
	    tFComentarioCoordinador.setBounds(254, 270, 231, 150);
	    frame.getContentPane().add(tFComentarioCoordinador);

	    // Obtener títulos desde la base de datos
	    List<String> titulos = model.obtenerTitulosArticulos();
	    listaTitulos = new JList<>(titulos.toArray(new String[0])); // Convertir la lista en un array para JList

	    // Crear JScrollPane correctamente
	    sPListadetitulos = new JScrollPane(listaTitulos);
	    sPListadetitulos.setBounds(36, 56, 178, 364);
	    frame.getContentPane().add(sPListadetitulos);

	    JButton btnNewButton = new JButton("Descargar");
	    btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    btnNewButton.setBounds(67, 430, 118, 21);
	    frame.getContentPane().add(btnNewButton);
	    
	    lblNewLabel = new JLabel("Comentario Autor");
	    lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    lblNewLabel.setBounds(258, 33, 227, 13);
	    frame.getContentPane().add(lblNewLabel);
	    
	    lblComentarioCoordinador = new JLabel("Comentario Coordinador");
	    lblComentarioCoordinador.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    lblComentarioCoordinador.setBounds(258, 247, 227, 13);
	    frame.getContentPane().add(lblComentarioCoordinador);
	    
	    lblListaDeTitulos = new JLabel("Lista de titulos");
	    lblListaDeTitulos.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    lblListaDeTitulos.setBounds(36, 35, 227, 13);
	    frame.getContentPane().add(lblListaDeTitulos);
	    
	    comboBox = new JComboBox();
	    comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    comboBox.setModel(new DefaultComboBoxModel(new String[] {"Alto", "Normal", "Medio", "Bajo"}));
	    comboBox.setBounds(519, 56, 85, 21);
	    frame.getContentPane().add(comboBox);
	    
	    comboBox_1 = new JComboBox();
	    comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"2", "1", "-1", "-2"}));
	    comboBox_1.setBounds(519, 168, 85, 21);
	    frame.getContentPane().add(comboBox_1);
	    
	    JLabel lblDecision = new JLabel("Decision");
	    lblDecision.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    lblDecision.setBounds(519, 124, 227, 13);
	    frame.getContentPane().add(lblDecision);
	    
	    JLabel lblGrado = new JLabel("Grado");
	    lblGrado.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    lblGrado.setBounds(519, 35, 227, 13);
	    frame.getContentPane().add(lblGrado);
	    
	    btnEnviar_coordinador_1 = new JButton("Envio \r\nFinal");
	    btnEnviar_coordinador_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    btnEnviar_coordinador_1.setBounds(505, 370, 115, 81);
	    frame.getContentPane().add(btnEnviar_coordinador_1);
	    
	    tFidAutor = new JTextField();
	    tFidAutor.setBounds(519, 246, 85, 30);
	    frame.getContentPane().add(tFidAutor);
	    tFidAutor.setColumns(10);
	    
	    JLabel lblNewLabel_1 = new JLabel("idRevisor");
	    lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    lblNewLabel_1.setBounds(519, 222, 85, 13);
	    frame.getContentPane().add(lblNewLabel_1);
	}


	public JFrame getFrame() {return frame;}

	public JButton getBtnEnviarRevision() {
		// TODO Auto-generated method stub
		return btnEnviar_coordinador_1;
	}

	public JTextField getTfComentarioCoordinador() {
		// TODO Auto-generated method stub
		return tFComentarioCoordinador;
	}
	
	public JTextField getTfComentarioAutor() {
		// TODO Auto-generated method stub
		return tFComentarioAutor;
	}
	
	public JComboBox getComboBoxDecision() {
		// TODO Auto-generated method stub
		return comboBox;
	}
	public JComboBox getComboBoxExperto() {
		// TODO Auto-generated method stub
		return comboBox_1;
	}
	
	public JTextField gettFidAutor(){
		return tFidAutor;
	}
	public JList<String> getListadoTitulos() {
	    return listaTitulos;
	}
}
