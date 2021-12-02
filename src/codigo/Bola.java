/*Autor: Víctor Jiménez Martín de la Torre*/

package codigo;

import java.awt.Color;

import acm.graphics.GObject;
import acm.graphics.GOval;

public class Bola extends GOval{
	double dx = 1; //velocidad eje x
	double dy = 1; //velocidad eje y

	public Bola(double width, double height, Color color) {
		//constructor
		super(width, height);
		setFillColor(color);
		setFilled(true);
	}
	public void muevete(Arkanoid ark){
		//metodo para movimiento

		//rebote con el suelo y rebote con techo
		if(getY()> ark.getHeight() + 220 ||getY()<5){
			dy = dy*-1;
		}
		//rebote con pared derecha y  rebote con pared izquierda
		if(getX()> ark.getWidth()-230||getX()<5){
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
		//detector de colisiones de la bola
		boolean noHaChocado = true;
		GObject aux;
		Ladrillo ladrillo;
		Cursor cursor;
		
		aux = ark.getElementAt(posx,posy);
		
		if(aux instanceof Cursor){
			//aseguramos que es la plataforma
			cursor=(Cursor)aux;
			if(posx>=cursor.getX() + cursor.getWidth()/2 && posx<=cursor.getX() + cursor.getWidth()){
				if(dx<0){
					dx=dx*-1;
				}
			}
			if(posx>=cursor.getX()&& posx<=cursor.getX() + cursor.getWidth()/2){
				if(dx>0){
					dx=dx*-1;
				}
			}
			dy=dy*-1;
			noHaChocado=false;
		}else if (aux == null){
		
		}else if(aux instanceof Ladrillo){
			//aseguramos que es un ladrillo
			ladrillo= (Ladrillo) aux;
			if(ladrillo.getY() + ladrillo.getHeight() <= posy || ladrillo.getY() >= posy){
				dy=dy*-1;
			}else if(ladrillo.getX() + ladrillo.getWidth() <= posx || ladrillo.getX() >= posx){
				dx=dx*-1;
			}
			//elimina el ladrillo
			
			ladrillo.eliminaLadrillo(ark);
			if(ladrillo.numero_golpes==1){
				ark.marcador.aumentaMarcador(1);
			}
			//actualiza el nivel
			if(ark.marcador.getPuntuacion()==27){
				ark.marcador.aumentaNivel(1, ark);
				ark.marcador.texto2.setLabel("0");
			}
			
			noHaChocado=false;
		}
		return noHaChocado;
	}
}
