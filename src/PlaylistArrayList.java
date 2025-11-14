package playlist;

import java.util.ArrayList;
import java.util.Random;

public class PlaylistArrayList {
	
	private ArrayList<Song> songs = new ArrayList<Song>();
	
	// General selectionSort for all types
	// Nice chance to use enums
	private enum SortMode { TITLE, ARTIST, DURATION, PLAYCOUNT }
	
	private void sortBy(SortMode mode) {
		for (int i = 0; i < songs.size(); i++) {
			int foundIndex = findTarget(i, mode);
			
			Song temp = songs.get(i);
			songs.set(i, songs.get(foundIndex));
			songs.set(foundIndex, temp);
		}
		System.out.println("\nSuccessfully Sorted!");
	}
	
	private int findTarget(int start, SortMode mode) {
		int target = start;
		
		for(int i = start + 1; i < songs.size(); i++) {
			boolean isTarget = false;
			
			switch(mode) {
			case TITLE: // Since Title and Artist are Strings, compareTo will return a + or - number, then compare it to 0 (< makes sure they're sorted alphabetically)
				isTarget = songs.get(i).getTitle().compareToIgnoreCase(songs.get(target).getTitle()) < 0;
				break;
			case ARTIST:
				isTarget = songs.get(i).getArtist().compareToIgnoreCase(songs.get(target).getArtist()) < 0;
				break;
			case DURATION: // Duration and PlayCount are just integers, so doing a normal > comparison works fine here (> makes sure they're sorted largest to smallest)
				isTarget = songs.get(i).getDuration() > songs.get(target).getDuration();
				break;
			case PLAYCOUNT:
				isTarget = songs.get(i).getPlayCount() > songs.get(target).getPlayCount();
				break;
			default:
				System.out.println("ERR: Please select a sorting option");
				break;
			}
			if (isTarget) target = i;
		}
		return target;
	}
	
	public void sortByTitle() { sortBy(SortMode.TITLE); }
	public void sortByArtist() { sortBy(SortMode.ARTIST); }
	public void sortByDuration() { sortBy(SortMode.DURATION); }
	public void sortByPlayCount() { sortBy(SortMode.PLAYCOUNT); }
	
	// Basic sequentialSearch methods, no quirks
	public ArrayList<Integer> searchByTitle(String title) {
		ArrayList<Integer> results = new ArrayList<>();
		
		for (int i = 0; i < songs.size(); i++) {
			if (songs.get(i).getTitle().equalsIgnoreCase(title)) results.add(i);
		}
		return results;
	}
	
	public ArrayList<Song> searchByArtist(String artist) {
		ArrayList<Song> results = new ArrayList<>();
		
		for (Song song : songs) {
			if (song.getArtist().equalsIgnoreCase(artist)) results.add(song);
		}
		return results;
	}
	
	public ArrayList<Song> searchByGenre(String genre) {
		ArrayList<Song> results = new ArrayList<>();
		
		for (Song song : songs) {
			if (song.getGenre().equalsIgnoreCase(genre)) results.add(song);
		}
		return results;
	}
	
	public void addSong(Song song) {
		// Appends the song into the playlist
		if(songs.contains(song)) {
			System.out.println("\nERR: Song already exists in playlist");
			return;
		}
		
		songs.add(song);
		System.out.println("\nSuccessfully added: " + song.getTitle() + "!");
	}
	
	public void insertSong(int index, Song song) {
		// Determines if the given index is in the bounds, before adding the song at given index
		// If index == size of ArrayList, append
		if(songs.contains(song)) {
			System.out.println("\nERR: Song already exists in playlist");
			return;
		}
		
		if (index >= 0 && index <= songs.size()) {
			songs.add(index, song);
			System.out.println("\nSuccessfully added: " + song.getTitle() + "!");
		}
		else System.out.println("\nERR: Invalid index");
	}
	
	public void removeSong(int index) {
		// Determines if the given index is in the bounds, before deleting the song at given index
		if (index >= 0 && index < songs.size()) {
			Song removed = songs.get(index);
			songs.remove(index);
			System.out.println("\nSuccessfully removed: " + removed.getTitle() + "!");
		}
		else System.out.println("\nERR: Invalid index");
	}
	
	public void getSong(int index) {
		// Determines if the given index is in the bounds, before returning the index of selected song
		if (index >= 0 && index < songs.size()) {
			songs.get(index).incrementPlayCount();
			
			System.out.println("\n===== SONG AT INDEX " + index + " =====\n");
			System.out.println(songs.get(index));
			System.out.println("===========================");
			
			// Adds the song to recently played queue
			RecentlyPlayedQueue.getInstance().addRecentlyPlayed(songs.get(index));
		}
		else System.out.println("\nERR: Invalid index");
	}
	
	public void shuffle() {		
		// Fisher-Yates shuffle method
		Random random = new Random();
		
		for (int i = songs.size() - 1; i > 0; i--) {
			int randomIndex = random.nextInt(i + 1);
			
			Song temp = songs.get(i);
			songs.set(i, songs.get(randomIndex));
			songs.set(randomIndex, temp);
		}
		System.out.println("\nPlaylist Shuffled!");
	}
	
	public void printPlaylist() {
		System.out.println("\n========== PLAYLIST ==========\n");
		// Sequential printing
		for (Song song : songs) {
			System.out.println(song + "\n==============================\n");
		}
	}
	
	// UnitTesting purposes
	public ArrayList<Song> getSong() { return songs; }
}
