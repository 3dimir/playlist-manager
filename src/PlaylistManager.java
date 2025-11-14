package playlist;

import java.util.Scanner;

public class PlaylistManager {

	private boolean isArrayList = true;
	private Scanner input = new Scanner(System.in);
	private PlaylistArrayList playlistArrayList = new PlaylistArrayList();
	private PlaylistLinkedList playlistLinkedList = new PlaylistLinkedList();
	
	// General use for unrestricted string inputs, such as title, artist, and genre
	private String getString(Scanner input, String prompt) {
		while (true) {
			System.out.print(prompt);
			String userInput = input.nextLine();
			
			if (userInput.isEmpty()) { // Handles empty strings
				System.out.println("\nERR: Please enter a valid word");
				continue;
			}
			if (!userInput.equalsIgnoreCase("exit")) return userInput;
			else {
				while (true) { // Edge case where 'exit' may be a desired string
					System.out.println("\n'EXIT' input detected");
					System.out.println("Is 'EXIT' the title/artist/genre?\n");
					System.out.print("1) Yes\n2) No\n>");
					String choice = input.nextLine().trim();
					
					int selection = 0;
					
					if (choice.matches("[12]")) selection = Integer.parseInt(choice);
					if (selection == 2) return null; 
					if (selection == 1) return userInput;
					else System.out.println("\nERR: Please enter 1 or 2");
				}
			}
		}
	}
	
	// General use for all integer inputs, such as duration or index
	private int getInteger(Scanner input, String prompt) {
		while (true) {
			System.out.print(prompt);
			String userInput = input.nextLine().trim();
			
			if (userInput.equalsIgnoreCase("exit")) return -1;
			if (userInput.matches("\\d+")) return Integer.parseInt(userInput);
			
			System.out.println("\nERR: Enter a valid integer");
		}
	}
	
	private Song getSong() {
		String title = getString(input, "\nEnter song's title\n>");
		if (title == null) return null; // Exit case
		
		String artist = getString(input, "\nEnter song's artist\n>");
		if (artist == null) return null;
		
		String genre = getString(input, "\nEnter song's genre\n>");
		if (genre == null) return null;
		
		int duration = getInteger(input, "\nEnter song's duration in seconds\n>");
		if (duration == -1) return null;
		
		Song song = new Song(title, artist, duration, genre);
		return song;
	}
	
	// Allows a pop up to hang around before returning to menu, resulting in a better viewing experience
	private void buffer() {
		System.out.println("\nPress 'ENTER' to continue");
		@SuppressWarnings("unused")
		String proceed = input.nextLine();
		return;
	}

	// Universal empty checker
	private boolean emptyCheck() {
		if (!isArrayList && playlistLinkedList.getSong().isEmpty()) {
			System.out.println("\nERR: Playlist is currently empty.");
			buffer();
			return true;
		}
		if (isArrayList && playlistArrayList.getSong().isEmpty()) {
			System.out.println("\nERR: Playlist is currently empty.");
			buffer();
			return true;
		}
		return false;
	}
	
	// Universal size checker
	private boolean validSizeCheck() {
		if (!isArrayList && playlistLinkedList.getSong().size() < 3) {
			System.out.println("\nERR: Not enough songs in current playlist");
			System.out.println("Playlist must contain at least 3 songs");
			buffer();
			return false;
		}
		if (isArrayList && playlistArrayList.getSong().size() < 3) {
			System.out.println("\nERR: Not enough songs in current playlist");
			System.out.println("Playlist must contain at least 3 songs");
			buffer();
			return false;
		}
		return true;
	}

	// Switches between list types by changing a boolean value
	private void switchType() {
		if (isArrayList) {
			isArrayList = false;
			System.out.println("\n!!SWITCHED TO LINKED LIST!!");
		} else {
			isArrayList = true;
			System.out.println("\n!!SWITCHED TO ARRAY LIST!!");
		}
		buffer();
	}
	
	private void addSong() {
		Song song = getSong();
		if (song == null) return;
		
		// Determines whether playlist is LinkedList or ArrayList
		if (!isArrayList) playlistLinkedList.addSong(song);
		else playlistArrayList.addSong(song);
		buffer();
	}
	
	private void insertSong() {
		Song song = getSong();
		if (song == null) return;
		
		int index = getInteger(input, "\nEnter the index which you wish to insert the song\n>");
		if (index == -1) return;
		
		if (!isArrayList) playlistLinkedList.insertSong(index, song);
		else playlistArrayList.insertSong(index, song);
		buffer();
	}
	
	private void removeSong() {
		if (!emptyCheck()) return;
		
		int index = getInteger(input, "\nEnter the index of the song which you wish to remove\n>");
		if (index == -1) return;
		
		if (!isArrayList) playlistLinkedList.removeSong(index);
		else playlistArrayList.removeSong(index);
		buffer();
	}
	
	private void viewSong() {
		if (emptyCheck()) return;
		
		int index = getInteger(input, "\nEnter the index of the song which you wish to view\n>");
		if (index == -1) return;
		
		if (!isArrayList) playlistLinkedList.getSong(index);
		else playlistArrayList.getSong(index);
		buffer();
	}
	
	private void shufflePlaylist() {
		if (!validSizeCheck()) return;
		
		if (!isArrayList) playlistLinkedList.shuffle();
		else playlistArrayList.shuffle();
		buffer();
	}
	
	private void printPlaylist() {
		if (emptyCheck()) return;
		
		System.out.println("Printing...\nPrinting...");
		if (!isArrayList) playlistLinkedList.printPlaylist();
		else playlistArrayList.printPlaylist();
		buffer();
	}
	
	private void sortPlaylist() {
		if (!validSizeCheck()) return;
		
		while (true) {
			System.out.println("\nHow would you like to sort your playlist?\n");
			System.out.println("1) Title");
			System.out.println("2) Artist");
			System.out.println("3) Duration");
			System.out.print("4) Playcount\n>");
		
			String userInput = input.nextLine().trim();
		
			if (userInput.equalsIgnoreCase("exit")) return;
			if (!userInput.matches("[1-4]")) {
				System.out.println("\nERR: Please select a valid option");
				buffer();
			} else {
				int choice = Integer.parseInt(userInput);
				
				switch (choice) {
				case 1:
					if (!isArrayList) playlistLinkedList.sortByTitle();
					else playlistArrayList.sortByTitle();
					break;
				case 2:
					if (!isArrayList) playlistLinkedList.sortByArtist();
					else playlistArrayList.sortByArtist();
					break;
				case 3:
					if (!isArrayList) playlistLinkedList.sortByDuration();
					else playlistArrayList.sortByDuration();
					break;
				case 4:
					if (!isArrayList) playlistLinkedList.sortByPlayCount();
					else playlistArrayList.sortByPlayCount();
					break;
				default :
					System.out.println("\nERR: Please select a valid option");
				}
				buffer();
				return;
			}
		}
	}
	
	private void searchPlaylist() {
		while (true) {
			System.out.println("\nHow would you like to search through your playlist?\n");
			System.out.println("1) Title");
			System.out.println("2) Artist");
			System.out.print("3) Genre\n>");

			String userInput = input.nextLine().trim();

			if (userInput.equalsIgnoreCase("exit")) return;
			if (!userInput.matches("[1-3]")) {
				System.out.println("\nERR: Please select a valid option");
				buffer();
			} else {
				int choice = Integer.parseInt(userInput);
				String string;

				switch (choice) {
				case 1:
					string = getString(input, "\nEnter song's title\n>");
					if (string == null) return;

					if (!isArrayList) { // LinkedList
						var results = playlistLinkedList.searchByTitle(string);
						if (results.isEmpty()) { // None found
							System.out.println("\nNo songs found with title: " + string);
						} else {
							System.out.println("\n===== SEARCH RESULTS =====\n");
							System.out.println("Found at index/indices: " + results);
						}
					} else { // ArrayList
						var results = playlistArrayList.searchByTitle(string);
						if (results.isEmpty()) {
							System.out.println("\nNo songs found with title: " + string);
						} else {
							System.out.println("\n===== SEARCH RESULTS =====\n");
							System.out.println("Found at index/indices: " + results);
						}
					}
					break;
				case 2:
					string = getString(input, "\nEnter song's artist\n>");
					if (string == null) return;

					if (!isArrayList) {
						var results = playlistLinkedList.searchByArtist(string);
						if (results.isEmpty()) {
							System.out.println("\nNo songs found by artist: " + string);
						} else {
							System.out.println("\n===== SEARCH RESULTS =====\n");
							// Prints valid songs sequentially
							for (Song song : results) {
								System.out.println(song + "\n==============================");
							}
						}
					} else {
						var results = playlistArrayList.searchByArtist(string);
						if (results.isEmpty()) {
							System.out.println("\nNo songs found by artist: " + string);
						} else {
							System.out.println("\n===== SEARCH RESULTS =====\n");
							for (Song song : results) {
								System.out.println(song + "\n==============================");
							}
						}
					}
					break;
				case 3:
					string = getString(input, "\nEnter song's genre\n>");
					if (string == null) return;

					if (!isArrayList) {
						var results = playlistLinkedList.searchByGenre(string);
						if (results.isEmpty()) {
							System.out.println("\nNo songs found in genre: " + string);
						} else {
							System.out.println("\n===== SEARCH RESULTS =====\n");
							for (Song song : results) {
								System.out.println(song + "\n==============================");
							}
						}
					} else {
						var results = playlistArrayList.searchByGenre(string);
						if (results.isEmpty()) {
							System.out.println("\nNo songs found in genre: " + string);
						} else {
							System.out.println("\n===== SEARCH RESULTS =====\n");
							for (Song song : results) {
								System.out.println(song + "\n==============================");
							}
						}
					}
					break;
				default:
					System.out.println("\nERR: Please select a valid option");
				}
				buffer();
				return;
			}
		}
	}

	private void run() {
		System.out.println("\nPlaylist Manager");
		
		while (true) {
			System.out.println("\n========== MENU ==========\n");
			System.out.println("1) Add song");
			System.out.println("2) Insert song at a position");
			System.out.println("3) Remove song at a position");
			System.out.println("4) View songs");
			System.out.println("5) Shuffle playlist");
			System.out.println("6) Print playlist");
			System.out.println("7) Sort playlist");
			System.out.println("8) Search");
			System.out.println("9) View recently played songs");
			System.out.println("0) Switch playlist type");
			System.out.println("\nType 'EXIT' at anytime to return to menu");
			System.out.print("From the menu, enter 'EXIT' again to close program\n>");
			
			String userInput = input.nextLine().trim(); // trim() clears input  buffer
			
			if (userInput.equalsIgnoreCase("exit")) {
				input.close();
				System.out.println("\n====== QUITTING PROGRAM ======\n");
				return; // Terminates
			}
			if (!userInput.matches("[0-9]")) { // Regex comparisons for simplicity
				System.out.println("\nERR: Please select a valid option");
				buffer();
			} else {
				int choice = Integer.parseInt(userInput);
				
				switch(choice) {
				case 0:
					System.out.println("\n======= SWITCH TYPE =======");
					switchType();
					break;
				case 1:
					System.out.println("\n======= ADD SONG =======");
					addSong();
					break;
				case 2:
					System.out.println("\n======= INSERT SONG =======");
					insertSong();
					break;
				case 3:
					System.out.println("\n======= REMOVE SONG =======");
					removeSong();
					break;
				case 4:
					System.out.println("\n======= VIEW SONG =======");
					viewSong();
					break;
				case 5:
					System.out.println("\n======= SHUFFLE =======");
					shufflePlaylist();
					break;
				case 6:
					System.out.println("\n======= PRINT PLAYLIST =======");
					printPlaylist();
					break;
				case 7:
					System.out.println("\n======= SORT PLAYLIST =======");
					sortPlaylist();
					break;
				case 8:
					System.out.println("\n======= SEARCH PLAYLIST =======");
					searchPlaylist();
					break;
				case 9:
					RecentlyPlayedQueue.getInstance().printRecentlyPlayed();
					buffer();
					break;
				default:
					System.out.println("\nERR: Please select a valid option");
				}
			}
		}
	}
	
	public static void main(String[] args) {
		PlaylistManager playlistManager = new PlaylistManager();
		playlistManager.run();
	}
}
