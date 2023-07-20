package vista;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controlador.Coordinador;
import modelo.Automovil;

public class VentanaPrincipal extends JFrame implements ActionListener, MouseListener, KeyListener, DocumentListener{
	
	private Coordinador coordinador;

	private JPanel contentPane;
	
	private JPanel topBar;
	private JLabel lblCatalogo;
	private JButton btnNuevoVehiculo;
	
	private JPanel searchBar;
	private JTextField txtBuscarPorNombre;
	private JPanel textoABuscar;
	private JComboBox filtrarPor;
	private JComboBox ordenarPor;
	
	
	private JScrollPane scrollPane;
	private DefaultListModel<JLabel> modelo;
	private JList<JLabel> list;

	public VentanaPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/outline_directions_car_black_24dp.png")));
		setResizable(false);
		setTitle("Taller Automotriz");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(793, 539);
		setLocationRelativeTo(null);
		iniciarComponentes();

	}

	private void iniciarComponentes() {
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		searchBar = new JPanel();
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
		txtBuscarPorNombre.setFont(new Font("Cascadia Code", Font.PLAIN, 14));
		txtBuscarPorNombre.setForeground(Color.LIGHT_GRAY);
		txtBuscarPorNombre.setBackground(new Color(240, 240, 240));
		txtBuscarPorNombre.setText("Buscar por nombre del vehículo");
		txtBuscarPorNombre.setFocusable(false);
		txtBuscarPorNombre.addMouseListener(this);
		txtBuscarPorNombre.addKeyListener(this);
		txtBuscarPorNombre.getDocument().addDocumentListener(this);
		txtBuscarPorNombre.setColumns(10);
		
		filtrarPor = new JComboBox();
		filtrarPor.setBounds(680, 14, 76, 25);
		filtrarPor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		filtrarPor.setModel(new DefaultComboBoxModel(new String[] {"Filtrar por", "Sedan", "Crossover", "Hatchback", "Suv"}));
		filtrarPor.setBorder(null);
		filtrarPor.setBackground(new Color(240, 240, 240));
		filtrarPor.setFocusable(false);
		filtrarPor.addActionListener(this);
		
		ordenarPor = new JComboBox();
		ordenarPor.setBounds(579, 14, 91, 25);
		ordenarPor.setModel(new DefaultComboBoxModel(new String[] {"Ordenar por", "Nombre", "Tipo", "Marca"}));
		ordenarPor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ordenarPor.setFocusable(false);
		ordenarPor.setBorder(null);
		ordenarPor.setBackground(UIManager.getColor("Button.background"));
		ordenarPor.addActionListener(this);
		searchBar.setLayout(null);
		searchBar.add(textoABuscar);
		searchBar.add(filtrarPor);
		searchBar.add(ordenarPor);
		
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
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setBounds(21, 141, 736, 359);
		contentPane.add(scrollPane);
		
		list = new JList();
		list.setSelectionForeground(SystemColor.textHighlight);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.addMouseListener(this);
		list.setVisibleRowCount(-1);
		list.setBorder(null);
		list.setBackground(Color.WHITE);
		list.setFixedCellHeight(-1);
		list.setFixedCellWidth(-1);
				
		modelo = new DefaultListModel<>();
		
		list.setModel(modelo);
		list.setCellRenderer(new JLabelRenderer());
		scrollPane.setViewportView(list);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnNuevoVehiculo) {
			coordinador.abrirVentanaAgregar();
		}
		
		if(e.getSource()==ordenarPor) {
			String response = (String) ordenarPor.getSelectedItem();
			
			if(response.equalsIgnoreCase("Ordenar por")) {
				coordinador.restoreData();
			}else {
				coordinador.orderList(response);
			}
		}
		
		if(e.getSource()==filtrarPor) {
			String response = (String) filtrarPor.getSelectedItem();
			
			if(response.equalsIgnoreCase("filtrar por") || response.equalsIgnoreCase("todos")) {
				coordinador.restoreData();
			}else {
				coordinador.filterList(response);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==txtBuscarPorNombre) {
			txtBuscarPorNombre.setFocusable(true);
			txtBuscarPorNombre.requestFocus();
			txtBuscarPorNombre.setText("");
		}
		
		if(e.getClickCount() == 2) {
			String selectedValue = list.getSelectedValue().getText();
			coordinador.abrirVentanaDetalles(selectedValue);
		}
	}
	
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
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			String nombre = txtBuscarPorNombre.getText();
			txtBuscarPorNombre.setText("Buscar por nombre del vehículo");
			txtBuscarPorNombre.setFocusable(false);
			
			if(nombre.isEmpty()) {
				coordinador.restoreData();
				
			}else {
				coordinador.searchByName(nombre);
			}			
		}
	}
 
	private static class JLabelRenderer extends JLabel implements ListCellRenderer<JLabel> {
		@Override
		public Component getListCellRendererComponent(JList<? extends JLabel> list, JLabel value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) value;
            label.setIcon(label.getIcon());
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
            if (isSelected) {
                label.setBackground(list.getSelectionBackground());
                label.setForeground(list.getSelectionForeground());
            } else {
                label.setBackground(list.getBackground());
                label.setForeground(list.getForeground());
            }
            return label;
		}
	}
	
	private static class RoundBorder implements Border{
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
	
	public void setCoordinador(Coordinador coordinador) {
		this.coordinador=coordinador;		
	}

	public void llenarLista() {
		modelo.removeAllElements();
		
		List<Automovil> listaVehiculos = coordinador.getCatalogo();
		
		for(Automovil automovil:listaVehiculos) {
			JLabel newLabel = new JLabel(automovil.getImage());
			newLabel.setText(automovil.getMarca()+" "+automovil.getNombre());
			newLabel.setFont(new Font("Cascadia Code", Font.PLAIN, 16));
			newLabel.setHorizontalTextPosition(JLabel.CENTER);
			newLabel.setVerticalTextPosition(JLabel.BOTTOM);
			modelo.addElement(newLabel);
		}
	}
	
	public void actualizarLista(List<Automovil> listaVehiculos) {
		modelo.removeAllElements();
				
		for(Automovil automovil:listaVehiculos) {
			JLabel newLabel = new JLabel(automovil.getImage());
			newLabel.setText(automovil.getMarca()+" "+automovil.getNombre());
			newLabel.setFont(new Font("Cascadia Code", Font.PLAIN, 16));
			newLabel.setHorizontalTextPosition(JLabel.CENTER);
			newLabel.setVerticalTextPosition(JLabel.BOTTOM);
			modelo.addElement(newLabel);
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	
	@Override
	public void keyReleased(KeyEvent e) {}
}
