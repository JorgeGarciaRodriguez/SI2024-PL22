package giis.demo.util;

import java.awt.EventQueue;
import javax.swing.JFrame;

import aceptar_articulos.HU29225_29226_Controller;
import aceptar_articulos.HU29225_29226_Model;
import aceptar_articulos.HU29225_29226_View;
import asignar_revisores.HU29229_Controller;
import asignar_revisores.HU29229_Model;
import asignar_revisores.Hu29229_View;
import nuevo_envio.H29222_Controller;
import nuevo_envio.H29222_Model;
import nuevo_envio.H29222_View;
import rechazar_aceptarconcambios.Rechazar_aceptarController;
import rechazar_aceptarconcambios.Rechazar_aceptarModel;
import rechazar_aceptarconcambios.Rechazar_aceptarView;
import revisar_articulo.H29224_Controller;
import revisar_articulo.H29224_Model;
import revisar_articulo.H29224_View;
import Discusiones.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Punto de entrada principal que incluye botones para la ejecucion de las pantallas 
 * de las aplicaciones de ejemplo
 * y acciones de inicializacion de la base de datos.
 * No sigue MVC pues es solamente temporal para que durante el desarrollo se tenga posibilidad
 * de realizar acciones de inicializacion
 */
public class SwingMain {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() { //NOSONAR codigo autogenerado
			public void run() {
				try {
					SwingMain window = new SwingMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace(); //NOSONAR codigo autogenerado
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SwingMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Main");
		frame.setBounds(0, 0, 500, 300);
		frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		
			
		JButton btnInicializarBaseDeDatos = new JButton("Inicializar Base de Datos en Blanco");
		btnInicializarBaseDeDatos.setBounds(114, 0, 235, 23);
		btnInicializarBaseDeDatos.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				Database db=new Database();
				db.createDatabase(false);
			}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btnInicializarBaseDeDatos);
			
		JButton btnCargarDatosIniciales = new JButton("Cargar Datos Iniciales para Pruebas");
		btnCargarDatosIniciales.setBounds(114, 23, 235, 23);
		btnCargarDatosIniciales.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				Database db=new Database();
				db.createDatabase(false);
				db.loadDatabase();
			}
		});
		frame.getContentPane().add(btnCargarDatosIniciales);
		
		JButton btn29222 = new JButton("Nuevo Envio");
		btn29222.setBounds(10, 86, 102, 23);
		frame.getContentPane().add(btn29222);
		
		JButton btn29224 = new JButton("Revisar articulo");
		btn29224.setBounds(150, 86, 134, 23);
		frame.getContentPane().add(btn29224);
		
		JButton btn29225 = new JButton("Aceptar Articulos");
		btn29225.setBounds(312, 120, 150, 23);
		frame.getContentPane().add(btn29225);
		
		JButton btn29511 = new JButton("Discutir Articulos");
		btn29511.setBounds(312, 154, 150, 23);
		frame.getContentPane().add(btn29511);
		
		JButton btnHU29229 = new JButton("Asignar revisores");
		btnHU29229.setBounds(312, 86, 150, 23);
		frame.getContentPane().add(btnHU29229);
		
		JLabel lblNewLabel = new JLabel("AUTOR");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 61, 90, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("REVISOR");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(163, 61, 108, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("COORDINADOR");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(312, 61, 134, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton btnRechazar_Aceptar = new JButton("Rechazo/AceptCambios");
		btnRechazar_Aceptar.setBounds(312, 188, 150, 23);
		frame.getContentPane().add(btnRechazar_Aceptar);
		
		btnHU29229.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
            public void actionPerformed(ActionEvent e) {
            	Hu29229_View v=new Hu29229_View();
                HU29229_Controller controller=new HU29229_Controller(new HU29229_Model(), 
                        v);
                v.getFrame().setVisible(true);
            }
        });
		
		btn29222.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
            public void actionPerformed(ActionEvent e) {
            	H29222_View v=new H29222_View();
                H29222_Controller controller=new H29222_Controller(new H29222_Model(), 
                        v);
                v.getFrame().setVisible(true);
            }
        });
		
		btn29511.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
            public void actionPerformed(ActionEvent e) {
            	HU29511_View v=new HU29511_View();
                HU29511_Controller controller=new HU29511_Controller(new HU29511_Model(), 
                        v);
                v.getFrame().setVisible(true);
            }
        });
		
		btn29225.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
            public void actionPerformed(ActionEvent e) {
            	HU29225_29226_View v=new HU29225_29226_View();
                HU29225_29226_Controller controller=new HU29225_29226_Controller(new HU29225_29226_Model(), 
                        v);
                v.getFrame().setVisible(true);
            }
        });
		
		
		btn29224.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
            public void actionPerformed(ActionEvent e) {
            	H29224_View v=new H29224_View();
                H29224_Controller controller=new H29224_Controller(new H29224_Model(), 
                        v);
                v.getFrame().setVisible(true);
            }
        });
		
		btnRechazar_Aceptar.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
            public void actionPerformed(ActionEvent e) {
            	Rechazar_aceptarView v=new Rechazar_aceptarView();
            	Rechazar_aceptarController controller=new Rechazar_aceptarController(new Rechazar_aceptarModel(), 
                        v);
                v.getFrame().setVisible(true);
            }
        });
		
	}
	
	

	public JFrame getFrame() { return this.frame; }
}
