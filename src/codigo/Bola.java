package codigo;

import java.awt.Color;

import acm.graphics.GObject;
import acm.graphics.GOval;

public class Bola extends GOval{

	int dx = 1; //velocidad eje x
	int dy = 1; //velocidad eje y

	public Bola(double width, double height) {
		super(width, height);

	}

	public Bola(double width, double height, Color c) {
		super(width, height);
		this.setFillColor(c);
		this.setFilled(true);
	}

	public void muevete(Arkanoid ark){
		//rebote con el suelo y rebote con techo
		if(getY()> ark.getHeight()||getY()<0){
			dy = dy*-1;
		}

		//rebote con pared derecha y  rebote con pared izquierda
		if(getX()> ark.getWidth()||getX()<0){
			dx = dx*-1;
		}
		
		if(chequeaColision(getX(),getY(), ark)){
			if(chequeaColision(getX() + getWidth(),getY(), ark)){
				if(chequeaColision(getX(),getY()+getHeight(), ark)){
					if(chequeaColision(getX() + getWidth(),getY()+getHeight(), ark)){
						
					}
				}
			}
		}
		
		//mueve la bola en la direccion correcta
		move(dx,dy);
	}
	private boolean chequeaColision(double posx, double posy, Arkanoid ark){
		boolean noHaChocado = true;
		GObject aux;
		
		aux = ark.getElementAt(posx,posy);
		if(aux==ark.miCursor){
			dy=dy*-1;
			noHaChocado=false;
			
		}else if (aux == null){
			
		}else{
			ark.remove(aux);
			dy=dy*-1;
			dx=dx*-1;
			noHaChocado=false;

		}
		
		return noHaChocado;
	}
}
