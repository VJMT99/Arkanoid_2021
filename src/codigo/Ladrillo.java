package codigo;

import java.awt.Color;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GRect;

public class Ladrillo extends GRect{
	MiFuente fuente= new MiFuente();
	GLabel golpes  = new GLabel("");
	int numero_golpes;
	GImage fondo_ladrillo = new GImage("images/fondo_ladrillo.jpg");

	public Ladrillo(double x, double y, double width, double height, Arkanoid ark) {
		super(x, y, width, height);
		fondo_ladrillo.setBounds(x, y, width, height);
		ark.add(fondo_ladrillo);
		
	}
	public void etiqueta(int num_golpes,Arkanoid ark){
		golpes.setLabel(""+num_golpes);
		numero_golpes=num_golpes;
		golpes.setFont(fuente.MyFont(1, 15f));
		golpes.setColor(Color.BLACK);
		ark.add(golpes, fondo_ladrillo.getX()+21,fondo_ladrillo.getY()+21);
	}
	public void eliminaLadrillo(Arkanoid ark){
		
			ark.remove(fondo_ladrillo);
			ark.remove(golpes);
			ark.remove(this);
		
		
	}
}
