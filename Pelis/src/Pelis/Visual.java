package Pelis;

public class Visual {

	private String title;
	private String type;
	private String date;
	private String lenght;
	private String genre;
	private String synopsis;
	private String language;
	private String actors;
	private String director;

	

	public void setTitle(String title) {
		this.title = title;
	}

	

	public void setType(String type) {
		this.type = type;
	}

	
	public void setDate(String date) {
		this.date = date;
	}

	
	public void setLenght(String lenght) {
		this.lenght = lenght;
	}

	
	public void setGenre(String genre) {
		this.genre = genre;
	}

	
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	
	public void setLanguage(String language) {
		this.language = language;
	}

	
	public void setActors(String actors) {
		this.actors = actors;
	}

	
	public void setDirector(String director) {
		this.director = director;
	}

	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("Info:");
		sb.append("\n");
		sb.append("Title: ");
		sb.append(title);
		sb.append(" ("+type+").");
		sb.append("\n");
		sb.append("Release Date: ");
		sb.append(date);
		sb.append("\n");
		sb.append("Lenght: ");
		sb.append(lenght);
		sb.append("\n");
		sb.append("Genre: ");
		sb.append(genre);
		sb.append("\n");
		sb.append("Synopsis: ");
		sb.append(synopsis);
		sb.append("\n");
		sb.append("Language: ");
		sb.append(language);
		sb.append("\n");
		sb.append("Actors: ");
		sb.append(actors);
		sb.append("\n");
		sb.append("Director: ");
		sb.append(director);
		sb.append("\n");

		return sb.toString();
	}


	
	
	
}
