package HU_29224;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.JButton;

public class View {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

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
		frame.setBounds(100, 100, 644, 505);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(254, 56, 231, 150);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(254, 270, 231, 150);
		frame.getContentPane().add(textField_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 56, 178, 364);
		frame.getContentPane().add(scrollPane);
		
		JComboBox CBGrado = new JComboBox();
		CBGrado.setToolTipText("");
		CBGrado.setFont(new Font("Tahoma", Font.PLAIN, 15));
		CBGrado.setModel(new DefaultComboBoxModel(new String[] {"Alto", "Normal", "Medio", "Bajo"}));
		CBGrado.setBounds(511, 56, 96, 21);
		CBGrado.setSelectedIndex(-1);
		frame.getContentPane().add(CBGrado);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(511, 270, 96, 21);
		frame.getContentPane().add(comboBox_1);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(81, 430, 85, 21);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(264, 216, 85, 21);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setBounds(254, 430, 85, 21);
		frame.getContentPane().add(btnNewButton_2);
	}
}
