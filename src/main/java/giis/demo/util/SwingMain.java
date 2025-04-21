package giis.demo.util;

import java.awt.EventQueue;
import javax.swing.JFrame;

import aceptar_articulos.HU29225_29226_Controller;
import aceptar_articulos.HU29225_29226_Model;
import aceptar_articulos.HU29225_29226_View;
import asignar_revisores.HU29229_Controller;
import asignar_revisores.HU29229_Model;
import asignar_revisores.Hu29229_View;
import evaluacion.Evaluacion_Controller;
import evaluacion.Evaluacion_Model;
import evaluacion.Evaluacion_View;
import login.Login_Controller;
import login.Login_Model;
import login.Login_View;
import rechazar_aceptarconcambios.Rechazar_aceptarController;
import rechazar_aceptarconcambios.Rechazar_aceptarModel;
import rechazar_aceptarconcambios.Rechazar_aceptarView;
import revisar_articulo.H29224_Controller;
import revisar_articulo.H29224_Model;
import revisar_articulo.H29224_View;
import ver_revisiones.Ver_revisionesController;
import ver_revisiones.Ver_revisionesModel;
import ver_revisiones.Ver_revisionesView;
import Discusiones.*;
import HU29798.Controller;
import HU29798.Model;
import HU29798.View;
import HU29806.SubrevisorController;
import HU29806.SubrevisorModel;
import HU29806.SubrevisorView;
import modificar_articulos.*;
import nuevo_envio.H29222_Model;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import subrevisores.subrevisor_Controller;
import subrevisores.subrevisor_View;
import subrevisores.subrevisor_Model;

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
		frame.setBounds(0, 0, 630, 300);
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
		
		JButton btnModificar_articulos = new JButton("Modificar Articulos");
		btnModificar_articulos.setBounds(10, 119, 120, 23);
		frame.getContentPane().add(btnModificar_articulos);
		
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
		
		JButton btnRechazar_Aceptar = new JButton("Rechazo/Cambios");
		btnRechazar_Aceptar.setBounds(312, 188, 150, 23);
		frame.getContentPane().add(btnRechazar_Aceptar);
		
		JButton btnEvaluarArticulo = new JButton("Evaluar articulo");
		btnEvaluarArticulo.setBounds(150, 119, 134, 23);
		frame.getContentPane().add(btnEvaluarArticulo);
		

		JButton btn_ver_revisiones = new JButton("Ver revisiones");
		btn_ver_revisiones.setBounds(150, 188, 134, 23);
		frame.getContentPane().add(btn_ver_revisiones);

		JButton btnSubrevisor = new JButton("Subrevisor");
		btnSubrevisor.setBounds(150, 155, 134, 23);
		frame.getContentPane().add(btnSubrevisor);
		
		JButton btnverdis = new JButton("Ver discusiones");
		btnverdis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnverdis.setBounds(312, 217, 120, 23);
		frame.getContentPane().add(btnverdis);
		
		JLabel lblNewLabel_2_1 = new JLabel("SUBREVISOR");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setBounds(472, 62, 134, 14);
		frame.getContentPane().add(lblNewLabel_2_1);
		
		JButton btnVerPeticiones = new JButton("Ver peticiones");
		btnVerPeticiones.setBounds(482, 87, 120, 23);
		frame.getContentPane().add(btnVerPeticiones);

		
		btnHU29229.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
            public void actionPerformed(ActionEvent e) {
            	Hu29229_View v=new Hu29229_View();
            	@SuppressWarnings("unused")
                HU29229_Controller controller=new HU29229_Controller(new HU29229_Model(), v);
                v.getFrame().setVisible(true);
            }
        });
		
		btn29222.addActionListener(new ActionListener() { 
		    public void actionPerformed(ActionEvent e) {
            	Login_View v=new Login_View();
            	@SuppressWarnings("unused")
            	Login_Controller controller=new Login_Controller(new Login_Model(), v);
                v.getFrame().setVisible(true);
            }
		});

		
		btn29511.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
            public void actionPerformed(ActionEvent e) {
            	HU29511_View v=new HU29511_View();
            	@SuppressWarnings("unused")
                HU29511_Controller controller=new HU29511_Controller(new HU29511_Model(), v);
                v.getFrame().setVisible(true);
            }
        });
		
		btn29225.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
            public void actionPerformed(ActionEvent e) {
            	HU29225_29226_View v=new HU29225_29226_View();
            	@SuppressWarnings("unused")
                HU29225_29226_Controller controller=new HU29225_29226_Controller(new HU29225_29226_Model(), v);
                v.getFrame().setVisible(true);
            }
        });
		
		
		btn29224.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
            public void actionPerformed(ActionEvent e) {
            	H29224_View v=new H29224_View();
            	@SuppressWarnings("unused")
                H29224_Controller controller=new H29224_Controller(new H29224_Model(), v);
                v.getFrame().setVisible(true);
            }
        });
		
		btnModificar_articulos.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
            public void actionPerformed(ActionEvent e) {
            	H29222_Model m = new H29222_Model();
            	Modificar_articulos_View v=new Modificar_articulos_View();
            	Cambios_View c= new Cambios_View(m);
            	Modificar_articulos_Controller controller=new Modificar_articulos_Controller(new Modificar_articulos_Model(), v, c);
                v.getFrame().setVisible(true);
            }
        });
		
		btnRechazar_Aceptar.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
            public void actionPerformed(ActionEvent e) {
            	Rechazar_aceptarView v=new Rechazar_aceptarView();
            	@SuppressWarnings("unused")
            	Rechazar_aceptarController controller=new Rechazar_aceptarController(new Rechazar_aceptarModel(), v);
                v.getFrame().setVisible(true);
            }
        });
		
		btnEvaluarArticulo.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Evaluacion_View v = new Evaluacion_View();
		        @SuppressWarnings("unused")
				Evaluacion_Controller controller = new Evaluacion_Controller(new Evaluacion_Model(), v);
		        v.getFrame().setVisible(true);
		    }
		});
		
		btnSubrevisor.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        subrevisor_View v = new subrevisor_View();
		        @SuppressWarnings("unused")
				subrevisor_Controller controller = new subrevisor_Controller(new subrevisor_Model(), v);
		        v.getFrame().setVisible(true);
		    }
		});
		
		btn_ver_revisiones.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Ver_revisionesView v = new Ver_revisionesView();
		        Ver_revisionesController controller = new Ver_revisionesController(new Ver_revisionesModel(), v);
		        v.getFrame().setVisible(true);
		    }
		});
		
		btnVerPeticiones.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	
		        SubrevisorView v = new SubrevisorView();
		        SubrevisorController controller = new SubrevisorController(new SubrevisorModel(), v);
		        v.getFrame().setVisible(true);
		    }
		});
		
	}
	
	

	public JFrame getFrame() { return this.frame; }
}
