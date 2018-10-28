

/**
 * 
 */

/**
 * @author jorgebonillo
 *
 */
public class Frase {
	
	private static final String[] fraseNovato = {"HOLA","ADDIOS", "ECLIPSE"};
	private static final String[] fraseMedio = {"QUE TAL","COMO ESTAS", "COMO TE ENCUENTRAS"};
	private static final String[] fraseExperto = {"QUE TAL TE ENCUENTRAS","QUE BUEN DIA HACE", "HACE UN DIA SOLEADO"};
	
	private String frase;
	private int numeroAleatorio;
		
	
	/**
	 * Metodo que nos devuelve una frase entre las frases predeterminandas
	 * @param modoJuego
	 * @return frase elegida
	 */
	public String dameFrase(String modoJuego)
	{	
		
		if (modoJuego.equals("NOVATO"))
		{
			frase = fraseNovato[nAleatorio()];
		}
		else if (modoJuego.equals("MEDIO"))
		{
			frase = fraseMedio[nAleatorio()];
		}
		else if (modoJuego.equals("EXPERTO"))
		{
			frase = fraseExperto[nAleatorio()];
		}
		else
		{
			frase = fraseNovato[nAleatorio()];
		}
			
		return frase;
		
	}
	
	private int nAleatorio()
	{
		numeroAleatorio = (int) (Math.random() * 3);
		return numeroAleatorio;
	}
	
	
}
	
	
	
	
	
	
