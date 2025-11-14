package playlist;

public class Song {
	
	private String title;
	private String artist;
	private int duration;
	private String genre;
	private int playCount = 0;
	
	// Getters
	public String getTitle() { return title; }
	public String getArtist() { return artist; }
	public int getDuration() { return duration; }
	public String getGenre() { return genre; }
	public int getPlayCount() { return playCount; }
	
	public void incrementPlayCount() { playCount++; }
	
	// Constructor
	public Song(String title, String artist, int duration, String genre) {
		this.title = title;
		this.artist = artist;
		this.duration = duration;
		this.genre = genre;
	}
	
	@Override
	public String toString() {
		return String.format(
				"Title:  %s\n" +
				"Artist: %s\n" +
				"Genre:  %s\n" +
				"\n|---------------------| %d sec\n" +
				"      << |> || >>\n" + 
				"\nPlays:  %d\n", 
				title, artist, genre, duration, playCount);
	}
}
