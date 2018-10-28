import java.util.Scanner;


/**
 * @author jorgebonillo
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String alias, password, nombre, modoJuego, fechaNacimiento;
		int partidasJugar;
		
		alias = "";
		password = "";
		fechaNacimiento = "";
		partidasJugar = 0;
		modoJuego = "";
		nombre = "";
	
		Jugador jugador = new Jugador(alias, password, fechaNacimiento, nombre);
		JuegoRuletaFortuna ruleta = new JuegoRuletaFortuna(modoJuego);
		Partida partida = new Partida(jugador,ruleta, partidasJugar);

		Scanner scanInput = new Scanner(System.in);
		
		System.out.println("Introduce el nombre: ");
		nombre = scanInput.nextLine();
		jugador.setNombre(nombre);
	
		
		System.out.println("Introduce el alias: ");
		alias = scanInput.nextLine();
		jugador.setAlias(alias);
		
		
		System.out.println("Introduce la password: ");
		password = scanInput.nextLine();
		jugador.setPassword(password);
		
		System.out.println("Introduce el modo de juego: (NOVATO/MEDIO/EXPERTO)");
		modoJuego = scanInput.nextLine();
		ruleta.setModoJuego(modoJuego);
		
		System.out.println("Introduce el fecha de nacimiento (DD/MM/YYYY): ");
		fechaNacimiento = scanInput.nextLine();
		jugador.setFechaNacimiento(fechaNacimiento);
		
		partida.iniciar();
		
		scanInput.close();
		
		
		
	}

}
