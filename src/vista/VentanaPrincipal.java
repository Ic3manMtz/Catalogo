package vista;

import java.awt.BasicStroke;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controlador.Coordinador;
import modelo.Automovil;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class VentanaPrincipal extends JFrame implements ActionListener, MouseListener, KeyListener{

	private JPanel contentPane;
	private DefaultListModel modelo;

	private Coordinador coordinador;
	private JTextField txtBuscarPorNombre;
	private JPanel textoABuscar;
	private JComboBox filtrarPor;
	private JComboBox ordenarPor;
	private JPanel topBar;
	private JButton btnAplicarFiltroYOrden;
	private JLabel lblCatalogo;
	private JButton btnNuevoVehiculo;
	private JTable table;
	private JScrollPane scrollPane;

	public VentanaPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/outline_directions_car_black_24dp.png")));
		setResizable(false);
		setTitle("Taller Automotriz");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(793, 416);
		setLocationRelativeTo(null);
		iniciarComponentes();

	}

	private void iniciarComponentes() {
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel searchBar = new JPanel();
		searchBar.setBounds(0, 70, 777, 54);
		searchBar.setBackground(Color.WHITE);
		contentPane.add(searchBar);
		
		textoABuscar = new JPanel();
		textoABuscar.setBounds(21, 6, 351, 42);
		textoABuscar.setBorder(new RoundBorder(12));
		textoABuscar.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(10, 9, 24, 24);
		textoABuscar.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/outline_search_black_24dp.png")));
		
		txtBuscarPorNombre = new JTextField();
		txtBuscarPorNombre.setBounds(46, 5, 295, 32);
		textoABuscar.add(txtBuscarPorNombre);
		txtBuscarPorNombre.setBorder(null);
		txtBuscarPorNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtBuscarPorNombre.setForeground(Color.LIGHT_GRAY);
		txtBuscarPorNombre.setBackground(new Color(240, 240, 240));
		txtBuscarPorNombre.setText("Buscar por nombre del vehículo");
		txtBuscarPorNombre.setFocusable(false);
		txtBuscarPorNombre.addMouseListener(this);
		txtBuscarPorNombre.addKeyListener(this);
		txtBuscarPorNombre.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateTextColor();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateTextColor();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateTextColor();
            }

            private void updateTextColor() {
                if (txtBuscarPorNombre.getText().equals("Buscar por nombre del vehículo")) {
                	txtBuscarPorNombre.setForeground(Color.LIGHT_GRAY);
                } else {
                	txtBuscarPorNombre.setForeground(Color.BLACK);
                }
            }
        });
		txtBuscarPorNombre.setColumns(10);
		
		filtrarPor = new JComboBox();
		filtrarPor.setBounds(517, 15, 76, 25);
		filtrarPor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		filtrarPor.setModel(new DefaultComboBoxModel(new String[] {"Filtrar por","Tipo", "Marca"}));
		filtrarPor.setBorder(null);
		filtrarPor.setBackground(new Color(240, 240, 240));
		filtrarPor.setFocusable(false);
		
		ordenarPor = new JComboBox();
		ordenarPor.setBounds(598, 15, 91, 25);
		ordenarPor.setModel(new DefaultComboBoxModel(new String[] {"Ordenar por", "Nombre", "Tipo", "Marca"}));
		ordenarPor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ordenarPor.setFocusable(false);
		ordenarPor.setBorder(null);
		ordenarPor.setBackground(UIManager.getColor("Button.background"));
		
		btnAplicarFiltroYOrden = new JButton("Aplicar");
		btnAplicarFiltroYOrden.setBounds(694, 12, 65, 30);
		btnAplicarFiltroYOrden.addActionListener(this);
		btnAplicarFiltroYOrden.setFont(new Font("Cascadia Code", Font.PLAIN, 11));
		btnAplicarFiltroYOrden.setForeground(Color.WHITE);
		btnAplicarFiltroYOrden.setBorder(null);
		btnAplicarFiltroYOrden.setFocusable(false);
		btnAplicarFiltroYOrden.setBackground(new Color(33, 107, 254));
		searchBar.setLayout(null);
		searchBar.add(textoABuscar);
		searchBar.add(filtrarPor);
		searchBar.add(ordenarPor);
		searchBar.add(btnAplicarFiltroYOrden);
		
		topBar = new JPanel();
		topBar.setBackground(Color.WHITE);
		topBar.setBounds(0, 0, 777, 69);
		contentPane.add(topBar);
		
		lblCatalogo = new JLabel("Catálogo de Vehículos");
		lblCatalogo.setBounds(10, 18, 221, 32);
		lblCatalogo.setFont(new Font("Cascadia Mono", Font.BOLD, 15));
		
		btnNuevoVehiculo = new JButton("Agregar nuevo vehículo");
		btnNuevoVehiculo.setBounds(572, 12, 188, 44);
		btnNuevoVehiculo.addActionListener(this);
		btnNuevoVehiculo.setFont(new Font("Cascadia Mono", Font.PLAIN, 12));
		btnNuevoVehiculo.setForeground(Color.WHITE);
		btnNuevoVehiculo.setBorder(null);
		btnNuevoVehiculo.setBackground(new Color(0, 150, 95));
		btnNuevoVehiculo.setFocusable(false);
		topBar.setLayout(null);
		topBar.add(lblCatalogo);
		topBar.add(btnNuevoVehiculo);
		
		scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBorder(null);
		scrollPane.setBounds(21, 141, 736, 236);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBackground(Color.WHITE);
		table.setBorder(null);
		scrollPane.setViewportView(table);
		
		modelo = new DefaultListModel<>();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnNuevoVehiculo) {
			VentanaAgregar ventanaAgregar = new VentanaAgregar();
			ventanaAgregar.setVisible(true);
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==txtBuscarPorNombre) {
			txtBuscarPorNombre.setFocusable(true);
			txtBuscarPorNombre.requestFocus();
			txtBuscarPorNombre.setText(" ");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	public void setCoordinador(Coordinador coordinador) {
		this.coordinador=coordinador;		
	}

	public void llenarLista(List<Automovil> encontrados) {
		modelo.removeAllElements();
	
		for(Automovil automovil:encontrados) {
			String fila="";// = "Nombre: " + automovil.getName() + ", Tipo: " + automovil.getType();
			modelo.addElement(fila);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			System.out.println("Enter presionado");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	static class RoundBorder implements Border{
		private int radius;
		
		public RoundBorder(int radius) {
			this.radius=radius;
		}
		
		@Override
		public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Shape border = new RoundRectangle2D.Double(x, y, width - 1, height - 1, radius, radius);
            g2.setStroke(new BasicStroke(2));
            g2.setColor(Color.LIGHT_GRAY);
            g2.draw(border);
		}

		@Override
		public Insets getBorderInsets(Component c) {
			  return new Insets(radius, radius, radius, radius);
		}

		@Override
		public boolean isBorderOpaque() {
			return true;
		}
		
	}
}
