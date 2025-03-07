package giis.demo.util;

import java.awt.EventQueue;
import javax.swing.JFrame;

import HU_29223.HU29223_Controller;
import HU_29223.HU29223_Model;
import HU_29223.HU29223_View;
import asignar_revisores.HU29229_Controller;
import asignar_revisores.HU29229_Model;
import asignar_revisores.Hu29229_View;
import nuevo_envio.H29222_Controller;
import nuevo_envio.H29222_Model;
import nuevo_envio.H29222_View;
import revisar_articulo.H29224_Controller;
import revisar_articulo.H29224_Model;
import revisar_articulo.H29224_View;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		frame.setBounds(0, 0, 352, 225);
		frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		
			
		JButton btnInicializarBaseDeDatos = new JButton("Inicializar Base de Datos en Blanco");
		btnInicializarBaseDeDatos.setBounds(51, 0, 235, 23);
		btnInicializarBaseDeDatos.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				Database db=new Database();
				db.createDatabase(false);
			}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btnInicializarBaseDeDatos);
			
		JButton btnCargarDatosIniciales = new JButton("Cargar Datos Iniciales para Pruebas");
		btnCargarDatosIniciales.setBounds(51, 23, 235, 23);
		btnCargarDatosIniciales.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				Database db=new Database();
				db.createDatabase(false);
				db.loadDatabase();
			}
		});
		frame.getContentPane().add(btnCargarDatosIniciales);
		
		JButton btn29222 = new JButton("HU29222");
		btn29222.setBounds(10, 57, 90, 23);
		frame.getContentPane().add(btn29222);
		
		JButton btnHU29223 = new JButton("HU29223");
		btnHU29223.setBounds(125, 57, 90, 23);
		frame.getContentPane().add(btnHU29223);
		
		btnHU29223.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
            public void actionPerformed(ActionEvent e) {
            	HU29223_View v=new HU29223_View();
                HU29223_Controller controller=new HU29223_Controller(new HU29223_Model(), 
                        v);
                v.getFrame().setVisible(true);
            }
        });
		
		JButton btn29224 = new JButton("HU29224");
		btn29224.setBounds(236, 57, 90, 23);
		frame.getContentPane().add(btn29224);
		
		JButton btnNewButton_3 = new JButton("HU29225");
		btnNewButton_3.setBounds(10, 104, 90, 23);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("HU29226");
		btnNewButton_4.setBounds(125, 104, 90, 23);
		frame.getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("HU29227");
		btnNewButton_5.setBounds(236, 104, 90, 23);
		frame.getContentPane().add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("HU29228");
		btnNewButton_6.setBounds(10, 152, 90, 23);
		frame.getContentPane().add(btnNewButton_6);
		
		JButton btnHU29229 = new JButton("HU29229");
		btnHU29229.setBounds(125, 152, 90, 23);
		frame.getContentPane().add(btnHU29229);
		
		btnHU29229.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
            public void actionPerformed(ActionEvent e) {
            	Hu29229_View v=new Hu29229_View();
                HU29229_Controller controller=new HU29229_Controller(new HU29229_Model(), 
                        v);
                v.getFrame().setVisible(true);
            }
        });
		
		JButton btnNewButton_1 = new JButton("HU29230");
		btnNewButton_1.setBounds(237, 152, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		btn29222.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
            public void actionPerformed(ActionEvent e) {
            	H29222_View v=new H29222_View();
                H29222_Controller controller=new H29222_Controller(new H29222_Model(), 
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

	}

	public JFrame getFrame() { return this.frame; }
	
	
}
