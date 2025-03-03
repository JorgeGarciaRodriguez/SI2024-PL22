package HU_29229;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class Hu29229_View {

	private JFrame frame;
	private JList<String> lstArticulos;
	private JLabel Palabras_clave;
	private JTextArea Resumen;
	private JList<Object[]> lstAutores;
	private JTable Tabla;
	private JList<String> lstRevisores;
	private JButton BotonAsignar;
	private JList<String> lstRevisoresAsignados;
	private JButton BotonDesasignar;
	private JButton BotonSinRevisores;
	private JButton BotonRevisores;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Hu29229_View window = new Hu29229_View();
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
	public Hu29229_View() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 634, 350);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 156, 311);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("Artículos:");
		lblNewLabel.setBounds(36, 11, 71, 14);
		panel.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.LIGHT_GRAY);
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(155, 11, 1, 289);
		panel.add(separator);
		
		lstArticulos=new JList<>();
		JScrollPane scrollPane = new JScrollPane(lstArticulos);
		scrollPane.setBounds(10, 34, 135, 210);
		panel.add(scrollPane);
		
		BotonSinRevisores = new JButton("Sin revisores");
		BotonSinRevisores.setBounds(10, 261, 135, 14);
		panel.add(BotonSinRevisores);
		
		BotonRevisores = new JButton("Con revisores");
		BotonRevisores.setBounds(10, 286, 135, 14);
		panel.add(BotonRevisores);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(156, 0, 312, 311);
		frame.getContentPane().add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Información");
		lblNewLabel_1.setBounds(104, 7, 81, 14);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Palabras clave:");
		lblNewLabel_2.setBounds(10, 32, 95, 14);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Resumen:");
		lblNewLabel_3.setBounds(10, 57, 74, 14);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Autores");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(104, 163, 64, 14);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Nombre");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(10, 188, 64, 14);
		panel_1.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Organización");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(104, 188, 95, 14);
		panel_1.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Grupo");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setBounds(228, 188, 46, 14);
		panel_1.add(lblNewLabel_7);
		
		Palabras_clave = new JLabel("(info)");
		Palabras_clave.setBounds(114, 32, 177, 14);
		panel_1.add(Palabras_clave);
		
		//Resumen = new JLabel("(info)");
		//Resumen.setBounds(81, 57, 132, 95);
		//panel_1.add(Resumen);
		
		Tabla = new JTable();
		Tabla.setBounds(10, 213, 281, 87);
		panel_1.add(Tabla);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(Color.LIGHT_GRAY);
		separator_1.setForeground(Color.LIGHT_GRAY);
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(301, 11, 1, 289);
		panel_1.add(separator_1);
		
		Resumen = new JTextArea();
		Resumen.setBounds(10, 77, 281, 75);
		Resumen.setLineWrap(true);  // Habilita el salto de línea automático
        Resumen.setWrapStyleWord(true);  // Asegura que no corte palabras a la mitad
        Resumen.setEditable(false); // Opcional: evita la edición del usuario
		panel_1.add(Resumen);

		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(468, 0, 156, 311);
		frame.getContentPane().add(panel_2);
		
		BotonAsignar = new JButton("ASIGNAR");
		BotonAsignar.setBounds(10, 167, 129, 14);
		panel_2.add(BotonAsignar);
		
		JLabel lblNewLabel_10 = new JLabel("Revisores:");
		lblNewLabel_10.setBounds(48, 11, 70, 14);
		panel_2.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("Asignacion");
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_11.setBounds(30, 192, 97, 14);
		panel_2.add(lblNewLabel_11);
		
		lstRevisores=new JList<>();
		JScrollPane scrollPane_1 = new JScrollPane(lstRevisores);
		scrollPane_1.setBounds(10, 34, 129, 122);
		panel_2.add(scrollPane_1);
		
		lstRevisoresAsignados = new JList<>();
		lstRevisoresAsignados.setBounds(10, 217, 129, 58);
		panel_2.add(lstRevisoresAsignados);
		
		BotonDesasignar = new JButton("DESASIGNAR");
		BotonDesasignar.setBounds(10, 286, 129, 14);
		panel_2.add(BotonDesasignar);
	}
	
	public JFrame getFrame() {return frame;}
	public JList<String> getListaArticulos() {return this.lstArticulos;}
	public JLabel getLabelPalabrasClave() {return Palabras_clave;}
	public JTextArea getTextAreaResumen() {return Resumen;}
	public JTable getTable() {return Tabla;}
	public JList<String> getListaRevisores() {return this.lstRevisores;}
	public JButton getBotonAsignar() {return BotonAsignar;}
	public JList<String> getListaRevisoresAsignados() {return this.lstRevisoresAsignados;}
	public JButton getBotonDesasignar() {return BotonDesasignar;}
	public JButton getBotonSinRevisores() {return BotonSinRevisores;}
	public JButton getBotonRevisores() {return BotonRevisores;}
}

