package HU29228;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class View {
    private JFrame frame;
    private JTable tableArticulos;
    private JTable tableAutores;
    private JTextArea txtInformacion;
    
    public View() {
        frame = new JFrame("Visualización de Artículos del Autor");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel lblArticulos = new JLabel("Artículos del Autor");
        lblArticulos.setBounds(20, 10, 200, 20);
        frame.add(lblArticulos);
        
        String[] columnasArticulos = {"Artículo", "Título"};
        DefaultTableModel modeloArticulos = new DefaultTableModel(null, columnasArticulos);
        tableArticulos = new JTable(modeloArticulos);
        JScrollPane scrollArticulos = new JScrollPane(tableArticulos);
        scrollArticulos.setBounds(20, 40, 350, 150);
        frame.add(scrollArticulos);
        
        JLabel lblInformacion = new JLabel("Información del Artículo");
        lblInformacion.setBounds(400, 10, 200, 20);
        frame.add(lblInformacion);
        
        txtInformacion = new JTextArea();
        txtInformacion.setBounds(400, 40, 450, 150);
        txtInformacion.setLineWrap(true);
        txtInformacion.setWrapStyleWord(true);
        txtInformacion.setEditable(false);
        frame.add(txtInformacion);
        
        JLabel lblAutores = new JLabel("Autores");
        lblAutores.setBounds(20, 210, 200, 20);
        frame.add(lblAutores);
        
        String[] columnasAutores = {"Nombre", "Correo", "Organización", "Grupo Investigación"};
        DefaultTableModel modeloAutores = new DefaultTableModel(null, columnasAutores);
        tableAutores = new JTable(modeloAutores);
        JScrollPane scrollAutores = new JScrollPane(tableAutores);
        scrollAutores.setBounds(20, 240, 830, 150);
        frame.add(scrollAutores);
    }
    
    public void show() {
        frame.setVisible(true);
    }
    
    public JFrame getFrame() { return frame; }
    public JTable getTableArticulos() { return tableArticulos; }
    public JTable getTableAutores() { return tableAutores; }
    public JTextArea getTxtInformacion() { return txtInformacion; }
    
    public void actualizarInformacionArticulo(Object[] detalles) {
        String info = "Número: " + detalles[0] + "\n" +
                      "Título: " + detalles[1] + "\n" +
                      "Palabras Clave: " + detalles[2] + "\n" +
                      "Resumen: " + detalles[3] + "\n" +
                      "Fichero: " + detalles[4] + "\n" +
                      "Fecha Envío: " + detalles[5] + "\n" +
                      "Enviado por: " + detalles[6];
        txtInformacion.setText(info);
    }
}