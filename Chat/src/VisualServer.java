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

public class VisualServer implements Runnable{

	private JFrame frmServidorDelChat;
	private JTextArea textArea;

	/**
	 * Launch the application.
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
	 * Create the application.
	 */
	public VisualServer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
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

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
			ServerSocket llegadaMensaje = new ServerSocket(9999);
			
			ObjetoDeEnvio entrada;
			
			while(true) {
				String usuario, ip, mensajeCompleto, ipDevuelta, mensajeError;
				
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
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					
					e.getMessage();
					
					e.getCause();
					
				}
				
					
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
