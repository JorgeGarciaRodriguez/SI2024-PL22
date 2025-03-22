package rechazar_aceptarconcambios;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class Rechazar_aceptarView {

	private JFrame frame;
	private JList<String> ListaArticulosPendientes;
	private JLabel LabelValoracion;
	private JButton BotonRechazoAutomatico;
	private JButton BotonAceptacionConCambios;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Rechazar_aceptarView window = new Rechazar_aceptarView();
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
	public Rechazar_aceptarView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Articulos pendientes");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 106, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 106, 214);
		frame.getContentPane().add(scrollPane);
		
		ListaArticulosPendientes=new JList<>();
		scrollPane.setViewportView(ListaArticulosPendientes);
		
		BotonRechazoAutomatico = new JButton("Rechazo auto.");
		BotonRechazoAutomatico.setBounds(126, 227, 216, 23);
		frame.getContentPane().add(BotonRechazoAutomatico);
		
		JLabel lblNewLabel_1 = new JLabel("Valoración global");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(126, 38, 106, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		LabelValoracion = new JLabel("");
		LabelValoracion.setBounds(136, 63, 96, 28);
		frame.getContentPane().add(LabelValoracion);
		
		BotonAceptacionConCambios = new JButton("Aceptación con cambios auto.");
		BotonAceptacionConCambios.setBounds(126, 193, 216, 23);
		frame.getContentPane().add(BotonAceptacionConCambios);
	}
	
	public JFrame getFrame() {return frame;}
	public JList<String> getListaArticulos() {return this.ListaArticulosPendientes;}
	public JLabel getLabelValoracion() {return this.LabelValoracion;}
	public JButton getBotonRechazoAutomatico() {return BotonRechazoAutomatico;}
	public JButton getBotonAceptacionConCambios() {return BotonAceptacionConCambios;}
}
