import java.awt.*;

import java.io.*;

import java.net.*;

import javax.swing.*;

public class Servidor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		VentanaServidor servidor1 = new VentanaServidor();
		
		servidor1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
class VentanaServidor extends JFrame implements Runnable{
	
	public VentanaServidor() {
		
		setSize(400, 300);
		
		setResizable(false);
		
		JPanel pantalla1 = new JPanel();
		
		pantalla1.setLayout(new BorderLayout());
		
		texto = new JTextArea();
		
		pantalla1.add(texto, BorderLayout.CENTER);
		
		add(pantalla1);
		
		setVisible(true);
		
		Thread puertoAbierto = new Thread(this);
		
		puertoAbierto.start();
		
	}
	
	private JTextArea texto;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		int contador = 0;
		
		try {
			ServerSocket servidorMensajes = new ServerSocket(9999);
			
			while (true) {
			
				Socket socketServidor = servidorMensajes.accept();
				
				DataInputStream entradaDatos = new DataInputStream(socketServidor.getInputStream());
				
				String mensaje = entradaDatos.readUTF();
				
				if(contador==0) {
					texto.append(mensaje);
	
					contador++;
				}
				
				else {
					texto.append("\n" + mensaje); 
				}
			
				socketServidor.close();
			
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
}