import java.net.*;

import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

public class Cliente {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		VentanaDelCliente cliente1 = new VentanaDelCliente();
		
		cliente1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
class VentanaDelCliente extends JFrame{
	
	public VentanaDelCliente() {
		
		setSize(1000, 650);
		
		setResizable(false);
		
		MarcoConTexto marco1 = new MarcoConTexto();
		
		add(marco1);
		
		setVisible(true);
			
	}
}
class MarcoConTexto extends JPanel{
	
	public MarcoConTexto() {
		
		JLabel tituloVentana = new JLabel("SERVICIO DE CHAT");
		
		add(tituloVentana);
		
		cajaTexto1 = new JTextField(20);
		
		add(cajaTexto1);
		
		botonEnviar1 = new JButton("Enviar Mensaje");
		
		EnviarTexto enviar = new EnviarTexto();
		
		botonEnviar1.addActionListener(enviar);
		
		add(botonEnviar1);
		
	}
	
	private class EnviarTexto implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			int numeroDePuerto = 1024;
			
			try {
				Socket envioDeMensaje = new Socket("192.168.56.1", numeroDePuerto);
				
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				
				e1.printStackTrace();
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				
				System.out.println(e1.getMessage());
			}
		}
		
		
	}
	
	private JTextField cajaTexto1;
	
	private JButton botonEnviar1;
}