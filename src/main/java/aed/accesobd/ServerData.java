package aed.accesobd;



public class ServerData {

//Datos para MySQL
private  String MySQLserverData="jdbc:mysql://localhost:3306/bdfutbol";
private String MySQLuser="root";
private String MySQLpassword= "";

//Datos para SQLServer

private String SQLServerData="jdbc:sqlserver://LAPTOP-6529CHBR\\SQLEXPRESS;DataBaseName=bdfutbol";
private String SQLUser="sa";
private String SQLPassword="sa";

//Datos para Access
private String AccessServerData = "jdbc:ucanaccess://src/main/resources/database\\bdFutbol.accdb";
 






public String getAccessServerData() {
	return AccessServerData;
}

public String getMySQLserverData() {
	return MySQLserverData;
}

public String getMySQLuser() {
	return MySQLuser;
}

public String getMySQLpassword() {
	return MySQLpassword;
}

public String getSQLServerData() {
	return SQLServerData;
}

public String getSQLUser() {
	return SQLUser;
}

public String getSQLPassword() {
	return SQLPassword;
}

public String MySQLgetServerData() {
	return MySQLserverData;
}

public String getMySQLUser() {
	return MySQLuser;
}

public String getMySQLPassword() {
	return MySQLpassword;
}


		



	}




		
