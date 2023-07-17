package controlador;

import vista.VentanaPrincipal;

public class Coordinador {

	private VentanaPrincipal ventanaPrincipal;

	public void setVentanaPrincipal(VentanaPrincipal ventanaPrincipal) {
		this.ventanaPrincipal=ventanaPrincipal;		
	}

	public void mostrarVentanaPrincipal() {
		ventanaPrincipal.setVisible(true);		
	}

	
}
