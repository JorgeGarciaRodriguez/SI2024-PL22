package modificar_articulos;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.List;
import nuevo_envio.*;
public class Cambios_View {

    private JFrame frame;
    private JTable table;
    private JTextField tfTitulo, tfPalabrasClave, tfArticulo, tfResumen;
    private JTextField tfNombre, tfCorreo, tfOrganizacion, tfGrupInvs;
    private JButton btnAñadir, btnEnviar;
    private JLabel lblNumeroAleatorio;
    private JList<String> listaTracks, listaPalabrasClave;
    private JCheckBox chkModificable;
    private H29222_Model model;
    

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                H29222_Model model = new H29222_Model();
                Cambios_View window = new Cambios_View(model);
                window.getFrame().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Cambios_View(H29222_Model model) {
        this.model = model;
        initialize();
        List<String> nombresTracks = model.obtenerNombresTracks();
        actualizarListaTracks(nombresTracks);
        
        // Listener para mostrar palabras clave cuando se selecciona un track
        listaTracks.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedTrack = listaTracks.getSelectedValue();
                    if (selectedTrack != null) {
                        List<String> palabrasClave = model.obtenerPalabrasClavePorTrack(selectedTrack);
                        actualizarPalabrasClaveTrack(palabrasClave);
                    }
                }
            }
        });
    }

    private void initialize() {
        // Configuración del frame principal
        frame = new JFrame("Sistema de Envío de Artículos Científicos");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 750);
        frame.setLocationRelativeTo(null);
        
        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        mainPanel.setLayout(new BorderLayout(15, 15));
        frame.getContentPane().add(mainPanel);

        // Panel superior - Información del artículo
        JPanel articlePanel = createArticlePanel();
        mainPanel.add(articlePanel, BorderLayout.NORTH);

        // Panel central - Autores y Tracks
        JPanel centerPanel = createCenterPanel();
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Panel inferior - Número de artículo y botón enviar
        JPanel bottomPanel = createBottomPanel();
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createArticlePanel() {
        JPanel panel = new JPanel();
        panel.setBorder(createTitledBorder("Información del Artículo", new Color(70, 130, 180)));
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lblTitulo = createLabel("Título:");
        panel.add(lblTitulo, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        tfTitulo = createTextField();
        tfTitulo.setEnabled(false);
        panel.add(tfTitulo, gbc);

        // Palabras clave
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel lblPalabrasClave = createLabel("Palabras clave:");
        panel.add(lblPalabrasClave, gbc);

        gbc.gridx = 1;
        tfPalabrasClave = createTextField();
        panel.add(tfPalabrasClave, gbc);

        // Artículo
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel lblArticulo = createLabel("Artículo:");
        panel.add(lblArticulo, gbc);

        gbc.gridx = 1;
        tfArticulo = createTextField();
        panel.add(tfArticulo, gbc);

        // Resumen
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel lblResumen = createLabel("Resumen:");
        panel.add(lblResumen, gbc);

        gbc.gridx = 1;
        gbc.gridheight = 2;
        tfResumen = new JTextField();
        tfResumen.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tfResumen.setPreferredSize(new Dimension(300, 60));
        panel.add(tfResumen, gbc);
        


        return panel;
    }

    private JPanel createCenterPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 20, 0));
        panel.setBackground(Color.WHITE);
        
        // Panel de Autores
        JPanel authorsPanel = new JPanel(new BorderLayout(10, 10));
        authorsPanel.setBorder(createTitledBorder("Autores", new Color(70, 130, 180)));
        authorsPanel.setBackground(Color.WHITE);
        
        // Tabla de autores
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Correo");
        tableModel.addColumn("Organización");
        tableModel.addColumn("Grupo Invst");
        
        table = new JTable(tableModel);
        table.setRowHeight(25);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setGridColor(new Color(200, 200, 200));
        
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setPreferredSize(new Dimension(400, 200));
        authorsPanel.add(tableScroll, BorderLayout.CENTER);
        
        // Panel para añadir nuevo autor
        JPanel newAuthorPanel = createNewAuthorPanel();
        authorsPanel.add(newAuthorPanel, BorderLayout.SOUTH);
        
        // Panel de Tracks
        JPanel tracksPanel = new JPanel(new BorderLayout(10, 10));
        tracksPanel.setBorder(createTitledBorder("Tracks", new Color(70, 130, 180)));
        tracksPanel.setBackground(Color.WHITE);
        
        // Lista de Tracks
        listaTracks = new JList<>();
        listaTracks.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaTracks.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        listaTracks.setBackground(new Color(240, 240, 240));
        
        JScrollPane tracksScroll = new JScrollPane(listaTracks);
        tracksScroll.setPreferredSize(new Dimension(300, 200));
        tracksPanel.add(tracksScroll, BorderLayout.CENTER);
        
        // Panel de palabras clave del track
        JPanel keywordsPanel = new JPanel(new BorderLayout());
        keywordsPanel.setBorder(createTitledBorder("Palabras clave del track seleccionado", new Color(100, 149, 237)));
        keywordsPanel.setBackground(Color.WHITE);
        
        listaPalabrasClave = new JList<>();
        listaPalabrasClave.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        listaPalabrasClave.setBackground(new Color(240, 240, 240));
        
        JScrollPane keywordsScroll = new JScrollPane(listaPalabrasClave);
        keywordsScroll.setPreferredSize(new Dimension(300, 100));
        keywordsPanel.add(keywordsScroll);
        
        tracksPanel.add(keywordsPanel, BorderLayout.SOUTH);
        
        // Añadir ambos paneles al panel central
        panel.add(authorsPanel);
        panel.add(tracksPanel);
        
        return panel;
    }

    private JPanel createNewAuthorPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(createTitledBorder("Añadir nuevo autor", new Color(100, 149, 237)));
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Nombre
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lblNombre = createLabel("Nombre:");
        panel.add(lblNombre, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        tfNombre = createTextField();
        panel.add(tfNombre, gbc);

        // Correo
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel lblCorreo = createLabel("Correo:");
        panel.add(lblCorreo, gbc);

        gbc.gridx = 1;
        tfCorreo = createTextField();
        panel.add(tfCorreo, gbc);

        // Organización
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel lblOrganizacion = createLabel("Organización:");
        panel.add(lblOrganizacion, gbc);

        gbc.gridx = 1;
        tfOrganizacion = createTextField();
        panel.add(tfOrganizacion, gbc);

        // Grupo Invst
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel lblGrupinvs = createLabel("Grupo Invst:");
        panel.add(lblGrupinvs, gbc);

        gbc.gridx = 1;
        tfGrupInvs = createTextField();
        panel.add(tfGrupInvs, gbc);

        // Botón Añadir
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        btnAñadir = createButton("Añadir Autor", new Color(70, 130, 180));
        panel.add(btnAñadir, gbc);
        
        return panel;
    }

    private JPanel createBottomPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        
        JLabel lblNumero = new JLabel("Número de artículo:");
        lblNumero.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        lblNumeroAleatorio = new JLabel("0000");
        lblNumeroAleatorio.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNumeroAleatorio.setForeground(new Color(70, 130, 180));
        
        btnEnviar = createButton("Enviar Artículo", new Color(34, 139, 34));
        btnEnviar.setPreferredSize(new Dimension(180, 35));
        
        panel.add(lblNumero);
        panel.add(lblNumeroAleatorio);
        panel.add(Box.createHorizontalStrut(30));
        panel.add(btnEnviar);
        
        return panel;
    }

    // Métodos auxiliares para crear componentes con estilo consistente
    private TitledBorder createTitledBorder(String title, Color color) {
        return BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(color), 
            title, 
            TitledBorder.LEFT, 
            TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 14),
            color);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return label;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField.setPreferredSize(new Dimension(200, 25));
        return textField;
    }

    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        return button;
    }

    // Métodos para actualizar la vista
    public void actualizarListaTracks(List<String> tracks) {
        listaTracks.setListData(tracks.toArray(new String[0]));
    }
    
    public void actualizarPalabrasClaveTrack(List<String> palabrasClave) {
        listaPalabrasClave.setListData(palabrasClave.toArray(new String[0]));
    }

    // Getters para los componentes
    public JFrame getFrame() { return frame; }
    public JTextField getTfNombre() { return tfNombre; }
    public JTextField getTfOrganizacion() { return tfOrganizacion; }
    public JTextField getTfGrupInvs() { return tfGrupInvs; }
    public JTextField getTfCorreo() { return tfCorreo; }
    public JButton getBtnNewButton() { return btnAñadir; }
    public JButton getBtnNewButton1() { return btnEnviar; }
    public JLabel getLb() { return lblNumeroAleatorio; }
    public DefaultTableModel getTableModel() { return (DefaultTableModel) table.getModel(); }
    public JTextField getTfTitulo() { return tfTitulo; }
    public JTextField getTfPalabrasClave() { return tfPalabrasClave; }
    public JTextField getTfArticulo() { return tfArticulo; }
    public JTextField getTfResumen() { return tfResumen; }
    public JList<String> getListaTracks() { return listaTracks; }
    public JList<String> getListaPalabrasClave() { return listaPalabrasClave; }
}