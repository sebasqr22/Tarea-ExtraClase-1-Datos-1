import javax.swing.*;

import java.awt.*;

public class Servidor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		VentanaServidor servidor1 = new VentanaServidor();
		
		servidor1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
class VentanaServidor extends JFrame{
	
	public VentanaServidor() {
		
		setSize(400, 300);
		
		setResizable(false);
		
		JPanel pantalla1 = new JPanel();
		
		//pantalla1.setLayout(new BorderLayout());
		
		JTextArea texto = new JTextArea();
		
		pantalla1.add(texto, BorderLayout.CENTER);
		
		add(pantalla1);
		
		setVisible(true);
	}
	
	//private JTextArea texto;
}
