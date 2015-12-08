package Pelis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class PARSEXML {

	Visual visual = new Visual();
	private Scanner sc = new Scanner(System.in);
	private String str;
	private String id;

	/**
	 * 
	 * @param doc
	 * @return
	 */
	// Gets the online data to see it.
	public Visual getDataToSee(Document doc) {
		try {
			NodeList movieList = doc.getElementsByTagName("movie");
			Node p = movieList.item(0);

			if (p.getNodeType() == Node.ELEMENT_NODE) {
				Element movie = (Element) p;

				visual.setTitle(movie.getAttribute("title"));
				visual.setType(movie.getAttribute("type"));
				visual.setDate(movie.getAttribute("year"));
				visual.setLenght(movie.getAttribute("runtime"));
				visual.setGenre(movie.getAttribute("genre"));
				visual.setSynopsis(movie.getAttribute("plot"));
				visual.setLanguage(movie.getAttribute("language"));
				visual.setDirector(movie.getAttribute("director"));
				visual.setActors(movie.getAttribute("actors"));
			}

		} catch (NullPointerException e) {
			System.out.println("Error 404 Not Found.");
		}

		return visual;
	}

	/**
	 * 
	 * @param doc
	 * @param type
	 */
	// Gets the online data to write it.
	public void getDataToWrite(Document doc, String type, String name) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		String loc = null;
		try {
			// 2 Documents: doc to get the data online, and docu where we will
			// write the data.
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document docu = builder.newDocument();
			Element root = docu.createElement(name.toLowerCase());
			docu.appendChild(root);

			NodeList movieList = doc.getElementsByTagName("movie");
			Node p = movieList.item(0);

			if (p.getNodeType() == Node.ELEMENT_NODE) {
				Element movie = (Element) p;
				root.setAttribute("title", movie.getAttribute("title"));
				root.setAttribute("type", movie.getAttribute("type"));
				root.setAttribute("year", movie.getAttribute("year"));
				root.setAttribute("runtime", movie.getAttribute("runtime"));
				root.setAttribute("genre", movie.getAttribute("genre"));
				root.setAttribute("plot", movie.getAttribute("plot"));
				root.setAttribute("language", movie.getAttribute("language"));
				root.setAttribute("actors", movie.getAttribute("actors"));
				root.setAttribute("director", movie.getAttribute("director"));
			}

			DOMSource source = new DOMSource(docu);

			if (type.equals("series")) {
				loc = "resources/series.xml";
			} else if (type.equals("movies")) {
				loc = "resources/movies.xml";
			}

			Result result = new StreamResult(loc);

			TransformerFactory transf = TransformerFactory.newInstance();
			Transformer transformer = transf.newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, result);

			System.out.println("Archivo escrito.");

		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param type
	 */
	// Reads the written data.

	public Visual readWrittenData(String type, String name) throws IOException {
		String id;
		Document doc = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();

			if (type.equals("series")) {
				doc = builder.parse("resources/series.xml");
			} else if (type.equals("movies")) {
				doc = builder.parse("resources/movies.xml");
			}

			NodeList personList = doc.getElementsByTagName(name);
			for (int i = 0; i < personList.getLength(); i++) {
				Node p = personList.item(i);
				if (p.getNodeType() == Node.ELEMENT_NODE) {
					Element movie = (Element) p;

					visual.setTitle(movie.getAttribute("title"));
					visual.setType(movie.getAttribute("type"));
					visual.setDate(movie.getAttribute("year"));
					visual.setLenght(movie.getAttribute("runtime"));
					visual.setGenre(movie.getAttribute("genre"));
					visual.setSynopsis(movie.getAttribute("plot"));
					visual.setLanguage(movie.getAttribute("language"));
					visual.setDirector(movie.getAttribute("director"));
					visual.setActors(movie.getAttribute("actors"));

					System.out.println("Archivo leido.");
				}

			}

		} catch (ParserConfigurationException e) {
			System.out.println("Error DocumentBuilder.");
		} catch (SAXException e) {
			System.out.println("Error S.A.X.");
		}

		return visual;
	}

	public void submenu(){
		System.out.println("-------------------------");
		System.out.println("Going back to menu? Y/N :");
		str = sc.next();
		if (str.equals("Y") || str.equals("y"))
			menu();
		else if (str.equals("N") || str.equals("n"))
			System.exit(0);
	}
	
	
	
	
	public void menu() {
		PARSEXML read = new PARSEXML();
		RESTAPI api = new RESTAPI();

		System.out.println("Insert the option number to execute it:");
		System.out.println("-------------------------");
		System.out.println("1 -Get data online and read it:");
		System.out.println("2 -Get online data and write it into the local database:");
		System.out.println("3 -Look for an specific record in the local database:");
		str = sc.next();
		switch (str) {

		case "1": {
			Visual visual;

			visual = read.getDataToSee(api.transformXML());

			System.out.println(visual.toString());
			System.out.println("-------------------------");
			System.out.println("Want to save it? Y/N :");
			str = sc.next();
			if (str.equals("Y") || str.equals("y")) {
				menu();
			} else if (str.equals("N") || str.equals("n")) {
				submenu();
			}

			submenu();
		}

		case "2": {

			read.getDataToSee(api.transformXML());

			System.out.println("Received data: Now choose in what database you want it, and the name of the register:");
			read.getDataToWrite(api.getDocu(), api.enterType(), api.enterName());

			submenu();
		}

		case "3":

			Visual visual = null;
			try {
				visual = read.readWrittenData(api.lookForType(), api.lookForName());

			} catch (IOException e) {
				System.out.println("Error. The file that you were trying to read doesn´t exist.");
				submenu();
			}

			System.out.println(visual.toString());

			submenu();
		}

	}

	public static void main(String[] args) {

		PARSEXML read = new PARSEXML();

		read.menu();

	}

}
