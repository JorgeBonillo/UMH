/**
 * @author jorgebonillo
 */
public class Alien extends Figura{

	private JuegoSpace juego;
	
	private int movimientoAlien = 8;
	/**
	 * Contructor de la clase Alien
	 * @param juego
	 * @param ref
	 * @param x
	 * @param y
	 */
	public Alien (JuegoSpace juego, String ref, int x, int y)
	{
		super(ref, x, y);
		
		this.juego = juego;
		dx = -movimientoAlien;
	}
	
	/**
	 * Metodo con el cual movemos a los aliens, tambien cuando ha llegado a uno de los extremos,
	 * llama a la funcion siguiente bajo, la cual hace que los aliens hagan la logica
	 * correspondiente
	 */
	public void mover() 
	{
		
		if ((dx < 0) && (x < 10)) 
		{
			juego.siguienteBajo();
		}

		if ((dx > 0) && (x > 750)) 
		{	
			juego.siguienteBajo();
		}
		
		super.mover();
		
	}
	
	/**
	 * Metodo para hacer la logica de los aliens, estos se moveran 10 px hacia bajo y iran
	 * hacia el otro lado
	 */
	public void logica()
	{
		dx = -dx;
		y += 10;
	}
	
	/**
	 * Notifica si esta figura ha colisionado con otra figura
	 */
	public void colisionaCon(Figura otro)
	{
		
	}
}
