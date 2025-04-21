package HU29806;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class SubrevisorView {
    private JFrame frame;
    private JTable tableInvitaciones;
    private DefaultTableModel tableModel;
    private JComboBox<String> cbSubrevisores;
    private JComboBox<String> cbFiltroTrack;
    private JButton btnFiltrar;
    private JButton btnResponder;

    public SubrevisorView() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Gestión de Subrevisores - HU29806");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 650);
        frame.setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        mainPanel.setLayout(new BorderLayout(15, 15));
        frame.getContentPane().add(mainPanel);

        // Panel superior con controles
        JPanel topPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        topPanel.setBorder(new EmptyBorder(0, 0, 15, 0));
        topPanel.setBackground(Color.WHITE);
        
        // Panel de selección de subrevisor
        JPanel panelSubrevisor = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSubrevisor.setBackground(Color.WHITE);
        JLabel lblSubrevisor = new JLabel("Subrevisor:");
        lblSubrevisor.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cbSubrevisores = new JComboBox<>();
        cbSubrevisores.setPreferredSize(new Dimension(300, 25));
        cbSubrevisores.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panelSubrevisor.add(lblSubrevisor);
        panelSubrevisor.add(cbSubrevisores);
        
        // Panel de filtrado por track
        JPanel panelFiltro = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelFiltro.setBackground(Color.WHITE);
        JLabel lblFiltro = new JLabel("Filtrar por Track:");
        lblFiltro.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cbFiltroTrack = new JComboBox<>();
        cbFiltroTrack.setPreferredSize(new Dimension(250, 25));
        cbFiltroTrack.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panelFiltro.add(lblFiltro);
        panelFiltro.add(cbFiltroTrack);
        panelFiltro.add(btnFiltrar);
        
        topPanel.add(panelSubrevisor);
        topPanel.add(panelFiltro);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        
        // Panel central con tabla de invitaciones
        tableModel = new DefaultTableModel(
            new Object[][] {},
            new String[] {"ID Artículo", "Título", "Track", "Revisor Principal", "Estado"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tableInvitaciones = new JTable(tableModel);
        tableInvitaciones.setRowHeight(25);
        tableInvitaciones.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tableInvitaciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane scrollPane = new JScrollPane(tableInvitaciones);
        
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(createTitledBorder("Invitaciones de Colaboración", new Color(70, 130, 180)));
        tablePanel.setBackground(Color.WHITE);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        
        // Panel inferior con botones
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        bottomPanel.setBackground(Color.WHITE);
        
        btnResponder = new JButton("Responder Invitación");
        btnResponder.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnResponder.setBackground(new Color(70, 130, 180));
        btnResponder.setForeground(Color.WHITE);
        btnResponder.setFocusPainted(false);
        btnResponder.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        
        bottomPanel.add(btnResponder);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
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

    public void cargarSubrevisores(List<String[]> subrevisores) {
        cbSubrevisores.removeAllItems();
        for (String[] subrevisor : subrevisores) {
            cbSubrevisores.addItem(subrevisor[0] + " - " + subrevisor[1]);
        }
    }

    public void cargarTracks(List<Map<String, Object>> tracks) {
        cbFiltroTrack.removeAllItems();
        cbFiltroTrack.addItem("Todos los tracks");
        for (Map<String, Object> track : tracks) {
            cbFiltroTrack.addItem(track.get("id") + " - " + track.get("nombre"));
        }
    }

    public void cargarInvitaciones(List<Map<String, Object>> invitaciones) {
        tableModel.setRowCount(0);
        for (Map<String, Object> invitacion : invitaciones) {
            // Crear strings descriptivas para mostrar en la tabla
            String trackDisplay = invitacion.get("idTrack") + " - " + invitacion.get("track");
            String revisorDisplay = invitacion.get("idRevisorPrincipal") + " - " + invitacion.get("revisor_principal");
            
            tableModel.addRow(new Object[] {
                invitacion.get("idArticulo"),
                invitacion.get("titulo"),
                trackDisplay,
                revisorDisplay,
                invitacion.get("estado_invitacion")
            });
        }
    }

    public int getSelectedSubrevisorId() {
        String selected = (String) cbSubrevisores.getSelectedItem();
        if (selected == null) return -1;
        return Integer.parseInt(selected.split(" - ")[0]);
    }

    public Integer getSelectedTrackId() {
        String selected = (String) cbFiltroTrack.getSelectedItem();
        if (selected == null || selected.equals("Todos los tracks")) {
            return null;
        }
        return Integer.parseInt(selected.split(" - ")[0]);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JComboBox<String> getCbSubrevisores() {
        return cbSubrevisores;
    }

    public JComboBox<String> getCbFiltroTrack() {
        return cbFiltroTrack;
    }

    public JButton getBtnFiltrar() {
        return btnFiltrar;
    }

    public JButton getBtnResponder() {
        return btnResponder;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JTable getTableInvitaciones() {
        return tableInvitaciones;
    }
}