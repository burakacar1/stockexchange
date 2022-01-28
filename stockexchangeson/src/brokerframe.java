import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class brokerframe extends JFrame {
	String username;
	int ID;
	JList list=null;
	

	DefaultListModel listModel= new DefaultListModel();

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public brokerframe(String username, int ID) {
		this.username = username;
		this.ID = ID;
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.DD");
		LocalDateTime now = LocalDateTime.now();
		
		
		setTitle("BROKER PANEL-" + String.valueOf(username) + "(" + ID + ")");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 290);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 434, 261);
		contentPane.add(tabbedPane);
		
		JPanel ManageTrade = new JPanel();
		ManageTrade.setToolTipText("");
		tabbedPane.addTab("Manage Trade", null, ManageTrade, null);
		ManageTrade.setLayout(null);
		
		
		JLabel lblPleaseSelectThe = new JLabel("Please select the company");
		lblPleaseSelectThe.setBounds(135, 31, 140, 14);
		ManageTrade.add(lblPleaseSelectThe);
		
		JLabel lblCurrentPrice = new JLabel("Current price:");
		lblCurrentPrice.setBounds(124, 100, 95, 14);
		ManageTrade.add(lblCurrentPrice);
		
		JLabel lblWriteTheNew = new JLabel("Write the new value ");
		lblWriteTheNew.setBounds(124, 145, 140, 14);
		ManageTrade.add(lblWriteTheNew);
		
		textField = new JTextField();
		textField.setBounds(124, 170, 86, 20);
		ManageTrade.add(textField);
		textField.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		MicrosoftSQLConnection sql = new MicrosoftSQLConnection();
		try {
			sql.openConnection();
			ArrayList<String> a =null;
			System.out.println("Before the companies");
			a =sql.getCompanies();
			comboBox.removeAllItems();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"Please Select The Company"}));
			for(String b: a) {
				comboBox.addItem(b);
			}
			sql.closeConnection();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			sql.closeConnection();
			e.printStackTrace();
		}
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(124, 120, 151, 14);
		ManageTrade.add(lblNewLabel);
		
		comboBox.setBounds(124, 56, 140, 20);
		ManageTrade.add(comboBox);
		
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MicrosoftSQLConnection sql = new MicrosoftSQLConnection();
				Double price = 0.0;
				try{
					sql.openConnection();
					clsResult result = sql.getCurrentPrice(comboBox.getSelectedItem().toString(), date);
					sql.closeConnection();
					
					if(!result.getResult()) {
						clsGeneral.infoBox("YOU WILL BE FIRED SOON, THERE IS NO SUCH STOCK!");
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
		
		
		JButton btnChange = new JButton("CHANGE");
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MicrosoftSQLConnection sql = new MicrosoftSQLConnection();
				try {
					String date = now.format(dtf).toString();
					sql.openConnection();
					clsResult result = sql.deleteStocks(comboBox.getSelectedItem().toString(), date);
					if(!result.getResult()) {
						clsGeneral.infoBox(result.getMessage());
					}
					clsCompany companyID = sql.getCompanyID(comboBox.getSelectedItem().toString());
					int company = companyID.getID();
					Double amount = new Double(textField.getText());
					clsResult stocks = sql.updateStock(company, amount , date);
					if(!stocks.result) {
						clsGeneral.infoBox(stocks.getMessage());
					}
					sql.closeConnection();
				}catch(SQLException e){
					e.printStackTrace();
					clsGeneral.infoBox(e.getMessage());
					sql.closeConnection();
				}
			}
		});
		btnChange.setBounds(124, 199, 89, 23);
		ManageTrade.add(btnChange);
		
		
	
		JPanel UserInfo = new JPanel();
		tabbedPane.addTab("User Info", null, UserInfo, null);
		UserInfo.setLayout(null);
		
		
		MicrosoftSQLConnection SQL = new MicrosoftSQLConnection();
		try {
			SQL.openConnection();
			ArrayList<clsUser> e = SQL.getUsers();
			System.out.print("ID:"  + e.size());
	
	 		for(int i = 0; i < e.size(); i++) 
	 		{
	 			//System.out.print("ID-aaa:"  + e.get(i).getUse());
	 			listModel.addElement("USER NAME: " + e.get(i).getUserName() + " PASSWORD: " + e.get(i).getPassword() + " USER TYPE: " + e.get(i).getDescrýptýon());
	 		}
			SQL.closeConnection();
		}catch(SQLException d) {
			SQL.closeConnection();
			d.printStackTrace();
		}
		
		JList list_1 = new JList(listModel);
		list_1.setBounds(10, 11, 409, 211);
		UserInfo.add(list_1);
		
		JPanel ViewFeedback = new JPanel();
		tabbedPane.addTab("View Feedback", null, ViewFeedback, null);
		ViewFeedback.setLayout(null);
		
		JLabel lblAllCustomerFeedbacks = new JLabel("ALL CUSTOMER FEEDBACKS");
		lblAllCustomerFeedbacks.setBounds(119, 11, 198, 27);
		ViewFeedback.add(lblAllCustomerFeedbacks);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 43, 409, 179);
		ViewFeedback.add(textArea);
		MicrosoftSQLConnection sql1 = new MicrosoftSQLConnection();
		sql1.openConnection();
		try {
			ArrayList<String> a =null; 
			String feedback = sql1.GetFeedbacks();
			textArea.setText(feedback);
			
			JLabel lblAllFeedbacksFrom = new JLabel("ALL FEEDBACKS FROM CUSTOMERS");
			lblAllFeedbacksFrom.setBounds(111, 0, 236, 25);
			sql1.closeConnection();
		}
		catch (SQLException d) {
			// TODO Auto-generated catch block
			sql1.closeConnection();
			d.printStackTrace();
		}
	}
}
