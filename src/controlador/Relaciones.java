package controlador;

import modelo.Catalogo;
import vista.*;

public class Relaciones {
	public void iniciar() {
		//Se instancias las clases que necesitamos, las que serán únicas
		VentanaPrincipal ventanaPrincipal=new VentanaPrincipal();
		VentanaAgregar ventanaAgregar=new VentanaAgregar();
		VentanaDetalles ventanaDetalles=new VentanaDetalles();
		Catalogo catalogo=new Catalogo();
		Coordinador coordinador=new Coordinador();
		
		//Se establecen las relaciones entre las clases
		ventanaPrincipal.setCoordinador(coordinador);
		ventanaAgregar.setCoordinador(coordinador);
		ventanaDetalles.setCoordinador(coordinador);
		catalogo.setCoordinador(coordinador);
		
		//Se establecen relaciones con la clase coordinador
		coordinador.setVentanaPrincipal(ventanaPrincipal);
		coordinador.setVentanaAgregar(ventanaAgregar);
		coordinador.setVentanaDetalles(ventanaDetalles);
		coordinador.setCatalogo(catalogo);
		
		coordinador.iniciarSistema();
	}
}
