package graficos;

import javax.swing.*;
import java.awt.GraphicsEnvironment;

public class PruebaDeFuentes {

	public static void main(String[] args) {
		
		String nombreDeLaFuente = JOptionPane.showInputDialog("Introduce el nombre de alguna Fuente de Texto");
		
		boolean fuenteEncontrada = false;
		
		String [] fuentes = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		
		for (String fuenteUsar: fuentes) {
			
			if (fuenteUsar.equals(nombreDeLaFuente)) {
				
				fuenteEncontrada = true;
			}
		}
		
		if (fuenteEncontrada) {
			
			System.out.println("Si se encontr� la fuente");
		}
		
		else {
			System.out.println("No se encontr� la fuente");
		}
	}
}
