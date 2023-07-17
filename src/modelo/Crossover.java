package modelo;

import javax.swing.ImageIcon;

public class Crossover extends Automovil{
	
	public Crossover(String marca, String nombre, String tipo) {
		super(marca, nombre, tipo);
	}

	@Override
	public void mostrarInformacion() {
		System.out.println("Marca: "+super.getMarca());
		System.out.println("Nombre: "+super.getNombre());
		System.out.println("Tipo: "+super.getTipo());
	}

	@Override
	public ImageIcon crearImagen() {
		return new ImageIcon("img/"+super.getTipo()+"/"+super.getNombre()+".png");
	}

}
