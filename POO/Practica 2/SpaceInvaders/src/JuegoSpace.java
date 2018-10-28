import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * @author jorgebonillo
 *
 */
public class JuegoSpace extends Canvas {

	private static final int ANCHO = 800;
	private static final int ALTO = 600;
	private static final int PUNTUACIONMUERTE = 10;
	private static final int PUNTUACIONREGALO = 200;
	private static final int NAVEX = 370;
	private static final int NAVEY = 550;
	
	private final long intervaloDisparo = 200;
	
	private BufferStrategy strategy;
	
	private final ArrayList<Figura> figuras = new ArrayList<Figura>();
	
	private final ArrayList figurasEliminar = new ArrayList();
	
	private boolean leftPressed = false;
	private boolean rightPressed = false;
	private boolean firePressed = false;
	private boolean puedeSeguirJugando = true;
	private boolean logica = false;
	
	private String regalo;
	
	private int puntuacion;
	private int nAliens;
	private int vidas;
	
	private long ultimoDisparo = 0;
	
	private Figura nave;
	
	/**
	 * Contructor de la clase JuegoSpace
	 */
	public JuegoSpace()
	{
		JFrame container = new JFrame("Space Invaders JSB");
		JPanel panel = (JPanel) container.getContentPane();
		
		panel.setPreferredSize(new Dimension(ANCHO,ALTO));
		panel.setLayout(null);
		
		setBounds(0,0,ANCHO,ALTO);
		panel.add(this);
		
		setIgnoreRepaint(true);
		
		container.pack();
		container.setResizable(false);			//Decimos que no se puede redimensionar
		container.setVisible(true);				//Lo hacemos visible
		
		container.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		addKeyListener(new KeyInputHandler());
		
		//Obtenemos el foco
		requestFocus();

		// create the buffering strategy which will allow AWT to manage our accelerated graphics
		createBufferStrategy(2);
		strategy = getBufferStrategy();
		
		regalo = "nada";
		
	}
	
	/**
	 * Metodo que permite que cada vez que se llama a la funcion, se pinte un disparo, por defecto tenemos que 
	 * tiene que haber un tiempo de 0,5 seg entre cada disparo, si el tiempo es menor a 0,5 seg, no se dispara,
	 * si el tiempo es mayor a 0,5 seg se dispara, para calcular el tiempo entre cada disparo, comprobamos la 
	 * ultima vez que se disparo con la vez que se ha pulsado disparar.
	 */
	public void disparar() {
        if ((System.currentTimeMillis() - ultimoDisparo) < intervaloDisparo) {
        	return;
        }

        ultimoDisparo =  System.currentTimeMillis();
        Disparo disparo = new Disparo(this, "res/shot.gif", nave.getX() + 10, nave.getY() - 30);
        figuras.add(disparo);
    }
	
	/**
	 * Metod que hace que el proximo loop, entre a hacer logica el juego
	 */
	public void siguienteBajo()
	{
		logica = true;
	}
	
	/**
	 * Metodo con el cual añadimos las figuras a eliminar a un array cuando hay una colision, tambien sumamos una puntuacion
	 * en esta caso de 10 cada vez que matamos a un alien y decrementamos el numero de aliens con el cual sabremos
	 * cuando hemos ganado.
	 * @param figura1 disparo
	 * @param figura2 alien
	 */
	public void eliminarFiguras (Figura figura1, Figura figura2)
	{
		figurasEliminar.add(figura1);
		figurasEliminar.add(figura2);
		puntuacion += PUNTUACIONMUERTE;
		nAliens--;
		posibilidadRegalo(figura2);
	}
	
	/**
	 * Metodo con el cual añadimos una figura (regalo, disparo, disparo alien...) al array de figuras a eliminar cuando se 
	 * ha salido del espacio de juego
	 * @param figura que se va a eliminar
	 */
	public void eliminarFigura (Figura figura)
	{
		figurasEliminar.add(figura);
	}
	
	/**
	 * Metodo con el cual sumamos la puntuacion del regalo o la vida a la puntuacion global de la partida
	 */
	public void regalo()
	{
		int tipoRegalo = (int) (Math.random() * 3); 
		
		switch (tipoRegalo) {
			case 1:
			{
				regalo = "+200 puntuacion";
				puntuacion += PUNTUACIONREGALO;
				break;
			}
			case 2:
			{
				regalo = "+1 vida";
				vidas++;
				break;
			}
		}
	}
	
	/**
	 * Metodo que nos permite de forma aleatoria crear un regalo, para ello generamos un numero aleatorio entre 1 y 20 y 
	 * si el numero es igual a 1, creamos un regalo, lo pintamos y lo añadimos al array de figuras.
	 * @param figura
	 */
	public void posibilidadRegalo(Figura figura)
	{
		Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
		
		if ((int) (Math.random() * 20) == 1)
		{
			 Regalo regalo = new Regalo(this, "res/regalo.png", figura.getX(), figura.getY());
			 regalo.dibujar(g);
		     figuras.add(regalo);
		}
	}
	
	/**
	 * Metodo con el cual inicializamos las figuras, es decir, pintamos por primera vez las figuras en el frame, 
	 * tambien las añadimos al array de figuras y contamos cuantos aliens hemos pintamos para saber cuando hemos
	 * ganado el juego
	 * @param filas
	 * @param columnas
	 */
	public void iniciarFiguras(int filas, int columnas)
	{
		
		nAliens = 0;
		
		Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
		
		nave = new Nave(this, "res/ship.gif", NAVEX, NAVEY);
		nave.dibujar(g);
		figuras.add(nave);
		
		
		for (int row = 0; row < columnas; row++) 
		{
			for (int x = 0; x < filas; x++) 
			{
				Figura alien = new Alien(this, "res/alien0.gif", 100 + (x * 50), (50) + row * 30);
				alien.dibujar(g);
				figuras.add(alien);
				nAliens++;
			}
		}
		
	}
	
	/**
	 * Metodo que nos implementa un marcador en la parte superior izquierda, y se va actualizando cada vez que
	 * matamos a un alien
	 */
	public void marcador()
	{
		Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
		
		g.setColor(Color.white);
		Font small = new Font("Helvetica", Font.BOLD, 24);
		g.setFont(small);
		g.drawString("" + puntuacion, 20 , 20);
		
		g.drawString("Vidas restantes: " + vidas, 100 , 20);
		
		//Comprobaciones yo
		g.drawString("Entidades: " + (figuras.size()), 310 , 20);
		
		g.drawString("Regalo: " + regalo, 500 , 20);
		
	}
	
	/**
	 * Metodo con el cual cuando somos ganadores de una partida, limpiar los dos array's que tenemos,
	 * tanto el array de figuras añadidas como el de figuras que tenemos para eliminar.
	 */
	public void ganador()
	{
		figuras.clear();
		figurasEliminar.clear();
	}
	
	/**
	 * Metodo con el cual contabilizamos las vidad que tiene el jugador, en caso de que no queden vidas
	 * el jugador habra terminado dicha partida
	 */
	public void vidas()
	{
		vidas--;
		
		if (vidas == 0)
		{
			puedeSeguirJugando = false;
			
			figuras.clear();
			figurasEliminar.clear();
		}
		
	}
	
	/**
	 * Metodo con el cual los aliens puede disparar para matar la nave, el disparo se realiza de forma aleatoria
	 * @param figura
	 */
	public void disparoAlien(Figura figura)
	{
		Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
		
			if ((int) (Math.random() * 2000) == 1)
			{
				DisparoAlien disparoalien = new DisparoAlien(this, "res/shotBad.gif", figura.getX(), figura.getY());
				disparoalien.dibujar(g);
				figuras.add(disparoalien);
			}
	}
	
	/**
	 * Metodo con el cual iniciamos las partidas que queremos jugar.
	 */
	public void game() 
	{
		
		pantallaJuego(5,12);
		
		pantallaJuego(8,12);
		
		pantallaFinal();
			
	}
	
	/**
	 *  Metodo con el cual jugamos al juego Space Invaders
	 */
	private void pantallaJuego(int filas, int columnas)
	{
		/**
		 * Inicializamos los aliens
		 */
		iniciarFiguras(columnas,filas);
	
		puedeSeguirJugando = true;
		
		/**
		 * Las vidas con la que se va a jugar la partida son 3 por defecto
		 */
		
		vidas = 3;
		
		while (puedeSeguirJugando) 
		{
			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
			
			/**
			 * Comprobamos si el jugador a ganado la partida, eso lo realizamos cuando el numero de
			 * aliens es igual a = 0, eso quiere decir que no queda ningun alien en el juego, en caso 
			 * contrario volvemos a pintar el marcador
			 */
			
			if (nAliens == 0)
			{
				ganador();
				puedeSeguirJugando = false;
				
			}
			else if(nAliens > 0)
			{
				g.setColor(Color.black);
				g.fillRect(0,0,ANCHO,ALTO);
				marcador();
			}
			
			/**
			 * Para todos las figuras dentro del array de figuras, les hacemos moverse cada una de su forma 
			 * en que debe
			 */
			 for (int p = 0; p < figuras.size(); p++) 
			 {
				 Figura figura = (Figura) figuras.get(p);
				 
		         figura.mover();
		     }
			
			 /**
			  * Para todas las figuras dentro del array de figuras, les hacemos que se pinten cada una en su 
			  * lugar que debe
			  */
			 for (int p = 0; p < figuras.size(); p++) 
			 {
				 Figura figura = (Figura) figuras.get(p);
				
				 figura.dibujar(g);
		     }
			 
			 /**
			  * Para cada alien, hacemos que pueda intentar disparar un disparo malo, es decir, un disparo hacia 
			  * la nave
			  */
			 for (int p = 0; p < figuras.size(); p++) 
			 {
				 Figura figura = (Figura) figuras.get(p);
				
				 disparoAlien(figura);
		      }
			 
			 /**
			  * A traves de fuerza bruta detectamos las colisiones entre todas las figuras dentro del array de 
			  * figuras
			  */
			 for (int p = 0; p < figuras.size(); p++) {
		            for (int s = p + 1; s < figuras.size(); s++) {
		                Figura me = (Figura) figuras.get(p);
		                Figura him = (Figura) figuras.get(s);
		                
		                if (me.haColisionadoCon(him)) 
		                {
		                	 
		                    him.colisionaCon(me);   
		                    
		                }
		            }
		        }
			 
			 /**
			  * Eliminamos las figuras del array de figuras y acto seguido limpiamos el array de las figuras que 
			  * vamos a eliminar en el proximo loop
			  */
			 figuras.removeAll(figurasEliminar);
			 figurasEliminar.clear();
			  
			 /**
			  * Los aliens han llegado a uno de los extremos, por lo que debemos tener en cuenta que en el proximo
			  * loop todos los aliens deben bajar una distancia x y tienen que moverse hacia el lado contrario que 
			  * lo estan haciendo,esto lo hacemos gracias a la logica implementada en la clase Alien
			  */
			 if (logica)
			 {
				 for (Figura figura : figuras) 
				 {
			           figura.logica();
				 }
				 
				 logica = false;
			 }
			 
			/**
			 * Pintamod
			 */
			g.dispose();
			strategy.show();
			
			/**
			 * La nave la paramos antes del proximo movimiento en caso que apretemos alguna de las teclas de derecha 
			 * o izquierda.
			 */
			nave.setMovimientoHorizontal(0);
			
			/**
			 * Movemos la nave a una velocidad x en caso de que apretemos alguna de las teclas de flecha hacia la 
			 * izauierda o derecha
			 */
			if (leftPressed) 
			{
	            nave.setMovimientoHorizontal(-20);
	            
	        } else if (rightPressed) {
	            nave.setMovimientoHorizontal(20);
	        }
			
			/**
			 * Al pulsar espacio debemos disparar siempre y cuando lo permita el metodo disparar, es deicri siempre 
			 * y cuando haya pasado un tiempo entre disparo y disparo
			 */
			if (firePressed) {
	            disparar();
	        }
			
			try { Thread.sleep(8); } catch (Exception e) {} 
		}
	}
	
	/**
	 * Metodo con el cual pintamos la pantalla final, en ella ponemos una imagen con fuegos artificiales declarando
	 * que hemos ganado y ademas le ponemos al usuario la puntuacion que ha conseguido en el total de las partidas
	 * jugadas.
	 */
	private void pantallaFinal()
	{
		Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
		
		Image imagen;
		URL url = this.getClass().getClassLoader().getResource("res/fuegos.jpg");
		try {
			imagen = ImageIO.read(url);
			
			g.drawImage(imagen, 0, 0,null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		g.setColor(Color.white);
		Font small = new Font("Helvetica", Font.BOLD, 50);
		g.setFont(small);
		g.drawString("Puntuación total: " + "" + puntuacion, 130 , 550);
		
		g.dispose();
		strategy.show();
	}
	
	private class KeyInputHandler extends KeyAdapter {
		private int pressCount = 1;
		
		public void keyPressed(KeyEvent e) {
			
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				leftPressed = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				rightPressed = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				firePressed = true;
			}
		} 
		
		/**
		 * Notification from AWT that a key has been released.
		 *
		 * @param e The details of the key that was released 
		 */
		public void keyReleased(KeyEvent e) {
			
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				leftPressed = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				rightPressed = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				firePressed = false;
			}
		}

		/**
		 * Notification from AWT that a key has been typed. Note that
		 * typing a key means to both press and then release it.
		 *
		 * @param e The details of the key that was typed. 
		 */
		public void keyTyped(KeyEvent e) {
			
			
			// if we hit escape, then quit the game
			if (e.getKeyChar() == 27) {
				System.exit(0);
			}
		}
	}
}