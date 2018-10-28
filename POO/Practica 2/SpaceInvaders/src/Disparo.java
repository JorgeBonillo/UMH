/**
 * @author jorgebonillo
 */
public class Disparo extends Figura{

	private JuegoSpace juego;
	
	private int movimientoDisparo = -20;
		
	/**
	 * Constructor de la clase Disparo
	 * @param juego
	 * @param ref
	 * @param x
	 * @param y
	 */
	public Disparo (JuegoSpace juego, String ref, int x, int y)
	{
		super(ref, x, y);
		
		this.juego = juego;
		
		dy = movimientoDisparo;
	}
	
	/**
	 * Metodo para mover el disparo, ademas cuando este llega al extremo superior se elimina
	 */
	public void mover()
	{
		super.mover();
		
		if(y < 0)
		{
			juego.eliminarFigura(this);
		}
	}
	
	/**
	 * Notifica si esta figura ha colisionado con otra figura
	 */
	public void colisionaCon(Figura otro)
	{
		if (otro instanceof Alien)
		{
			juego.eliminarFiguras(this, otro);
		}
	}
	
}
