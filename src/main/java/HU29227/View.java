package HU29227;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class View {
    private JFrame frame;
    private JTable tableArticulos;
    private JTable tableComentarios;
    private JButton btnVolver;
    
    public View() {
        frame = new JFrame("Revisión de Artículos Enviados");
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel lblArticulos = new JLabel("Artículos Enviados");
        lblArticulos.setBounds(20, 10, 200, 20);
        frame.add(lblArticulos);
        
        String[] columnasArticulos = {"Artículo", "Decisión", "Autor"};
        DefaultTableModel modeloArticulos = new DefaultTableModel(null, columnasArticulos);
        tableArticulos = new JTable(modeloArticulos);
        JScrollPane scrollArticulos = new JScrollPane(tableArticulos);
        scrollArticulos.setBounds(20, 40, 750, 100);
        frame.add(scrollArticulos);
        
        JLabel lblComentarios = new JLabel("Comentarios de Revisión");
        lblComentarios.setBounds(20, 150, 200, 20);
        frame.add(lblComentarios);
        
        String[] columnasComentarios = {"Comentario", "Nivel Experto", "Decisión"};
        DefaultTableModel modeloComentarios = new DefaultTableModel(null, columnasComentarios);
        tableComentarios = new JTable(modeloComentarios);
        JScrollPane scrollComentarios = new JScrollPane(tableComentarios);
        scrollComentarios.setBounds(20, 180, 750, 150);
        frame.add(scrollComentarios);
        
        btnVolver = new JButton("VOLVER");
        btnVolver.setBounds(350, 350, 100, 30);
        frame.add(btnVolver);
    }
    
    public void show() {
        frame.setVisible(true);
    }
    
    public JFrame getFrame() { return frame; }
    public JTable getTableArticulos() { return tableArticulos; }
    public JTable getTableComentarios() { return tableComentarios; }
    public JButton getBtnVolver() { return btnVolver; }

}