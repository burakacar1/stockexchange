import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.ListSelectionModel;
import javax.swing.JTextPane;


public class adminframe extends JFrame {
	DefaultTableModel dtm;	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private String username;
	private int ID;
	JList list=null;
	

	DefaultListModel listModel= new DefaultListModel();
	

	/*void _FillExchanges()
	{
		
		for(int i=0;i<dtm.getRowCount();i++)
		{
			dtm.removeRow(0);
		}
		
		MicrosoftSQLConnection sql = new MicrosoftSQLConnection();
		sql.openConnection();
		ArrayList<clsExchange> L= sql.getExchanges();
		sql.closeConnection();
		for(int i = 0; i < L.size(); i++) {
			String[] veriler=new String[4];

			veriler[0]=String.valueOf(L.get(i).getUserID());
			dtm.addRow(veriler);
		}
		
		
	}
	*/
	
	public adminframe(String username, int ID) {
		this.username = username;
		this.ID = ID;
		
		setTitle("ADMIN PANEL-" + String.valueOf(username) + "(" + ID + ")");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1093, 480);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Update", null, panel, null);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(424, 135, 205, 20);
		textField.setVisible(false);
		panel.add(textField);
		textField.setColumns(10);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(424, 200, 205, 20);
		comboBox_1.setVisible(false);
		panel.add(comboBox_1);
		
		
		JLabel lblSelectTheOperation = new JLabel("Select the operation");
		lblSelectTheOperation.setBounds(469, 68, 109, 14);
		panel.add(lblSelectTheOperation);
		
		JLabel lblNewLabel = new JLabel("Enter New Password");
		lblNewLabel.setBounds(288, 169, 134, 14);
		lblNewLabel.setVisible(false);
		panel.add(lblNewLabel);
		
		JLabel lblEnterNewUsername = new JLabel("Enter Username");
		lblEnterNewUsername.setBounds(326, 138, 141, 14);
		lblEnterNewUsername.setVisible(false);
		panel.add(lblEnterNewUsername);
	
		JLabel lblEnterNewUsertype = new JLabel("Enter New UserType");
		lblEnterNewUsertype.setBounds(288, 203, 115, 14);
		lblEnterNewUsertype.setVisible(false);
		panel.add(lblEnterNewUsertype);
		
	
		JLabel lblEnterNewUsername_1 = new JLabel("Enter New Username");
		lblEnterNewUsername_1.setBounds(288, 169, 121, 14);
		lblEnterNewUsername_1.setVisible(false);
		panel.add(lblEnterNewUsername_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(424, 166, 205, 20);
		passwordField.setVisible(false);
		panel.add(passwordField);
		
		
		textField_1 = new JTextField();
		textField_1.setBounds(424, 166, 205, 20);
		textField_1.setVisible(false);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedIndex() == 1) {
					comboBox_1.setVisible(false);
					lblNewLabel.setVisible(false);
					lblEnterNewUsertype.setVisible(false);
					passwordField.setVisible(false);
					textField.setVisible(true);
					lblEnterNewUsername_1.setVisible(true);
					lblEnterNewUsertype.setVisible(false);
					lblEnterNewUsername.setVisible(true);
					textField_1.setVisible(true);
				}
				else if(comboBox.getSelectedIndex() == 2) {
					comboBox_1.setVisible(false);
					lblEnterNewUsername_1.setVisible(false);
					textField_1.setVisible(false);
					lblNewLabel.setVisible(true);
					textField.setVisible(true);
					passwordField.setVisible(true);
					lblEnterNewUsertype.setVisible(false);
					lblEnterNewUsername.setVisible(true);
					
				}
				else if(comboBox.getSelectedIndex() == 3) {
					MicrosoftSQLConnection sql = new MicrosoftSQLConnection();
					try {
						textField_1.setVisible(false);
						lblNewLabel.setVisible(false);
						lblEnterNewUsername_1.setVisible(false);
						passwordField.setVisible(false);
						textField.setVisible(true);
						lblEnterNewUsername.setVisible(true);
						lblEnterNewUsertype.setVisible(true);
						sql.openConnection();
						ArrayList<String> a =null; 
						a =sql.GetUserTypes();
						comboBox_1.removeAllItems();
						comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Please Select The UserType"}));
						for(String b: a) {
							comboBox_1.addItem(b);
						}
						comboBox_1.setVisible(true);
						sql.closeConnection();
					}
					catch (SQLException d) {
						// TODO Auto-generated catch block
						sql.closeConnection();
						d.printStackTrace();
					}
				}
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Please Select Operation","Change UserName", "Change Password", "Change UserType"}));
		comboBox.setBounds(424, 93, 205, 20);
		panel.add(comboBox);
		
		JButton btnChange = new JButton("Change");
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					if(comboBox.getSelectedIndex() == 1) {
						MicrosoftSQLConnection sql = new MicrosoftSQLConnection();
						try{
							sql.openConnection();
							clsResult result = sql.UpdateUsername(textField.getText(), textField_1.getText());
							if (!result.getResult()) {
								clsGeneral.infoBox(result.getMessage());
								return;
							}
							clsGeneral.infoBox(result.message);
							sql.closeConnection();
						}catch (SQLException h) {
							// TODO Auto-generated catch block
							sql.closeConnection();
							h.printStackTrace();
						}
					}
					else if (comboBox.getSelectedIndex() == 2) {
						MicrosoftSQLConnection sql = new MicrosoftSQLConnection();
						try{
							sql.openConnection();
							clsResult result = sql.UpdatePassword(textField.getText(), passwordField.getText());
							if (!result.getResult()) {
								clsGeneral.infoBox(result.getMessage());
								return;
							}
							clsGeneral.infoBox(result.message);
							sql.closeConnection();
						}catch (SQLException h) {
							// TODO Auto-generated catch block
							sql.closeConnection();
							h.printStackTrace();
						}

					}
					else if (comboBox.getSelectedIndex() == 3) {
						MicrosoftSQLConnection sql = new MicrosoftSQLConnection();
						try{
							sql.openConnection();
							clsResult result = sql.UpdateUsertype(textField.getText(), comboBox_1.getSelectedIndex());
							if (!result.getResult()) {
								clsGeneral.infoBox(result.getMessage());
								return;
							}
							clsGeneral.infoBox(result.message);
							sql.closeConnection();
						}catch (SQLException e) {
							// TODO Auto-generated catch block
							sql.closeConnection();
							e.printStackTrace();
						}
					}
		}});
		btnChange.setBounds(396, 251, 276, 23);
		panel.add(btnChange);
		
	
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Transaction Details", null, panel_1, null);
		panel_1.setLayout(null);
		MicrosoftSQLConnection SQL = new MicrosoftSQLConnection();
		
		try {
			SQL.openConnection();
			ArrayList<clsExchange> e = SQL.getExchanges();
			System.out.print("ID:"  + e.size());
	
	 		for(int i = 0; i < e.size(); i++) 
	 		{
	 			//System.out.print("ID-aaa:"  + e.get(i).getUse());
	 			listModel.addElement("USER NAME: " + e.get(i).getUserName() + " COMPANY DESCRIPTION: " + e.get(i).getCompanyDesc() + " PRICE: " + e.get(i).getPrice() + " AMOUNT: " + e.get(i).getAmount() + " TOTAL PRICE: " + e.get(i).getTotal() + " CREATED DATE: " + e.get(i).getCreatedDate());
	 		}
			SQL.closeConnection();
		}catch(SQLException d) {
			SQL.closeConnection();
			d.printStackTrace();
		}
		//System.out.println(listModel.toString());
		JList list_1 = new JList(listModel);
		list_1.setBounds(0, 0, 1062, 403);
		panel_1.add(list_1);
		list_1.setVisible(true);
		
	
		String[] colum= {"Ad","Soyad","Yas","Sehir"};
		
		dtm=new DefaultTableModel();
		dtm.setColumnIdentifiers(colum);

		String[] veriler=new String[4];

		veriler[0]="REHA";
		dtm.addRow(veriler);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Feedbacks", null, panel_2, null);
		panel_2.setLayout(null);
		JTextArea textArea = new JTextArea();
		textArea.setBounds(0, 37, 674, 366);
		panel_2.add(textArea);
			MicrosoftSQLConnection sql = new MicrosoftSQLConnection();
		try {
			sql.openConnection();
			ArrayList<String> a =null; 
			/*a =sql.GetCustomers();
			for(String b: a) {
				comboBox_2.addItem(b);
			}*/
			String feedback = sql.GetFeedbacks();
			textArea.setText(feedback);
			
			JLabel lblAllFeedbacksFrom = new JLabel("ALL FEEDBACKS FROM CUSTOMERS");
			lblAllFeedbacksFrom.setBounds(244, 1, 236, 25);
			panel_2.add(lblAllFeedbacksFrom);
			sql.closeConnection();
		}
		catch (SQLException d) {
			// TODO Auto-generated catch block
			sql.closeConnection();
			d.printStackTrace();
		}
		
		//_FillExchanges();
		
		
	}
}

