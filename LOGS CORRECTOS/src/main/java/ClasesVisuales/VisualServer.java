package ClasesVisuales;
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

import java.awt.EventQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import jdk.internal.org.jline.utils.Log;

/**
 * 
 * @author sebas
 * código elaborado con la ayuda de los videos del canal "pildorasinformáticas", se usó los videos sobre swing, sockets, chat y JavaDoc. Se usó en total 42 videos del canal.
 * se implementa la clase "Runnable" para la creación de un hilo
 */

public class VisualServer implements Runnable{

	private static final Logger log = LogManager.getLogger(VisualServer.class);

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
					log.info("---Visual-Cliente-creado-correctamente---");
				} catch (Exception e) {
					e.printStackTrace();
					log.warn("---La-aplicacion-no-se-creo---");
					log.error("---Error-de-creacion---", new NullPointerException());
					log.fatal("---No-se-puede-continuar---");
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
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setBounds(0, 0, 450, 397);
		frmServidorDelChat.getContentPane().add(textArea);
		
		Thread entradaDatos = new Thread(this);
		
		entradaDatos.start();
	}

	/**
	 * Se crea el hilo que permite que el servidor esté siempre a la esucha en el puerto 10234
	 * Si el mensaje es enviado al destinatario correctamente, el servidor envia un mensaje de exito
	 */
	
	//@Override
	public void run() {
		// TODO Auto-generated method stub
		
		boolean fallido = false;
		
		String ultimoIp = null;
		
		int ultimoPuerto = 0;
		
		while (true) {
			ServerSocket  llegadaMensaje = null;
			
			try {
				llegadaMensaje = new ServerSocket(10234);
				log.info("---Conexion-con-cliente-creada-correctamente---");

				ObjetoDeEnvio entrada;

				
				if (fallido == true) {
					
					Socket enviaFallo = new Socket(ultimoIp, ultimoPuerto);
					log.info("---Conexion-cliente-creada-correctamente---");
					
					ObjetoDeEnvio error = new ObjetoDeEnvio();
					
					error.setNombre("Servidor");
					
					error.setMensaje(null);
					
					ObjectOutputStream errorCompleto = new ObjectOutputStream(enviaFallo.getOutputStream());
					
					errorCompleto.writeObject(error);
					log.info("---Error-cliente-enviado-correctamente---");
					textArea.append(" " + "(El mensaje no se envió por error del cliente)");
					
					errorCompleto.close();
					
					enviaFallo.close();
					log.info("---Conexion-cliente-finalizada---");
					
					fallido = false;

				}
				
				while(true) {
					String usuario, ip, mensajeCompleto;
					
					Socket llegadaServidor = llegadaMensaje.accept();

					log.info("---Mensaje-recibido-correctamente---");

					ObjectInputStream llegada = new ObjectInputStream(llegadaServidor.getInputStream());
					
					try {
						entrada = (ObjetoDeEnvio) llegada.readObject();
						log.info("---Objeto-leido-correctamente---");

						usuario = entrada.getNombre();
						
						ip = entrada.getIp();
						
						int puerto = Integer.parseInt(entrada.getPuerto());
						
						mensajeCompleto = entrada.getMensaje();
						
						ultimoPuerto = Integer.parseInt(entrada.getMiPuerto());
						
						ultimoIp = entrada.getMiIp();
						
						textArea.append("\n" + usuario + ": " + mensajeCompleto + " hacia: " + ip + " por: " + entrada.getPuerto());
						
						llegadaServidor.close();
						
						llegada.close();
						log.info("---Conexion-con-cliente-finalizada---");
						
						
						Socket envioDeMensaje = new Socket(ip, puerto);
						log.info("---Conexion-con-cliente-destino-creada-correctamente---");

						ObjectOutputStream reenvio = new ObjectOutputStream(envioDeMensaje.getOutputStream());
						
						reenvio.writeObject(entrada);
						log.info("---Mensaje-enviado-correctamente---");
						
						System.out.println("---Enviado---");
						
						envioDeMensaje.close();
						log.info("---Conexion-con-cliente-destino-finalizada---");
						
						
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						
						e.getMessage();
						
						e.getCause();
						
						System.out.println("Error 1");


						log.warn("---Creacion-de-conexion-con-cliente-destino-fallida---");
						log.warn("---Creacion-de-conexion-con-cliente-destino-se-intentara-otra-vez---");
						log.error("---Error-de-conexion---", new NullPointerException());
						log.info("---Se-envia-error-al-cliente---");
						
						fallido = true;
						
					}
					
						
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				System.out.println("Error 2");
		
				System.out.println("Corriendo otravez");

				log.warn("---Creacion-de-Servidor-fallida---");
				log.warn("---Creacion-de-Servidor-se-intentara-otra-vez---");
				log.error("---Error-de-creacion---", new NullPointerException());
				
				fallido = true;
			}
			
			finally {
				
				try {
					llegadaMensaje.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log.warn("---Cierre-conexion-cliente-fallida---");
					log.error("---Error-de-creacion---", new NullPointerException());
				}
			}
		}	
	} // cierra el run
}