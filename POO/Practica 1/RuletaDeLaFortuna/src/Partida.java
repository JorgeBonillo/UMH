import java.util.Scanner;

/**
 * 
 */

/**
 * @author jorgebonillo
 *
 */
public class Partida {
	private int partidasJugar, pAcumulada;
	private String respuesta;
	private Jugador jugador;
	private JuegoRuletaFortuna juego;
	
	public Partida(Jugador jugador, JuegoRuletaFortuna juego, int partidasJugar)
	{
		this.jugador = jugador;
		this.juego = juego;
		this.partidasJugar = partidasJugar;
	}
	
	public Partida (Jugador jugador, int partidasJugar)
	{
		this.jugador = jugador;
		this.partidasJugar = partidasJugar;
	}
	
	public Partida (int partidasJugar)
	{
		this.partidasJugar = partidasJugar;
	}
	
	public void setPartidasJugar(int partidasJugar) {
		this.partidasJugar = partidasJugar;
	}
	
	Scanner scanInput = new Scanner(System.in);
	/**
	 * Metodo que nos permite escoger cuantas partidas queremos jugar
	 */
	public void nPartidas()

	{
		
		System.out.println("Quieres escoger el numero de partidas? (SI/NO)");
		respuesta = scanInput.nextLine();
		respuesta = respuesta.toUpperCase();
		
		if (respuesta.equals("SI"))
		{
			System.out.println("Cuantas partidas quieres jugar?");
			partidasJugar = scanInput.nextInt();
			new Partida(partidasJugar);
		}
		else if (respuesta.equals("NO"))
		{
			partidasJugar = 3;
			new Partida(partidasJugar);
		}
		else if (respuesta.equals(""))
		{
			partidasJugar = 3;
			new Partida(partidasJugar);
		}
	}
	
	/**
	 * Metodo iniciar con el cual iniciamos los juegos
	 */
	public void iniciar()
	{
		if (jugador.comprobaciones() == true)
		{
			if (juego.comprobarEdad(jugador.getFechaNacimiento()) == true )
			{
				nPartidas();
				
				for (int i = 1; i <= partidasJugar; i++) 
				{
					System.out.println("Partida n: " + i);
					pAcumulada += juego.jugar();
				}
				System.out.println("Enhorabuena: " + jugador.getNombre());
				System.out.println("Has obtenido una puntuacion total en las " + partidasJugar + " partidas que has jugado de: " + pAcumulada + "puntos.");
			}
			else if (juego.comprobarEdad(jugador.getFechaNacimiento()) == false )
			{
				System.out.println("No puedes jugar");
			}
		}
		else if (jugador.comprobaciones() == false)
		{
			System.out.println("No tienes la edad suficiente para jugar");
		}
		else 
		{
			System.out.println("Error");
		}
		
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public void setJuego(JuegoRuletaFortuna juego) {
		this.juego = juego;
	}
	
}
