package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import model.*;
import view.*;

public class GestionEventos {

	private GestionDatos model;
	private LaunchView view;
	private Libreria lib;
	private ActionListener actionListener_comparar, actionListener_buscar,actionListener_LibrosInterfaz,actionListener_enviar;
	private ActionListener actionListener_recoger;
	private ActionListener actionListener_recogerTodos;

	public GestionEventos(GestionDatos model, LaunchView view, Libreria l) {
		this.model = model;
		this.view = view;
		this.lib = l;
	}

	public void contol() {
		actionListener_comparar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				call_compararContenido();
			}
		};
		view.getComparar().addActionListener(actionListener_comparar);

		actionListener_buscar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				try {
					call_buscarPalabra();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		view.getBuscar().addActionListener(actionListener_buscar);
		actionListener_LibrosInterfaz=new ActionListener() {

			public void actionPerformed(ActionEvent actionEvent) {
				call_Libreria();
			}
		};
		view.getBtnLibros().addActionListener(actionListener_LibrosInterfaz);
		actionListener_enviar=new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				call_Enviar();
			}

		};
		lib.getBtnEnviar().addActionListener(actionListener_enviar);

		actionListener_recoger=new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				call_Recoger();
			}

		};
		lib.getBtnIDR().addActionListener(actionListener_recoger);
		actionListener_recogerTodos=new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				call_RecogerTodos();
			}

		};
		lib.getBtnIDR().addActionListener(actionListener_recogerTodos);


	}

	private void call_compararContenido() {
		try {
			if (model.compararContenido(view.getFichero1().getText(), view.getFichero2().getText())) {
				view.getTextArea().setText("Los archivos son iguales");
			}else {
				view.getTextArea().setText("Los archivos son diferentes");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			view.showError("No existe el fichero");
		}
	}

	private void call_buscarPalabra() throws IOException {
		try 
		{
			model.buscarPalabra(view.getFichero1().getText(), view.getPalabra().getText(), view.getTextArea());
		} catch (IOException e){
			view.showError("No existe el fichero");
		}
		
		
		
		


	}
	private void call_Libreria() {
		lib.setVisible(true);
	}
	private void call_Enviar() {
		if(model.enviar(lib)) {
			lib.getTextResult().setText("Libro guardado correctamente");
		}else {
			lib.getTextResult().setText("Error al guardar");
		} 
	}
	private void call_Recoger() {
		if(model.recuperar_libro(lib.getTextIDR().getText())!=null) {
			Libro l=model.recuperar_libro(lib.getTextIDR().getText());
			l.print();
		}
	}
	private void call_RecogerTodos() {


	}

}
