
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author jorgebonillo
 *
 */
public class JuegoRuletaFortuna {

	private static final String REG_EXP_ALL_CARACTERES = "[A-Z]";
	private static final String REG_EXP = "[^%s\\s]";						
	private static final String CHAR_ENC = "_";

	private String modoJuego, respuestaComprar, elegidaVocalConsonante, elegidaConsonante;
	private String fraseVirgen, respuestaResolver, respuestaFrase;
	private String caracteresAcertadas;
	private String caracteresFalladas;
	private String cadenaEncriptada = "";
	private int intentos, puntuacionAcumulada, puntuacionJugamos;
	private int nConsonantesFrase;
	
	Scanner scanInput = new Scanner(System.in);
	
	Tirada tirada = new Tirada();
	Frase frase = new Frase();
	Caracter caracter = new Caracter();
	
	public JuegoRuletaFortuna(String modoJuego)
	{
		this.modoJuego = modoJuego;
		caracteresAcertadas = "";
		caracteresFalladas = "";
	}
	
	public String getModoJuego() {
		return modoJuego;
	}

	public JuegoRuletaFortuna ()
	{
		caracteresAcertadas = "";
		caracteresFalladas = "";
		puntuacionAcumulada = 0;
	}
	
	public void setModoJuego(String modoJuego) 
	{
		this.modoJuego = modoJuego;
	}
	
	public int jugar()
	{
		caracteresAcertadas = "";
		caracteresFalladas = "";
		puntuacionAcumulada = 0;
		
		modoJuego = comprobarNivel(modoJuego);
		intentos = nIntentos(modoJuego);
		fraseVirgen = frase.dameFrase(modoJuego);
				System.out.println("Frase: " + fraseVirgen);
				for (int j = 0; j < intentos; j++) 
				{
					System.out.println("Intentos restantes: " + (intentos - j));
					actualizarFraseEncriptada();
					puntuacionJugamos = tirada.puntuacionJugamos();
					System.out.println("Puntuacion por la que jugamos: " + puntuacionJugamos);
					
					if (puntuacionJugamos == 0)
					{
						puntuacionAcumulada = 0;
						System.out.println("Has perdido");
						
					}
					else if (puntuacionJugamos > 0)
					{
						System.out.println("Consonante por la que jugamos: ");
						elegidaConsonante = scanInput.nextLine();
						elegidaConsonante = elegidaConsonante.toUpperCase();
						caracter.setElegidaVocalConsonate(elegidaConsonante);
				
						if (consonanteYaElegida() == true)
						{
							System.out.println("Has elegido una consonante ya elegida");
							System.out.println("Has perdido el turno");
						}
						else if (consonanteYaElegida() == false)
						{
							
							if ((caracter.esConsonante() == true))
							{
								if (validaConsonante(elegidaConsonante) == true)
								{
									System.out.println("Puntuacion total hemos jugamos: " + nConsonantes() * puntuacionJugamos);
									puntuacionAcumulada += (nConsonantesFrase * puntuacionJugamos);
									System.out.println("Puntuacion acumulada: " + puntuacionAcumulada);
								}
								else if (validaConsonante(elegidaConsonante) == false)
								{
									System.out.println("Puntuacion acumulada: " + puntuacionAcumulada);
								}
							}
						
							else if (caracter.esConsonante() == false)
							{
								System.out.println("Has elegido una vocal");
								j--;
							}
							
						}
					
						if (puntuacionAcumulada >= 30)
						{
							comprarVocal();
						}
						
						if (esGanador() == true)
						{
							j = intentos;
						}
						
					}
					
					
				}
			return puntuacionAcumulada;
			}
		
	/**
	 * Metodo que comprueba que el usuario que juega tiene mas de 10 años
	 * @param fechaNacimiento
	 * @return true si puede tiene mas de 10 años, false si tiene menos de 10 años
	 */
	public boolean comprobarEdad(String fechaNacimiento)
	{
		boolean comprobacion = false;
		Calendar cal= Calendar.getInstance(); 
		int year= cal.get(Calendar.YEAR); 
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DATE);
		year = (year-10);
		String fechaActual = day + "/" + month + "/" + year;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date fecha1 = sdf.parse(fechaNacimiento , new ParsePosition(0));
		Date fecha2 = sdf.parse(fechaActual , new ParsePosition(0));
		
		
		if (fecha1.after(fecha2))
		{
			//System.out.println("No puede jugar");
			comprobacion = false;
		}
		else if (fecha1.before(fecha2))
		{
			//System.out.println("Puede jugar");
			comprobacion = true;
		}
		else 
		{
			System.out.println("Formato introducido no correcto");
			comprobacion = false;
		}
	
		return comprobacion;
	}
	
	/**
	 * Comprueba que el modo de juego introducido por el jugador sea uno de los propuestos, si no el modo de juego por defecto es novato
	 * @param modoJuego
	 * @return modo de juego
	 */
	private String comprobarNivel(String modoJuego)
	{	
		modoJuego = modoJuego.toUpperCase();
		
		if (modoJuego.equals("NOVATO"))
		{
			//System.out.println("1");
			return "NOVATO";
		}
		else if (modoJuego.equals("MEDIO"))
		{
			//System.out.println("2");
			return "MEDIO";
		}
		else if (modoJuego.equals("EXPERTO"))
		{
			//System.out.println("3");
			return "EXPERTO";
		}
		else 
		{
			//System.out.println("4");
			return "NOVATO";
		}
	}
	
	/**
	 * Segun el modo de juego que ha introducido el jugador, nos devuelve el numero de juegos
	 * @param modoJuego
	 * @return el numero de intentos dependiendo del modo de juego
	 */
	private int nIntentos(String modoJuego)
	{	
		modoJuego = modoJuego.toUpperCase();
			
		if (modoJuego.equals("NOVATO"))
		{			
			//System.out.println("1");
			return 10;
		}
		else if (modoJuego.equals("MEDIO"))
		{
			//System.out.println("2");
			return 8;
		}
		else if (modoJuego.equals("EXPERTO"))
		{
			//System.out.println("3");
			return 5;
		}
		else 
		{
			//System.out.println("4");
			return 10;
		}
	}
	
	/**
	 * Metodo con el cual nos pregunta si queremos comprar una vocal y en caso afirmativo actualiza la frase ocualta
	 * y nos resta 30 puntos
	 * @return puntuacion despues de restarle los 30 de la puntuacion acumulada en caso afirmativo
	 */
	private int comprarVocal()
	{
		System.out.println("¿Quieres comprar una vocal? (SI/NO)");
		respuestaComprar = scanInput.nextLine();
		respuestaComprar = respuestaComprar.toUpperCase();
		
		switch (respuestaComprar) {
		case "SI":
		{
			do
			{
				System.out.println("Que vocal quieres comprar?");
				elegidaVocalConsonante = scanInput.nextLine();
				elegidaVocalConsonante = elegidaVocalConsonante.toUpperCase();
				caracter.setElegidaVocalConsonate(elegidaVocalConsonante);
				if (caracter.esVocal() == true)
				{
					puntuacionAcumulada -= 30;
					validaConsonante(elegidaVocalConsonante);
					break;
				}
				System.out.println("No has elegido una vocal");
			} while (caracter.esVocal() == false);
			
		}
		case	 "NO":
		{
			break;
		}
			
		default:
			comprarVocal();
		}
		return puntuacionAcumulada;
		
	}
	
	/**
	 * Metodo que comprueba que la consonante elegida no ha sido elegida anteriormente
	 * @return true si se ha elegido, false si no se ha elegido
	 */
	private boolean consonanteYaElegida()
	{
		if (caracteresAcertadas.contains(elegidaConsonante))
		{
			System.out.println("Caracter ya elegido");
			return true;
		}
		else 
		{
		return false;
		}
	}
	
	/**
	 * comprueba si hemos ganado o no
	 * @return
	 */
	private boolean esGanador()
	{
		boolean existe = false;
		if ((resolverFrase() == true))
		{
			existe = true;
		}
		return existe;
	}
	
	
	
	/**
	 * Metodo que nos permite resolver la frase oculta
	 * @return true si ha acertado, false si ha fallado
	 */
	private boolean resolverFrase()
	{
		System.out.println("Quieres resolver? (SI/NO)");
		respuestaResolver = scanInput.nextLine();
		respuestaResolver = respuestaResolver.toUpperCase();
		
		if (respuestaResolver.equals("SI"))
		{
			System.out.println("Cual es la frase?");
			respuestaFrase = scanInput.nextLine();
			respuestaFrase = respuestaFrase.toUpperCase();
			if (respuestaFrase.equals(fraseVirgen))
			{
				//System.out.println("Has acertado");
				return true;
			}
		}
		else if (respuestaResolver.equals("NO"))
		{
			return false;
		}
		else 
		{
			resolverFrase();
		}
		return false;
		
	}
	
	/**
	 * Metodo que nos devuelve cuantas consonantes hay repetidas en una frase dependiendo de la 
	 * consonante elegida por el jugador
	 * @return numero de consonantes que tiene la frase elegida, dependiendo de la consonante elegida por 
	 * el usuario
	 */
	private int nConsonantes()
	{
		nConsonantesFrase = 0;
		for (int i = 0; i < fraseVirgen.length(); i++) 
		{
			elegidaConsonante = elegidaConsonante.toUpperCase();
			if(fraseVirgen.charAt(i)== elegidaConsonante.charAt(0) )
			{
			nConsonantesFrase++;
			}
			
			}
		return nConsonantesFrase;
		
	}
	
	/**
	 * Metodo que añade la consonante-vocal elegida a una cadena de consonantes acertadas o falladas 
	 * @param caracter
	 * @return
	 */
	private Boolean validaConsonante(String caracter)
	{
		Boolean existe;
		caracter = caracter.toUpperCase();
		if(fraseVirgen.indexOf(caracter) != -1)
		{
			caracteresAcertadas+=caracter;
			
			actualizarFraseEncriptada();
			
			existe=true;
		}else
		{			
			caracteresFalladas+=caracter;
			existe=false;
		}
		System.out.println("Caracteres acertados: " + caracteresAcertadas);
		System.out.println("Caracteres fallados: " + caracteresFalladas);
		return existe;
	}
	
	/**
	 * Actualiza la frase oculta
	 */
	private void actualizarFraseEncriptada()
	{
		//System.out.println("Hola");
		if(caracteresAcertadas.isEmpty())
		{
			cadenaEncriptada = fraseVirgen.replaceAll(REG_EXP_ALL_CARACTERES, CHAR_ENC);
			System.out.println(cadenaEncriptada);
			//cadenaEncriptada = this.getFraseEncriptada();
		}else
		{
			//String.format reemplaza el %s por el contenido de la cadena consonantesAcertadas
			cadenaEncriptada = fraseVirgen.replaceAll(String.format(REG_EXP, caracteresAcertadas), CHAR_ENC);	
			System.out.println(cadenaEncriptada);
			//Otra forma de hacerlo
			//cadenaEncriptada = fraseVirgen.replaceAll("[^" + consonantesAcertadas + "\\s]", caracterReemplazo);
		}
		
	}
	
}