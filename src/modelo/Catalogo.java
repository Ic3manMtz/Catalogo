package modelo;

import java.util.ArrayList;
import java.util.List;

import controlador.Coordinador;

public class Catalogo {
	private List<Automovil> listaAutomoviles;
	private List<Sedan> sedanes;
	
	public Catalogo() {
		this.listaAutomoviles=new ArrayList<>();
	}
	
	public void agregarVehiculo(Sedan sedan) {
		listaAutomoviles.add(sedan);
	}
	
	public void agregarVehiculo(Crossover crossover) {
		listaAutomoviles.add(crossover);
	}
	
	public void agregarVehiculo(SUV suv) {
		listaAutomoviles.add(suv);
	}
	
	public void agregarVehiculo(Hatchback hatchback) {
		listaAutomoviles.add(hatchback);
	}
	
	public List<Automovil> getListaAutomoviles(){
		return this.listaAutomoviles;
	}
	
	public List<Automovil> buscarAutmovilPorNombre(String nombre){
		List<Automovil> encontrados = new ArrayList<>();
		
		for(Automovil automovil:listaAutomoviles) {
			if(automovil.getNombre().equalsIgnoreCase(nombre)) {
				encontrados.add(automovil);
			}
		}
		return encontrados;
	}
	
	public List<Automovil> buscarAutomovilPorTipo(String tipo){
		List<Automovil> encontrados = new ArrayList<>();
		
		for(Automovil automovil:listaAutomoviles) {
			if(automovil.getNombre().equals("cadena")) {
				encontrados.add(automovil);
			}
		}
		return encontrados;
	}
	
}
