/**
 * 
 */

/**
 * @author jorgebonillo
 *
 */
public class Caracter {
	private static final String CONSONANTE = "BCDFGHJKLMNPQRSTVWXYZ";
	private static final String  VOCAL = "AEIOU";
	private String elegidaVocalConsonante;
	
	
	public void setElegidaVocalConsonate(String elegidaVocalConsonante) {
		this.elegidaVocalConsonante = elegidaVocalConsonante;
	}

	/**
	 * metodo que nos comprueba si el cactere elegido es consonante
	 * @return true si es consonante, false si es vocal
	 */
	public Boolean esConsonante()
	{
		return CONSONANTE.contains(elegidaVocalConsonante);
	}
	/**
	 * metodo que nos comprueba si el caracter elegido es vocal
	 * @return true == vocal; false si es consonante;
	 */
	public Boolean esVocal()
	{
		return VOCAL.contains(elegidaVocalConsonante);
	}
}
