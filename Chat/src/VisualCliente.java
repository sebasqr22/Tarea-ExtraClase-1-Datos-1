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

public class VisualCliente {

	private JFrame frmServicioDeChat;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

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
		frmServicioDeChat = new JFrame();
		frmServicioDeChat.getContentPane().setBackground(new Color(47, 79, 79));
		frmServicioDeChat.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField.setBounds(122, 11, 213, 20);
		frmServicioDeChat.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nombre Usuario");
		lblNewLabel.setFont(new Font("Kumbh Sans", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(0, 15, 176, 14);
		frmServicioDeChat.getContentPane().add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setBounds(122, 55, 213, 20);
		frmServicioDeChat.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("DIRECCI\u00D3N IP");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Kumbh Sans", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_1.setBounds(10, 58, 102, 14);
		frmServicioDeChat.getContentPane().add(lblNewLabel_1);
		
		JTextArea textArea = new JTextArea();
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
		frmServicioDeChat.getContentPane().add(btnNewButton);
		frmServicioDeChat.setBackground(Color.LIGHT_GRAY);
		frmServicioDeChat.setTitle("SERVICIO DE CHAT");
		frmServicioDeChat.setBounds(100, 100, 1000, 699);
		frmServicioDeChat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
