import java.net.*;

import java.awt.event.*;

import java.io.DataOutputStream;

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
		
		nombre = new JTextField(5);
		
		add(nombre);
		
		numIp = new JTextField(5);
		
		add(numIp);
		
		JLabel tituloVentana = new JLabel("SERVICIO DE CHAT");
		
		add(tituloVentana);
		
		chat = new JTextArea(50,50);
		
		add(chat);
		
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
			
			//int numeroDePuerto = 26963;
			
			try {
				Socket envioDeMensaje = new Socket(InetAddress.getLocalHost(), 9999);
				
				Envio mensaje = new Envio();
				
				mensaje.setNombre(nombre.getText());
				
				mensaje.setNumIp(numIp.getText());
				
				mensaje.setCajaTexto1(cajaTexto1.getText());
				
				/*DataOutputStream datos = new DataOutputStream(envioDeMensaje.getOutputStream());
				
				datos.writeUTF(cajaTexto1.getText());
				
				datos.close();*/
				
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				
				e1.printStackTrace();
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				
				//numeroDePuerto++;
				
				System.out.println(e1.getMessage());
				
			}
		}		
	}
	
	private JTextField cajaTexto1, nombre, numIp;
	
	private JButton botonEnviar1;
	
	private JTextArea chat;
}

class Envio{
	
	private String nombre, numIp, cajaTexto1;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNumIp() {
		return numIp;
	}

	public void setNumIp(String numIp) {
		this.numIp = numIp;
	}

	public String getCajaTexto1() {
		return cajaTexto1;
	}

	public void setCajaTexto1(String cajaTexto1) {
		this.cajaTexto1 = cajaTexto1;
	}
}