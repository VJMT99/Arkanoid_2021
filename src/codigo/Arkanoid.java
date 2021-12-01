package codigo;
import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
public class Arkanoid extends GraphicsProgram {

	static final int ANCHO_LADRILLO =50;
	static final int ALTO_LADRILLO =30;
	boolean detector=false;
	MiFuente fuente= new MiFuente();
	Bola bola1 = new Bola(10,10,Color.YELLOW);
	//declaro el cursor del juego
	Cursor miCursor = new Cursor(0,400,60,10,this);
	Marcador marcador = new Marcador(200, 520);
	GImage fondo= new GImage("images/fondo_pantalla.jpg");
	GImage gameover = new GImage("images/gameoverVic.png");
	GImage fondo_marcador = new GImage("fondo_marcador.jpg");
	GLabel start = new GLabel("CLICK TO START");
	GLabel autor = new GLabel("VICTOR JIMENEZ MARTIN DE LA TORRE");
	GImage titulo = new GImage("images/titulo.png");


	
	public void init(){
		if (!detector){
			inicio();
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
		
		marcador.addMarcador(this,fondo_marcador);
		creaPiramide();
		while (true){
			bola1.muevete(this);
			pause(2);
			if(acabaJuego()){
				removeAll();
				fondo.setBounds(0,0,530, 700);
				add(fondo);
				marcador.addMarcador(this,fondo_marcador);
				gameover.setBounds(60, 130, 400, 200);
				add(gameover);
				waitForClick();
				removeAll();
				init();
				run();
				
			}
			miCursor.muevete(getWidth()+10, (int) miCursor.getX());
		}
	}
	public void mouseMoved(MouseEvent evento){
		miCursor.muevete(getWidth(), evento.getX());
		
	}

	public void creaPiramide(){
		int numeroLadrillos = 9;
		int num_golpes=marcador.getNivel();
		
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
	public boolean acabaJuego(){
		if(bola1.getY() > miCursor.getY()+10){
			return true;
		}
		return false;
	}

}
