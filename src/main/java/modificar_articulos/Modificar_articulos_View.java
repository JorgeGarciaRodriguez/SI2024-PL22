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

	public JList<String> getListArticulos() {
		return listArticulos;
	}






	private JPanel panel;
	private JButton btnModificar;
	private JButton btnRegresar;
	

	private JLabel lblNewLabel;


	JList<String> listArticulos;

	private JScrollPane scrollPane_1;



}
