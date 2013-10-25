package servidor;

//package com.lineadecodigo.java.io;

/**
 * @file EscribirEnFicheroProperties.java
 * @version 1.1
 * @author Linea de Codigo (http://lineadecodigo.com)
 * @date 05-febrero-2007
 * @url   http://lineadecodigo.com/2008/02/06/escribir-en-un-fichero-de-texto-con-java/
 * @description Escribir contenido sobre un fichero de texto  
 */

/*
 * Escrbir en el log:
 * ID MESA
 * FECHA
 * JUGADORES DE CADA RONDA (HASTA 5) 
 * APUESTAS (HASTA 4)
 * APUESTAS DEL SPLIT (HASTA 4)
 * CARTAS RECIBIDAS
 * AVISAR DE TERMINACION
 * 
 * 
 * 
 */



import java.io.*;
import java.util.*;

public class EscribirFicheroTexto {

	/**
	 * @param args
	 */
	/*public static void main(String[] args) {
		// Validamos si existe el fichero

		Date cal = new Date();
		int idMesa = 2;
		String Alias = "PEPE";
		int idCarta = 23;

		EscribirFicheroTexto eft = new EscribirFicheroTexto();
		eft.pedirCarta(idMesa, cal, Alias, idCarta);
		eft.apostar(idMesa, cal, Alias, idCarta);
	}*/
		public void pedirCarta(int idMESA, Date cal, String Alias, int idCarta)
		{
			String sFichero = "LOG-"+idMESA+"-"+cal.getDate()+"-"+(cal.getMonth()+1)+"-"+(cal.getYear()+1900)+".txt";
			File fichero = new File(sFichero);

				try{
				  BufferedWriter bw = 
					new BufferedWriter(new FileWriter(sFichero,true));
			
		   		  // Escribimos 10 filas
			 		bw.write("\nEl jugador "+Alias+" ha recibido la carta "+idCarta+"\n");
				  // Hay que cerrar el fichero
				  bw.close();
				} catch (IOException ioe){
					ioe.printStackTrace();
				}
		}

		public void apostar(int idMESA, Date cal, String Alias, int apuesta)
		{
			String sFichero = "LOG-" + idMESA + "-" + cal.getDate() + "-" + (cal.getMonth() + 1) + "-" + (cal.getYear() + 1900) + ".txt";
			File fichero = new File(sFichero);

			try
			{
				BufferedWriter bw =
				  new BufferedWriter(new FileWriter(sFichero, true));

				// Escribimos 10 filas
				bw.write("\nEl jugador " + Alias + " ha apostado " + apuesta + " lereles\n");
				// Hay que cerrar el fichero
				bw.close();
			}
			catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
		}

		public void ganar(int idMESA, Date cal, String Alias, int puntos)
		{
			String sFichero = "LOG-" + idMESA + "-" + cal.getDate() + "-" + (cal.getMonth() + 1) + "-" + (cal.getYear() + 1900) + ".txt";
			File fichero = new File(sFichero);

			try
			{
				BufferedWriter bw =
				  new BufferedWriter(new FileWriter(sFichero, true));

				// Escribimos 10 filas
				bw.write("\nEl jugador " + Alias + " ha ganado la ronda con " + puntos + " puntos\n");
				// Hay que cerrar el fichero
				bw.close();
			}
			catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
		}

		public void blackJack(int idMESA, Date cal, String Alias)
		{
			String sFichero = "LOG-" + idMESA + "-" + cal.getDate() + "-" + (cal.getMonth() + 1) + "-" + (cal.getYear() + 1900) + ".txt";
			File fichero = new File(sFichero);

			try
			{
				BufferedWriter bw =
				  new BufferedWriter(new FileWriter(sFichero, true));

				// Escribimos 10 filas
				bw.write("\nEl jugador " + Alias + " ha conseguido un blackjack!!!!! \n");
				// Hay que cerrar el fichero
				bw.close();
			}
			catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
		}

		public void juegaPartida(int idMESA, Date cal, String Alias)
		{
			String sFichero = "LOG-" + idMESA + "-" + cal.getDate() + "-" + (cal.getMonth() + 1) + "-" + (cal.getYear() + 1900) + ".txt";
			File fichero = new File(sFichero);

			try
			{
				BufferedWriter bw =
				  new BufferedWriter(new FileWriter(sFichero, true));

				// Escribimos 10 filas
				bw.write("\nEl jugador " + Alias + " participa en la ronda\n");
				// Hay que cerrar el fichero
				bw.close();
			}
			catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
		}

		public void haceSplit(int idMESA, Date cal, String Alias, int idCarta)
		{
			String sFichero = "LOG-" + idMESA + "-" + cal.getDate() + "-" + (cal.getMonth() + 1) + "-" + (cal.getYear() + 1900) + ".txt";
			File fichero = new File(sFichero);

			try
			{
				BufferedWriter bw =
				  new BufferedWriter(new FileWriter(sFichero, true));

				// Escribimos 10 filas
				bw.write("\nEl jugador " + Alias + " hace split gracias a "+idCarta+" \n");
				// Hay que cerrar el fichero
				bw.close();
			}
			catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
		}

		public void apostarSplit(int idMESA, Date cal, String Alias, int apuesta)
		{
			String sFichero = "LOG-" + idMESA + "-" + cal.getDate() + "-" + (cal.getMonth() + 1) + "-" + (cal.getYear() + 1900) + ".txt";
			File fichero = new File(sFichero);

			try
			{
				BufferedWriter bw =
				  new BufferedWriter(new FileWriter(sFichero, true));

				// Escribimos 10 filas
				bw.write("\nEl jugador " + Alias + " ha apostado " + apuesta + " lereles en su split\n");
				// Hay que cerrar el fichero
				bw.close();
			}
			catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
		}

		public void haceDoble(int idMESA, Date cal, String Alias, int idCarta)
		{
			String sFichero = "LOG-" + idMESA + "-" + cal.getDate() + "-" + (cal.getMonth() + 1) + "-" + (cal.getYear() + 1900) + ".txt";
			File fichero = new File(sFichero);

			try
			{
				BufferedWriter bw =
				  new BufferedWriter(new FileWriter(sFichero, true));

				// Escribimos 10 filas
				bw.write("\nEl jugador " + Alias + " hace doble y recibe la carta "+idCarta+"\n");
				// Hay que cerrar el fichero
				bw.close();
			}
			catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
		}

		public void pideSeguro(int idMESA, Date cal, String Alias)
		{
			String sFichero = "LOG-" + idMESA + "-" + cal.getDate() + "-" + (cal.getMonth() + 1) + "-" + (cal.getYear() + 1900) + ".txt";
			File fichero = new File(sFichero);

			try
			{
				BufferedWriter bw =
				  new BufferedWriter(new FileWriter(sFichero, true));

				// Escribimos 10 filas
				bw.write("\nEl jugador " + Alias + " pide seguro \n");
				// Hay que cerrar el fichero
				bw.close();
			}
			catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
		}

		public void turno(int idMESA, Date cal, int turno)
		{
			String sFichero = "LOG-" + idMESA + "-" + cal.getDate() + "-" + (cal.getMonth() + 1) + "-" + (cal.getYear() + 1900) + ".txt";
			File fichero = new File(sFichero);

			try
			{
				BufferedWriter bw =
				  new BufferedWriter(new FileWriter(sFichero, true));

				// Escribimos 10 filas
				bw.write("\nTurno para el jugador "+turno+"\n");
				// Hay que cerrar el fichero
				bw.close();
			}
			catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
		}

		public void plantarse(int idMESA, Date cal, String Alias)
		{
			String sFichero = "LOG-" + idMESA + "-" + cal.getDate() + "-" + (cal.getMonth() + 1) + "-" + (cal.getYear() + 1900) + ".txt";
			File fichero = new File(sFichero);

			try
			{
				BufferedWriter bw =
				  new BufferedWriter(new FileWriter(sFichero, true));

				// Escribimos 10 filas
				bw.write("\nEl jugador " + Alias + " se planta \n");
				// Hay que cerrar el fichero
				bw.close();
			}
			catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
		}

		public void plantarseSplit(int idMESA, Date cal, String Alias)
		{
			String sFichero = "LOG-" + idMESA + "-" + cal.getDate() + "-" + (cal.getMonth() + 1) + "-" + (cal.getYear() + 1900) + ".txt";
			File fichero = new File(sFichero);

			try
			{
				BufferedWriter bw =
				  new BufferedWriter(new FileWriter(sFichero, true));

				// Escribimos 10 filas
				bw.write("\nEl jugador " + Alias + " se planta en el split \n");
				// Hay que cerrar el fichero
				bw.close();
			}
			catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
		}

		public void pasarseSplit(int idMESA, Date cal, String Alias)
		{
			String sFichero = "LOG-" + idMESA + "-" + cal.getDate() + "-" + (cal.getMonth() + 1) + "-" + (cal.getYear() + 1900) + ".txt";
			File fichero = new File(sFichero);

			try
			{
				BufferedWriter bw =
				  new BufferedWriter(new FileWriter(sFichero, true));

				// Escribimos 10 filas
				bw.write("\nEl jugador " + Alias + " se pasa en el split \n");
				// Hay que cerrar el fichero
				bw.close();
			}
			catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
		}


}
