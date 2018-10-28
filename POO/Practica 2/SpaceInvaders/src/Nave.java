/**
 * @author jorgebonillo
 *
 */
public class Nave extends Figura{

	/**
	 * Contructor de la clase Nave
	 * @param juego
	 * @param ref
	 * @param x
	 * @param y
	 */
	public Nave (JuegoSpace juego, String ref, int x, int y)
	{
		super(ref, x, y);
		
	}
	
	/**
	 * Metodo con el cual movemos la nave, ademas cuando esta llega a uno de los extremos
	 * se queda como si fuera una pared y no puede avanzar.
	 */
	public void mover() {
		
		if  ((dx < 0) && (x < 10)) 
		{
			return;
		}
		
		if ((dx > 0) &&(x > 750)) 
		{
			return;
		}
		
		super.mover();
	}

	/**
	 * Notifica si esta figura ha colisionado con otra figura.
	 */
	public void colisionaCon(Figura otro) {
		
		
	}
}
