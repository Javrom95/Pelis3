package Pelis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

import org.w3c.dom.Document;

public class RESTAPI {

	private String finalURL, Name, Type;
	private URLConnection conn;
	private Document doc = null;
	private Document docu = null;
	private Scanner sc = new Scanner(System.in);
	private PARSEXML parse = new PARSEXML();

	public String Name() {
		System.out.println("Write the name of the title:");
		Name = sc.nextLine();
		return Name;
	}

	public String Type() {
		System.out.println("Is series or movie?");
		Name = sc.nextLine();
		return Name;
	}

	public String enterName() {
		System.out.println("Write the name of the new register:");
		Name = sc.nextLine();
		return Name;
	}

	public String enterType() {
		System.out.println("In what database do you want to save it, series or movies?:");
		Name = sc.nextLine();
		return Name;
	}

	public String lookForName() {
		System.out.println("Write the name of the register:");
		Name = sc.nextLine();
		return Name;
	}

	public String lookForType() {
		System.out.println("In what database do you want to search, series or movies?:");
		Name = sc.nextLine();
		return Name;
	}

	// Gets the name of the Movie or TV program and adds it to the final U.R.L.
	public String formURL(String name, String type) {
		name = name.replace(' ', '+');
		finalURL = "http://www.omdbapi.com/?t=" + name + "&y=&plot=full&type=" + type + "&r=xml";
		return finalURL;
	}

	// Gets the connection to the A.P.I. with the U.R.L. previously formed.
	private InputStream getXMLInputStream(String urlString) throws IOException {
		URL url = null;
		InputStream stream = null;

		try {
			url = new URL(urlString);

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.connect();

			stream = connection.getInputStream();

		} catch (MalformedURLException e) {

		}
		return stream;
	}

	public Document transformXML() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = (Document) builder.parse(getXMLInputStream(formURL(Name(), Type())));
		} catch (IOException e) {
			System.out.println("Couldn´t find what you were looking for:");
			parse.submenu();

		} catch (Exception e) {
			System.out.println("Error in transformX.M.L.");
			e.printStackTrace();
		}
		docu = doc;
		return doc;
	}

	public Document getDocu() {
		return docu;
	}

}
