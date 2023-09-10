package com.latam.alura.hotel.main;

import javax.swing.JFrame;
import com.latam.alura.hotel.view.MenuPrincipal;

public class Main {
	public static void main(String[] args) {
		
		CrearBaseDatos.crearBase();
		
		MenuPrincipal menuPrincipal = new MenuPrincipal();
		menuPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuPrincipal.setVisible(true);
		
	}

}
