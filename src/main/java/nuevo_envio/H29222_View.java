package nuevo_envio;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.List;

public class H29222_View {

    private JFrame frame;
    private JTable table;
    private JTextField tfTitulo, tfPalabrasClave, tfArticulo, tfResumen;
    private JTextField tfNombre, tfCorreo, tfOrganizacion, tfGrupInvs;
    private JTextField tfBuscarAutor;
    private JButton btnAñadir, btnEnviar, btnBuscarAutor;
    private JLabel lblNumeroAleatorio;
    private JList<String> listaTracks, listaPalabrasClave, listaAutores;
    private JCheckBox chkModificable;
    private H29222_Model model;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                H29222_Model model = new H29222_Model();
                H29222_View window = new H29222_View(model);
                window.getFrame().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public H29222_View(H29222_Model model) {
        this.model = model;
        initialize();
        List<String> nombresTracks = model.obtenerNombresTracks();
        actualizarListaTracks(nombresTracks);
        
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
        frame = new JFrame("Sistema de Envío de Artículos Científicos");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1200, 800); // Aumentamos el tamaño para la nueva funcionalidad
        frame.setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        mainPanel.setLayout(new BorderLayout(15, 15));
        frame.getContentPane().add(mainPanel);

        JPanel articlePanel = createArticlePanel();
        mainPanel.add(articlePanel, BorderLayout.NORTH);

        JPanel centerPanel = createCenterPanel();
        mainPanel.add(centerPanel, BorderLayout.CENTER);

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

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lblTitulo = createLabel("Título:");
        panel.add(lblTitulo, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        tfTitulo = createTextField();
        panel.add(tfTitulo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel lblPalabrasClave = createLabel("Palabras clave:");
        panel.add(lblPalabrasClave, gbc);

        gbc.gridx = 1;
        tfPalabrasClave = createTextField();
        panel.add(tfPalabrasClave, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel lblArticulo = createLabel("Artículo:");
        panel.add(lblArticulo, gbc);

        gbc.gridx = 1;
        tfArticulo = createTextField();
        panel.add(tfArticulo, gbc);

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
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridheight = 1;
        JLabel lblModificable = createLabel("Modificable:");
        panel.add(lblModificable, gbc);
        
        gbc.gridx = 1;
        chkModificable = new JCheckBox();
        chkModificable.setBackground(Color.WHITE);
        panel.add(chkModificable, gbc);

        return panel;
    }

    private JPanel createCenterPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 20, 0)); // Cambiamos a 3 columnas
        panel.setBackground(Color.WHITE);
        
        // Panel de Autores (original)
        JPanel authorsPanel = new JPanel(new BorderLayout(10, 10));
        authorsPanel.setBorder(createTitledBorder("Autores", new Color(70, 130, 180)));
        authorsPanel.setBackground(Color.WHITE);
        
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
        
        JPanel newAuthorPanel = createNewAuthorPanel();
        authorsPanel.add(newAuthorPanel, BorderLayout.SOUTH);
        
        // Nuevo panel de búsqueda de coautores
        JPanel searchPanel = new JPanel(new BorderLayout(10, 10));
        searchPanel.setBorder(createTitledBorder("Buscar Coautores", new Color(100, 149, 237)));
        searchPanel.setBackground(Color.WHITE);
        
        JPanel searchControls = new JPanel(new BorderLayout(5, 5));
        tfBuscarAutor = createTextField();
        btnBuscarAutor = createButton("Buscar", new Color(70, 130, 180));
        btnBuscarAutor.setPreferredSize(new Dimension(80, 25));
        searchControls.add(new JLabel("Buscar por nombre o correo:"), BorderLayout.NORTH);
        searchControls.add(tfBuscarAutor, BorderLayout.CENTER);
        searchControls.add(btnBuscarAutor, BorderLayout.EAST);
        
        listaAutores = new JList<>();
        listaAutores.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        listaAutores.setBackground(new Color(240, 240, 240));
        listaAutores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane autoresScroll = new JScrollPane(listaAutores);
        autoresScroll.setPreferredSize(new Dimension(300, 150));
        
        searchPanel.add(searchControls, BorderLayout.NORTH);
        searchPanel.add(autoresScroll, BorderLayout.CENTER);
        
        // Panel de Tracks (original)
        JPanel tracksPanel = new JPanel(new BorderLayout(10, 10));
        tracksPanel.setBorder(createTitledBorder("Tracks", new Color(70, 130, 180)));
        tracksPanel.setBackground(Color.WHITE);
        
        listaTracks = new JList<>();
        listaTracks.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaTracks.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        listaTracks.setBackground(new Color(240, 240, 240));
        
        JScrollPane tracksScroll = new JScrollPane(listaTracks);
        tracksScroll.setPreferredSize(new Dimension(300, 200));
        tracksPanel.add(tracksScroll, BorderLayout.CENTER);
        
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
        
        // Añadimos los tres paneles al centro
        panel.add(authorsPanel);
        panel.add(searchPanel);
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
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lblNombre = createLabel("Nombre:");
        panel.add(lblNombre, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        tfNombre = createTextField();
        panel.add(tfNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel lblCorreo = createLabel("Correo:");
        panel.add(lblCorreo, gbc);

        gbc.gridx = 1;
        tfCorreo = createTextField();
        panel.add(tfCorreo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel lblOrganizacion = createLabel("Organización:");
        panel.add(lblOrganizacion, gbc);

        gbc.gridx = 1;
        tfOrganizacion = createTextField();
        panel.add(tfOrganizacion, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel lblGrupinvs = createLabel("Grupo Invst:");
        panel.add(lblGrupinvs, gbc);

        gbc.gridx = 1;
        tfGrupInvs = createTextField();
        panel.add(tfGrupInvs, gbc);

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

    public void actualizarListaTracks(List<String> tracks) {
        listaTracks.setListData(tracks.toArray(new String[0]));
    }
    
    public void actualizarPalabrasClaveTrack(List<String> palabrasClave) {
        listaPalabrasClave.setListData(palabrasClave.toArray(new String[0]));
    }
    
    public void actualizarListaAutores(List<String> autores) {
        listaAutores.setListData(autores.toArray(new String[0]));
    }

    public JFrame getFrame() { return frame; }
    public JTextField getTfNombre() { return tfNombre; }
    public JTextField getTfOrganizacion() { return tfOrganizacion; }
    public JTextField getTfGrupInvs() { return tfGrupInvs; }
    public JTextField getTfCorreo() { return tfCorreo; }
    public JButton getBtnNewButton() { return btnAñadir; }
    public JButton getBtnNewButton1() { return btnEnviar; }
    public JButton getBtnBuscarAutor() { return btnBuscarAutor; }
    public JLabel getLb() { return lblNumeroAleatorio; }
    public DefaultTableModel getTableModel() { return (DefaultTableModel) table.getModel(); }
    public JTextField getTfTitulo() { return tfTitulo; }
    public JTextField getTfPalabrasClave() { return tfPalabrasClave; }
    public JTextField getTfArticulo() { return tfArticulo; }
    public JTextField getTfResumen() { return tfResumen; }
    public JTextField getTfBuscarAutor() { return tfBuscarAutor; }
    public JList<String> getListaTracks() { return listaTracks; }
    public JList<String> getListaAutores() { return listaAutores; }
    public boolean isModificable() {return chkModificable.isSelected();}
    public JList<String> getListaPalabrasClave() { return listaPalabrasClave; }
}