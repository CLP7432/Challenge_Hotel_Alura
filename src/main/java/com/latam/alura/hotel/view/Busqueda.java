package com.latam.alura.hotel.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import com.latal.alura.hotel.controller.*;
import com.latam.alura.hotel.factory.ConnectionFactory;
import com.latam.alura.hotel.modelo.Huesped;
import com.latam.alura.hotel.modelo.Reservacion;
import com.latam.alura.hotel.persistencia.HuespedDao;
import com.latam.alura.hotel.persistencia.ReservacionDao;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Container;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modeloReservas;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;

	private JTabbedPane panel;
	private JPanel header;
	private JPanel btnAtras;
	private JPanel btnexit;
	private JButton btnbuscar;
	private JButton btnEditar;
	private JButton btnEliminar;

	private boolean reservas = true;
	private boolean huesped = false;

	private ReservacionController reservacionController;
	private HuespedController huespedController;

	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	public Busqueda() {
		this.reservacionController = new ReservacionController();
		this.huespedController = new HuespedController();

		configurarCamposDelPanel();
		configurarAccionesDelFormulario();

		mostrarConsultaReservacion(txtBuscar.getText());
		mostrarConsultaHuesped(txtBuscar.getText());
	}

	public void configurarCamposDelPanel() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);

		txtBuscar = new JTextField();
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE  BÚSQUEDA");
		panel = new JTabbedPane(JTabbedPane.TOP);

		tbReservas = new JTable() {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return colIndex != 0 && colIndex != 1 && colIndex != 3;
			}
		};

		tbHuespedes = new JTable() {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return colIndex != 0 && colIndex != 6;
			}
		};
		

		JLabel lblNewLabel_2 = new JLabel("");
		header = new JPanel();

		btnAtras = new JPanel();
		btnexit = new JPanel();
		btnbuscar = new JButton();
		btnEditar = new JButton();
		btnEliminar = new JButton();

		labelExit = new JLabel("X");
		labelAtras = new JLabel("<");

		JSeparator separator_1_2 = new JSeparator();
		JLabel lblBuscar = new JLabel("BUSCAR");
		JLabel lblEditar = new JLabel("EDITAR");
		JLabel lblEliminar = new JLabel("ELIMINAR");

		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Dialog", Font.BOLD, 24));
		lblNewLabel_4.setBounds(278, 61, 346, 76);
		contentPane.add(lblNewLabel_4);

		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloReservas = (DefaultTableModel) tbReservas.getModel();
		modeloReservas.addColumn("Numero de Reserva");
		modeloReservas.addColumn("Fecha Check In");
		modeloReservas.addColumn("Fecha Check Out");
		modeloReservas.addColumn("Valor");
		modeloReservas.addColumn("Forma de Pago");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		scroll_table.setFocusable(false);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table,
				null);
		scroll_table.setVisible(true);
		

		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")),
				scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);

		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);

		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);

		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);

		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setForeground(Color.BLACK);
		btnexit.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);

		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);

		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);

		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnbuscar.setForeground(Color.WHITE);
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);

		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);

		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);

		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);

		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}
	
	public void configurarAccionesDelFormulario() {
		panel.addChangeListener(new ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				JTabbedPane seleccion = (JTabbedPane) evt.getSource();
				if (seleccion.getSelectedIndex() == 0) {
					reservas = true;
					huesped = false;
					limpiarTablaReservacion();
					mostrarConsultaReservacion(txtBuscar.getText());
				}
				if (seleccion.getSelectedIndex() == 1) {
					reservas = false;
					huesped = true;
					limpiarTablaHuesped();
					mostrarConsultaHuesped(txtBuscar.getText());
				}
			}
		});
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}
		});
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) { // Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) { // Al usuario quitar el mouse por el botón este volverá al estado//
													// original
				btnexit.setBackground(Color.white);
				labelExit.setForeground(Color.black);
			}
		});
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (reservas == true) {
					modeloReservas.setRowCount(0);
					mostrarConsultaReservacion(txtBuscar.getText());
				}
				if (huesped == true) {
					modeloHuesped.setRowCount(0);
					mostrarConsultaHuesped(txtBuscar.getText());
				}
			}
		});
		
	
		
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (reservas == true) {
					modificarReservaciones();
					limpiarTablaReservacion();
					mostrarConsultaReservacion(txtBuscar.getText());
				}
				if (huesped == true) {
					modificarHuespedes();
				}
			}
		});
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (reservas == true) {
					eliminarReservacion();
					limpiarTablaReservacion();
					mostrarConsultaReservacion(txtBuscar.getText());
				}
				if (huesped == true) {
					eliminarHuesped();
					limpiarTablaHuesped();
					mostrarConsultaHuesped(txtBuscar.getText());
				}
			}
		});
	}

	private void modificarReservaciones() {
		if (tieneFilaElegida()) {
			JOptionPane.showMessageDialog(null, "Por favor, elige una fila");
			return;
		}
				         
		Optional.ofNullable(modeloReservas.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
				.ifPresentOrElse(fila -> {
					try {
						Double valor;
						Date fechaEntrada;
						Date fechaSalida;
						
						Integer id = Integer.valueOf(modeloReservas.getValueAt(tbReservas.getSelectedRow(), 0).toString());
						fechaEntrada = new SimpleDateFormat("yyyy-MM-dd").parse(modeloReservas.getValueAt(tbReservas.getSelectedRow(), 1).toString());
						fechaSalida = new SimpleDateFormat("yyyy-MM-dd")
								.parse(modeloReservas.getValueAt(tbReservas.getSelectedRow(), 2).toString());
						
						Date fechaActual = new Date();
						if(fechaSalida.getTime() < fechaActual.getTime()) {
							JOptionPane.showMessageDialog(null, "No se puede modificar a una fecha anterior");
							return;
						}
						valor = Double.valueOf(modeloReservas.getValueAt(tbReservas.getSelectedRow(), 3).toString());
						String formaPago = (String) modeloReservas.getValueAt(tbReservas.getSelectedRow(), 4);

						long diferenciaDias = fechaSalida.getTime() - fechaEntrada.getTime();
						TimeUnit time = TimeUnit.DAYS;
						Long dif = time.convert(diferenciaDias, TimeUnit.MILLISECONDS);
						Double valorCalculado = (double) ((dif + 1) * 15);

						if (Double.compare(valorCalculado, valor) == 0) {
							valor = Double
									.valueOf(modeloReservas.getValueAt(tbReservas.getSelectedRow(), 3).toString());
						} else if (Double.compare(valorCalculado, valor) < 0) {
							valor = valorCalculado;							
						} else if (Double.compare(valorCalculado, valor) > 0) {
							valor = valorCalculado;							
						}
						Reservacion reservacion = new Reservacion(id, fechaEntrada, fechaSalida, valor, formaPago);
						int filasModificadas;
						filasModificadas = reservacionController.modificar(reservacion);
						JOptionPane.showMessageDialog(null,
								String.format("%d item modificado con éxito!", filasModificadas));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}, () -> JOptionPane.showMessageDialog(null, "Por favor, elije una fila"));
	}
	private void modificarHuespedes() {
		if (tieneFilaElegida2()) {
			JOptionPane.showMessageDialog(null, "Por favor, elije una fila");
		}
		Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
				.ifPresentOrElse(fila -> {					
					try {
					Integer id = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
					String nombre = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1);
					String apellido = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2);
					Date fechaNacimiento;
					fechaNacimiento = new SimpleDateFormat("yyyy-MM-dd")
								.parse(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 3).toString());
					
					String nacionalidad = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 4);
					String telefono = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 5);
					Integer idreservas = Integer
							.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 6).toString());
					int filasModificadas;
					Huesped huesped = new Huesped(id, nombre, apellido, fechaNacimiento, nacionalidad, telefono,
							idreservas);
					filasModificadas = huespedController.modificar(huesped);
					JOptionPane.showMessageDialog(null,
							String.format("%d item modificado con éxito", filasModificadas));
					} catch (ParseException e) {						
						e.printStackTrace();
					}
				}, () -> JOptionPane.showMessageDialog(null, "Por favor, elije una fila"));
	}

	private void eliminarHuesped() {
		if (tieneFilaElegida2()) {
			JOptionPane.showMessageDialog(null, "Por favor, elija una fila");
		}
		Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
				.ifPresentOrElse(fila -> {
					Integer id = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
					int cantidadEliminada;
					cantidadEliminada = huespedController.eliminar(id);
					modeloHuesped.removeRow(tbHuespedes.getSelectedRow());
					JOptionPane.showMessageDialog(null, cantidadEliminada + " item eliminado con éxito!");
				}, () -> JOptionPane.showMessageDialog(null, "Por favor, elije una fila"));
	}

	private void eliminarReservacion() {
		if (tieneFilaElegida()) {
			JOptionPane.showMessageDialog(null, "Por favor, elija una fila");
		}
		Optional.ofNullable(modeloReservas.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
				.ifPresentOrElse(fila -> {
					Integer id = Integer.valueOf(modeloReservas.getValueAt(tbReservas.getSelectedRow(), 0).toString());
					int cantidadEliminada;
					cantidadEliminada = reservacionController.eliminar(id);
					modeloReservas.removeRow(tbReservas.getSelectedRow());
					JOptionPane.showMessageDialog(null, cantidadEliminada + " item eliminado con éxito!");
				}, () -> JOptionPane.showMessageDialog(null, "Por favor, elije una fila"));
	}

	private void mostrarConsultaReservacion(String criterio) {
		List<Reservacion> reservaciones = reservacionController.mostrar(criterio);
		reservaciones.forEach(res -> modeloReservas.addRow(
				new Object[] {
						res.getId(),
						res.getFechaInicio(),
						res.getFechaFinal(), 
						res.getValorReservacion(), 
						res.getFormaPago() }));		
	}
	private void mostrarConsultaHuesped(String criterio) {
		List<Huesped> huesped = huespedController.mostrar(criterio);
		huesped.forEach(hues -> modeloHuesped.addRow(
				new Object[] {
						hues.getId(), 
						hues.getNombre(), 
						hues.getApellido(),
						hues.getFechaNacimiento(),
						hues.getNacionalidad(), 
						hues.getTelefono(), 
						hues.getIdReservacion() }));
	}

	private boolean tieneFilaElegida() {
		return tbReservas.getSelectedRowCount() == 0 || tbReservas.getSelectedColumnCount() == 0;
	}

	private boolean tieneFilaElegida2() {
		return tbHuespedes.getSelectedRowCount() == 0 || tbHuespedes.getSelectedColumnCount() == 0;
	}

//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}
	private void limpiarTablaReservacion() {
		modeloReservas.getDataVector().clear();
	}
	private void limpiarTablaHuesped() {
		modeloHuesped.getDataVector().clear();
	}
}
