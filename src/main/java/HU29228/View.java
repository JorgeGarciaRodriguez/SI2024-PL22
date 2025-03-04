package HU29228;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class View {
    private JFrame frame;
    private JTable tableArticulos;
    private JTable tableAutores;
    private JTextArea infoArticulo;

    public View() {
        frame = new JFrame("Visualización de Artículos del Autor");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel artículos
        JPanel panelArticulos = new JPanel(new BorderLayout());
        panelArticulos.setBorder(BorderFactory.createTitledBorder("Artículos"));

        String[] columnNamesArticulos = {"Artículo", "Título Artículo"};
        tableArticulos = new JTable(new DefaultTableModel(columnNamesArticulos, 0));
        JScrollPane scrollPaneArticulos = new JScrollPane(tableArticulos);
        panelArticulos.add(scrollPaneArticulos, BorderLayout.CENTER);

        frame.add(panelArticulos, BorderLayout.WEST);

        // Panel info artículo
        JPanel panelInfo = new JPanel(new BorderLayout());
        panelInfo.setBorder(BorderFactory.createTitledBorder("Información"));
        infoArticulo = new JTextArea();
        infoArticulo.setEditable(false);
        panelInfo.add(new JScrollPane(infoArticulo), BorderLayout.CENTER);

        frame.add(panelInfo, BorderLayout.CENTER);

        // Panel autores
        JPanel panelAutores = new JPanel(new BorderLayout());
        panelAutores.setBorder(BorderFactory.createTitledBorder("Autores"));

        String[] columnNamesAutores = {"Nombre", "Correo", "Organización", "Grupo Investigación"};
        tableAutores = new JTable(new DefaultTableModel(columnNamesAutores, 0));
        JScrollPane scrollPaneAutores = new JScrollPane(tableAutores);
        panelAutores.add(scrollPaneAutores, BorderLayout.CENTER);

        frame.add(panelAutores, BorderLayout.SOUTH);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JTable getTableArticulos() {
        return tableArticulos;
    }

    public JTable getTableAutores() {
        return tableAutores;
    }

    public void setInfoArticulo(String numero, String titulo, String palabrasClave, String resumen, String fichero, String fechaEnvio, String enviadoPor) {
        String info = "Número: " + numero + "\n" +
                      "Título: " + titulo + "\n" +
                      "Palabras Clave: " + palabrasClave + "\n" +
                      "Resumen: " + resumen + "\n" +
                      "Fichero: " + fichero + "\n" +
                      "Fecha Envío: " + fechaEnvio + "\n" +
                      "Enviado por: " + enviadoPor;
        infoArticulo.setText(info);
    }

    public void updateArticleInfo(Object[] detalles) {
    	
    }
}
