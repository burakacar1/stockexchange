import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class registerframe extends JFrame {

	private JPanel contentPane;
	private final JPasswordField passwordField = new JPasswordField();
	private JTextField textField_1;

	/**
	 * Launch the application.

	/**
	 * Create the frame.
	 */
	public registerframe() {
		setTitle("REGISTER ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 397, 245);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Customer", "Broker"}));
		comboBox.setToolTipText("");
		comboBox.setBounds(134, 88, 106, 20);
		contentPane.add(comboBox);
		passwordField.setBounds(134, 57, 106, 20);
		contentPane.add(passwordField);
		
		textField_1 = new JTextField();
		textField_1.setBounds(134, 26, 106, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setBounds(65, 29, 72, 14);
		contentPane.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(65, 60, 72, 14);
		contentPane.add(lblPassword);
		
		JLabel lblUserType = new JLabel("User Type");
		lblUserType.setBounds(65, 91, 59, 14);
		contentPane.add(lblUserType);
		
		
		System.out.println(comboBox.getSelectedIndex()+1);
		JButton btnRegster = new JButton("REGISTER");
		btnRegster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MicrosoftSQLConnection sql = new MicrosoftSQLConnection();
				System.out.println(textField_1.getText());
				System.out.println(passwordField.getText().toString());
				sql.openConnection();
				try {
					if(textField_1.getText().equals("") || passwordField.getText().toString().contentEquals("")) {
						clsGeneral.infoBox("PASSWORD AND USERNAME FIELDS CAN NOT BE EMPTY!");
						return;
					}
					clsResult result = sql.register(textField_1.getText(), passwordField.getText().toString(), (comboBox.getSelectedIndex()+1));
					sql.closeConnection();
					if(!result.getResult()) {
						clsGeneral.infoBox(result.getMessage());
					}
					else {
						clsGeneral.infoBox(result.getMessage());
					}
				}
				catch (SQLException d) {
					// TODO Auto-generated catch block
					d.printStackTrace();
					clsGeneral.infoBox(d.getMessage());
					sql.closeConnection();
				}
			}
		});
		btnRegster.setBounds(134, 141, 89, 23);
		contentPane.add(btnRegster);
	}
}
