/**
 * @author jorgebonillo
 */
public class DisparoAlien extends Figura{
	
	private JuegoSpace juego;
	
	private int movimientoDisparo = 20;
	/**
	 * Contructor de DisparoAlien
	 * @param juego
	 * @param ref
	 * @param x
	 * @param y
	 */
	public DisparoAlien(JuegoSpace juego, String ref, int x, int y)
	{
		super (ref, x, y);
		
		this.juego = juego;
		
		dy = movimientoDisparo;
	}
	
	/**
	 * Metodo con el cual movemos los disparos y ademas cuando un disparo ha llegado al final
	 * de la pantalla lo eliminamos
	 */
	public void mover()
	{
		super.mover();
		{
			if (y > 600)
			{
				juego.eliminarFigura(this);
			}
		}
	}
	
	/**
	 * Notifica si esta figura ha colisionado con otra figura
	 */
	public void colisionaCon(Figura otro)
	{
		if (otro instanceof Nave)
		{
			juego.vidas();
			juego.eliminarFigura(this);
		}
	}
	
}
