package modelo;

import javax.swing.ImageIcon;

public abstract class Automovil {
    private String marca;
    private String nombre;
    private String tipo;
    
	public Automovil(String marca, String nombre, String tipo) {
		super();
		this.marca = marca;
		this.nombre = nombre;
		this.tipo = tipo;
	}
	
	public String getMarca() {
		return this.marca;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getTipo() {
		return this.tipo;
	}

	public abstract void mostrarInformacion();
    public abstract ImageIcon crearImagen();
}


