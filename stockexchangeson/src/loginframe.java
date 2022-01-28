import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Scrollbar;
import javax.swing.JPasswordField;
import javax.swing.Box;
import javax.swing.JTextPane;
import java.awt.TextField;
import javax.swing.JLabel;
//import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class loginframe extends JFrame {

	private JPanel contentPane;
	private JTextField userField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginframe frame = new loginframe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public loginframe() {
		setTitle("Stock Exchange System-LOGIN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 388, 201);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		userField = new JTextField();
		userField.setBounds(173, 23, 86, 20);
		contentPane.add(userField);
		userField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(173, 54, 86, 20);
		contentPane.add(passwordField);
		
		/*JLabel lblAsdasdasd = DefaultComponentFactory.getInstance().createTitle("Username");
		lblAsdasdasd.setBounds(115, 26, 48, 14);
		contentPane.add(lblAsdasdasd);
		
		JLabel lblPassword = DefaultComponentFactory.getInstance().createTitle("Password");
		lblPassword.setBounds(115, 57, 48, 14);
		contentPane.add(lblPassword);
		*/
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registerframe reg =new registerframe();
				reg.setVisible(true);
				dispose();
				System.out.println("deneme");
			}
		});
		btnRegister.setBounds(170, 95, 122, 23);
		contentPane.add(btnRegister);
		
		JLabel lblMessage = new JLabel("New label");
		lblMessage.setText("");
		lblMessage.setBounds(67, 137, 225, 14);
		contentPane.add(lblMessage);
		
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("butona basýldý!");
				MicrosoftSQLConnection login = new MicrosoftSQLConnection();
				login.openConnection();
				try {
					boolean loginResult=login.Login(userField.getText(), passwordField.getText());
					if(!loginResult)
					{
						clsGeneral.infoBox("INVALID USERNAME OR PASSWORD!");
						login.closeConnection();
					}
					else
					{
						String username = login.GetUsername();
						int ID = login.getUserID(username);
						lblMessage.setText(login.GetUserType());
						if (login.GetUserType().equals("CUSTOMER")) {
							customerframe a = new customerframe(username, ID);
							a.setVisible(true);
							dispose();
							login.closeConnection();
						}
						else if (login.GetUserType().equals("BROKER")) {
							brokerframe a = new brokerframe(username, ID);
							a.setVisible(true);
							dispose();
							login.closeConnection();
						}
						else if (login.GetUserType().equals("ADMIN")) {
							adminframe a = new adminframe(username, ID);
							a.setVisible(true);
							dispose();
							login.closeConnection();
						}
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					login.closeConnection();
					e.printStackTrace();
				}
			}
		});
		btnLogin.setBounds(34, 95, 122, 23);
		contentPane.add(btnLogin);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(113, 26, 65, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword_1 = new JLabel("Password");
		lblPassword_1.setBounds(113, 57, 80, 14);
		contentPane.add(lblPassword_1);
		
	
	}
}
