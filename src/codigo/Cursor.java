/*Autor: Víctor Jiménez Martín de la Torre*/

package codigo;

import acm.graphics.GImage;
import acm.graphics.GRect;

public class Cursor extends GRect {
	 GImage fondo_cursor;
	public Cursor(double x, double y, double width, double height, Arkanoid ark) {
		//constructor
		super(x, y, width, height);
		fondo_cursor = new GImage("images/imagen_plataforma.jpg");
		fondo_cursor.setBounds(x, y, width, height);

	}
	public void muevete(int anchoPantalla,double posX){
		//movimiento de la plataforma
		if(posX + getWidth()<anchoPantalla-220){
			setLocation(posX, getY());
			fondo_cursor.setLocation(posX, getY());
		}
	}
}
