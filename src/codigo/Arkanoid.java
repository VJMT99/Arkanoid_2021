/*Autor: Víctor Jiménez Martín de la Torre*/

package codigo;
import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.program.GraphicsProgram;
public class Arkanoid extends GraphicsProgram {
	
	//defino el ancho y alto de un ladrillo
	static final int ANCHO_LADRILLO =50;
	static final int ALTO_LADRILLO =30;
	
	//variables e imagenes de la clase
	boolean detector=false;//detector para solo iniciar la pantalla de inicio 1 vez
	MiFuente fuente= new MiFuente();//fuente personalizada
	Bola bola1 = new Bola(10,10,Color.YELLOW);
	Bola bola2 = new Bola(10,10,Color.RED);
	Cursor miCursor = new Cursor(0,400,60,10,this);//declaro el cursor del juego
	Marcador marcador = new Marcador(200, 520);
	GImage fondo= new GImage("images/fondo_pantalla.jpg");
	GImage gameover = new GImage("images/gameoverVic.png");
	GImage fondo_marcador = new GImage("fondo_marcador.jpg");
	GLabel start = new GLabel("CLICK TO START");
	GLabel start2 = new GLabel("CLICK TO START AGAIN");
	GLabel autor = new GLabel("VICTOR JIMENEZ MARTIN DE LA TORRE");
	GImage titulo = new GImage("images/titulo.png");
	

	boolean visible = false;
	
	public void init(){
		//añadimos todo
		if (!detector){
			inicio();
			marcador.setVidas(3);
		}
		fondo.setBounds(0,0,530, 700);
		add(fondo);
		add(miCursor);
		add(miCursor.fondo_cursor);
		addMouseListeners();
		add(bola1,50,100);	
		setSize(745,520);
	}
	public void run(){
		//empieza el juego
		marcador.addMarcador(this,fondo_marcador);//añade el marcador
		creaPiramide();//crea piramide de ladrillos
		while (true){
			if(bonus((float)marcador.getPuntuacion())){
				//bonus del juego
				add(bola2,bola1.getX(),bola1.getY());
				bola2.sendToFront();
				bola2.setVisible(true);
				visible=true;
			}
			if(bola2.getY() > miCursor.getY()+10){
				bola2.setLocation(10000,10000);
				remove(bola2);
				visible=false;
			}
			bola1.muevete(this);
			if(visible=true){
				bola2.muevete(this);
			}
			pause(2);
			if(acabaJuego()){//pantalla de game over y vuelta a empezar
				removeAll();
				fondo.setBounds(0,0,530, 700);
				add(fondo);
				marcador.addMarcador(this,fondo_marcador);
				gameover.setBounds(60, 130, 400, 200);
				start2.setFont(fuente.MyFont(1, 21f));
				add(start2,67,375);
				add(gameover);
				waitForClick();
				removeAll();
				marcador.setVidas(3);
				init();
				run();
				
			}
			miCursor.muevete(getWidth()+10, (int) bola1.getX());
		}
	}
	public void mouseMoved(MouseEvent evento){
		miCursor.muevete(getWidth(), evento.getX());
		
	}
	public void creaPiramide(){
		//crea la piramide
		int numeroLadrillos = 9;
		int num_golpes=marcador.getNivel();//numero de golpes para romper un ladrillo
		
		for(int a = 1;a<=num_golpes;a++){
			for(int j=0; j<numeroLadrillos; j++){
				for(int i=j; i<numeroLadrillos; i++){
					Ladrillo miLadrillo = new Ladrillo(
							ANCHO_LADRILLO*i - ANCHO_LADRILLO/2*j +40, //posX
							ALTO_LADRILLO*j,//posY
							ANCHO_LADRILLO,//ancho
							ALTO_LADRILLO,//alto
							this);//color
					add(miLadrillo);
					miLadrillo.etiqueta(a,this);
					pause(10);
				}
			}
		}
	}
	public void inicio(){
		//pantalla de inicio
		fondo.setBounds(0,0,760,520);
		add(fondo);
		titulo.setBounds(80,100,600,225);
		add(titulo);
		autor.setFont(fuente.MyFont(1, 12.5f));
		autor.setColor(Color.WHITE);
		add(autor,180,125);
		start.setFont(fuente.MyFont(1, 27f));
		add(start,193,335);
		waitForClick();
		detector = true;
	}
	public boolean bonus(float puntos){
		if(puntos%9==0 && puntos >=9){
			return true;
		}else{
			return false;
		}
	}
	public boolean acabaJuego(){
		//detector para el game over
		if(bola1.getY() > miCursor.getY()+10){
			if(marcador.getVidas()==0){
				return true;
			}else{
				remove(bola1);
				pause(4);
				add(bola1,50,100);
				marcador.restaVida(1);
			}
		}
		if(bola1.getY() > miCursor.getY()+10 && marcador.getVidas()==0){
			return true;
		}
		return false;
	}
}
