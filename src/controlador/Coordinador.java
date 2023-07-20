package controlador;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import modelo.Automovil;
import modelo.Catalogo;
import modelo.Crossover;
import modelo.Hatchback;
import modelo.SUV;
import modelo.Sedan;
import vista.VentanaAgregar;
import vista.VentanaDetalles;
import vista.VentanaPrincipal;

public class Coordinador {

	private VentanaPrincipal ventanaPrincipal;
	private VentanaAgregar ventanaAgregar;
	private VentanaDetalles ventanaDetalles;
	private Catalogo catalogo;

	public void setVentanaPrincipal(VentanaPrincipal ventanaPrincipal) {
		this.ventanaPrincipal=ventanaPrincipal;		
	}

	public void setVentanaAgregar(VentanaAgregar ventanaAgregar) {
		this.ventanaAgregar=ventanaAgregar;		
	}

	public void setVentanaDetalles(VentanaDetalles ventanaDetalles) {
		this.ventanaDetalles=ventanaDetalles;		
	}

	public void setCatalogo(Catalogo catalogo) {
		this.catalogo=catalogo;
	}

	public void iniciarSistema() {
		llenarCatalogo();
		restoreData();
		this.ventanaPrincipal.setVisible(true);		
	}

	ImageIcon getImage(String fileName, String tipo) {
		String path = "/img/" + tipo +"/" + fileName + ".png";
		ImageIcon imageIcon = new ImageIcon(Coordinador.class.getResource(path));
		
		Image image = imageIcon.getImage();
		int originalHeight = image.getHeight(null);
		int originalWidth = image.getWidth(null);
		
        double aspectRatio = (double) originalWidth / originalHeight;
        double scale = Math.min((double) 230 / originalWidth, (double) 130 / originalHeight);

        int newWidth = (int) (originalWidth * scale);
        int newHeight = (int) (originalHeight * scale);

		Image newImg = image.getScaledInstance(newWidth, newHeight, java.awt.Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newImg);
		return imageIcon;
	}
	
	public List<Automovil> getCatalogo() {
		return catalogo.getListaAutomoviles();
	}
	
	public void restoreData() {
		this.ventanaPrincipal.llenarLista();
	}	
	
	private void llenarCatalogo() {
		String tipo = "sedan";
		Sedan corolla = new Sedan("Toyota", "Corolla" , tipo , getImage("Toyota Corolla",tipo));
		Sedan versa = new Sedan("Nissan", "Versa" , tipo , getImage("Nissan Versa",tipo));
		Sedan rio = new Sedan("Kia", "Rio" , tipo , getImage("Kia Rio",tipo));
		Sedan civic = new Sedan("Honda", "Civic",tipo , getImage("Honda Civic",tipo));
		Sedan altima = new Sedan("Nissan", "Altima" , tipo , getImage("Nissan Altima",tipo));
		catalogo.agregarVehiculo(corolla);
		catalogo.agregarVehiculo(versa);
		catalogo.agregarVehiculo(rio);
		catalogo.agregarVehiculo(civic);
		catalogo.agregarVehiculo(altima);
		
		tipo="crossover";
		Crossover rav = new Crossover("Toyota", "RAV4" , tipo , getImage("Toyota RAV4",tipo));
		Crossover crv = new Crossover("Honda", "CR-V" , tipo , getImage("Honda CRV",tipo));
		Crossover rogue = new Crossover("Nissan", "Rogue" , tipo , getImage("Nissan Rogue",tipo));
		Crossover escape = new Crossover("Ford", "Escape",tipo , getImage("Ford Escape",tipo));
		Crossover tucson = new Crossover("Hyundai", "Tucson" , tipo , getImage("Hyundai Tucson",tipo));
		catalogo.agregarVehiculo(rav);
		catalogo.agregarVehiculo(crv);
		catalogo.agregarVehiculo(rogue);
		catalogo.agregarVehiculo(escape);
		catalogo.agregarVehiculo(tucson);
		
		tipo="hatchback";
		Crossover golf = new Crossover("Volkswagen", "Golf" , tipo , getImage("Volkswagen Golf",tipo));
		Crossover fit = new Crossover("Honda", "Fit" , tipo , getImage("Honda Fit",tipo));
		Crossover yaris = new Crossover("Toyota", "Yaris" , tipo , getImage("Toyota Yaris",tipo));
		Crossover fiesta = new Crossover("Ford", "Fiesta",tipo , getImage("Ford Fiesta",tipo));
		Crossover i30 = new Crossover("Hyundai", "i30" , tipo , getImage("Hyundai i30",tipo));
		catalogo.agregarVehiculo(golf);
		catalogo.agregarVehiculo(fit);
		catalogo.agregarVehiculo(yaris);
		catalogo.agregarVehiculo(fiesta);
		catalogo.agregarVehiculo(i30);
		
		tipo="suv";
		SUV equinox = new SUV("Chevrolet", "Equinox" , tipo , getImage("Chevrolet Equinox",tipo));
		SUV sorento = new SUV("Kia", "Sorento" , tipo , getImage("Kia Sorento",tipo));
		SUV x5 = new SUV("BMW", "X5" , tipo , getImage("BMW X5",tipo));
		SUV rx = new SUV("Lexus", "RX",tipo , getImage("Lexus RX",tipo));
		SUV santaFe = new SUV("Hyundai", "SantaFe" , tipo , getImage("Hyundai Santa Fe",tipo));
		catalogo.agregarVehiculo(equinox);
		catalogo.agregarVehiculo(sorento);
		catalogo.agregarVehiculo(x5);
		catalogo.agregarVehiculo(rx);
		catalogo.agregarVehiculo(santaFe);
	}

	public void orderList(String response) {
		List<Automovil> listaOrdenada = new ArrayList<>();
		switch(response) {
			case "Nombre": 
				listaOrdenada = this.catalogo.ordenarPorNombre();
				this.ventanaPrincipal.actualizarLista(listaOrdenada);			
				break;
			case "Tipo":
				listaOrdenada = this.catalogo.ordenarPorTipo();
				this.ventanaPrincipal.actualizarLista(listaOrdenada);
				break;
			case "Marca":
				listaOrdenada = this.catalogo.ordenarPorMarca();
				this.ventanaPrincipal.actualizarLista(listaOrdenada);
				break;
			default: break;
		}
	}
	
	public void searchByName(String nombre) {
		List<Automovil> encontrados = new ArrayList<>();
		
		encontrados = this.catalogo.buscarVehiculoPorNombre(nombre);
		
		if(encontrados.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Vehiculo no encotrado", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		this.ventanaPrincipal.actualizarLista(encontrados);
		
	}
	
	public void filterList(String tipo) {
		List<Automovil> filtrados = new ArrayList<>();
		
		filtrados = this.catalogo.filtrarVehiculoPorTipo(tipo);
		
		if(filtrados.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No hay vehiculos del tipo "+tipo, "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		this.ventanaPrincipal.actualizarLista(filtrados);		
	}

	public void abrirVentanaAgregar() {
		this.ventanaAgregar.setVisible(true);
	}
	
	public void agregarVehiculo(String marca, String nombre, String tipo, File selectedFile) {
		ImageIcon icon = fileToImageIcon(selectedFile);
		switch(tipo) {
		case "Sedan":
			Sedan newSedan = new Sedan(marca, nombre, tipo, icon);
			this.catalogo.agregarVehiculo(newSedan);
			this.ventanaPrincipal.llenarLista();
			break;
		case "Crossover":
			Crossover newCrossover = new Crossover(marca, nombre, tipo, icon);
			this.catalogo.agregarVehiculo(newCrossover);
			this.ventanaPrincipal.llenarLista();
			break;
		case "Hatchback":
			Hatchback newHatchback = new Hatchback(marca, nombre, tipo, icon);
			this.catalogo.agregarVehiculo(newHatchback);
			this.ventanaPrincipal.llenarLista();
			break;
		case "SUV":
			SUV newSUV = new SUV(marca, nombre, tipo, icon);
			this.catalogo.agregarVehiculo(newSUV);
			this.ventanaPrincipal.llenarLista();
			break;
		default: JOptionPane.showMessageDialog(null, "Tipo Incorrecto", "Error", JOptionPane.ERROR_MESSAGE);;
		}
		
	}
	
	private ImageIcon fileToImageIcon(File selectedFile) {
		 try {
			 // Read the image data from the file
	         Image image = ImageIO.read(selectedFile);
	         
			int originalHeight = image.getHeight(null);
			int originalWidth = image.getWidth(null);
			
	        double aspectRatio = (double) originalWidth / originalHeight;
	        double scale = Math.min((double) 230 / originalWidth, (double) 130 / originalHeight);

	        int newWidth = (int) (originalWidth * scale);
	        int newHeight = (int) (originalHeight * scale);

			Image newImg = image.getScaledInstance(newWidth, newHeight, java.awt.Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(newImg);
			return imageIcon;
		 } catch (IOException e) {
			 e.printStackTrace();
	         return null;
		 }
	}


	public void abrirVentanaDetalles(String selectedValue) {
		this.ventanaDetalles.setTitle(selectedValue);
		String[] selectedValueSeparated = selectedValue.split("\\s+");
		System.out.println(selectedValueSeparated[1]);
		Automovil auto = this.catalogo.getAutomovil(selectedValueSeparated[0], selectedValueSeparated[1]);
		
		this.ventanaDetalles.showVehicle(auto);
		this.ventanaDetalles.setVisible(true);
	}










	

}
