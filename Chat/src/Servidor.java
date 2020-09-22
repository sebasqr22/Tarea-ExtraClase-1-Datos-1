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
			
			String nombre, numIp, mensajeCompleto;
			
			Envio mensajeRecibido;
			
			while (true) {
			
				Socket socketServidor = servidorMensajes.accept();
				
				ObjectInputStream llegada = new ObjectInputStream(socketServidor.getInputStream());
				
				mensajeRecibido = (Envio) llegada.readObject();
				
				socketServidor.close();
				
				nombre = mensajeRecibido.getNombre();
				
				numIp = mensajeRecibido.getNumIp();
				
				mensajeCompleto = mensajeRecibido.getCajaTexto1();
				
				/*DataInputStream entradaDatos = new DataInputStream(socketServidor.getInputStream());
				
				String mensaje = entradaDatos.readUTF();
				
				if(contador==0) {
					texto.append(mensaje);
	
					contador++;
				}
				
				else {
					texto.append("\n" + mensaje); 
				}*/
				
				if (contador == 0) {
					
					texto.append(nombre + ": " + mensajeCompleto + " para " + numIp);
					
					contador ++;
				}
				
				else {
					
					texto.append("\n" + nombre + ": " + mensajeCompleto + " para " + numIp);
				}
				
				Socket servidor_cliente = new Socket(numIp, 9090);
				
				ObjectOutputStream reenvio = new ObjectOutputStream(servidor_cliente.getOutputStream());
				
				reenvio.writeObject(mensajeRecibido);
				
				servidor_cliente.close();
			
				socketServidor.close();
			
			}
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
}