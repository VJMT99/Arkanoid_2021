/*Autor: Víctor Jiménez Martín de la Torre*/

package codigo;

import java.awt.Color;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GRect;

public class Marcador extends GRect{
	
	//variables e imagenes de la clase
	GLabel texto1 = new GLabel("PUNTOS");
	GLabel texto2 = new GLabel("0");
	GLabel texto3 = new GLabel("NIVEL");
	GLabel texto4 = new GLabel("1");
	GLabel texto5 = new GLabel("3");
	GLabel texto6 = new GLabel("VIDAS");
	GImage titulo = new GImage("images/titulo.png");
	GImage b1 = new GImage("images/b1.png");
	GImage b2 = new GImage("images/b2.png");
	GImage b3 = new GImage("images/b3.png");
	GImage f = new GImage("images/f.jpg");
	int puntuacion = 0;
	int level = 1;
	int vida = 3;
	MiFuente fuente= new MiFuente();

	public Marcador(double width, double height){
		//constructor
		super(width, height);
		setFilled(true);
		setFillColor(Color.BLACK);
		//propiedades de las etiquetas del marcador
		texto1.setFont(fuente.MyFont(1, 27f));
		texto1.setColor(Color.RED);
		texto2.setFont(fuente.MyFont(1, 27f));
		texto2.setColor(Color.RED);
		texto3.setFont(fuente.MyFont(1, 27f));
		texto3.setColor(Color.RED);
		texto4.setFont(fuente.MyFont(1, 27f));
		texto4.setColor(Color.RED);
		texto5.setFont(fuente.MyFont(1, 27f));
		texto5.setColor(Color.RED);
		texto6.setFont(fuente.MyFont(1, 27f));
		texto6.setColor(Color.RED);
	}
	public void aumentaMarcador(int puntos){
		//aumenta la puntuacion
		puntuacion = puntuacion + puntos;
		texto2.setLabel(""+puntuacion);
	}
	public int getPuntuacion(){
		//devuelve la puntuacion
		return puntuacion;
	}
	public void aumentaNivel(int nivel, Arkanoid ark){
		//aumenta el nivel del juego
		ark.removeAll();
		level=level+nivel;
		vida=vida+1;
		texto5.setLabel(""+vida);
		texto4.setLabel(""+level);
		puntuacion =0;
		texto2.setLabel("0");
		ark.init();
		ark.run();
	}
	public int getNivel(){
		//devuelve el nivel del juego
		return level;
	}
	public void restaVida(int vidas){
		vida = vida - vidas;
		texto5.setLabel(""+vida);
		
	}
	public int getVidas(){
		//devuelve el nivel del juego
		return vida;
	}
	public void setVidas(int vidas){
		vida=vidas;
		texto5.setLabel(""+vida);
	}
	public void addMarcador(Arkanoid ark, GImage imagen){
		//añade el marcador al juego
		ark.add(this, 530,0);
		imagen.setBounds(570,0, 130, 200);
		titulo.setBounds(535,10,185,75);
		b1.setBounds(480,-100,100,620);
		b2.setBounds(520,175,225,50);
		b3.setBounds(680,-100,100,620);
		f.setBounds(560,200,137,370);

		ark.add(imagen);
		ark.add(titulo);
		ark.add(f);
		ark.add(b2);
		ark.add(b1);
		ark.add(b3);
		titulo.sendToFront();
		ark.add(texto1, 550,250);
		ark.add(texto2, 618,290);
		ark.add(texto3, 575,350);
		ark.add(texto4, 618,390);
		ark.add(texto6, 567,180);
		ark.add(texto5, 618,220);
		texto5.sendToFront();
	}
}
