package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import view.Libreria;

public class GestionDatos {
	public GestionDatos() {

	}

	//TODO: Implementa una función para abrir ficheros

	//TODO: Implementa una función para cerrar ficheros

	public boolean compararContenido (String fichero1, String fichero2) throws IOException{
		//TODO: Implementa la función
		FileReader fr1=new FileReader(fichero1);
		BufferedReader br1=new BufferedReader(fr1);
		FileReader fr2=new FileReader(fichero2);
		BufferedReader br2=new BufferedReader(fr2);

		String Cadena1 = br1.readLine();
		String Cadena2=br2.readLine();
		boolean temp = true;

		while ((Cadena1!=null) || (Cadena2!=null) && temp) {

			if (Cadena1 == null || Cadena2== null || !Cadena1.equals(Cadena2))
				temp = false;

			Cadena1 = br1.readLine();
			Cadena2 = br2.readLine();
		} 
		fr1.close();
		fr2.close();
		br1.close();
		br2.close();


		return temp;
	}

	public void buscarPalabra (String fichero1, String palabra, JTextArea area) throws IOException{
			FileReader fr = new FileReader(fichero1);
			BufferedReader bf = new BufferedReader(fr);
			String palabraBuscar = palabra;
			String sTexto;
			int aparece = 0;
			while ((sTexto = bf.readLine())!=null)
			{
				if (sTexto.equals(palabraBuscar))
				{
					aparece++;
				}
			}
			if (aparece>0)
			{
				area.setText("La palabra: "+palabra+", sale: "+aparece+" veces");
			}
			else
			{
				area.setText("No hay ninguna palabra: "+palabra);
			}

			fr.close();
			bf.close();
	}
	public boolean enviar(Libreria lib) {
		String ID=lib.getTextID().getText();
		String Titulo=lib.getTextTitulo().getText();
		String Autor=lib.getTextAutor().getText();
		int Año_publi=Integer.parseInt(lib.getTextAño().getText().trim());
		String Editor=lib.getTextEditor().getText();
		int num_pg=Integer.parseInt(lib.getTextNumPg().getText().trim());

		Libro libro=new Libro(ID,Año_publi,num_pg,Titulo,Autor,Editor);

		boolean fin=true;
		ObjectOutputStream out=null;
		try {
			out=new ObjectOutputStream(new FileOutputStream(libro.getId()+".dat"));
			out.writeObject(libro);
			out.close();
		} catch (IOException e) {
			fin=false;
		}
		return fin;

	}
	public Libro recuperar_libro(String identificador) {
		Libro l=null;
		ObjectInputStream in=null;
		try {
			in=new ObjectInputStream(new FileInputStream(identificador+".dat"));
			l=(Libro) in.readObject();
			in.close();
		} catch (IOException e) {
			l=null;
		} catch (ClassNotFoundException e) {
			l=null;
		}
		return l;
	}
	public void recuperar_todos(){
		File file=new File("libros");
		ArrayList<Libro> libros =new ArrayList<Libro>();



		Iterator it=libros.iterator();
		while(it.hasNext()) {
			Libro l=(Libro) it.next();
			l.print();
		}

	}
}
