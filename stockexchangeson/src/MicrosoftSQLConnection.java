import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

public class MicrosoftSQLConnection {
    // Connect to your database.
	Connection connection;
	String userType;
	String Username;
	public String GetUserType()
	{
		return this.userType;
		
	}
	public String GetUsername()
	{
		return this.Username;
		
	}
    // Replace server name, username, and password with your credentials
	
	public boolean CheckRight(String Username,String RightCode)throws SQLException
	{
		String SQL="SELECT        dbo.USERS.USERNAME, dbo.RIGHTS.RIGHT_CODE, dbo.RIGHTS.RIGHT_DESCRIPTION, dbo.USER_TYPE.DESCRIPTION AS USER_TYPE_DESCRIPTION\r\n" + 
				"FROM            dbo.USER_TYPE_RIGHTS LEFT OUTER JOIN\r\n" + 
				"                         dbo.RIGHTS ON dbo.USER_TYPE_RIGHTS.RIGHT_ID = dbo.RIGHTS.ID RIGHT OUTER JOIN\r\n" + 
				"                         dbo.USER_TYPE ON dbo.USER_TYPE_RIGHTS.USER_TYPE_ID = dbo.USER_TYPE.ID RIGHT OUTER JOIN\r\n" + 
				"                         dbo.USERS ON dbo.USER_TYPE.ID = dbo.USERS.USER_TYPE\r\n" + 
				"WHERE        dbo.USERS.USERNAME = N'"+Username+"' and dbo.RIGHTS.RIGHT_CODE='"+RightCode+"'";
		Statement cmd=this.connection.createStatement();
		
		ResultSet  RESULT = cmd.executeQuery(SQL);
		
		
		while ( RESULT.next() ) {		
			return true;
        }
			
		return false;
		
	}
	
	
	
	
	public ArrayList<String> GetUserTypes()throws SQLException
	{
		String SQL="SELECT * FROM USER_TYPE";
		Statement cmd=this.connection.createStatement();
		
		ResultSet  RESULT = cmd.executeQuery(SQL);
		
		ArrayList<String> a=new ArrayList<String>(); 
		while ( RESULT.next()) {
			String DESCRIPTION = RESULT.getString("DESCRIPTION").toString();
			a.add(DESCRIPTION);	
        }
		
        return a;
		
	}
	
	public ArrayList<String> getCompanies()throws SQLException
	{
		System.out.println("Beginnin of companies.");
		String SQL="SELECT COMPANY_DESCRIPTION FROM COMPANIES  ORDER BY COMPANY_DESCRIPTION";
		Statement cmd=this.connection.createStatement();
		
		ResultSet  RESULT = cmd.executeQuery(SQL);
		
		ArrayList<String> a=new  ArrayList<String>(); 
		while ( RESULT.next()) {
			String DESCRIPTION = RESULT.getString("COMPANY_DESCRIPTION").toString();
			a.add(DESCRIPTION);	
	    }
		Collections.sort(a);
		for(String temp: a) {
			System.out.println(temp);
		}
		return a;
		
		
	}
	
	public boolean makeTransactions(int userID, int companyID, double price, double amount, double totalPrice)throws SQLException {
		String SQL = "INSERT INTO EXCHANGE(USER_ID, COMPANY_ID, PRICE, AMOUNT, TOTAL_PRICE, CREATED_DATE)"+
				"VALUES(" + userID + ", " + companyID + ", " + price + ", " + amount + ", " + totalPrice + ", GETDATE())";
		System.out.print(SQL);
		Statement cmd = this.connection.createStatement();
		try {
			int aaa = cmd.executeUpdate(SQL);
			return true;
		}catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
	}
	
	public boolean Login(String Username,String Password) throws SQLException
	{
        System.out.println("Þifre:"+Password);
		
		String SQL="SELECT       dbo.USERS.ID,dbo.USERS.USERNAME, dbo.USERS.PASSWORD, dbo.USER_TYPE.DESCRIPTION as USER_TYPE_DESCRIPTION\r\n" + 
				"FROM            dbo.USERS INNER JOIN\r\n" + 
				"                         dbo.USER_TYPE ON dbo.USERS.USER_TYPE = dbo.USER_TYPE.ID\r\n" + 
				"WHERE        (dbo.USERS.PASSWORD = '"+Password+"') AND (dbo.USERS.USERNAME = '"+Username+"')";
		Statement cmd=this.connection.createStatement();
		
		ResultSet  RESULT = cmd.executeQuery(SQL);
		if(!RESULT.next())
		{					
			return false;
		}
		
		
//		RESULT.first();
		 
		Integer userID=0;
		userID=RESULT.getInt("ID");
		this.Username = RESULT.getString("USERNAME").toString();
        //String password = RESULT.getString("PASSWORD").toString();
        this.userType = RESULT.getString("USER_TYPE_DESCRIPTION").toString();            
        //System.out.println(Username+" "+Password+" "+userType);
		
		SQL="INSERT INTO USER_LOGIN_LOGS(USER_ID,LOGIN_DATE,LOGIN_HOSTNAME) VALUES("+userID.toString()+",GETDATE(),HOST_NAME())";
        System.out.println(SQL);
        cmd.executeUpdate(SQL);
        System.out.println("After user login logs");
		return true;
	}
	
	
	
	
    public void openConnection(){
        String connectionUrl =
                "jdbc:sqlserver://127.0.0.1:1433;"
                        + "database=StockExchange;"
                        + "user=user1;"
                        + "password=1;"
                        + "loginTimeout=30;";

       
        try
        {
            this.connection = DriverManager.getConnection(connectionUrl); 
            System.out.println("Connection OK");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
public clsResult UpdateUsername(String Username, String newUsername)throws SQLException {
	Statement cmd = this.connection.createStatement();
	String SQL = "UPDATE USERS SET USERS.USERNAME ='"+newUsername+"' WHERE USERNAME ='"+Username+"'";
	try {
		int aaa = cmd.executeUpdate(SQL);	
		if(aaa==0)
		{
			return new clsResult(false, "NO USER FOUND!");
		}
		return new clsResult(true, "USERNAME WAS SUCCESFULLY CHANGED!");
	}catch (SQLException e){
		e.printStackTrace();
		return new clsResult(false, e.getMessage());
	}

}
    
public clsResult UpdatePassword(String Username, String newPassword)throws SQLException {
	Statement cmd = this.connection.createStatement();
	String SQL = "UPDATE USERS SET USERS.PASSWORD ='"+newPassword+"' WHERE USERNAME ='"+Username+"'";
	try {
		int aaa = cmd.executeUpdate(SQL);	
		if(aaa==0)
		{
			return new clsResult(false, "NO USER FOUND!");
		}
		return new clsResult(true, "PASSWORD WAS SUCCESFULLY CHANGED!");
	}catch (SQLException e){
		e.printStackTrace();
		return new clsResult(false, e.getMessage());
	}

}

public boolean AddComment(int customerID, String Comment)throws SQLException {
	String SQL = "INSERT INTO CUSTOMER_FEEDBACKS(CUSTOMER_ID, FEEDBACK, CREATED_DATE)"+
			"VALUES('" + customerID + "', '" + Comment.replace("'", "''") + "', GETDATE())";
	Statement cmd = this.connection.createStatement();
	try {
		int aaa = cmd.executeUpdate(SQL);	
		return true;
	}catch (SQLException e) {
		e.printStackTrace();
		return false;
	}

}

public clsResult register(String userName, String password, int userType)throws SQLException {
	String SQL = "INSERT INTO USERS(USERNAME, PASSWORD, USER_TYPE)" +
			"VALUES('" + userName + "', '" + password + "', '" + userType + "')";
	Statement cmd = this.connection.createStatement();
	try {
		int aaa = cmd.executeUpdate(SQL);
		return new clsResult(true,"SUCCESFULL!");
	}catch(SQLException e) {
		e.printStackTrace();
		return new clsResult(false, e.getMessage());
	}
	
	
}


public clsResult updateStock(int companyID, double price, String date)throws SQLException {
	String SQL = "INSERT INTO COMPANY_PRICES(COMPANY_ID, CURRENT_PRICE, STOCK_DATE, CREATED_DATE)\r\n" +
				"VALUES('" + companyID + "', '" + price + "', '" + date + "', GETDATE())";
	Statement cmd = this.connection.createStatement();
	
	try {
		int aaa = cmd.executeUpdate(SQL);
		return new clsResult(true, "SUCCESSFULLY ADDED!");
	}catch(SQLException e) {
		e.printStackTrace();
		return new clsResult(false, e.getMessage());
	}
}
public int getUserID(String userName)throws SQLException {
	String SQL = "SELECT ID FROM USERS WHERE USERNAME = '" + userName + "'"; 
	Statement cmd = this.connection.createStatement();
	
	ResultSet RESULT = cmd.executeQuery(SQL);
	if (!RESULT.next()) {
		System.out.println("There is no such user!");
		return 0;
	}

	try {
		int a = RESULT.getInt("ID");
		return a;
	}catch (SQLException e){
		e.printStackTrace();
		return 0;
	}
}

public String GetFeedbacks()throws SQLException{
	String SQL = "SELECT * FROM CUSTOMER_FEEDBACKS";
	Statement cmd = this.connection.createStatement();
	
	ResultSet RESULT = cmd.executeQuery(SQL);
	String a = ""; 
	while( RESULT.next() ) {
		String feedback = RESULT.getString("FEEDBACK").toString();
		a += feedback + " \n";
	}
	return a;
}
public ArrayList<String> GetCustomers()throws SQLException
{
	String SQL="SELECT * FROM USERS WHERE USER_TYPE = 1";
	Statement cmd=this.connection.createStatement();
	
	ResultSet  RESULT = cmd.executeQuery(SQL);
	
	ArrayList<String> a=new ArrayList<String>(); 
	while ( RESULT.next()) {
		String DESCRIPTION = RESULT.getString("USERNAME").toString();
		a.add(DESCRIPTION);	
    }
	
    return a;
	
}

public ArrayList<clsExchange> getExchanges()throws SQLException {
	String SQL = "SELECT        dbo.EXCHANGE.ID, dbo.EXCHANGE.USER_ID, dbo.EXCHANGE.COMPANY_ID, dbo.EXCHANGE.PRICE, dbo.EXCHANGE.AMOUNT, dbo.EXCHANGE.TOTAL_PRICE, dbo.EXCHANGE.CREATED_DATE, dbo.USERS.USERNAME, \r\n" + 
			"                         dbo.COMPANIES.COMPANY_DESCRIPTION\r\n" + 
			"FROM            dbo.EXCHANGE LEFT OUTER JOIN\r\n" + 
			"                         dbo.COMPANIES ON dbo.EXCHANGE.COMPANY_ID = dbo.COMPANIES.ID LEFT OUTER JOIN\r\n" + 
			"                         dbo.USERS ON dbo.EXCHANGE.USER_ID = dbo.USERS.ID";
	Statement cmd = this.connection.createStatement();
	ArrayList<clsExchange> Result=new ArrayList<clsExchange>();
	
	ResultSet RESULT = cmd.executeQuery(SQL);
	while(RESULT.next()) {
		String USER_NAME = RESULT.getString("USERNAME");
		String COMPANY_DESCRIPTION = RESULT.getString("COMPANY_DESCRIPTION");
		double PRICE = RESULT.getDouble("PRICE");
		double AMOUNT = RESULT.getDouble("AMOUNT");
		double TOTAL_PRICE = RESULT.getDouble("TOTAL_PRICE");
		String CREATED_DATE = RESULT.getString("CREATED_DATE").toString();
		clsExchange exchange = new clsExchange(USER_NAME, COMPANY_DESCRIPTION, PRICE, AMOUNT, TOTAL_PRICE, CREATED_DATE);
		Result.add(exchange);
	}
	return Result;
}

public ArrayList<clsUser> getUsers()throws SQLException {
	String SQL = "SELECT        dbo.EXCHANGE.ID, dbo.EXCHANGE.USER_ID, dbo.EXCHANGE.COMPANY_ID, dbo.EXCHANGE.PRICE, dbo.EXCHANGE.AMOUNT, dbo.EXCHANGE.TOTAL_PRICE, dbo.EXCHANGE.CREATED_DATE, dbo.USERS.USERNAME, \r\n" + 
			"                         dbo.COMPANIES.COMPANY_DESCRIPTION\r\n" + 
			"FROM            dbo.EXCHANGE LEFT OUTER JOIN\r\n" + 
			"                         dbo.COMPANIES ON dbo.EXCHANGE.COMPANY_ID = dbo.COMPANIES.ID LEFT OUTER JOIN\r\n" + 
			"                         dbo.USERS ON dbo.EXCHANGE.USER_ID = dbo.USERS.ID";
	Statement cmd = this.connection.createStatement();
	ArrayList<clsUser>  a= new ArrayList<clsUser>();
	ResultSet RESULT = cmd.executeQuery(SQL);
	while(RESULT.next()) {
		String USERNAME = RESULT.getString("USERNAME");
		String PASSWORD = RESULT.getString("PASSWORD");
		String USERTYPE = RESULT.getString("DESCRIPTION");
		clsUser user = new clsUser(USERNAME, PASSWORD, USERTYPE);
		a.add(user);
	}
	return a;
}

public clsResult deleteStocks(String companyName, String date)throws SQLException {
	String SQL = "DELETE COMPANY_PRICES WHERE COMPANY_ID=(select ID from COMPANIES where COMPANY_DESCRIPTION='" + companyName + "') AND STOCK_DATE='" + date + "'"; 
	Statement cmd = this.connection.createStatement();
	
	try {
		int aaa = cmd.executeUpdate(SQL);
		return new clsResult(true, "WORKING HARD!");
	}catch(SQLException e) {
		e.printStackTrace();
		return new clsResult(false, e.getMessage());
	}
	
}


public clsResult getCurrentPrice(String companyName, String date)throws SQLException {
	String SQL = "select CURRENT_PRICE from   COMPANY_PRICES\r\n" + 
			"  where \r\n" + 
			"  COMPANY_ID=(select ID from COMPANIES where COMPANY_DESCRIPTION='" + companyName + "') ANd\r\n" + 
			"	STOCK_DATE= '" + date + "'";
	Statement cmd = this.connection.createStatement();
	ResultSet RESULT = cmd.executeQuery(SQL);
	if (!RESULT.next()) {
		System.out.println("There is no such stock for that date!");
		return new clsResult(false,"THERE IS NO SUCH STOCK FOR THAT DATE, PLEASE CONTACT TO BROKER!");
	}
	try {
		double a = RESULT.getDouble("CURRENT_PRICE");
		return new clsResult(true, "", a); 
	}catch (SQLException e){
		e.printStackTrace();
		return new clsResult(false, e.getMessage());
	}
	
}

public void closeConnection() {
    try
    {
        this.connection.close(); 
        System.out.println("Connection closed!");
    }
    catch (SQLException e) {
        e.printStackTrace();
    }
}

public clsCompany getCompanyID(String companyName)throws SQLException {
	//String SQL = "select ID as COMPANY_ID from COMPANIES where COMPANY_DESCRIPTION='" + companyName + "')";
	String SQL = "select ID, COMPANY_CODE from COMPANIES where COMPANY_DESCRIPTION = '" + companyName + "'";
	Statement cmd = this.connection.createStatement();
	ResultSet RESULT = cmd.executeQuery(SQL);
	if (!RESULT.next()) {
		System.out.println("There is no such stock for that date!");
		return null;	
	}
	try {
		//int a = RESULT.getInt("COMPANY_ID");
		int a = RESULT.getInt("ID");
		String b = RESULT.getString("COMPANY_CODE");
		return new clsCompany(a, b);
	}catch (SQLException e){
		e.printStackTrace();
		return null;
	}
	
}



public clsResult UpdateUsertype(String Username, int Usertype)throws SQLException {	
	Statement cmd = this.connection.createStatement();
	String SQL = "UPDATE USERS SET USER_TYPE ='"+Usertype+"' WHERE USERNAME ='"+Username+"'";
	try {
		int aaa = cmd.executeUpdate(SQL);
		if(aaa==0)
		{
			return new clsResult(false, "NO USER FOUND!");
		}
		return new clsResult(true, "USERTYPE WAS SUCCESFULLY CHANGED!");
	}catch (SQLException e){
		e.printStackTrace();
		return new clsResult(false, e.getMessage());
	}
	
}
}


