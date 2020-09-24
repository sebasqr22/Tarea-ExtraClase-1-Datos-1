import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.TextField;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.awt.Toolkit;
import javax.swing.SwingConstants;

public class VisualCliente implements Runnable{

	private JFrame frmServicioDeChat;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextArea textArea; 
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualCliente window = new VisualCliente();
					window.frmServicioDeChat.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VisualCliente() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		String miPuerto = String.valueOf(puertoLlegada());
		
		frmServicioDeChat = new JFrame();
		frmServicioDeChat.setIconImage(Toolkit.getDefaultToolkit().getImage("logo.png"));
		frmServicioDeChat.setResizable(false);
		frmServicioDeChat.getContentPane().setBackground(new Color(47, 79, 79));
		frmServicioDeChat.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Kumbh Sans", Font.PLAIN, 11));
		textField.setBounds(122, 11, 213, 20);
		frmServicioDeChat.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("NOMBRE");
		lblNewLabel.setFont(new Font("Kumbh Sans", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(43, 12, 176, 17);
		frmServicioDeChat.getContentPane().add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Kumbh Sans", Font.PLAIN, 11));
		textField_1.setBounds(122, 55, 213, 20);
		frmServicioDeChat.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("DIRECCI\u00D3N IP");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Kumbh Sans", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_1.setBounds(10, 54, 113, 20);
		frmServicioDeChat.getContentPane().add(lblNewLabel_1);
		
		JLabel textoAquiMen = new JLabel("AQUI APARECEN SUS MENSAJES");
		textoAquiMen.setFont(new Font("Kumbh Sans Light", Font.ITALIC, 10));
		textoAquiMen.setForeground(Color.GRAY);
		textoAquiMen.setVerticalAlignment(SwingConstants.TOP);
		textoAquiMen.setBounds(594, 11, 176, 14);
		frmServicioDeChat.getContentPane().add(textoAquiMen);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Kumbh Sans", Font.PLAIN, 15));
		textArea.setBounds(368, 9, 606, 640);
		frmServicioDeChat.getContentPane().add(textArea);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Kumbh Sans", Font.PLAIN, 12));
		textField_2.setBounds(10, 184, 348, 38);
		frmServicioDeChat.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("Enviar Mensaje");
		btnNewButton.setFont(new Font("Kumbh Sans", Font.BOLD, 12));
		btnNewButton.setBounds(114, 233, 121, 23);
		EnviarMensaje envio = new EnviarMensaje();
		btnNewButton.addActionListener(envio);
		frmServicioDeChat.getContentPane().add(btnNewButton);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Kumbh Sans", Font.PLAIN, 11));
		textField_3.setBounds(122, 99, 213, 19);
		frmServicioDeChat.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("PUERTO");
		lblNewLabel_2.setFont(new Font("Kumbh Sans", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(36, 93, 76, 30);
		frmServicioDeChat.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Puerto de llegada: ");
		lblNewLabel_3.setFont(new Font("Kumbh Sans", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setBounds(10, 358, 135, 14);
		frmServicioDeChat.getContentPane().add(lblNewLabel_3);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Kumbh Sans", Font.PLAIN, 11));
		textField_4.setEditable(false);
		textField_4.setBackground(new Color(47, 79, 79));
		textField_4.setForeground(new Color(255, 255, 255));
		textField_4.setBounds(161, 356, 37, 20);
		textField_4.setText(miPuerto);
		
		System.out.println("pUERTO INICIAL: " + miPuerto);
		
		frmServicioDeChat.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		frmServicioDeChat.setBackground(Color.LIGHT_GRAY);
		frmServicioDeChat.setTitle("SERVICIO DE CHAT");
		frmServicioDeChat.setBounds(100, 100, 1000, 699);
		frmServicioDeChat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Thread reciboDeMensajes = new Thread(this);
		
		reciboDeMensajes.start();
	}
	
	private class EnviarMensaje implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println("funca salida");
			
			String name, ipDir, port, men, myPort, mensajeError;
			
			name = textField.getText();
			
			ipDir = textField_1.getText();
			
			port = textField_3.getText();
			
			men = textField_2.getText();
			
			myPort = textField_4.getText();
			
			mensajeError = ("Servidor: Porfavor llene todos los espacios...");
			
			if (name.isEmpty() || ipDir.isEmpty() || port.isEmpty() || men.isEmpty()){
				
				textArea.append("\n" + mensajeError);
			}
			
			else {
				try {
					Socket EnvioDeMensaje = new Socket("192.168.100.75", 9999);
					
					ObjetoDeEnvio mensajeCompleto = new ObjetoDeEnvio();
					
					mensajeCompleto.setNombre(name);
					
					mensajeCompleto.setIp(ipDir);
					
					mensajeCompleto.setPuerto(port);
					
					mensajeCompleto.setMensaje(men);
					
					mensajeCompleto.setMiPuerto(myPort);
					
					mensajeCompleto.setMiIp(InetAddress.getLocalHost().getHostAddress());
					
					System.out.println(InetAddress.getLocalHost().getHostAddress());
					
					ObjectOutputStream mensaje = new ObjectOutputStream(EnvioDeMensaje.getOutputStream());
					
					mensaje.writeObject(mensajeCompleto);
					
					textArea.append("\n" + mensajeCompleto.getNombre() + ": " + mensajeCompleto.getMensaje());
					
					mensaje.close();
					
					EnvioDeMensaje.close();
					
					
					
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					
					e1.getMessage();
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					
					e1.getMessage();
				}
				
			}
		}
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		String puerto = textField_4.getText();
		
		int miPuerto = Integer.parseInt(textField_4.getText());
		
		try {
			ServerSocket llegadaDeMensaje = new ServerSocket(miPuerto);
			
			System.out.println("funca llegada al puerto: " + puerto);
			
			ObjetoDeEnvio recibo;
			
			while(true) {
				
				Socket datos = llegadaDeMensaje.accept();
				
				ObjectInputStream entradaDatos = new ObjectInputStream(datos.getInputStream());
				
				try {
					recibo = (ObjetoDeEnvio) entradaDatos.readObject();
					
					textArea.append("\n" + recibo.getNombre() + ": " + recibo.getMensaje());
					
					datos.close();
					
				} 
				
				catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.getMessage();
					
					e.getCause();
				}
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			e.getMessage();
		}
		
	}

	private ObjectInputStream ObjectInputStream(InputStream inputStream) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int puertoLlegada() {
		
		Random puertoNuevo = new Random();
		
		int puerto = 0;
		
		while(puerto <= 9010) {
			
			puerto = puertoNuevo.nextInt(9989);
		}
		
		return puerto;
	}
}

class ObjetoDeEnvio implements Serializable{
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPuerto() {
		return puerto;
	}

	public void setPuerto(String puerto) {
		this.puerto = puerto;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getMiPuerto() {
		return miPuerto;
	}

	public void setMiPuerto(String miPuerto) {
		this.miPuerto = miPuerto;
	}

	public String getMiIp() {
		return miIp;
	}

	public void setMiIp(String miIp) {
		this.miIp = miIp;
	}

	private String nombre, ip, puerto, mensaje, miIp, miPuerto;
	
	
}
