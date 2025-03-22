package Discusiones;

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



public class HU29511_View {


	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HU29511_View window = new HU29511_View();
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
	public HU29511_View() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 480, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		panel.setBackground(Color.GRAY);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		btnDiscutir = new JButton("DISCUTIR ARTICULO");
		btnDiscutir.setBounds(20, 336, 137, 21);
		panel.add(btnDiscutir);

		btnRegresar = new JButton("REGRESAR");
		btnRegresar.setBounds(330, 378, 98, 21);
		panel.add(btnRegresar);

		lblNewLabel = new JLabel("LISTA DE ARTICULOS DUDOSOS");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(20, 46, 250, 37);
		panel.add(lblNewLabel);


		listArticulos = new JList<>();		
		scrollPane_1 = new JScrollPane(listArticulos);
		scrollPane_1.setBounds(20, 93, 300, 220);
		panel.add(scrollPane_1);


	}

	public JFrame getFrame() {
		return frame;
	}

	public JPanel getPanel() {
		return panel;
	}

	public JButton getBtnDiscutir() {
		return btnDiscutir;
	}

	public JButton getBtnRegresar() {
		return btnRegresar;
	}

	public JList<String> getListArticulos() {
		return listArticulos;
	}






	private JPanel panel;
	private JButton btnDiscutir;
	private JButton btnRegresar;

	private JLabel lblNewLabel;

	JList<String> listArticulos;

	private JScrollPane scrollPane_1;




}
