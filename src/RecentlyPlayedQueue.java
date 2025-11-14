package playlist;

public class RecentlyPlayedQueue {
	
	// Imitates a linked list
	private class Node {
		public Song data;
		public Node next;
		public Node(Song val) { data = val; }
	}
	private Node head;
	private int counter = 0;
	private static RecentlyPlayedQueue instance;
	
	public RecentlyPlayedQueue() { head = null; }
	
	// Singleton
	public static RecentlyPlayedQueue getInstance() {
		if (instance == null) instance = new RecentlyPlayedQueue();
		return instance;
	}
	
	private void remove(Song song) {
		if (head == null) return;
		
		if (head.data.equals(song)) {
			head = head.next;
			counter--;
			return;
		}
		
		Node previous = head;
		Node current = head.next;
		
		while (current != null) {
			if (current.data.equals(song)) {
				previous.next = current.next;
				counter--;
				return;
			}
			previous = current;
			current = current.next;
		}
	}
	
	public void addRecentlyPlayed(Song song) {
		// Avoids duplication
		remove(song);
		
		if (head == null) {
			head = new Node(song);
			counter++;
			return;
		}
		
		Node current = head;
		
		while (current.next != null) current = current.next;
		
		current.next = new Node(song);
		counter++;
		
		if (counter > 10) remove(head.data);
	}
	
	public void printRecentlyPlayed() {
		// Determines if the playlist is empty, before printing it out sequentially
		Node current = head;
		if (current == null) {
			System.out.println("\nERR: Recently played queue is currently empty.");
			return;
		}
				
		System.out.println("\n======= RECENTLY PLAYED =======\n");
		while (current != null) {
			System.out.println(current.data + "\n===============================\n");
			current = current.next;
		}
		System.out.println("    ^^ MOST RECENT TRACK ^^    ");
	}
	
	// UnitTesting purposes
	public int getCount() { return counter; }
}
