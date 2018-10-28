import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author jorgebonillo
 *
 */
public class Jugador {
	
	private String password, fechaNacimiento, nombre;
	private String alias;
	
	Scanner scanInput = new Scanner(System.in);
	
	public String getNombre() {
		return nombre;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setAlias(String alias) {
		this.alias = alias;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	 
	public Jugador(String alias, String password, String fechaNacimiento, String nombre) 
	{
		
		this.alias = alias;
		this.password = password;
		this.fechaNacimiento = fechaNacimiento;
		this.nombre = nombre;
		
	}
	
	public Jugador ()
	{
		
	}
	
	public boolean comprobaciones()
	{		
		Boolean comprobacion = false;
		
		if (alias.equals(""))
		{
			do
			{
				System.out.println(alias);
				System.out.println("Introduce un alias");
				alias = scanInput.nextLine();
			} while ((alias.equals("")) == true);
		}
		
		if (password.equals(""))
		{
			do
			{
				System.out.println("Introduce una password");
				password = scanInput.nextLine();
			} while ((password.equals("")) == true);
		}
		
		if (fechaNacimiento.equals(""))
		{
			do
			{
				System.out.println("Introduce una fecha de nacimiento");
				fechaNacimiento = scanInput.nextLine();
			} while ((fechaNacimiento.equals("")) == true);
		}
		
		if (comprobarFecha() == false )
		{
			do
			{
				System.out.println("Introduce una fecha valida");
				fechaNacimiento = scanInput.nextLine();
			}
			while (comprobarFecha() != true);
		}
		
		if (comprobarPassword() == false)
		{
			do
			{
				password = scanInput.nextLine();
			}
			while (comprobarPassword() != true);
		}
		
		if (login() == true)
		{
			System.out.println("Bienvenido: " + alias);
			comprobacion = true;
		}
		else if (login() == false)
		{
			System.out.println("No estas registrado");
		}
		
		return comprobacion;
	}
	
	/**
	 * Metodo que comprueba que la password tiene al menos 6 caracteres
	 * @return true si tiene 6 o mas caracteres, false si tiene menos de 6 caracteres
	 */
	private boolean comprobarPassword()
	{
		Boolean comprobacion = false;
		
		if (password.length() >= 6)
		{
			comprobacion = true;
		}
		else if (password.length() < 6)
		{
			System.out.println("Introduce una contraseña correcta");
			comprobacion = false;
		}
		return comprobacion;
	}
	
	/**
	 * Metodo que comprueba que la fecha de nacimiento introducida por el jugador no es mayor
	 * a la actual
	 * @return
	 */
	private boolean comprobarFecha() 
	{
		Calendar cal= Calendar.getInstance(); 
		int year= cal.get(Calendar.YEAR); 
		int month = cal.get(Calendar.MONTH);
		month = month + 1;
		int day = cal.get(Calendar.DATE);
		String fechaActual = day + "/" + month + "/" + year;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date fecha1 = sdf.parse(fechaNacimiento , new ParsePosition(0));
		Date fecha2 = sdf.parse(fechaActual , new ParsePosition(0));
		
		if  (fecha1.before(fecha2))
		{
			return true;
		}
		else if (fecha1.after(fecha2))
		{
			return false;
		}
		else
		{
			System.out.println("Introduce un formato correcto");
			return false;
		}
		
	}

	/**
	 * Comprobamos si el alias y la password son correctas con el alias y password por defecto
	 * es decir invitado
	 * @return true si hay un alias y una password que son ambos INVITADOS, false si introducimos otra cosa
	 */
	private boolean login ()
	{
		Boolean comprobacion = false;
		
		alias = alias.toUpperCase();
		password = password.toUpperCase();
		
		if ((alias.equals("INVITADO")) && (password.equals("INVITADO")))
		{
			comprobacion = true;
		}
		
		
		return comprobacion;
	}
}
