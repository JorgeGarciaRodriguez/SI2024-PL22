package HU29798;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class View {
	private JFrame frame;
	private JTable tableDiscusiones;
	private JPanel panelDetalles;
	private Model model;
	private JComboBox<String> comboFiltro;
	private JButton btncerrarDisc;
	private JLabel lblAnotaciones;
	private JTextArea taAnotacion;
	private JButton btnSubirAnotacion;

	public View(Model model) {
		this.model = model;
		initialize();
	}

	private void initialize() {
		frame = new JFrame("Gestión de Discusiones - Coordinador");
		frame.setSize(1000, 700);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Panel superior con el filtro
		JPanel panelFiltro = new JPanel();
		panelFiltro.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		JLabel lblFiltro = new JLabel("Filtrar por estado:");
		lblFiltro.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboFiltro = new JComboBox<>(new String[]{"Todas", "Abiertas", "Cerradas", "Finalizadas por revisores", "Cumplido deadline"});
		comboFiltro.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboFiltro.addActionListener(e -> actualizarVista());
		panelFiltro.add(lblFiltro);
		panelFiltro.add(comboFiltro);
		mainPanel.add(panelFiltro, BorderLayout.NORTH);

		String[] columnNames = {"ID Discusión", "ID Artículo", "Título Artículo", "Estado"};
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableDiscusiones = new JTable(tableModel);
		tableDiscusiones.setRowHeight(30);
		tableDiscusiones.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < tableDiscusiones.getColumnCount(); i++) {
			tableDiscusiones.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		JScrollPane scrollPane = new JScrollPane(tableDiscusiones);
		mainPanel.add(scrollPane, BorderLayout.WEST);

		panelDetalles = new JPanel();
		panelDetalles.setLayout(new BoxLayout(panelDetalles, BoxLayout.Y_AXIS));
		panelDetalles.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JScrollPane detallesScroll = new JScrollPane(panelDetalles);
		mainPanel.add(detallesScroll, BorderLayout.CENTER);

		JButton btnActualizar = new JButton("Actualizar Lista");
		btnActualizar.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnActualizar.setBackground(new Color(70, 130, 180));
		btnActualizar.setForeground(Color.WHITE);
		btnActualizar.addActionListener(e -> actualizarVista());

		btncerrarDisc = new JButton("Cerrar Discusion");
		btncerrarDisc.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btncerrarDisc.setBackground(new Color(70, 130, 180));
		btncerrarDisc.setForeground(Color.WHITE);
		btncerrarDisc.addActionListener(e -> actualizarVista());
		btncerrarDisc.setEnabled(false);


		JPanel panelBoton = new JPanel();
		panelBoton.add(btnActualizar);
		panelBoton.add(btncerrarDisc);
		mainPanel.add(panelBoton, BorderLayout.SOUTH);

		frame.getContentPane().add(mainPanel);

		actualizarVista();

		tableDiscusiones.getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				int selectedRow = tableDiscusiones.getSelectedRow();
				if (selectedRow >= 0) {
					int idDiscusion = (int) tableDiscusiones.getValueAt(selectedRow, 0);

					mostrarDetallesDiscusion(idDiscusion);
				}
			}
		});
	}

	public void actualizarVista() {
		DefaultTableModel modelTable = (DefaultTableModel) tableDiscusiones.getModel();
		modelTable.setRowCount(0);

		String filtro = (String) comboFiltro.getSelectedItem();
		List<Model.Discusion> discusiones = model.obtenerTodasLasDiscusiones();

		for (Model.Discusion discusion : discusiones) {
			if (aplicarFiltro(discusion, filtro)) {
				modelTable.addRow(new Object[]{
						discusion.getIdDiscusion(),
						discusion.getIdArticulo(),
						discusion.getTituloArticulo(),
						discusion.getEstado()
				});
			}
		}
		enableBtnCerrarDisc();
		cerrarDisc();
		panelDetalles.removeAll();
		panelDetalles.revalidate();
		panelDetalles.repaint();
	}

	private boolean aplicarFiltro(Model.Discusion discusion, String filtro) {
		if (filtro.equals("Todas")) return true;

		String estado = discusion.getEstado();
		switch (filtro) {
		case "Abiertas":
			return "abierta".equals(estado);
		case "Cerradas":
			return "cerrada".equals(estado);
		case "Finalizadas por revisores":
			return "Finalizada por revisores".equals(estado);
		case "Cumplido deadline":
			return "Cumplido deadline".equals(estado);
		default:
			return true;
		}
	}
	private void enableBtnCerrarDisc() {
		if(comboFiltro.getSelectedItem().toString().equals("Cumplido deadline") ||
				comboFiltro.getSelectedItem().toString().equals("Finalizadas por revisores")) btncerrarDisc.setEnabled(true);
		else btncerrarDisc.setEnabled(false);
	}
	private void cerrarDisc() {
		btncerrarDisc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int selectedRow = tableDiscusiones.getSelectedRow();
				if (selectedRow >= 0) {
					int idDiscusion = (int) tableDiscusiones.getValueAt(selectedRow, 0);
					model.updateDiscusion(idDiscusion);
				}

			}
		});
	}
	private void mostrarDetallesDiscusion(int idDiscusion) {
		panelDetalles.removeAll();

		List<Model.Discusion> discusiones = model.obtenerTodasLasDiscusiones();
		Model.Discusion discusionSeleccionada = null;

		for (Model.Discusion d : discusiones) {
			if (d.getIdDiscusion() == idDiscusion) {
				discusionSeleccionada = d;
				break;
			}
		}

		if (discusionSeleccionada == null) return;

		JLabel lblTitulo = new JLabel("Discusión #" + idDiscusion + " - Artículo: " + discusionSeleccionada.getTituloArticulo());
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelDetalles.add(lblTitulo);

		JLabel lblEstado = new JLabel("Estado: " + discusionSeleccionada.getEstado());
		lblEstado.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblEstado.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelDetalles.add(lblEstado);

		panelDetalles.add(Box.createVerticalStrut(20));

		JLabel lblRevisiones = new JLabel("Revisiones:");
		lblRevisiones.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblRevisiones.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelDetalles.add(lblRevisiones);

		for (Model.Revision revision : discusionSeleccionada.getRevisiones()) {
			JPanel panelRevision = crearPanelRevision(revision);
			panelRevision.setAlignmentX(Component.LEFT_ALIGNMENT);
			panelDetalles.add(panelRevision);
			panelDetalles.add(Box.createVerticalStrut(10));
		}
		lblAnotaciones = new JLabel("Anotaciones: ");
		lblAnotaciones.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblAnotaciones.setAlignmentX(Component.LEFT_ALIGNMENT);
		lblAnotaciones.setVisible(false);
		panelDetalles.add(lblAnotaciones);

		taAnotacion = new JTextArea();
		taAnotacion.setAlignmentX(Component.LEFT_ALIGNMENT);
		taAnotacion.setVisible(false);
		panelDetalles.add(taAnotacion);
		
		btnSubirAnotacion = new JButton("Subir Anotacion");
		btnSubirAnotacion.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnSubirAnotacion.setBackground(new Color(70, 130, 180));
		btnSubirAnotacion.setForeground(Color.WHITE);
		taAnotacion.setAlignmentX(Component.LEFT_ALIGNMENT);
		btnSubirAnotacion.setVisible(false);
		panelDetalles.add(btnSubirAnotacion);
		
		activaAnotaciones(idDiscusion);
		btnSubir(idDiscusion);

		panelDetalles.revalidate();
		panelDetalles.repaint();
	}

    private void activaAnotaciones(int id) {
    	String estado = model.getEstado(id);
    	if(estado.equals("abierta")||estado.equals("Cumplido deadline")) {
    		lblAnotaciones.setVisible(true);
    		taAnotacion.setVisible(true);
    		btnSubirAnotacion.setVisible(true);
    		
    	}
    }
    private void btnSubir(int id_Discusion) {

    	btnSubirAnotacion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
		    	String anotacion = taAnotacion.getText();
				model.insertAnotacion(id_Discusion, anotacion);
			}
		});
    }

	private JPanel crearPanelRevision(Model.Revision revision) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(200, 200, 200)),
				BorderFactory.createEmptyBorder(10, 10, 10, 10)
				));

		JLabel lblRevisor = new JLabel("Revisor: " + revision.getNombreRevisor() +
				" (Experto: " + revision.getExperto() + ")");
		lblRevisor.setFont(new Font("Segoe UI", Font.BOLD, 14));
		panel.add(lblRevisor);

		JLabel lblDecision = new JLabel("Decisión: " + revision.getDecisionTexto());
		lblDecision.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panel.add(lblDecision);

		JLabel lblComentAutor = new JLabel("Comentarios para el autor:");
		lblComentAutor.setFont(new Font("Segoe UI", Font.BOLD, 13));
		panel.add(lblComentAutor);

		JLabel lblTextoAutor = new JLabel("<html>" + revision.getComentAutor() + "</html>");
		lblTextoAutor.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		panel.add(lblTextoAutor);

		JLabel lblComentCoor = new JLabel("Comentarios para el coordinador:");
		lblComentCoor.setFont(new Font("Segoe UI", Font.BOLD, 13));
		panel.add(lblComentCoor);

		JLabel lblTextoCoor = new JLabel("<html>" + revision.getComentCoor() + "</html>");
		lblTextoCoor.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		panel.add(lblTextoCoor);



		return panel;
	}

	public JFrame getFrame() {
		return frame;
	}
	public JButton getbtncerrarDisc() {
		return btncerrarDisc;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			Model model = new Model();
			View view = new View(model);
			view.getFrame().setVisible(true);
		});
	}
}
