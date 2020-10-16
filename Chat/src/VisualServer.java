import java.awt.EventQueue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.Toolkit;

/**
 * 
 * @author sebas
 * código elaborado con la ayuda de los videos del canal "pildorasinformáticas", se usó los videos sobre swing, sockets, chat y JavaDoc. Se usó en total 42 videos del canal.
 * se implementa la clase "Runnable" para la creación de un hilo
 */

public class VisualServer implements Runnable{

	private JFrame frmServidorDelChat;
	private JTextArea textArea;

	/**
	 * Ejecuta la aplicación
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualServer window = new VisualServer();
					window.frmServidorDelChat.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Crea la ventana visual del servidor
	 */
	public VisualServer() {
		initialize();
	}

	/**
	 * Inicializa los contenidos de la parte gráfica de la aplicación
	 */
	private void initialize() {
		frmServidorDelChat = new JFrame();
		frmServidorDelChat.setIconImage(Toolkit.getDefaultToolkit().getImage("logo.png"));
		frmServidorDelChat.setResizable(false);
		frmServidorDelChat.setTitle("SERVIDOR DEL CHAT");
		frmServidorDelChat.setBackground(new Color(245, 245, 245));
		frmServidorDelChat.setBounds(100, 100, 466, 436);
		frmServidorDelChat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmServidorDelChat.getContentPane().setLayout(null);
		
		textArea = new JTextArea();
		textArea.setBounds(0, 0, 450, 397);
		frmServidorDelChat.getContentPane().add(textArea);
		
		Thread entradaDatos = new Thread(this);
		
		entradaDatos.start();
	}

	/**
	 * Se crea el hilo que permite que el servidor esté siempre a la esucha en el puerto 10234
	 * Si el mensaje es enviado al destinatario correctamente, el servidor envia un mensaje de exito
	 */
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while (true) {
			ServerSocket  llegadaMensaje = null;
			
			try {
				llegadaMensaje = new ServerSocket(10234);
				
				ObjetoDeEnvio entrada;
				
				while(true) {
					String usuario, ip, mensajeCompleto;
					
					Socket llegadaServidor = llegadaMensaje.accept();
					
					ObjectInputStream llegada = new ObjectInputStream(llegadaServidor.getInputStream());
					
					try {
						entrada = (ObjetoDeEnvio) llegada.readObject();
						
						usuario = entrada.getNombre();
						
						ip = entrada.getIp();
						
						int puerto = Integer.parseInt(entrada.getPuerto());
						
						mensajeCompleto = entrada.getMensaje();
						
						textArea.append("\n" + usuario + ": " + mensajeCompleto + " hacia: " + ip + " por: " + entrada.getPuerto());
						
						llegadaServidor.close();
						
						llegada.close();
						
						
						
						Socket envioDeMensaje = new Socket(ip, puerto);
						
						ObjectOutputStream reenvio = new ObjectOutputStream(envioDeMensaje.getOutputStream());
						
						reenvio.writeObject(entrada);
						
						System.out.println("---Enviado---");
						
						envioDeMensaje.close();
						
						
						
						ip = entrada.getMiIp();
						
						usuario = ("ser");
						
						mensajeCompleto = ("(Mensaje enviado con éxito)");
						
						entrada.setMensaje(mensajeCompleto);
						
						entrada.setNombre(null);
						
						int miPuerto = Integer.parseInt(entrada.getMiPuerto());
						
						Socket envioExito = new Socket(ip, miPuerto);
						
						ObjectOutputStream exito = new ObjectOutputStream(envioExito.getOutputStream());
						
						exito.writeObject(entrada);
						
						System.out.println("---Envio--de--exito---");
						
						envioExito.close();
						
						
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						
						e.getMessage();
						
						e.getCause();
						
						System.out.println("Error 1");
						
					}
					
						
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				System.out.println("Error 2");
		
				System.out.println("Corriendo otravez");
			}
			
			finally {
				
				try {
					llegadaMensaje.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}	
	} // cierra el run
}