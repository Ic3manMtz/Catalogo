package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;

public class VentanaAgregar extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton btnChooseImage;
	private JTextField textNombre;
	private JButton btnAgregar;
	private JComboBox comboTypes;
	private File selectedFile;
	private JLabel lblerror;
	private JTextField textMarca;


	public VentanaAgregar() {
		getContentPane().setBackground(Color.WHITE);
		setTitle("Agregar vehículo");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaAgregar.class.getResource("/img/outline_directions_car_black_24dp.png")));
		setResizable(false);
		setSize(359, 250);
		setLocationRelativeTo(null);

		iniciarComponentes();
	}


	private void iniciarComponentes() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().setLayout(null);
		
		btnChooseImage = new JButton("Buscar imagen");
		btnChooseImage.addActionListener(this);
		btnChooseImage.setBounds(181, 110, 101, 23);
		getContentPane().add(btnChooseImage);
		
		JLabel lblNombre = new JLabel("Nombre del vehículo: ");
		lblNombre.setFont(new Font("Cascadia Mono", Font.BOLD, 15));
		lblNombre.setBounds(10, 54, 197, 32);
		getContentPane().add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.setFont(new Font("Cascadia Mono", Font.BOLD, 14));
		textNombre.setBounds(201, 57, 121, 27);
		getContentPane().add(textNombre);
		textNombre.setColumns(10);
		
		comboTypes = new JComboBox();
		comboTypes.setModel(new DefaultComboBoxModel(new String[] {"Tipos", "Sedan", "Crossover", "Hatchback", "Suv"}));
		comboTypes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboTypes.setFocusable(false);
		comboTypes.setBorder(null);
		comboTypes.setBackground(UIManager.getColor("Button.background"));
		comboTypes.setBounds(48, 108, 91, 25);
		getContentPane().add(comboTypes);
		
		btnAgregar = new JButton("Agregar nuevo vehículo");
		btnAgregar.addActionListener(this);
		btnAgregar.setForeground(Color.WHITE);
		btnAgregar.setFont(new Font("Cascadia Mono", Font.PLAIN, 12));
		btnAgregar.setFocusable(false);
		btnAgregar.setBorder(null);
		btnAgregar.setBackground(new Color(0, 150, 95));
		btnAgregar.setBounds(81, 155, 188, 44);
		getContentPane().add(btnAgregar);
		
		JLabel lblMarca = new JLabel("Marca del vehículo: ");
		lblMarca.setFont(new Font("Cascadia Mono", Font.BOLD, 15));
		lblMarca.setBounds(10, 11, 197, 32);
		getContentPane().add(lblMarca);
		
		textMarca = new JTextField();
		textMarca.setFont(new Font("Cascadia Mono", Font.BOLD, 14));
		textMarca.setColumns(10);
		textMarca.setBounds(201, 14, 121, 27);
		getContentPane().add(textMarca);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnChooseImage) {
			JFileChooser fileChooser = new JFileChooser();
		    FileFilter imageFileFilter = createSearchFilter();

			 // Establecer el file filter en el file chooser
			 fileChooser.setFileFilter(imageFileFilter);
			 int response = fileChooser.showOpenDialog(null); // select file to open
			
			 if(response == JFileChooser.APPROVE_OPTION) {
				 selectedFile = ChooseImage(fileChooser);
			 }
		}
		if(e.getSource()==btnAgregar) {
			if(validateForm()) {
				JOptionPane.showMessageDialog(null, "Todos los campos deben llenarse", "Error", JOptionPane.ERROR_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null, "Vehiculo agregado correctamente!", "Vehículo agregado",JOptionPane.INFORMATION_MESSAGE);
				SaveImage(selectedFile);
			}
		}
		
	}


	private boolean validateForm() {
		String nombre = textNombre.getText().trim();
		String marca = textMarca.getText().trim();
		
		return nombre.isEmpty() && marca.isEmpty() && selectedFile==null;
	}


	FileFilter createSearchFilter() {
		// Crear un file filter que acepta solo archivos de texto con extensión .txt
		 FileFilter imageFileFilter = new FileFilter() {
			 @Override
		     public boolean accept(File file) {
				 // Aceptar directorios y archivos con extensión .txt
				 return file.isDirectory() || file.getName().toLowerCase().endsWith(".png");
			 }

		     public String getDescription() {
		    	 return "PNG Images (*.png)";
		     }
		 };
		return imageFileFilter;
	}

	private File ChooseImage(JFileChooser fileChooser) {
		File selectedFile = fileChooser.getSelectedFile();

		// Verificar que el archivo seleccionado sea una imagen PNG
		if (!selectedFile.getName().toLowerCase().endsWith(".png")) {
			//System.out.println("Seleccione un archivo de imagen PNG válido.");
		    return null;
		}
		return selectedFile;
	}

	private void SaveImage(File selectedFile) {
		String projectDirectory = System.getProperty("user.dir");
		// Obtener el nombre de archivo proporcionado por el usuario
		String selectedFileName = selectedFile.getName();
		String tipoSeleccionado = getSelectedType();
		// Construir la ruta del directorio destino dentro del proyecto
		String destinationDirectory = projectDirectory + File.separator + "\\src\\img\\"+tipoSeleccionado; // Reemplaza "directorio_destino" con el nombre del directorio deseado

		// Crear el directorio destino si no existe
		File destinationDir = new File(destinationDirectory);
		if (!destinationDir.exists()) {
		    destinationDir.mkdirs();
		}

		// Construir la ruta completa del archivo destino
		String destinationFilePath = destinationDirectory + File.separator + selectedFileName;

		// Copiar el archivo seleccionado al directorio destino
		File destinationFile = new File(destinationFilePath);
		try (InputStream inputStream = new FileInputStream(selectedFile);
		     OutputStream outputStream = new FileOutputStream(destinationFile)) {
		         byte[] buffer = new byte[4096];
		         int bytesRead;
		         while ((bytesRead = inputStream.read(buffer)) != -1) {
		             outputStream.write(buffer, 0, bytesRead);
		         }
		    //System.out.println("Archivo PNG seleccionado copiado en: " + destinationFile.getAbsolutePath());
		} catch (IOException ioe) {
		    //System.out.println("Error al copiar el archivo: " + ioe.getMessage());
		}
	}

	private String getSelectedType() {
		int response = comboTypes.getSelectedIndex();
//		"Tipos", "Sedan", "Crossover", "Hatchback", "Suv"}
		switch(response) {
			case 1: return "sedan";
			case 2: return "crossover";
			case 3: return "hatchback";
			case 4: return "suv";
			default: return "";
		}
	}
}	
	 