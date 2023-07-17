package controlador;

import javax.swing.UIManager;

import modelo.Catalogo;
import vista.VentanaAgregar;
import vista.VentanaPrincipal;


public class Main {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e) {
			System.out.println("Error setting native LAF: "+e);
		}
		
		new Relaciones().iniciar();
	}
}


