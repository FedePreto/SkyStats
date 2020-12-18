package com.example.demo.services;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class CercaMeteo {

	public static String getMeteo(String url) {

		String meteo_citta = "";

		try {

			URLConnection openConnection = (URLConnection) new URL(url).openConnection();
			Scanner in = new Scanner(new BufferedReader(new InputStreamReader(openConnection.getInputStream())));
			meteo_citta = in.nextLine();
			in.close();
		} catch (IOException e) {
			System.out.println("ERRORE. OPERAZIONE I/O.");
			System.out.println(e);
		} catch (Exception e) {
			System.out.println("ERRORE GENERICO.");
			System.out.println(e);
		}		
		return meteo_citta;

	}

}
