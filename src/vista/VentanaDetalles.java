package vista;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Coordinador;
import modelo.Automovil;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class VentanaDetalles extends JFrame {
	
	private Coordinador coordinador;

	private JPanel contentPane;

	private JLabel lblImage;

	private JLabel txtMarca;
	private JLabel lblTipo;
	private JLabel txtTipo;
	private JLabel lblNombre;
	private JLabel txtNombre;

	public VentanaDetalles() {
		getContentPane().setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaDetalles.class.getResource("/img/outline_directions_car_black_24dp.png")));
		setResizable(false);
		setSize(348, 340);
		setLocationRelativeTo(null);
		
		iniciarComponentes(); 
	}
	 
	public void setCoordinador(Coordinador coordinador) {
		this.coordinador=coordinador;
	}

	private void iniciarComponentes() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblImage = new JLabel("");
		lblImage.setBounds(47, 11, 230, 130);
		lblImage.setHorizontalAlignment(JLabel.CENTER);
		lblImage.setVerticalAlignment(JLabel.CENTER);
		contentPane.add(lblImage);
		
		JLabel lblMarca = new JLabel("Marca: ");
		lblMarca.setFont(new Font("Cascadia Mono", Font.BOLD, 15));
		lblMarca.setBounds(57, 152, 66, 32);
		contentPane.add(lblMarca);
		
		txtMarca = new JLabel("");
		txtMarca.setHorizontalAlignment(SwingConstants.CENTER);
		txtMarca.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtMarca.setFont(new Font("Cascadia Mono", Font.BOLD, 15));
		txtMarca.setBounds(135, 152, 134, 32);
		contentPane.add(txtMarca);
		
		lblTipo = new JLabel("Tipo: ");
		lblTipo.setFont(new Font("Cascadia Mono", Font.BOLD, 15));
		lblTipo.setBounds(67, 195, 56, 32);
		contentPane.add(lblTipo);
		
		txtTipo = new JLabel("");
		txtTipo.setHorizontalAlignment(SwingConstants.CENTER);
		txtTipo.setFont(new Font("Cascadia Mono", Font.BOLD, 15));
		txtTipo.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtTipo.setBounds(135, 195, 134, 32);
		contentPane.add(txtTipo);
		
		lblNombre = new JLabel("Nombre: ");
		lblNombre.setFont(new Font("Cascadia Mono", Font.BOLD, 15));
		lblNombre.setBounds(47, 238, 76, 32);
		contentPane.add(lblNombre);
		
		txtNombre = new JLabel("");
		txtNombre.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombre.setFont(new Font("Cascadia Mono", Font.BOLD, 15));
		txtNombre.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtNombre.setBounds(135, 238, 134, 32);
		contentPane.add(txtNombre);
	}

	public void showVehicle(Automovil auto) {
		lblImage.setIcon(auto.getImage());
		txtMarca.setText(auto.getMarca());
		txtTipo.setText(auto.getTipo());
		txtNombre.setText(auto.getNombre());
	}
}
