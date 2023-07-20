package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import controlador.Coordinador;

public class Catalogo {
	
	private Coordinador coordinador;
	private List<Automovil> listaAutomoviles;
	
	public Catalogo() {
		this.listaAutomoviles=new ArrayList<>();
	}
	
	public void setCoordinador(Coordinador coordinador) {
		this.coordinador=coordinador;		
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
	
	public List<Automovil> buscarVehiculoPorNombre(String nombre){
		List<Automovil> encontrados = new ArrayList<>();
		
		for(Automovil automovil:listaAutomoviles) {
			if(automovil.getNombre().equalsIgnoreCase(nombre)) {
				encontrados.add(automovil);
			}
		}
		
		return encontrados;
	}
	
	public Automovil getAutomovil(String marca, String nombre) {
		for(Automovil automovil:listaAutomoviles) {
			if(automovil.getMarca().equalsIgnoreCase(marca) && automovil.getNombre().equalsIgnoreCase(nombre)) {
				return automovil;
			}
		}
		
		return null;
	}

	public List<Automovil> filtrarVehiculoPorTipo(String tipo) {
		List<Automovil> filtrados = new ArrayList<>();
		
		for(Automovil automovil:listaAutomoviles) {
			if(automovil.getTipo().equalsIgnoreCase(tipo)) {
				filtrados.add(automovil);
			}
		}
		
		return filtrados;
	}
	
    public List<Automovil> ordenarPorTipo() {
    	List<Automovil> automovilesOrdenados = new ArrayList<>(listaAutomoviles);
   
        Collections.sort(listaAutomoviles, new Comparator<Automovil>() {
            @Override
            public int compare(Automovil auto1, Automovil auto2) {
                return auto1.getTipo().compareTo(auto2.getTipo());
            }
        });
         
        return automovilesOrdenados;
    }

    public List<Automovil> ordenarPorNombre() {
    	List<Automovil> automovilesOrdenados = new ArrayList<>(listaAutomoviles);
        Collections.sort(automovilesOrdenados, new Comparator<Automovil>() {
            @Override
            public int compare(Automovil auto1, Automovil auto2) {
                return auto1.getNombre().compareTo(auto2.getNombre());
            }
        });
        
        return automovilesOrdenados;
    }

    public List<Automovil> ordenarPorMarca() {
    	List<Automovil> automovilesOrdenados = new ArrayList<>(listaAutomoviles);
        Collections.sort(automovilesOrdenados, new Comparator<Automovil>() {
            @Override
            public int compare(Automovil auto1, Automovil auto2) {
                return auto1.getMarca().compareTo(auto2.getMarca());
            }
        });
        
        return automovilesOrdenados;
    }
    
}
