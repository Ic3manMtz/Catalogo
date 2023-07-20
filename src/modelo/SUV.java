package modelo;

import javax.swing.ImageIcon;

public class SUV extends Automovil{	
	public SUV(String marca, String nombre, String tipo, ImageIcon imageIcon) {
		super(marca, nombre, tipo, imageIcon);
	}

	@Override
	public void mostrarInformacion() {
		System.out.println("Marca: "+super.getMarca());
		System.out.println("Nombre: "+super.getNombre());
		System.out.println("Tipo: "+super.getTipo());
	}
}
