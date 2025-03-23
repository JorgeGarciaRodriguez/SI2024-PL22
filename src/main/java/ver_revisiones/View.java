package ver_revisiones;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;

public class View {

	private JFrame frame;
	private JTextField tf_IdRevisor;
	private JTextField tfComentariosRevisor;
	private JTable table;
	private JButton btnPendientes;
	private JButton btnRevisados;
	private JList<String> listArticulos;
	private JButton btnInicioSesion;
	private JComboBox cbDecision;
	private JButton btnModificar;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View window = new View();
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
	public View() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 330);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		tf_IdRevisor = new JTextField();
		tf_IdRevisor.setBounds(10, 40, 104, 29);
		frame.getContentPane().add(tf_IdRevisor);
		tf_IdRevisor.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("IdRevisor");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 104, 18);
		frame.getContentPane().add(lblNewLabel);
		
		btnInicioSesion = new JButton("Iniciar sesión");
		btnInicioSesion.setBounds(10, 80, 104, 23);
		frame.getContentPane().add(btnInicioSesion);
		
		JLabel lblNewLabel_1 = new JLabel("Artículos");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(145, 9, 94, 23);
		frame.getContentPane().add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(145, 40, 94, 182);
		frame.getContentPane().add(scrollPane);
		
		listArticulos = new JList();
		scrollPane.setViewportView(listArticulos);
		
		btnPendientes = new JButton("Pendientes");
		btnPendientes.setBounds(145, 262, 94, 18);
		frame.getContentPane().add(btnPendientes);
		
		btnRevisados = new JButton("Ya revisados");
		btnRevisados.setBounds(145, 233, 94, 18);
		frame.getContentPane().add(btnRevisados);
		
		JLabel lblNewLabel_2 = new JLabel("Decisión");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(273, 13, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		cbDecision = new JComboBox();
		cbDecision.setModel(new DefaultComboBoxModel(new String[] {"2", "1", "-1", "-2"}));
		cbDecision.setBounds(270, 43, 49, 22);
		frame.getContentPane().add(cbDecision);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(315, 124, 75, 23);
		frame.getContentPane().add(btnModificar);
		
		JLabel lblNewLabel_3 = new JLabel("Revisiones");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(315, 172, 75, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Comentarios");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(329, 13, 95, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		tfComentariosRevisor = new JTextField();
		tfComentariosRevisor.setBounds(329, 40, 145, 73);
		frame.getContentPane().add(tfComentariosRevisor);
		tfComentariosRevisor.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(276, 197, 198, 83);
		frame.getContentPane().add(scrollPane_1);
		
		table = new JTable();
		scrollPane_1.setViewportView(table);
	}
	public JTextField getTF_IdRevisor() {return this.tf_IdRevisor;}
	public JButton getBotonRevisados() {return this.btnRevisados;}
	public JButton getBotonPendientes() {return this.btnPendientes;}
	public JButton getBotonModificar() {return this.btnPendientes;}
	public JList<String> getListaArticulos() {return this.listArticulos ;}
	public JButton getBotonInicioSesion() {return this.btnInicioSesion;}
	public JComboBox getcbDecision() {return this.cbDecision;}
	public JTextField getTF_ComentarioRevisor() {return this.tfComentariosRevisor;}
	public JFrame getFrame() {return this.frame;}
}
