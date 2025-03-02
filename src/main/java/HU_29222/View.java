package HU_29222;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

public class View {

	private JFrame frame;
	private JTable table;
	private JTextField tfTitulo;
	private JTextField tfPalabrasClave;
	private JTextField tfArticulo;
	private JTextField tfResumen;
	private JLabel lblOtroAutor;
	private JLabel lblNombre;
	private JLabel lblCorreo;
	private JLabel lblOrganizacion;
	private JLabel lblGrupinvs;
	private JLabel lblNDeArticulo;
	private JTextField tfNombre;
	private JTextField tfCorreo;
	private JTextField tfOrganizacion;
	private JTextField tfGrupInvs;
	private JButton btnNewButton;
	private JButton btnEnviar;
	private JLabel lblNumeroAleatorio;

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
		frame.setBounds(100, 100, 863, 443);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Nombre");
		model.addColumn("Correo");
		model.addColumn("Organización");
		model.addColumn("Grupo Invst");
		
		table = new JTable(model);
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		table.setBounds(145, 211, 307, 150);
		frame.getContentPane().add(table);
		
		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTitulo.setBounds(26, 38, 46, 25);
		frame.getContentPane().add(lblTitulo);
		
		JLabel lblPalabrasClave = new JLabel("Palabras clave");
		lblPalabrasClave.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPalabrasClave.setBounds(26, 73, 94, 19);
		frame.getContentPane().add(lblPalabrasClave);
		
		JLabel lblArticulo = new JLabel("Articulo");
		lblArticulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblArticulo.setBounds(26, 102, 94, 19);
		frame.getContentPane().add(lblArticulo);
		
		JLabel lblResumen = new JLabel("Resumen");
		lblResumen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblResumen.setBounds(26, 135, 94, 19);
		frame.getContentPane().add(lblResumen);
		
		tfTitulo = new JTextField();
		tfTitulo.setBounds(145, 43, 96, 19);
		frame.getContentPane().add(tfTitulo);
		tfTitulo.setColumns(10);
		
		tfPalabrasClave = new JTextField();
		tfPalabrasClave.setColumns(10);
		tfPalabrasClave.setBounds(145, 75, 96, 19);
		frame.getContentPane().add(tfPalabrasClave);
		
		tfArticulo = new JTextField();
		tfArticulo.setBounds(145, 104, 96, 19);
		frame.getContentPane().add(tfArticulo);
		tfArticulo.setColumns(10);
		
		tfResumen = new JTextField();
		tfResumen.setBounds(145, 137, 203, 50);
		frame.getContentPane().add(tfResumen);
		tfResumen.setColumns(10);
		
		lblOtroAutor = new JLabel("Otro autor:");
		lblOtroAutor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOtroAutor.setBounds(540, 46, 94, 25);
		frame.getContentPane().add(lblOtroAutor);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNombre.setBounds(540, 81, 94, 25);
		frame.getContentPane().add(lblNombre);
		
		lblCorreo = new JLabel("Correo");
		lblCorreo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCorreo.setBounds(540, 118, 94, 25);
		frame.getContentPane().add(lblCorreo);
		
		lblOrganizacion = new JLabel("Organizacion");
		lblOrganizacion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOrganizacion.setBounds(540, 155, 94, 25);
		frame.getContentPane().add(lblOrganizacion);
		
		lblGrupinvs = new JLabel("GrupInvs");
		lblGrupinvs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGrupinvs.setBounds(540, 200, 94, 25);
		frame.getContentPane().add(lblGrupinvs);
		
		lblNDeArticulo = new JLabel("Nº de articulo");
		lblNDeArticulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNDeArticulo.setBounds(540, 289, 94, 25);
		frame.getContentPane().add(lblNDeArticulo);
		
		tfNombre = new JTextField();
		tfNombre.setColumns(10);
		tfNombre.setBounds(627, 86, 96, 19);
		frame.getContentPane().add(tfNombre);
		
		tfCorreo = new JTextField();
		tfCorreo.setColumns(10);
		tfCorreo.setBounds(637, 123, 96, 19);
		frame.getContentPane().add(tfCorreo);
		
		tfOrganizacion = new JTextField();
		tfOrganizacion.setColumns(10);
		tfOrganizacion.setBounds(647, 160, 96, 19);
		frame.getContentPane().add(tfOrganizacion);
		
		tfGrupInvs = new JTextField();
		tfGrupInvs.setColumns(10);
		tfGrupInvs.setBounds(637, 205, 96, 19);
		frame.getContentPane().add(tfGrupInvs);
		
		btnNewButton = new JButton("Añadir");
		btnNewButton.setBounds(601, 235, 85, 21);
		frame.getContentPane().add(btnNewButton);
		
		btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(540, 324, 85, 21);
		frame.getContentPane().add(btnEnviar);
		
		lblNumeroAleatorio = new JLabel("");
		lblNumeroAleatorio.setBounds(641, 289, 92, 21);
		frame.getContentPane().add(lblNumeroAleatorio);
		
		JLabel lblNewLabel = new JLabel("Lista de \r\nautores");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(26, 200, 109, 70);
		frame.getContentPane().add(lblNewLabel);
	}
	public JFrame getFrame() {return frame;}
	
	// Métodos get para acceder a los campos de texto

    public JTextField getTfNombre() {
        return tfNombre;
    }

    public JTextField getTfOrganizacion() {
        return tfOrganizacion;
    }

    public JTextField getTfGrupInvs() {
        return tfGrupInvs;
    }

    public JTextField getTfCorreo() {
        return tfCorreo;
    }

    public JButton getBtnNewButton() {
        return btnNewButton; 
    }
    
    public JButton getBtnNewButton1() {
        return btnEnviar; 
    }
    
    public JLabel getLb() {
        return lblNumeroAleatorio; 
    }
    
    public DefaultTableModel getTableModel() {
        return (DefaultTableModel) table.getModel();
    }

	public JTextField getTfTitulo() {
		return tfTitulo;
	}

	public JTextField getTfPalabrasClave() {
		// TODO Auto-generated method stub
		return tfPalabrasClave;
	}

	public JTextField getTfArticulo() {
		// TODO Auto-generated method stub
		return tfArticulo;
	}

	public JTextField getTfResumen() {
		// TODO Auto-generated method stub
		return tfResumen;
	}
}
