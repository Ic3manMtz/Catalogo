package modelo;

import javax.swing.ImageIcon;

public abstract class Automovil {
    private String marca;
    private String nombre;
    private String tipo;
    private ImageIcon imageIcon;
    
    
	public Automovil(String marca, String nombre, String tipo, ImageIcon imageIcon) {
		super();
		this.marca = marca;
		this.nombre = nombre;
		this.tipo = tipo;
		this.imageIcon=imageIcon;
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
	
	public ImageIcon getImage() {
		return this.imageIcon;
	}

	public abstract void mostrarInformacion();
}


