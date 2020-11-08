package aed.accesobd;


import java.util.Scanner;

public class Main {
	
private static int opcion;
private static int bdopcion;

	public static void main(String[] args) {
	
		Scanner entrada= new Scanner(System.in);
		
		
		do {
			showDataBaseMenu();
			bdopcion=entrada.nextInt();
		switch(bdopcion) {
			
		case 1:
		
		do {
		showMenu();
		opcion= entrada.nextInt();
		switch(opcion) {
		case 1:
			try {
			Accion mostrarDatos= new Accion();
			mostrarDatos.ejecutarConsulta(bdopcion);
			}catch(Exception e){
			System.err.println("\n\nNo se pudo ejecutar la consulta\n\n");	
			}
			break;
		case 2:
			Accion insercion= new Accion();
			try {
			insercion.insertarRegistro(bdopcion);
			System.out.println("\nRegistro insertado correctamente\n");
			}catch(Exception e) {
			System.err.println("\n\nNo se pudo insertar el registro\n\n");
				
			}
			break;
		case 3:
			try {
			System.out.print("Escribe el código del equipo que quieres borrar: ");
			String eliminado= entrada.next();	
			Accion eliminar= new Accion ();
			eliminar.eliminarRegistro(eliminado,bdopcion);
			System.out.println("\nRegistro eliminado correctamente\n");
			}catch(Exception e) {
			System.err.println("\n\nNo se pudo borrar el registro\n\n");
			
			}
			break;
		case 4:
			try {
				System.out.print("Escribe el código del equipo que quieres modificar: ");
				int modificado= entrada.nextInt();
				Accion modificar= new Accion();
				modificar.modificarRegistro(modificado,bdopcion);
				System.out.println("\nRegistro modificado correctamente\n\n");
				
			}catch(Exception e) {
				System.err.println("\nNo se pudo modificar el registro\n");
			}
			break;
		case 5:
			try {
		showProcedureMenu();
		int opcion = entrada.nextInt();
		Accion procedimiento= new Accion();
		procedimiento.ejecutarProcedimiento(opcion,bdopcion);
			}catch(Exception e) {
				e.printStackTrace();
				System.err.println("\nHa ocurrido un error\n");
			}
			break;
	
		case 6: 
			break;
			
		default: break;
		}

		}while(opcion!=6);
		
		break;
		
		
		case 2:
			
			do {
				showMenu();
				opcion=entrada.nextInt();
			switch(opcion) {
			case 1:
				try {
				Accion consulta= new Accion();
				consulta.ejecutarConsulta(bdopcion);
				}catch(Exception e) {
					System.err.println("\nNo se ha podido ejecutar la consulta\n");
				}
				break;
			case 2:
				try {
				Accion insercion= new Accion();
				insercion.insertarRegistro(bdopcion);
				System.out.println("\nRegistro insertado correctamente\n");
				
				}catch(Exception e) {
					System.err.println("\nNo se pudo insertar el registro\n");
				}
				break;
			case 3:
				try {
					String eliminado;
					System.out.print("Escribe el código del equipo que quieres eliminar: ");
					eliminado=entrada.next();
					Accion borrar= new Accion();
					borrar.eliminarRegistro(eliminado,bdopcion);
					System.out.println("\nRegistro eliminado correctamente\n");
				}catch(Exception e) {
					System.err.println("\nNo se pudo borrar el registro indicado\n");
					
				}
				break;
			case 4:
				try {
					int modificado;
					modificado = entrada.nextInt();
					Accion modificar= new Accion();
					modificar.modificarRegistro(modificado,bdopcion);
					System.out.println("\nRegistro modificado correctamente\n");
					
				}catch(Exception e) {
					System.err.println("\nNo se pudo modificar\n");
				}
				break;
			case 5:	
				try {
					showProcedureMenu();
					int opcion = entrada.nextInt();
					Accion procedimiento= new Accion();
					procedimiento.ejecutarProcedimiento(opcion,bdopcion);
					
				}catch(Exception e) {
					System.err.println("\nNo se pudo acceder\n");
				}
				break;
			case 6:break;
			default:break;
				
			}
			
		
				
				
			}while(opcion!=6);
			break;
			
		case 3:
		
		do {
			showAccessMenu();
			opcion=entrada.nextInt();
			switch(opcion) {
			case 1:
				try {
				Accion consulta= new Accion();
				consulta.ejecutarConsulta(bdopcion);
				}catch(Exception e) {
					System.err.println("No se pudo ejecutar la consulta");
				}
				break;
			case 2:
				try {
					Accion insertar= new Accion();
					insertar.insertarRegistro(bdopcion);
					System.out.println("\nRegistro insertado correctamente\n");
					
				}catch(Exception e) {
					System.err.println("\nNo se pudo insertar el registro.\n");
				}
				break;
			case 3:
				try {
					System.out.print("Escribe el código del equipo que deseas eliminar: ");
					String eliminado= entrada.next();
					Accion borrar= new Accion();
					borrar.eliminarRegistro(eliminado,bdopcion);
					System.out.println("\nRegistro eliminado correctamente\n");
					
					
				}catch(Exception e) {
					System.err.println("No se pudo borrar el registro");
				}
				break;
			case 4:
				try {
				System.out.print("Escribe el código del equipo que deseas modificar: ");
				int modificado=entrada.nextInt();
				Accion modificar= new Accion();
				modificar.modificarRegistro(modificado,bdopcion);
				System.out.println("\nRegistro modificado correctamente\n");
				}catch(Exception e) {
					System.err.println("No se pudo modificar el registro");
				}
				break;
			case 5:break;
			default: break;
			}
			
		}while(opcion!=5);
		
		default:break;	
	
		
	}
		}while(bdopcion!=4);
		
		entrada.close();
		
	}
	
	
	
public static void showDataBaseMenu() {
	System.out.println("\n\n¿Qué base de datos vas a utilizar?");
	System.out.println("1.MySQL");
	System.out.println("2.SQLServer");
	System.out.println("3.Access");
	System.out.println("4.Salir");
	System.out.print("Elige una opción: ");
}	
	
public static void showMenu() {
		
		System.out.println("\n\nBASE DE DATOS BDFUTBOL");
		System.out.println("----------------------");
		System.out.println("1.Consultar datos de la tabla");
		System.out.println("2.Añadir un nuevo registro");
		System.out.println("3.Eliminar un registro");
		System.out.println("4.Modificar un registro");
		System.out.println("5.Ejecutar un procedimiento");
		System.out.println("6.Cambiar base de datos/Salir");
		System.out.println("-------------------------");
		System.out.print("Elige una opción: ");
		
	}
	
public static void showProcedureMenu() {
	System.out.println("1.Listar contratos de un futbolista.");
	System.out.println("2.Listar los equipos de una liga.");
	System.out.println("3.Listar a los jugadores extranjeros.");
	System.out.println("4.Contar jugadores en base a su precio anual y precio de rescisión.");
	System.out.println("Elige una opcion: ");
	
}	

public static void showAccessMenu() {
	
	System.out.println("\n\nBASE DE DATOS BDFUTBOL");
	System.out.println("----------------------");
	System.out.println("1.Consultar datos de la tabla");
	System.out.println("2.Añadir un nuevo registro");
	System.out.println("3.Eliminar un registro");
	System.out.println("4.Modificar un registro");
	System.out.println("5.Cambiar base de datos/Salir");
	System.out.println("-------------------------");
	System.out.print("Elige una opción: ");
	
}
}
