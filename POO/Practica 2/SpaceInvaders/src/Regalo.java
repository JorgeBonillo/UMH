/**
 * @author jorgebonillo
 *
 */
public class Regalo extends Figura{

	private JuegoSpace juego;
	
	private int movimientoRegalo = 10;
	
	/**
	 * Contructor de la clase Regalo
	 * @param juego
	 * @param ref
	 * @param x
	 * @param y
	 */
	public Regalo(JuegoSpace juego, String ref, int x, int y)
	{
		super (ref, x, y);
		
		this.juego = juego;
		
		dy = movimientoRegalo;
	}
	
	/**
	 * Metodo para mover el regalo, ademas cuando llega al extremo inferior, el regalo se elimina
	 */
	public void mover ()
	{
		super.mover();
		
		if (y > 600)
		{
			juego.eliminarFigura(this);
		}
	}
	
	/**
	 * Notifica si esta figura ha colisionado con otra figura
	 */
	public void colisionaCon(Figura otro) 
	{
		if (otro instanceof Nave)
		{
			juego.regalo();
			juego.eliminarFigura(this);
		}
	}

}
