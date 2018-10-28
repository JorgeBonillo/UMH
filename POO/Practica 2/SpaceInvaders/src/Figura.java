import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * @author jorgebonillo
 *
 */
public abstract class Figura {

	
	protected int x;
	protected int y;
	private Image imagen;
	protected int dx;
	protected int dy;
	protected String ref;
	
	public Figura (String ref, int x, int y)
	{
		this.ref = ref;
		this.x = x;
		this.y = y;
	}
	/**
	 * Metodo dibujar con el cual dibujamos en la posicion "x" e "y" la imagen pasada por la referencia "ref"
	 * @param g
	 */
	public void dibujar(Graphics g)
	{
		
		URL url = this.getClass().getClassLoader().getResource(ref);
		try {
			imagen = ImageIO.read(url);
			
			g.drawImage(imagen, x, y,null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * A partir de la altura y anchura de las figuras obtenidas con el metodo getWidth y getHeight, generamos un rectangulo 
	 * para cada una de las figuras, si los rectangulo intersectan, es por que ha habido una colision y devuelve el resultado
	 * true si hay colision o false si no hay colision.
	 * @param otro
	 * @return true si han colisionado, false si no han colisionado las figuras.
	 */
	public boolean haColisionadoCon(Figura otro) 
	{ 
		Rectangle me = new Rectangle(); 
		Rectangle him = new Rectangle();
		
		me.setBounds((int) x,(int) y, imagen.getWidth(null), imagen.getHeight(null));
		him.setBounds((int) otro.x,(int) otro.y, otro.imagen.getWidth(null), otro.imagen.getHeight(null));
		
		return me.intersects(him); 
	
	}
	
	/**
	 * Notifica si una figura a colisionado con otra.
	 * @param otra
	 */
	public abstract void colisionaCon(Figura otra);

	/**
	 * Hace logica en caso de que se haga falta en la clase
	 */
	public void logica()
	{
		
	}
	
	/**
	 * Metodo con el cual movemos cada figura dependiendo del dx o dy
	 */
	public void mover ()
	{
		x += dx;
		y += dy;
	}
	
	public void setMovimientoHorizontal(int dx)
	{
		this.dx = dx;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
}
