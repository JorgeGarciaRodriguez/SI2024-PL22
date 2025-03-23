package nuevo_envio;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Font;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;

public class H29222_View {

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
	private JButton btnAadir;
	private JLabel lblNumeroAleatorio;
    private JList<String> listaTracks;
    private JList<String> listaPalabrasClave;
    private H29222_Model model;

	/**
	 * Launch the application.
	 */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Crea una instancia de H29222_Model
                    H29222_Model model = new H29222_Model();

                    // Pasa el modelo al constructor de H29222_View
                    H29222_View window = new H29222_View(model);

                    // Haz visible la ventana
                    window.getFrame().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

	/**
	 * Create the application.
	 */
	public H29222_View(H29222_Model model) {
		this.model = model;
		initialize();
		
		// Cargar los nombres de los tracks al iniciar la vista
	    List<String> nombresTracks = model.obtenerNombresTracks();
	    actualizarListaTracks(nombresTracks);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 863, 611);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		DefaultTableModel tableModel  = new DefaultTableModel();
		tableModel .addColumn("Nombre");
		tableModel .addColumn("Correo");
		tableModel .addColumn("Organización");
		tableModel .addColumn("Grupo Invst");
		
		table = new JTable(tableModel );
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
		
		JLabel lblListaDeTracks = new JLabel("Lista de \r\ntracks");
		lblListaDeTracks.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblListaDeTracks.setBounds(26, 427, 109, 70);
		frame.getContentPane().add(lblListaDeTracks);
		
		JScrollPane PalabraTrack = new JScrollPane();
		PalabraTrack.setBounds(592, 398, 247, 150);
		frame.getContentPane().add(PalabraTrack);
		
		JLabel lblPalabrasClaveTrack = new JLabel("Palabras clave track");
		lblPalabrasClaveTrack.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPalabrasClaveTrack.setBounds(439, 427, 136, 70);
		frame.getContentPane().add(lblPalabrasClaveTrack);
		
		btnAadir = new JButton("Añadir");
		btnAadir.setBounds(26, 517, 85, 21);
		frame.getContentPane().add(btnAadir);
		
		JScrollPane ListaTrack = new JScrollPane();
		ListaTrack.setBounds(169, 398, 247, 150);
		frame.getContentPane().add(ListaTrack);
		
		
		// Crear JList para mostrar los tracks
        listaTracks = new JList<>();
        ListaTrack.setViewportView(listaTracks);

        // Crear JList para mostrar las palabras clave
        listaPalabrasClave = new JList<>();
        PalabraTrack.setViewportView(listaPalabrasClave);

        // Añadir un listener para cuando se seleccione un track
        listaTracks.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedTrack = listaTracks.getSelectedValue();
                if (selectedTrack != null) {
                    // Obtener las palabras clave del track seleccionado
                    List<String> palabrasClave = model.obtenerPalabrasClavePorTrack(selectedTrack);
                    listaPalabrasClave.setListData(palabrasClave.toArray(new String[0]));
                }
            }
        });
    }

    // Método para actualizar la lista de tracks
    public void actualizarListaTracks(List<String> tracks) {
        listaTracks.setListData(tracks.toArray(new String[0]));
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

    public JButton getBtnAñadir() {
        return btnAadir; 
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
	public JList<String> getListaTracks() {
        return listaTracks;
    }
	public JList<String> getListaPalabrasClave() {
        return listaPalabrasClave;
    }
}
