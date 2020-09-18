package graficos;

import java.awt.Frame;

import javax.swing.*;

import java.awt.*;

import java.awt.Toolkit; 

public class ParaPantallas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ventana ven1=new ventana();
		
		ven1.setVisible(true);
		
		ven1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	}

}

class ventana extends JFrame{
	
	public ventana() {
		Toolkit pantallaCliente = Toolkit.getDefaultToolkit();
		
		//Dimension pantallaTotal = pantallaCliente.getScreenSize();
		
		//int heigth = pantallaTotal.height;
		
		//int width = pantallaTotal.width;
		
		//setSize(heigth+300, width);
		
		Image logo = pantallaCliente.getImage("fotos/avancemos.png");
		
		setIconImage(logo);
		
		setSize(1200, 700);
		
		setLocation(100, 10); 
		//setBounds(x, y, width, heigth) para juntarlos    
		//setResizable(true) para cambiarle tamaño
		//setExtended(Frame.MAXIMIZED_BOTH);
		
		setTitle("Esto es una Prueba");
		
		TextoEnPantalla pantalla1 = new TextoEnPantalla(); //crear la instancia basandose en la clase de TextoEnPantalla
		
		add(pantalla1); //agrega la pantalla creada	
	}
}

class TextoEnPantalla extends JPanel{
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);//llamar a clase padre para que dibuje y haga primero su trabajo original 
		
		g.drawString("Hola mundo!", 100, 100);
	}
}