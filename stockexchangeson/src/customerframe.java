import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;
import javax.swing.JTextField;    


public class customerframe extends JFrame {
	clsCompany x = new clsCompany();
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	String Username;
	int customerID;
	private JTextField textField;
	
	public customerframe(String Username, int customerID) {
		this.Username=Username;
		this.customerID=customerID;
		
		setTitle("CUSTOMER PANEL-" + Username + "(" + customerID + ")");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 434, 261);
		contentPane.add(tabbedPane);
		JPanel panel = new JPanel();
		tabbedPane.addTab("Exchange", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblPleaseSelectA = new JLabel("Please Select a Company");
		lblPleaseSelectA.setBounds(124, 31, 198, 14);
		panel.add(lblPleaseSelectA);
		
		
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(228, 80, 168, 14);
		panel.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		
		

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.DD");
		LocalDateTime now = LocalDateTime.now();
		JLabel label = new JLabel(now.format(dtf));
		label.setBounds(319, 30, 77, 33);
		panel.add(label);
		

		MicrosoftSQLConnection SQL = new MicrosoftSQLConnection();
		try {
			SQL.openConnection();
			ArrayList<String> a =null;
			System.out.println("Before the companies");
			a =SQL.getCompanies();
			comboBox.removeAllItems();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"Please Select The Company"}));
			for(String b: a) {
				comboBox.addItem(b);
			}
			SQL.closeConnection();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			SQL.closeConnection();
			e.printStackTrace();
		}
		comboBox.setBounds(124, 56, 140, 20);
		panel.add(comboBox);
		
		comboBox.addActionListener(new ActionListener() {
			MicrosoftSQLConnection sql = new MicrosoftSQLConnection();
			public void actionPerformed(ActionEvent e) {
				Double price  = 0.0;
				try{
					sql.openConnection();
					String date = now.format(dtf).toString();
					clsResult result = sql.getCurrentPrice(comboBox.getSelectedItem().toString(), date);
					sql.closeConnection();
					if(!result.getResult()) {
						clsGeneral.infoBox(result.getMessage());
						return;
					}

					price = result.getPrice();
					System.out.println("price: " + price);
					
					String currentPrice = price.toString();
					lblNewLabel.setVisible(false);
					lblNewLabel.setText(currentPrice);
					lblNewLabel.setVisible(true);
				}catch (SQLException f) {
					// TODO Auto-generated catch block
					f.printStackTrace();
					clsGeneral.infoBox(f.getMessage());
					sql.closeConnection();
				}
			}
		});
		
		
		
		
		JLabel lblSelectTheQuantity = new JLabel("Enter the Quantity");
		lblSelectTheQuantity.setBounds(124, 100, 120, 14);
		panel.add(lblSelectTheQuantity);
		
		JLabel lblyouCanNot = new JLabel("*You can not buy/sell more than 100!");
		lblyouCanNot.setBounds(124, 125, 225, 14);
		panel.add(lblyouCanNot);
		
		textField = new JTextField();
		textField.setBounds(124, 150, 86, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		
		JButton btnBuy = new JButton("Buy");
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String date = now.format(dtf).toString();
				String companyName = comboBox.getSelectedItem().toString();
				double amount = new Double(textField.getText());
				if(!x.Transaction(customerID, companyName, amount, date)) {
					clsGeneral.infoBox("YOU CAN NOT BUY A STOCK FOR THAT DATE!");
					return;
				}
				else {
					clsGeneral.infoBox("SUCCESSFULLY BOUGHT!");
				}
			}
		});
		btnBuy.setBounds(90, 181, 89, 23);
		panel.add(btnBuy);
		
		JButton btnSell = new JButton("Sell");
		btnSell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String date = now.format(dtf).toString();
				String companyName = comboBox.getSelectedItem().toString();
				double amount = new Double(textField.getText());
				amount *= -1;
				if(!x.Transaction(customerID, companyName, amount, date)) {
					clsGeneral.infoBox("YOU CAN NOT SELL A STOCK FOR THAT DATE!");
					return;
				}
				else {
					clsGeneral.infoBox("SUCCESSFULLY SOLD!");
				}
			}
		});
		btnSell.setBounds(189, 181, 89, 23);
		panel.add(btnSell);
		
		
		
		JLabel lblCurrentDate = new JLabel("CURRENT DATE");
		lblCurrentDate.setBounds(319, 22, 100, 14);
		panel.add(lblCurrentDate);
		
		JLabel lblCurrentPrce = new JLabel("CURRENT PRICE");
		lblCurrentPrce.setBounds(154, 61, 100, 14);
		panel.add(lblCurrentPrce);
		
		JLabel lblCurrentPrce_1 = new JLabel("CURRENT PRICE:");
		lblCurrentPrce_1.setBounds(124, 80, 120, 14);
		panel.add(lblCurrentPrce_1);
		
		
		
		
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Feedback", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblYourFeedbacksAre = new JLabel("Your feedbacks are important for us :)");
		lblYourFeedbacksAre.setBounds(107, 24, 220, 14);
		panel_1.add(lblYourFeedbacksAre);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textArea.setBounds(38, 56, 359, 135);
		panel_1.add(textArea);
		
		JButton btnSend = new JButton("Send!");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MicrosoftSQLConnection  sql= new MicrosoftSQLConnection();
				try {
					System.out.println("Customer ID:" + customerID);
					sql.openConnection();
					sql.AddComment(customerID, textArea.getText());
					sql.closeConnection();
				}catch (SQLException e) {
					// TODO Auto-generated catch block
					sql.closeConnection();
					e.printStackTrace();
				}
				
			}
		});
		btnSend.setBounds(170, 202, 89, 23);
		panel_1.add(btnSend);
	}
}
