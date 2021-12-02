/*Autor: Víctor Jiménez Martín de la Torre*/

package codigo;

import java.awt.Color;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GRect;

public class Ladrillo extends GRect{
	//variables e imagenes
	MiFuente fuente= new MiFuente();
	GLabel golpes  = new GLabel("");
	int numero_golpes;
	GImage fondo_ladrillo = new GImage("images/fondo_ladrillo.jpg");
	SonidoLadrillo sonido_ladrillo=new SonidoLadrillo();

	public Ladrillo(double x, double y, double width, double height, Arkanoid ark) {
		//constructor
		super(x, y, width, height);
		fondo_ladrillo.setBounds(x, y, width, height);
		ark.add(fondo_ladrillo);
		
	}
	public void etiqueta(int num_golpes,Arkanoid ark){
		//contador de golpes restantes para romper el ladrillo
		golpes.setLabel(""+num_golpes);
		numero_golpes=num_golpes;
		golpes.setFont(fuente.MyFont(1, 15f));
		golpes.setColor(Color.BLACK);
		ark.add(golpes, fondo_ladrillo.getX()+21,fondo_ladrillo.getY()+21);
	}
	public void eliminaLadrillo(Arkanoid ark){
		//elimina el ladrillo
		sonido_ladrillo.start();
		ark.remove(fondo_ladrillo);
		ark.remove(golpes);
		ark.remove(this);
	}
	public class SonidoLadrillo extends Thread {		
		public void run() {                               
	       Sonido ladrillo = new Sonido();
	       ladrillo.sonido(ladrillo.getClass().getResource("../sonidos/sonido_ladrillo.wav").getFile());

	    }
	}
}
