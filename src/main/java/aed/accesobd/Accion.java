package aed.accesobd;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Scanner;

public class Accion {
	
	Scanner entrada= new Scanner(System.in);
	ServerData datos= new ServerData();
	Connection conexion;
	

	
	
	
public void insertarRegistro(int bdopcion) throws SQLException,ClassNotFoundException {

	
	switch (bdopcion) {
	case 1:
	Class.forName("com.mysql.cj.jdbc.Driver");
	conexion=DriverManager.getConnection(datos.MySQLgetServerData(),datos.getMySQLUser(),datos.getMySQLPassword());
	break;
	case 2:
	Class.forName( "com.microsoft.sqlserver.jdbc.SQLServerDriver" );
	conexion=DriverManager.getConnection(datos.getSQLServerData(),datos.getSQLUser(),datos.getSQLPassword()); 
	break;

	case 3:
	Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	conexion=DriverManager.getConnection(datos.getAccessServerData());
	break;
	}
	
	String nomEquipo;
	String codLiga;
	String localidad;
	int internacional;
	
	ArrayList<String>ligas= new ArrayList<String>();
	PreparedStatement visualizaLigas= conexion.prepareStatement("select codLiga from ligas");
	ResultSet resultado= visualizaLigas.executeQuery();
	while(resultado.next()) {
	ligas.add(resultado.getString("codLiga"));		
	}
	
	String listaLigas="|";
	
	for(int i=0;i<ligas.size();i++)
	 listaLigas+=ligas.get(i)+"|";

	
	
	System.out.print("Introduzca el nombre del equipo: ");
	nomEquipo=entrada.next();
	System.out.print("Introduzca el código de la liga(" +listaLigas+ "):");
	codLiga=entrada.next();
	System.out.print("Introduzca la localidad: ");
	localidad=entrada.next();
	System.out.print("¿Es internacional? (1=SÍ/0=NO): ");
	internacional=entrada.nextInt();

	
	
	
	PreparedStatement insertaEquipo=conexion.prepareStatement("insert into equipos(nomEquipo,codLiga,localidad,internacional) values (?,?,?,?)");
	insertaEquipo.setString(1,nomEquipo);
	insertaEquipo.setString(2,codLiga);
	insertaEquipo.setString(3, localidad);
	insertaEquipo.setInt(4,internacional);
	insertaEquipo.executeUpdate();
	
	
	}

public void eliminarRegistro(String eliminado, int bdopcion) throws SQLException,ClassNotFoundException {
		

	switch (bdopcion) {
	case 1:
	Class.forName("com.mysql.cj.jdbc.Driver");
	conexion=DriverManager.getConnection(datos.MySQLgetServerData(),datos.getMySQLUser(),datos.getMySQLPassword());
	break;
	case 2:
	Class.forName( "com.microsoft.sqlserver.jdbc.SQLServerDriver" );
	conexion=DriverManager.getConnection(datos.getSQLServerData(),datos.getSQLUser(),datos.getSQLPassword()); 
	break;

	case 3:
	Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	conexion=DriverManager.getConnection(datos.getAccessServerData());
	break;
	}
	
	PreparedStatement borraEquipo=conexion.prepareStatement("delete from equipos where codEquipo= ?");
	borraEquipo.setString(1,eliminado);
	borraEquipo.executeUpdate();
	
	
	
	
	}

public void modificarRegistro(int modificado, int bdopcion) throws SQLException,ClassNotFoundException{
	switch (bdopcion) {
	case 1:
	Class.forName("com.mysql.cj.jdbc.Driver");
	conexion=DriverManager.getConnection(datos.MySQLgetServerData(),datos.getMySQLUser(),datos.getMySQLPassword());
	break;
	case 2:
	Class.forName( "com.microsoft.sqlserver.jdbc.SQLServerDriver" );
	conexion=DriverManager.getConnection(datos.getSQLServerData(),datos.getSQLUser(),datos.getSQLPassword()); 
	break;

	case 3:
	Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	conexion=DriverManager.getConnection(datos.getAccessServerData());
	break;
	}
	

	

	String nomEquipo="";
	String codLiga="";
	String localidad="";
	int internacional=0;
	
		PreparedStatement visualizar= conexion.prepareStatement("select * from equipos where codEquipo= "+modificado);
		ResultSet resultado= visualizar.executeQuery();
		
		while(resultado.next()) {
			nomEquipo= resultado.getString("nomEquipo");
			codLiga=resultado.getString("codLiga");
			localidad=resultado.getString("localidad");
			internacional=resultado.getInt("internacional");
		}
	

		System.out.println("Va a modificar los datos del equipo: "+modificado);
		System.out.print("Introduzca el nombre del equipo(actual:"+nomEquipo+"): ");
		nomEquipo=entrada.next();
		System.out.print("Introduzca el código de la liga(actual:"+codLiga+"): ");
		codLiga=entrada.next();
		System.out.print("Introduzca la localidad(actual:"+localidad+"):");
		localidad=entrada.next();
		System.out.print("¿Es internacional? (1=SÍ/0=NO)(actual:"+internacional+"):");
		internacional=entrada.nextInt();
		
		
		PreparedStatement modificar=conexion.prepareStatement("update equipos set nomEquipo= ?, codLiga=?,localidad=?,internacional=? where codEquipo= "+modificado);
		modificar.setString(1, nomEquipo);
		modificar.setString(2,codLiga);
		modificar.setString(3, localidad);
		modificar.setInt(4, internacional);
		
		modificar.executeUpdate();
		
		
		
	}
	
public void ejecutarProcedimiento(int opcion, int bdopcion) throws SQLException, ClassNotFoundException{
	switch (bdopcion) {
	case 1:
	Class.forName("com.mysql.cj.jdbc.Driver");
	conexion=DriverManager.getConnection(datos.MySQLgetServerData(),datos.getMySQLUser(),datos.getMySQLPassword());
	break;
	case 2:
	Class.forName( "com.microsoft.sqlserver.jdbc.SQLServerDriver" );
	conexion=DriverManager.getConnection(datos.getSQLServerData(),datos.getSQLUser(),datos.getSQLPassword()); 
	break;
	}
	
	switch (opcion) {
	case 1:
		System.out.print("Escribe el DNI del jugador para listar sus contratos: ");
		String dni= entrada.next();
		CallableStatement listarContratos=conexion.prepareCall("Call pr_listaContratos(?)");
		listarContratos.setString(1,dni);
		ResultSet resultado= listarContratos.executeQuery();
		
		if (resultado.equals(null)) {
			System.out.println("\nNo hay datos para ese futbolista en concreto\n");
		}
	
		
		while(resultado.next()) {

			System.out.print("| "+resultado.getInt("codContrato"));
			System.out.print("| "+resultado.getString("nomEquipo"));
			System.out.print("| "+resultado.getString("nomLiga"));
			System.out.print("| "+resultado.getString("fechaInicio"));
			System.out.print("| "+resultado.getString("fechaFin"));
			System.out.print("| "+resultado.getInt("precioanual"));
			System.out.println("| "+resultado.getInt("preciorescision"));
		}
	break;
	
	case 2:
		
		ArrayList<String>ligas= new ArrayList<String>();
		PreparedStatement visualizaLigas= conexion.prepareStatement("select nomLiga from ligas");
		ResultSet resultligas= visualizaLigas.executeQuery();
		while(resultligas.next()) {
		ligas.add(resultligas.getString("nomLiga"));		
		}
		
		String listaLigas="|";
		
		for(int i=0;i<ligas.size();i++)
		 listaLigas+=ligas.get(i)+"|";
		
		
		
		
		System.out.print("Escribe el nombre de la liga( "+listaLigas+" ):");
		String liga= entrada.next();
		CallableStatement listarEquipos=conexion.prepareCall("Call pr_equiposLiga(?)");
		listarEquipos.setString(1, liga);
		ResultSet result= listarEquipos.executeQuery();
		
	
		while(result.next()) {
			System.out.println(result.getString("nomEquipo"));
		}
	
		break;
	default:break;
	
	case 3: 
		System.out.print("Escribe el nombre del equipo: ");
		String equipo= entrada.next();
		CallableStatement listarJugadoresExtranjeros=conexion.prepareCall("Call jugadoresExtranjeros(?)");
		listarJugadoresExtranjeros.setString(1, equipo);
		ResultSet listaJugadores=listarJugadoresExtranjeros.executeQuery();
		
		if(listaJugadores.equals(null)) {
			System.out.println("\nNo hay datos para ese equipo en concreto\n\n");
			
		}
		
		while(listaJugadores.next()){
		System.out.print("| "+listaJugadores.getString("coddnionie"));
		System.out.println("| "+listaJugadores.getString("nombre"));
		System.out.println("| "+listaJugadores.getString("nacionalidad"));
		}
		
		
		break;
		
		
	case 4:
		int codEquipo;
		int precioAnual;
		int precioRescision;
		System.out.print("Escribe el código del equipo: ");
		codEquipo=entrada.nextInt();
		System.out.print("Escribe el precio anual del jugador: ");
		precioAnual=entrada.nextInt();
		System.out.print("Escribe el precio de rescisión del jugador: ");
		precioRescision=entrada.nextInt();
		CallableStatement listarJugadores=conexion.prepareCall("Call pr_listafutbolistas(?,?,?,?,?)");
		listarJugadores.setInt(1, codEquipo);
		listarJugadores.setInt(2, precioAnual);
		listarJugadores.setInt(3, precioRescision);
		listarJugadores.registerOutParameter(4, Types.INTEGER);
		listarJugadores.registerOutParameter(5, Types.INTEGER);
		listarJugadores.execute();
		System.out.println("\nNumero de jugadores: "+listarJugadores.getInt(4));
		System.out.println("Numero de jugadores con precio menor: "+listarJugadores.getInt(5)+"\n");
		
	
	}
	
	
	
	
}

public void ejecutarConsulta(int bdopcion) throws SQLException, ClassNotFoundException {
	
	switch (bdopcion) {
	case 1:
	Class.forName("com.mysql.cj.jdbc.Driver");
	conexion=DriverManager.getConnection(datos.MySQLgetServerData(),datos.getMySQLUser(),datos.getMySQLPassword());
	break;
	case 2:
	Class.forName( "com.microsoft.sqlserver.jdbc.SQLServerDriver" );
	conexion=DriverManager.getConnection(datos.getSQLServerData(),datos.getSQLUser(),datos.getSQLPassword()); 
	break;

	case 3:
	Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	conexion=DriverManager.getConnection(datos.getAccessServerData());
	break;
	}

PreparedStatement statement= conexion.prepareStatement("select codEquipo,nomEquipo,equipos.codLiga,nomLiga,localidad,internacional from equipos inner join ligas on equipos.codLiga=ligas.codLiga order by equipos.codLiga");


int codEquipo=0;
String nomEquipo="";
String codLiga="";
String nomLiga="";
String localidad="";
int internacional=0;


ResultSet resultado=statement.executeQuery();

while(resultado.next()) {
	codEquipo= resultado.getInt("codEquipo");
	nomEquipo= resultado.getString("nomEquipo");
	codLiga= resultado.getString("codLiga");
	nomLiga=resultado.getString("nomLiga");
	localidad = resultado.getString("localidad");
	internacional = resultado.getInt("internacional");
	
	System.out.println(codEquipo+ "| "+nomEquipo+" | "+codLiga+" | "+nomLiga+ "|"+localidad+" | "+internacional+"\n---------------------------");
	
	
}


}
}

