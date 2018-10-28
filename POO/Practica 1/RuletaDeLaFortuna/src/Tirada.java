

/**
 * 
 */

/**
 * @author jorgebonillo
 *
 */
public class Tirada {
	private int[] puntuacion = {10, 20, 30, 40, 0};
	private int punt, numeroAleatorio;
	
	//Elegimos dentro del array puntuacion, la puntuacion por la que jugamos
	public int puntuacionJugamos() 
	{
		punt = puntuacion [nAleatorio()];
		return punt;
	}
	
	/**
	 * metodo para elegir entre los numero de la puntacion, uno aleatorio
	 * @return numero aleatorio entre 1 y 
	 */
	private int nAleatorio()
	{
		
		
		numeroAleatorio = (int) (Math.random() * 5);
		return numeroAleatorio;
		
		
	}
	
	
	
}


