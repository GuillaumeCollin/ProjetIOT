package lecteur;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Connection extends Thread  {
	public String liste;
	    
		  
	public Connection() {
		// TODO Auto-generated constructor stub
	}


	public void  run(){
		/*retourne le site web sous forme de string*/
		try {
			URL url = new URL("https://dweet.io/get/latest/dweet/for/projetIOT");
		
		
		// 1. This is the point where the connection is opened.
		HttpURLConnection connection;
		
			connection = (HttpURLConnection) url.openConnection();
		 
		 // set connection output to true
		connection.setDoOutput(true);
		//Nous voulons récupérer des données du serveur 
		connection.setRequestMethod("GET");
		//Ouverture d'un pont afin de lire les données
		InputStream reader = connection.getInputStream();
		int data=reader.read();
		reader.read();
		this.liste=null;
		while(data != -1){
		    data = reader.read();
		    char c=(char) data;
		    this.liste= this.liste + c;
		    System.out.print(c);
		}
	    System.out.println("");

		reader.close(); 
		connection.disconnect();

		}
		catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String GetListe(){
		return this.liste;
	}
}

