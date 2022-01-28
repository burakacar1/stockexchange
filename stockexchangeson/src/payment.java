import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class payment extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					payment frame = new payment();
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
	public payment() {
		setTitle("PAYMENT");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnterTheCard = new JLabel("Enter the Card Number");
		lblEnterTheCard.setBounds(37, 75, 111, 14);
		contentPane.add(lblEnterTheCard);
		
		textField = new JTextField();
		textField.setBounds(150, 72, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnProceedToCheckout = new JButton("Proceed to Checkout");
		btnProceedToCheckout.setBounds(120, 139, 150, 23);
		contentPane.add(btnProceedToCheckout);
		
		textField_1 = new JTextField();
		textField_1.setBounds(150, 103, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblCvv = new JLabel("CVV");
		lblCvv.setBounds(120, 106, 46, 14);
		contentPane.add(lblCvv);
	}

}
